package domaine;

import java.util.*;

public class Livre {
    private Map<Plat.Type, SortedSet<Plat>> plats;

    public Livre() {
        plats = new HashMap<>();
    }

    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {
        if (!plats.containsKey(plat.getType())) {
            plats.put(plat.getType(), new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    int difficulty = o1.getNiveauDeDifficulte().compareTo(o2.getNiveauDeDifficulte());
                    if (difficulty == 0) {
                        return o1.getNom().compareTo(o2.getNom());
                    }
                    return difficulty;
                }
            }));
        }
        if (plats.get(plat.getType()).contains(plat)) {
            return false;
        }
        plats.get(plat.getType()).add(plat);
        return false;
    }
    /**
     * Supprime un plat du livre, s'il est dedans.
     * Si le plat supprimé est le dernier de ce type de plat, il faut supprimer
     ce type de
     * plat de la Map.
     * @param plat le plat à supprimer
     * @return true si le plat a été supprimé, false sinon.
     */
    public boolean supprimerPlat (Plat plat) {
        if (!plats.get(plat.getType()).contains(plat)) {
            return false;
        }
        plats.get(plat.getType()).remove(plat);
        if (plats.get(plat.getType()).isEmpty()) {
            plats.remove(plat.getType());
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (domaine.Plat.Type type : plats.keySet()) {
            sb.append(type).append("\n");
            sb.append("=====\n");
            for (domaine.Plat plat : plats.get(type)) {
                sb.append(plat.getNom()).append("\n");
            }
        }
        return sb.toString();
    }
    /*
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Plat.Type> sortedTypes = new ArrayList<>(plats.keySet());
        sortedTypes.sort(Comparator.comparing(Enum::name)); // Sort types alphabetically

        for (Type type : sortedTypes) {
            sb.append(type).append("\n");
            sb.append("=====\n");
            for (Plat plat : plats.get(type)) {
                sb.append(plat.getNom()).append("\n");
            }
        }
        return sb.toString();
    }

     */
}
