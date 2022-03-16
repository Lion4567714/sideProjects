import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Display extends JFrame implements Runnable {
    
    public static boolean START_SIMULATION = false;

    JFrame frame;

    JPanel hotbar;
    JTextPane turnDisplay;
    JButton startButton;
    JButton resetButton;

    JPanel viewport;
    JButton buttonGrid[][];
    ArrayList<JButton> buttonList;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                if (START_SIMULATION) {
                    startButton.setText("Start Simulation");
                    START_SIMULATION = false;
                } else {
                    startButton.setText("Stop Simulation");
                    START_SIMULATION = true;
                }
            }

            if (e.getSource() == resetButton) {
                START_SIMULATION = false;

                turnDisplay.setText("Turn: 0");
                Life.turnNumber = 0;

                startButton.setText("Start Simulation");

                Life.grid = Life.initializeGrid();
                for (JButton button : buttonList) {
                    button.setBackground(Color.WHITE);
                }
            }

            if (buttonList.contains(e.getSource())) {
                for (JButton button : buttonList) {
                    if (button == e.getSource()) {
                        int x = buttonList.indexOf(button) % Life.GRID_SIZE;
                        int y = buttonList.indexOf(button) / Life.GRID_SIZE;

                        if (Life.grid[x][y]) {
                            Life.grid[x][y] = false;    
                            button.setBackground(Color.WHITE);
                        } else {
                            Life.grid[x][y] = true;
                            button.setBackground(Color.BLACK);
                        }
                    }
                }
            }
        }
    };

    @Override
    public void run() {
        frame = new JFrame("Conway's Game of Life");

        Container content = frame.getContentPane();
        // content.setLayout(new GridLayout(Life.GRID_SIZE, Life.GRID_SIZE));
        
        // Top Panel
        hotbar = new JPanel();
        hotbar.setLayout(new FlowLayout());

        turnDisplay = new JTextPane();
        turnDisplay.setText("Turn: 0");
        hotbar.add(turnDisplay);
        startButton = new JButton("Start Simulation");
        startButton.addActionListener(actionListener);
        hotbar.add(startButton);
        resetButton = new JButton("Reset Simulation");
        resetButton.addActionListener(actionListener);
        hotbar.add(resetButton);

        // Bottom Panel
        viewport = new JPanel();
        viewport.setLayout(new GridLayout(Life.GRID_SIZE, Life.GRID_SIZE));

        buttonGrid = new JButton[Life.GRID_SIZE][Life.GRID_SIZE];
        buttonList = new ArrayList<JButton>();
        for (int i = 0; i < Life.GRID_SIZE; i++) {
            for (int j = 0; j < Life.GRID_SIZE; j++) {
                buttonGrid[i][j] = new JButton();
                buttonList.add(buttonGrid[i][j]);
                buttonGrid[i][j].setBackground(Color.WHITE);
                buttonGrid[i][j].addActionListener(actionListener);
                viewport.add(buttonGrid[i][j]);
            }
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, hotbar, viewport);
        splitPane.setDividerLocation(50);
        content.add(splitPane);

        frame.setSize(600, 650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void updateDisplay(boolean boolGrid[][], int turnNumber) {
        for (int i = 0; i < boolGrid.length; i++) {
            for (int j = 0; j < boolGrid[0].length; j++) {
                if (boolGrid[i][j]) {
                    buttonGrid[i][j].setBackground(Color.BLACK);
                } else {
                    buttonGrid[i][j].setBackground(Color.WHITE);
                }
            }
        }

        turnDisplay.setText("Turn: " + turnNumber);
    }

}
