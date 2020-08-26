package com.company;

import javax.swing.*;
import java.awt.*;

public class GameForm extends JFrame {

    private Game game;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel boardPanel;

    public GameForm(String title) throws HeadlessException {
        super(title);

        game = new Game();

        this.setSize(300, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    public void start() {
        Init();
        this.setVisible(true);
    }

    private void Init() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 0, 300, 50);
        buttonPanel.setBackground(Color.GRAY);

        boardPanel = new JPanel();
        boardPanel.setBounds(0, 50, 300, 300);
        boardPanel.setBackground(Color.LIGHT_GRAY);
        boardPanel.setLayout(new GridLayout(3,3));

        for (int i = 0; i < Game.FieldSize; i++) {
            for (int j = 0; j < Game.FieldSize; j++) {
                JButton btn = new JButton();
                btn.setName("btn_"+i+"_"+j);

                switch (game.getFieldStatus(i,j)) {
                    case FLOWER -> {
                        btn.setBackground(Color.YELLOW);
                        break;
                    }
                    case MINE -> {
                        btn.setBackground(Color.RED);
                        break;
                    }
                    case OPENED -> {
                        btn.setBackground(Color.GRAY);
                        break;
                    }
                    case CLOSED -> {
                        btn.setBackground(Color.WHITE);
                        break;
                    }
                }

                boardPanel.add(btn);
            }
        }

        mainPanel.add(buttonPanel);
        mainPanel.add(boardPanel);

        this.setContentPane(mainPanel);
    }
}
