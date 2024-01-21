package AutomatesCellulaires.td;

import javax.swing.*;

public class GUI {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Automates cellulaires");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);

        ImageIcon icon = new ImageIcon(""); // Ajouter logo fenetre
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(new java.awt.Color(50, 100, 135));


        // Label

        JLabel label = new JLabel("Automates cellulaires");
        label.setBounds(400, 0, 400, 100);
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setFont(new java.awt.Font("Arial", 1, 26));
        frame.add(label);

        // Input

        JTextField input = new JTextField();
        input.setBounds(0, 100, 400, 100);
        input.setForeground(new java.awt.Color(255, 255, 255));
        input.setBackground(new java.awt.Color(40, 80, 115));
        input.setFont(new java.awt.Font("Arial", 1, 26));
        frame.add(input);


    }
}
