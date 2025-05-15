package main;


import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import loginServlets.SignInServlet;
import loginServlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        startServer();
        exampleOfLesson();
    }

    private static void exampleOfLesson() {
        DBService dbService = new DBService();

        try {
            dbService.printConnectInfo();

            long userId = dbService.addUser("tully2");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private static void startServer() throws Exception {
        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(SignUpServlet.class, "/signup");
        handler.addServletWithMapping(SignInServlet.class, "/signin");

        server.start();
        System.out.println("Server started");

        server.join();
    }
}
