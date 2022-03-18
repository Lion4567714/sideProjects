import javax.swing.*;
import java.awt.*;

public class StatisticsDisplay {
    
    JFrame frame;
    JPanel panel;
    JLabel frameTitle;
    JLabel turnLabel;
    JLabel turnDisplay;
    JLabel populationLabel;
    JLabel populationDisplay;

    public void initialize(JFrame parentFrame) {
        frame = new JFrame("Statistics");

        Container content = frame.getContentPane();

        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        frameTitle = new JLabel("Statistics");
        panel.add(frameTitle);
        panel.add(new JLabel());
        turnLabel = new JLabel("Turn:");
        panel.add(turnLabel);
        
        // for (int i = 0; i < 4; i++) {
        //     panel.add(new JLabel());
        // }
        turnDisplay = new JLabel("0");
        panel.add(turnDisplay);
        populationLabel = new JLabel("Population:");
        panel.add(populationLabel);
        populationDisplay = new JLabel("0");
        panel.add(populationDisplay);

        content.add(panel);

        frame.setLocationRelativeTo(parentFrame);
        frame.setBounds(parentFrame.getX() + 600, parentFrame.getY(), 200, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void updateStatistics(int turn, int population) {
        turnDisplay.setText(Integer.toString(turn));
        populationDisplay.setText(Integer.toString(population));
        frame.repaint();
    }
}
