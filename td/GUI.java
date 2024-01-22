package AutomatesCellulaires.td;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private JComboBox<String> comboBox;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField_voison;
    private JTextField textField_regle;
    private JButton validateButton;
    private JButton backButton;
    private JButton secondaryValidateButton;
    private JPanel panel;
    private CardLayout cardLayout;

    public GUI() {
        super("Automates cellulaires");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2));
        JPanel secondaryPanel = new JPanel(new GridLayout(5, 2));

        JLabel label1 = new JLabel("Choix automate:");
        JLabel label2 = new JLabel("Nombre de colonnes:");
        JLabel label3 = new JLabel("Nombre de lignes:");

        String[] options = {"Feu", "Conway", "1D"};
        comboBox = new JComboBox<>(options);

        textField1 = new JTextField();
        textField2 = new JTextField();
        textField_voison = new JTextField();
        textField_regle = new JTextField();

        validateButton = new JButton("Validate");
        backButton = new JButton("Retour");
        secondaryValidateButton = new JButton("Validate");

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                int columns = Integer.parseInt(textField1.getText());
                int rows = Integer.parseInt(textField2.getText());
                if (selectedOption.equals("1D")) {
                    JLabel label_voisin = new JLabel("Nombre de voisins:");
                    JLabel label_regle = new JLabel("Numéro de la règle:");

                    secondaryPanel.add(label_voisin);
                    secondaryPanel.add(textField_voison);
                    secondaryPanel.add(label_regle);
                    secondaryPanel.add(textField_regle);
                    secondaryPanel.add(secondaryValidateButton);
                    cardLayout.next(panel);
                } else {
                    Automate automate = new Automate(selectedOption, columns, rows);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(panel);
            }
        });

        secondaryValidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int neighbors = Integer.parseInt(textField_voison.getText());
                int regle = Integer.parseInt(textField_regle.getText());
                int col = Integer.parseInt(textField1.getText());
                
                Automate automate1D = new Automate("1D", neighbors, regle, col);
            }
        });

        mainPanel.add(label1);
        mainPanel.add(comboBox);
        mainPanel.add(label2);
        mainPanel.add(textField1);
        mainPanel.add(label3);
        mainPanel.add(textField2);
        mainPanel.add(validateButton);

        secondaryPanel.add(backButton);

        panel.add(mainPanel, "mainPanel");
        panel.add(secondaryPanel, "secondaryPanel");

        this.setContentPane(panel);
        this.setVisible(true);
    }
}