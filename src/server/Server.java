package server;

import client.Controller;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private boolean work;
    private ServerView serverView;
    private List<Controller> controllerList;
    private WriteReadServer wrServer;


    public Server(ServerView serverView, WriteReadServer wrServer) {
        this.serverView = serverView;
        this.wrServer = wrServer;
        controllerList = new ArrayList<>();
    }

    private void showWindow(String text) {
        serverView.sMessage(text + "/n");
    }


    public void start() {
        if (work) {
            showWindow("Сервер запущен");
        } else {
            work = true;
            showWindow("Сервер запущен");
        }
    }

    public void stop() {
        if (!work) {
            showWindow("Сервер остановлен");
        } else {
            work = false;
            for (Controller controller : controllerList) {
                disconnectUser(controller);
            }
            showWindow("Сервер остановлен");

        }

    }

    public void disconnectUser(Controller controller) {
        controllerList.remove(controller);
        if (controller != null) {
            controller.disconnectFromServer();
            showWindow(controller.getName() + " отключён");
        }
    }

    public boolean coonectUser(Controller controller) {
        if (!work) {
            return false;
        }
        controllerList.add(controller);
        showWindow(controller.getName() + " подключён");
        return true;
    }

    public void message(String text) {
        if (!work) {
            return;
        }
        showWindow(text);
        answerAll(text);
        seveInHistory(text);
    }

    private void answerAll(String text) {
        for (Controller controller : controllerList) {
            controller.answerFromServer(text);
        }
    }


    private void seveInHistory(String text) {
        wrServer.save(text);
    }
    public String getHistory(){
        return wrServer.load();
    }

}