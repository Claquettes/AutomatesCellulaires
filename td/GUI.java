package AutomatesCellulaires.td;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The GUI class of the AutomatesCellulaires application.
 */
public class GUI extends JFrame {

    private JComboBox<String> comboBox;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField_voison;
    private JTextField textField_regle;
    private JTextField textField_iterations;
    private JButton validateButton;
    private JButton backButton;
    private JButton secondaryValidateButton;
    private JPanel panel;
    private CardLayout cardLayout;

    /**
     * The constructor of the GUI class.
     * This constructor is responsible for initializing the GUI.
     */
    public GUI() {
        super("Automates cellulaires");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // set look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        JPanel mainPanel = new JPanel();
        JPanel secondaryPanel = new JPanel();

        JLabel label1 = new JLabel("Choix automate:");
        JLabel label2 = new JLabel("Nombre de colonnes:");
        JLabel label3 = new JLabel("Nombre de lignes:");
        JLabel label4 = new JLabel("Nombre d'itérations:");

        String[] options = { "Feu", "Conway", "1D", "Majorité" };
        comboBox = new JComboBox<>(options);

        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField_voison = new JTextField(10);
        textField_regle = new JTextField(10);
        textField_iterations = new JTextField(10);

        validateButton = new JButton("Validate");
        backButton = new JButton("Retour");
        secondaryValidateButton = new JButton("Validate");

        validateButton.addActionListener(new ActionListener() {
            @Override
            /**
             * This method is called when the validateButton is clicked.
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                int columns = Integer.parseInt(textField1.getText());
                int rows = Integer.parseInt(textField2.getText());
                if (selectedOption.equals("1D") || selectedOption.equals("Majorité")) {
                    if(selectedOption.equals("Majorité")){
                        JLabel label_voisin = new JLabel("Nombre de voisins impaire:");
                        secondaryPanel.add(label_voisin);
                    }else{
                        JLabel label_voisin = new JLabel("Nombre de voisins (1 ou 2):");
                        secondaryPanel.add(label_voisin);
                    }
                    
                    JLabel label_regle = new JLabel("Numéro de la règle:");

                    
                    secondaryPanel.add(textField_voison);
                    if (selectedOption.equals("1D")){
                        secondaryPanel.add(label_regle);
                        secondaryPanel.add(textField_regle);
                    }
                    
                    secondaryPanel.add(secondaryValidateButton);
                    cardLayout.show(panel, "secondaryPanel");

                }
                else {
                    if (selectedOption.equals("Conway")) {
                        Automate automate = new Automate(8, columns,rows, "VIE" );

                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() throws Exception {
                                for (int i = 0; i < Integer.parseInt(textField_iterations.getText()); i++){
                                    automate.miseAJour();

                                    SwingUtilities.invokeLater(() -> {
                                        secondaryPanel.removeAll();
                                        secondaryPanel.setLayout(new BoxLayout(secondaryPanel, BoxLayout.Y_AXIS)); 

                                        JPanel gridPanel = new JPanel(
                                                new GridLayout(automate.grid.nbLine, automate.grid.nbCol)); 
                                        gridPanel.setMaximumSize(
                                                new Dimension((int) (getWidth() * 0.8), (int) (getHeight() * 0.8)));
                                        for (int row = 0; row < automate.grid.nbLine; row++) {
                                            for (int col = 0; col < automate.grid.nbCol; col++) {
                                                JPanel cellPanel = new JPanel();
                                                cellPanel.setPreferredSize(
                                                        new Dimension((int) (getWidth() * 0.8 / automate.grid.nbCol),
                                                                (int) (getHeight() * 0.8 / automate.grid.nbLine)));
                                                if (automate.grid.getValeurCellule(row, col) == "1") {
                                                    cellPanel.setBackground(Color.BLACK);
                                                } else {
                                                    cellPanel.setBackground(Color.WHITE);
                                                }
                                                    
                                                gridPanel.add(cellPanel); // Add the cellPanel to the gridPanel
                                            }
                                        }

                                        JButton backButton = new JButton("Fermer");
                                        backButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                // fermer le programme
                                                System.exit(0);
                                            }
                                        });

                                        secondaryPanel.add(backButton); // Add the backButton to the secondaryPanel
                                        secondaryPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add a vertical
                                                                                                    // spacer
                                        secondaryPanel.add(gridPanel); // Add the gridPanel to the secondaryPanel

                                        secondaryPanel.revalidate(); // Update the panel after adding the cells
                                        secondaryPanel.repaint();
                                    });

                                    Thread.sleep(250);
                                }
                                return null;
                            }
                        }.execute();
                    
                    }
                    else{
                        Automate automate = new Automate(selectedOption, columns, rows);

                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() throws Exception {
                                for (int i = 0; i < Integer.parseInt(textField_iterations.getText()); i++) {
                                    automate.miseAJour();

                                    SwingUtilities.invokeLater(() -> {
                                        secondaryPanel.removeAll();
                                        secondaryPanel.setLayout(new BoxLayout(secondaryPanel, BoxLayout.Y_AXIS)); 

                                        JPanel gridPanel = new JPanel(
                                                new GridLayout(automate.grid.nbLine, automate.grid.nbCol)); 
                                        gridPanel.setMaximumSize(
                                                new Dimension((int) (getWidth() * 0.8), (int) (getHeight() * 0.8)));
                                        for (int row = 0; row < automate.grid.nbLine; row++) {
                                            for (int col = 0; col < automate.grid.nbCol; col++) {
                                                JPanel cellPanel = new JPanel();
                                                cellPanel.setPreferredSize(
                                                        new Dimension((int) (getWidth() * 0.8 / automate.grid.nbCol),
                                                                (int) (getHeight() * 0.8 / automate.grid.nbLine)));
                                                switch (automate.grid.getValeurCellule(row, col)) {
                                                    case "CENDRE":
                                                        cellPanel.setBackground(Color.GRAY);
                                                        break;

                                                    case "FEU":
                                                        cellPanel.setBackground(Color.RED);
                                                        break;

                                                    case "ARBRE":
                                                        cellPanel.setBackground(Color.GREEN);
                                                        break;

                                                    default:
                                                        cellPanel.setBackground(Color.WHITE);
                                                        break;
                                                }

                                                gridPanel.add(cellPanel); // Add the cellPanel to the gridPanel
                                            }
                                        }

                                        JButton backButton = new JButton("Fermer");
                                        backButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                // fermer le programme
                                                System.exit(0);
                                            }
                                        });

                                        secondaryPanel.add(backButton); // Add the backButton to the secondaryPanel
                                        secondaryPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add a vertical
                                                                                                    // spacer
                                        secondaryPanel.add(gridPanel); // Add the gridPanel to the secondaryPanel

                                        secondaryPanel.revalidate(); // Update the panel after adding the cells
                                        secondaryPanel.repaint();
                                    });

                                    Thread.sleep(250); // Wait for 1 second
                                }
                                return null;
                            }
                        }.execute();
                    }

                    cardLayout.show(panel, "secondaryPanel");
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
                String selectedOption = (String) comboBox.getSelectedItem();
                int neighbors = Integer.parseInt(textField_voison.getText());
        
                int col = Integer.parseInt(textField1.getText());

                if (selectedOption.equals("Majorité")) {
                    int row = Integer.parseInt(textField2.getText());

                    Automate automate = new Automate(neighbors, col,row, "MAJORITE" );

                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            for (int i = 0; i < Integer.parseInt(textField_iterations.getText()); i++){
                                automate.miseAJour();

                                SwingUtilities.invokeLater(() -> {
                                    secondaryPanel.removeAll();
                                    secondaryPanel.setLayout(new BoxLayout(secondaryPanel, BoxLayout.Y_AXIS)); 

                                    JPanel gridPanel = new JPanel(
                                            new GridLayout(automate.grid.nbLine, automate.grid.nbCol)); 
                                    gridPanel.setMaximumSize(
                                            new Dimension((int) (getWidth() * 0.8), (int) (getHeight() * 0.8)));
                                    for (int row = 0; row < automate.grid.nbLine; row++) {
                                        for (int col = 0; col < automate.grid.nbCol; col++) {
                                            JPanel cellPanel = new JPanel();
                                            cellPanel.setPreferredSize(
                                                    new Dimension((int) (getWidth() * 0.8 / automate.grid.nbCol),
                                                            (int) (getHeight() * 0.8 / automate.grid.nbLine)));
                                            if (automate.grid.getValeurCellule(row, col) == "1") {
                                                cellPanel.setBackground(Color.BLACK);
                                            } else {
                                                cellPanel.setBackground(Color.WHITE);
                                            }
                                                
                                            gridPanel.add(cellPanel); // Add the cellPanel to the gridPanel
                                        }
                                    }

                                    JButton backButton = new JButton("Fermer");
                                    backButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // fermer le programme
                                            System.exit(0);
                                        }
                                    });

                                    secondaryPanel.add(backButton); // Add the backButton to the secondaryPanel
                                    secondaryPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add a vertical
                                                                                                // spacer
                                    secondaryPanel.add(gridPanel); // Add the gridPanel to the secondaryPanel

                                    secondaryPanel.revalidate(); // Update the panel after adding the cells
                                    secondaryPanel.repaint();
                                });

                                Thread.sleep(250);
                            }
                            return null;
                        }
                    }.execute();



                }
                else{
                    int regle = Integer.parseInt(textField_regle.getText());

                    Automate automate = new Automate("1D", neighbors, regle, col);

                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            for (int i = 0; i < Integer.parseInt(textField_iterations.getText()); i++) {
                                automate.miseAJour();

                                // supprimer les cellules précédentes
                                SwingUtilities.invokeLater(() -> {
                                    secondaryPanel.removeAll();
                                    secondaryPanel.setLayout(new BoxLayout(secondaryPanel, BoxLayout.Y_AXIS));

                                    JPanel gridPanel = new JPanel(new GridLayout(1, automate.grid.nbCol));
                                    gridPanel.setMaximumSize(new Dimension(automate.grid.nbCol * 20, 20));

                                    for (int j = 0; j < automate.grid.nbCol; j++) {
                                        JPanel cellPanel = new JPanel();
                                        cellPanel.setPreferredSize(new Dimension(20, 20)); // Set preferred size to create a
                                                                                        // square
                                        if (automate.grid.getValeurCellule(0, j) == "1") {
                                            cellPanel.setBackground(Color.BLACK);
                                        } else {
                                            cellPanel.setBackground(Color.WHITE);
                                        }
                                        gridPanel.add(cellPanel); // Add the cellPanel to the gridPanel
                                    }

                                    // Add a "Back" button at the end
                                    JButton backButton = new JButton("Fermer");
                                    backButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // fermer le programme
                                            System.exit(0);
                                        }
                                    });
                                    secondaryPanel.add(backButton); // Add the backButton to the secondaryPanel
                                    secondaryPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add a vertical spacer
                                    secondaryPanel.add(gridPanel); // Add the gridPanel to the secondaryPanel

                                    secondaryPanel.revalidate(); // Update the panel after adding the cells
                                    secondaryPanel.repaint();
                                });

                                Thread.sleep(250); // Wait for 1 second
                            }
                            return null;
                        }
                    }.execute();
                }
            }
        });

        mainPanel.add(label1);
        mainPanel.add(comboBox);
        mainPanel.add(label2);
        mainPanel.add(textField1);
        mainPanel.add(label3);
        mainPanel.add(textField2);
        mainPanel.add(label4);
        mainPanel.add(textField_iterations);
        mainPanel.add(validateButton);

        secondaryPanel.add(backButton);

        panel.add(mainPanel, "mainPanel");
        panel.add(secondaryPanel, "secondaryPanel");

        this.setContentPane(panel);
        this.setVisible(true);
    }
}