package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    final int boardWidth = 600;
    final int boardHeight = 650; // 50px for text panel on top

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3]; // 3x3 array of buttons
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    Boolean gameOver = false;
    int turns = 0;

    void ticTacToe() {
        frame.setSize(boardWidth, boardHeight); // Sets size of window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes window when window is closed
        frame.setResizable(true); // Prevents window from being resized
        frame.setLocationRelativeTo(null); // Centers window on screen
        frame.setVisible(true); // Makes window visible
        frame.setLayout(new BorderLayout()); // Sets frame layout

        textLabel.setBackground(Color.DARK_GRAY); // Background colour
        textLabel.setForeground(Color.WHITE); // Text colour
        textLabel.setFont(new Font("Arial", Font.BOLD, 50)); // Text font
        textLabel.setHorizontalAlignment(JLabel.CENTER); // Centers text
        textLabel.setText("Tic Tac Toe"); // Sets text to "Tic Tac Toe"
        textLabel.setOpaque(true); // Makes text opaque

        textPanel.setLayout(new BorderLayout()); // Sets text panel layout
        textPanel.add(textLabel); // Adds text label to text panel
        frame.add(textPanel, BorderLayout.NORTH); // Adds text label to frame

        boardPanel.setLayout(new GridLayout(3, 3)); // Sets board panel layout
        boardPanel.setBackground(Color.DARK_GRAY); // Background colour
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Border colour
        frame.add(boardPanel); // Adds board panel to frame

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile); // Adds button to board panel

                tile.setBackground(Color.DARK_GRAY); // Background colour
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120)); // Text font
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver)
                            return;
                        JButton tile = (JButton) e.getSource(); // Gets clicked button

                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = (currentPlayer == playerX) ? playerO : playerX; // Switches player
                                textLabel.setText(currentPlayer + "'s turn"); // Sets text to current player's turn
                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        // Horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "")
                continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                    board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        // Vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "")
                continue;
            if (board[0][c].getText() == board[1][c].getText() &&
                    board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        // Diagonal
        if (board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        // Anti-diagonal
        if (board[0][2].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][0].getText() &&
                board[0][2].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][2 - i]);
            }
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setBackground(Color.GRAY);
        tile.setForeground(Color.GREEN);
        textLabel.setText(tile.getText() + " wins!");
    }

    void setTie(JButton tile) {
        tile.setBackground(Color.GRAY);
        tile.setForeground(Color.ORANGE);
        textLabel.setText("It's a tie!");
    }
}