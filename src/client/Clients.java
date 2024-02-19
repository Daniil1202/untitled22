package client;

import server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Clients extends JFrame implements ClientView {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    JTextArea log;
    JTextField IPAddress, Port, fLogin, Message;
    JPasswordField passwordField;
    JButton jButtonLogin, jButtonSend;
    JPanel jPanel;
    private Controller controller;

    public Clients(ServerWindow server) {
        setting(server);
        createPanel();

        setVisible(true);
    }

    private void setting(ServerWindow server) {

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat clients");
        setLocation(server.getX(), server.getY());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        controller = new Controller(this, server.getConnection());

    }

    @Override
    public void sMessage(String message) {
        log.append(message);

    }

    public void disconnectFromServer() {
        hideHeaderPanel(true);
        controller.disconnectFromServer();
    }

    public void hideHeaderPanel(boolean visible) {
        jPanel.setVisible(visible);
    }

    public void login() {
        if (controller.connecttoServer(fLogin.getText())) {
            jPanel.setVisible(false);
        }
    }

    public void message() {
        controller.message(Message.getText());
        Message.setText("");
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);

    }

    private Component createHeaderPanel() {
        jPanel = new JPanel(new GridLayout(2, 3));
        IPAddress = new JTextField("12.0.1.1");
        Port = new JTextField("3422");
        fLogin = new JTextField("Petr Petrovich");
        passwordField = new JPasswordField("223311");
        jButtonLogin = new JButton("Login");
        jButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        jPanel.add(IPAddress);
        jPanel.add(Port);
        jPanel.add(new JPanel());
        jPanel.add(fLogin);
        jPanel.add(passwordField);
        jPanel.add(jButtonLogin);

        return jPanel;
    }

    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        Message = new JTextField();
        Message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    message();
                }
            }
        });
        jButtonSend = new JButton("send");
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(Message);
        panel.add(jButtonSend, BorderLayout.EAST);
        return panel;


    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSED) {
            disconnectFromServer();
        }
        super.processWindowEvent(e);
    }
}
