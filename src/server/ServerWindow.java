package server;

import client.WriterRead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ServerWindow extends JFrame implements ServerView {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;


    JButton jButtonStart, jButtonStop;
    JTextArea log;

    private Server server;


    public ServerWindow() {
        setting();
        createPanel();
        setVisible(true);

    }

    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Общий чат");
        setLocationRelativeTo(null);
        server = new Server(this, new WriterRead());

    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        jButtonStart = new JButton("Start");
        jButtonStop = new JButton("Stop");

        jButtonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();

            }

        });
        jButtonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop();
            }
        });
        panel.add(jButtonStart);
        panel.add(jButtonStop);
        return panel;
    }



    @Override
    public void sMessage(String message) {
        log.append(message);

    }

    public Server getConnection() {
        return server;
    }
}
