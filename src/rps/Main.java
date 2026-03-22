package rps;

import javax.swing.SwingUtilities;
import rps.ui.GameUI;

/**
 * Main application launcher class.
 * Initializes the GUI on the Event Dispatch Thread to follow Swing guidelines.
 */
public class Main {
    public static void main(String[] args) {
        // Run the UI construction on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            GameUI ui = new GameUI();
            ui.setVisible(true);
        });
    }
}
