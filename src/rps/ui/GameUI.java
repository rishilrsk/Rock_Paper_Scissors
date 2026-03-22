package rps.ui;

import rps.logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The graphical user interface built with Java Swing.
 * Responsible for handling user interactions and displaying state.
 */
public class GameUI extends JFrame {
    private GameLogic gameLogic;
    
    private JLabel playerScoreLabel;
    private JLabel computerScoreLabel;
    
    private JLabel playerChoiceLabel;
    private JLabel computerChoiceLabel;
    private JLabel resultLabel;
    
    private Timer winTimer;
    private int animationStep = 0;
    
    public GameUI() {
        super("Rock Paper Scissors");
        gameLogic = new GameLogic();
        
        initializeUI();
    }
    
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 550);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 36)); // Sleek dark theme background
        
        // --- Header Section (Scores) ---
        JPanel scorePanel = new JPanel(new GridLayout(1, 2));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        scorePanel.setBackground(new Color(20, 20, 25)); // Darker header for contrast
        
        playerScoreLabel = new JLabel("Player: 0", SwingConstants.CENTER);
        playerScoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        playerScoreLabel.setForeground(new Color(42, 157, 143)); // Cyan/Green tint for player
        
        computerScoreLabel = new JLabel("Computer: 0", SwingConstants.CENTER);
        computerScoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        computerScoreLabel.setForeground(new Color(230, 57, 70)); // Red tint for computer
        
        scorePanel.add(playerScoreLabel);
        scorePanel.add(computerScoreLabel);
        
        add(scorePanel, BorderLayout.NORTH);
        
        // --- Center Section (Results Display) ---
        JPanel displayPanel = new JPanel(new GridLayout(3, 1));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        displayPanel.setOpaque(false);
        
        playerChoiceLabel = new JLabel("Your Choice: -", SwingConstants.CENTER);
        playerChoiceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        playerChoiceLabel.setForeground(Color.LIGHT_GRAY);
        
        computerChoiceLabel = new JLabel("Computer's Choice: -", SwingConstants.CENTER);
        computerChoiceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        computerChoiceLabel.setForeground(Color.LIGHT_GRAY);
        
        resultLabel = new JLabel("Make your move!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        resultLabel.setForeground(new Color(244, 162, 97)); // Default neutral orange
        
        displayPanel.add(playerChoiceLabel);
        displayPanel.add(computerChoiceLabel);
        displayPanel.add(resultLabel);
        
        add(displayPanel, BorderLayout.CENTER);
        
        // --- Footer Section (Control Buttons) ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.setOpaque(false);
        
        JButton rockBtn = createStyledButton("Rock", new Color(230, 57, 70));
        JButton paperBtn = createStyledButton("Paper", new Color(69, 123, 157));
        JButton scissorsBtn = createStyledButton("Scissors", new Color(29, 53, 87));
        
        JButton resetBtn = createStyledButton("Reset", new Color(108, 117, 125));
        JButton quitBtn = createStyledButton("Quit", new Color(153, 30, 30));
        
        // Attach event listeners
        rockBtn.addActionListener(e -> playRound(GameLogic.Move.ROCK));
        paperBtn.addActionListener(e -> playRound(GameLogic.Move.PAPER));
        scissorsBtn.addActionListener(e -> playRound(GameLogic.Move.SCISSORS));
        resetBtn.addActionListener(e -> resetGame());
        quitBtn.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(rockBtn);
        buttonPanel.add(paperBtn);
        buttonPanel.add(scissorsBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(quitBtn);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
    }
    
    /**
     * Helper method to create 3D styled buttons.
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Create 3D effect border
        Color shadowColor = bgColor.darker().darker();
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 5, 0, shadowColor),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Mouse listener to animate the button press (3D effect)
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Flatten the button (shift down)
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(30, 30, 36)), // Top padding to shift content down
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                // Restore 3D look
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 5, 0, shadowColor),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
        });
        
        return button;
    }
    
    /**
     * Executes one round of the game.
     */
    private void playRound(GameLogic.Move playerMove) {
        // Stop any running animations
        if (winTimer != null && winTimer.isRunning()) {
            winTimer.stop();
        }
        
        GameLogic.Move computerMove = gameLogic.getRandomMove();
        GameLogic.Result result = gameLogic.determineWinner(playerMove, computerMove);
        
        playerChoiceLabel.setText("Your Choice: " + playerMove);
        computerChoiceLabel.setText("Computer's Choice: " + computerMove);
        
        // Base Font size
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        
        // Update result styling based on outcome
        switch (result) {
            case WIN:
                resultLabel.setText("You Win!");
                resultLabel.setForeground(new Color(42, 157, 143)); // Green
                startWinAnimation();
                break;
            case LOSE:
                resultLabel.setText("You Lose!");
                resultLabel.setForeground(new Color(230, 57, 70)); // Red
                break;
            case DRAW:
                resultLabel.setText("It's a Draw!");
                resultLabel.setForeground(new Color(244, 162, 97)); // Orange
                break;
        }
        
        updateScores();
    }
    
    /**
     * Animates the resultLabel when the player wins.
     */
    private void startWinAnimation() {
        animationStep = 0;
        final Color baseColor = new Color(42, 157, 143); // Green tint
        final Color highlightColor = new Color(133, 227, 215); // Lighter bright highlight
        
        winTimer = new Timer(50, e -> {
            animationStep++;
            if (animationStep > 15) {
                winTimer.stop();
                resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
                resultLabel.setForeground(baseColor);
            } else {
                // Pulse the font size
                int size = 30 + (int)(Math.sin(animationStep * Math.PI / 15.0) * 12);
                resultLabel.setFont(new Font("Segoe UI", Font.BOLD, size));
                
                // Flash color
                if (animationStep % 2 == 0) {
                    resultLabel.setForeground(highlightColor);
                } else {
                    resultLabel.setForeground(baseColor);
                }
            }
        });
        winTimer.start();
    }
    
    /**
     * Resets the game state back to default.
     */
    private void resetGame() {
        if (winTimer != null && winTimer.isRunning()) {
            winTimer.stop();
        }
        
        gameLogic.resetScores();
        updateScores();
        
        playerChoiceLabel.setText("Your Choice: -");
        computerChoiceLabel.setText("Computer's Choice: -");
        resultLabel.setText("Make your move!");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        resultLabel.setForeground(new Color(244, 162, 97));
    }
    
    private void updateScores() {
        playerScoreLabel.setText("Player: " + gameLogic.getPlayerScore());
        computerScoreLabel.setText("Computer: " + gameLogic.getComputerScore());
    }
}
