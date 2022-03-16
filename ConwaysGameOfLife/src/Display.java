import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    
    JFrame frame;
    JButton buttonGrid[][];

    public void initialize(int gridSize) {
        frame = new JFrame("Conway's Game of Life");

        Container content = frame.getContentPane();
        content.setLayout(new GridLayout(gridSize, gridSize));

        buttonGrid = new JButton[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buttonGrid[i][j] = new JButton();
                buttonGrid[i][j].setBackground(Color.WHITE);
                content.add(buttonGrid[i][j]);
            }
        }

        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void updateDisplay(Boolean boolGrid[][]) {
        for (int i = 0; i < boolGrid.length; i++) {
            for (int j = 0; j < boolGrid[0].length; j++) {
                if (boolGrid[i][j]) {
                    buttonGrid[i][j].setBackground(Color.BLACK);
                } else {
                    buttonGrid[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

}
