import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.BorderLayout.NORTH;

public class TicTacToeGame extends JFrame
{
    JFrame gameFrame = new JFrame();
    JPanel mainPnl, titlePnl, buttonPnl, gamePnl;
    JButton quitBtn;
    JLabel titleLbl;

    boolean done;

    TicTacToeBoard TicTacToeBoard = new TicTacToeBoard();

    public TicTacToeGame()
    {
        mainPnl = new JPanel();

        createTitlePanel();
        createButtonPanel();
        createGamePanel();
        createCommandPanel();

        add(mainPnl);

        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createTitlePanel()
    {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Tic Tac Toe", JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(new Font("Comic Sans Ms", Font.BOLD, 30));
        mainPnl.add(titleLbl, NORTH);
    }

    private void createButtonPanel()
    {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(3,3));

        mainPnl.add(gamePnl, BorderLayout.CENTER);
    }

    private void createGamePanel()
    {
        for(int row = 0; row < 3; row++) for (int col = 0; col < 3; col++)
        {
            gamePnl.add(TicTacToeBoard.getBoard()[row][col]);
            TicTacToeBoard.getBoard()[row][col].addActionListener((ActionEvent ae) ->
            {
                Object source = ae.getSource();
                if (source instanceof JButton)
                {
                    JButton buttonClick = (JButton) source;

                    if (buttonClick.getText().equals(" "))
                    {
                        buttonClick.setText(TicTacToeBoard.getPlayer());
                        TicTacToeBoard.setMoves(TicTacToeBoard.getMoves() + 1);
                        if (TicTacToeBoard.getMoves() >= TicTacToeBoard.getMOVES_FOR_WIN())
                        {
                            if (isWin(TicTacToeBoard.getPlayer()))
                            {
                                int a = JOptionPane.showConfirmDialog(gameFrame, TicTacToeBoard.getPlayer() + " wins!  Wanna play again?");
                                if(a == JOptionPane.YES_OPTION)
                                {
                                    TicTacToeBoard.clearBoard();
                                }
                                else if (a == JOptionPane.NO_OPTION)
                                {
                                    System.exit(0);
                                }
                            }
                        }
                        if (TicTacToeBoard.getMoves() >= TicTacToeBoard.getMOVES_FOR_TIE())
                        {
                            if (TicTacToeBoard.isTie())
                            {
                                int a = JOptionPane.showConfirmDialog(gameFrame, "Tie game! Do you want to play again?");
                                if (a == JOptionPane.YES_OPTION)
                                {
                                    TicTacToeBoard.clearBoard();
                                }
                                else if (a == JOptionPane.NO_OPTION)
                                {
                                    System.exit(0);
                                }
                            }
                        }
                        if (TicTacToeBoard.getPlayer().equals("x") && !done)
                        {
                            TicTacToeBoard.setPlayer("o");
                        }
                        else
                        {
                            TicTacToeBoard.setPlayer("x");
                        }
                        done = false;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(gameFrame, "Invalid move.  Try again");
                    }
                }
            });
        }
    }
    private boolean isWin(String player)
    {
        if (TicTacToeBoard.isColWin(player) || TicTacToeBoard.isRowWin(player) || TicTacToeBoard.isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }
    public void createCommandPanel()
    {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 2));

        quitBtn = new JButton("Quit");
        quitBtn.setBackground(Color.RED);

        buttonPnl.add(quitBtn);
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        mainPnl.add(buttonPnl, BorderLayout.SOUTH);
    }
}

