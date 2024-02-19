package client;

import server.Server;

public class Controller {
    private boolean connected;
    private String name;
    private ClientView clientView;
    private Server server;

    public Controller(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }
    public boolean connecttoServer(String name){
        this.name = name;
        if (server.coonectUser(this)){
            showOnWindow("Вы подключились");
            connected = true;
            String log = server.getHistory();
            if ((log !=null)){
                showOnWindow(log);
            }
            return true;
        }
        else {
            showOnWindow("Вы не подключились");
            return false;
        }
    }
    private void showOnWindow(String text){
        clientView.sMessage(text +"\n");
    }
    public void disconnectFromServer(){
        if (connected){
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            showOnWindow("Вы отключены от сервера");
        }
    }
    public void answerFromServer (String text){
        showOnWindow(text);
    }
    public void message(String text){
        if(connected){
            if (!text.isEmpty()){
                server.message(name+": "+text);
            }
        }
    }
    public String getName(){
        return name;
    }
}
