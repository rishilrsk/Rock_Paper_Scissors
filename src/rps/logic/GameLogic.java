package rps.logic;

import java.util.Random;

/**
 * Handles all the core game logic:
 * - Generating computer moves
 * - Determining the winner
 * - Tracking scores
 */
public class GameLogic {
    public enum Move {
        ROCK, PAPER, SCISSORS
    }
    
    public enum Result {
        WIN, LOSE, DRAW
    }
    
    private int playerScore = 0;
    private int computerScore = 0;
    private final Random random = new Random();
    
    /**
     * Randomly select a move for the computer.
     */
    public Move getRandomMove() {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }
    
    /**
     * Compare the player and computer moves and return the result.
     * Also updates the scores appropriately.
     */
    public Result determineWinner(Move playerMove, Move computerMove) {
        if (playerMove == computerMove) {
            return Result.DRAW;
        }
        
        // Standard Rock-Paper-Scissors rules
        if ((playerMove == Move.ROCK && computerMove == Move.SCISSORS) ||
            (playerMove == Move.PAPER && computerMove == Move.ROCK) ||
            (playerMove == Move.SCISSORS && computerMove == Move.PAPER)) {
            playerScore++;
            return Result.WIN;
        }
        
        computerScore++;
        return Result.LOSE;
    }
    
    /**
     * Reset the scores to 0.
     */
    public void resetScores() {
        playerScore = 0;
        computerScore = 0;
    }
    
    public int getPlayerScore() {
        return playerScore;
    }
    
    public int getComputerScore() {
        return computerScore;
    }
}
