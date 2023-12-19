package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates a JPanel to find a word in the dictionary.
 * It includes user interface components to enter the word to search
 * and a button to start the search process in all the words of the
 * dictionary. The class communicates with a WordController in order
 * to perform word search.
 * @author Nicolas Tinjaca
 */

public class FindPanel extends JPanel implements ActionListener {

    private WordController controller;
    private JTextField textWord;
    private JButton sendButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextArea name;
    private JTextArea meaning;
    private JTextArea translation;
    private Image background;
    private Font customFont;

    FindPanel(WordController controller, Font customFont){
        this.controller = controller;
        this.customFont = customFont;
        setFindPanel();
    }

    /**
     * Configure the layout, background, and word find panel components.
     * The word find panel consists of two main panels: the left panel
     * and right panel.
     */

    private void setFindPanel(){
        this.setLayout(new GridLayout(1,2));

        this.setBackground("./imgs/Libro abierto.png");

        this.setLeftPanel();

        this.setRightPanel();

        this.add(leftPanel);
        this.add(rightPanel);
    }

    /**
     * Overrides the paintComponent method to place a custom
     * panel background image, gets the width and height of the
     * panel, uses the Graphics object to draw the image. The method
     * then calls the superclass's paintComponent to ensure proper rendering.
     *
     * @param g Graphics object used for painting.
     */

    public void paintComponent(Graphics g) {

        int width = this.getSize().width;
        int height = this.getSize().height;

        if (this.background != null) {
            g.drawImage(this.background, 0, 0, width, height, null);
        }

        super.paintComponent(g);
    }

    /**
     * Sets the background image of the panel using the provided file path.
     * It makes the panel transparent and assigns the specified image to the
     * background variable. The repaint method is called to update the display.
     *
     * @param imagePath File path of the background image.
     */

    public void setBackground(String imagePath) {

        this.setOpaque(false);
        this.background = new ImageIcon(imagePath).getImage();
        repaint();
    }

    /**
     * Sets up the leftPanel with user input components, including a label, a
     * text field for entering the word to search, and a button to confirm the
     * start of the word search process. The leftPanel is configured with a
     * GridBagLayout, and its background is set to be transparent.
     */

    private void setLeftPanel(){
        this.leftPanel = new JPanel( new GridBagLayout() );
        leftPanel.setOpaque(false);

        JLabel labelWord = new JLabel("Palabra: ");
        labelWord.setFont( customFont );
        this.textWord = new JTextField();
        this.textWord.setPreferredSize( new Dimension(190, 30) );
        this.textWord.setFont( customFont );

        this.sendButton = new JButton("Buscar");
        this.sendButton.addActionListener( this );
        this.sendButton.setBackground(new Color(110, 110, 110));
        this.sendButton.setForeground(Color.WHITE);
        this.sendButton.setFont( customFont );


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(labelWord, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(textWord, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        leftPanel.add( sendButton, gbc );
    }

    /**
     * Sets up the right panel that shows the information of the found word,
     * includes three text areas that shows the word found, the meaning of
     * the word and the translation of the word in the English language.
     * The right Panel is configured with a GridBagLayout, and its background
     * is set to be transparent.
     */

    private void setRightPanel(){
        this.rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);

        this.name = new JTextArea();

        this.name.setMinimumSize(new Dimension(330, 20));
        this.name.setEditable(false);
        this.name.setOpaque(false);
        this.name.setLineWrap(true);
        this.name.setWrapStyleWord(true);
        this.name.setBackground(new Color(0, 0, 0, 0));
        this.name.setFont( customFont );

        this.meaning = new JTextArea();

        this.meaning.setMinimumSize(new Dimension(330, 70));
        this.meaning.setEditable(false);
        this.meaning.setOpaque(false);
        this.meaning.setLineWrap(true);
        this.meaning.setWrapStyleWord(true);
        this.meaning.setBackground(new Color(0, 0, 0, 0));
        this.meaning.setFont( customFont );

        this.translation = new JTextArea();

        this.translation.setMinimumSize(new Dimension(330, 70));
        this.translation.setEditable(false);
        this.translation.setOpaque(false);
        this.translation.setLineWrap(true);
        this.translation.setWrapStyleWord(true);
        this.translation.setBackground(new Color(0, 0, 0, 0));
        this.translation.setFont( customFont );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        rightPanel.add(name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(meaning, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add( translation, gbc );

    }

    /**
     * Handles button click events for the sendButton. It checks if the
     * textWord field is empty and displays an error message, after
     * that if the controller's find method, does not find the word display another
     * error message If the field is not empty, and the method find the word updates
     * the text areas with the name, meaning and translation of the found word,
     * and reset the left panel text field.
     *
     * @param e ActionEvent object representing the button click event.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == sendButton){

            if( textWord.getText().isEmpty() ){
                this.meaning.setText("Porfavor digita una palabra");
                this.name.setText("");
                this.translation.setText("");
            } else if ( controller.findWord(textWord.getText()) == null ){
                this.meaning.setText("No se encontro la palabra " + textWord.getText());
                this.name.setText("");
                this.translation.setText("");
                this.textWord.setText("");
            } else {
                String[] response = controller.findWord(textWord.getText());
                this.name.setText( response[0] );
                this.meaning.setText("Definición: " + response[1] );
                this.translation.setText("Traducción: " +  response[2] );
                this.textWord.setText("");
            }
        }
    }
}
