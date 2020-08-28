package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameForm extends JFrame {

    private Game game;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel boardPanel;
    private JButton btnRestartGame;
    private JButton[][] boardButtons = new JButton[Game.FieldSize][Game.FieldSize];

    public GameForm(String title) throws HeadlessException {
        super(title);

        game = new Game();

        this.setSize(320, 390);
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

        btnRestartGame = new JButton("Restart");
        btnRestartGame.addActionListener(a -> {
            game.initGame();
            resetButtonState();
        });

        buttonPanel.add(btnRestartGame);

        boardPanel = new JPanel();
        boardPanel.setBounds(0, 50, 300, 300);
        boardPanel.setBackground(Color.LIGHT_GRAY);
        boardPanel.setLayout(null);

        var startXPosition = 0;
        var startYPosition = 0;

        for (int i = 0; i < Game.FieldSize; i++) {
            for (int j = 0; j < Game.FieldSize; j++) {
                JButton btn = new JButton();
                btn.setName("btn_"+i+"_"+j);

                btn.setBounds(startXPosition, startYPosition, 100, 100);

                startXPosition += 100;

                updateBtn(btn, game.getFieldStatus(i,j));

                btn.addActionListener(e -> {
                    var nameParts = btn.getName().split("_");

                    var row = Integer.parseInt(nameParts[1]);
                    var column = Integer.parseInt(nameParts[2]);

                    var newFieldStat = game.fieldClicked(row, column);

                    updateBtn(btn, newFieldStat);

                });

                boardPanel.add(btn);
                boardButtons[i][j] = btn;
            }

            startYPosition += 100;
            startXPosition = 0;
        }

        mainPanel.add(buttonPanel);
        mainPanel.add(boardPanel);

        this.setContentPane(mainPanel);
    }

    private void updateBtn(JButton btn, FieldStatus status) {
        switch (status) {
            case FLOWER -> {
                Icon icon = new ImageIcon("C:\\Temp\\flower.PNG");
                btn.setIcon(icon);
                btn.setBackground(Color.YELLOW);
                break;
            }
            case MINE -> {
                Icon icon = new ImageIcon("C:\\Temp\\bomb.PNG");
                btn.setIcon(icon);
                btn.setBackground(Color.RED);
                break;
            }
            case OPENED -> {
                btn.setBackground(Color.GRAY);
                btn.setIcon(null);
                break;
            }
            case CLOSED -> {
                btn.setBackground(Color.WHITE);
                btn.setIcon(null);
                break;
            }
        }
    }

    private void resetButtonState() {
        for (int i = 0; i < Game.FieldSize; i++) {
            for (int j = 0; j < Game.FieldSize; j++) {
                updateBtn(boardButtons[i][j], game.getFieldStatus(i, j));
            }
        }
    }
}
