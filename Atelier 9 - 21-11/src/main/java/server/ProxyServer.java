package server;

import domaine.Query;
import domaine.Query.QueryMethod;
import domaine.QueryFactory;

import java.util.Scanner;

public class ProxyServer {

    private final QueryFactory queryFactory;

    public ProxyServer(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public void startServer() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter the URL:");
                String url = scanner.nextLine();
                Query query = queryFactory.getQuery();
                query.setMethod(QueryMethod.GET);
                query.setUrl(url);
                QueryHandler queryHandler = new QueryHandler(query);
                queryHandler.sendQueryAndPrintResponse();
            }
        }
    }
}