import client.Clients;
import server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new Clients(serverWindow);
        new Clients(serverWindow);


    }
}