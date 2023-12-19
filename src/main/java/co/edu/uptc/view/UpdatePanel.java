package co.edu.uptc.view;

import co.edu.uptc.controller.ATT_TYPE;
import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * This class creates a JPanel to update a word in the dictionary.
 * It includes user interface components to enter the word to update,
 * three buttons to choose the attribute to update, a text field to
 * enter the new value and a button to start the update process. The
 * class communicates with a WordController in order to perform word
 * update.
 *
 * @author Nicolas Tinjaca
 */

public class UpdatePanel extends JPanel implements ActionListener {

    private WordController controller;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextArea name;
    private JTextArea meaning;
    private JTextArea translation;
    private JTextField textWord;
    private int election;
    private JTextField textNewValue;
    private JButton sendButton;
    private JButton word;
    private JButton meaningButton;
    private JButton translationButton;
    private Image background;
    private  Font customFont;

    UpdatePanel(WordController controller, Font customFont){
        this.controller = controller;
        this.customFont = customFont;
        this.setFindPanel();
    }

    /**
     * Configure the layout, background, and word update panel components.
     * The word update panel consists of two main panels: the left panel
     * and right panel.
     */

    private void setFindPanel(){
        this.setLayout(new GridLayout(1,2));
        this.setBackground("imgs/Libro abierto.png");

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
        URL path = MainView.class.getClassLoader().getResource(imagePath);
        if ( path == null ) return;
        this.setOpaque(false);
        this.background = new ImageIcon(path).getImage();
        repaint();
    }

    /**
     * Sets up the left panel with user input components that include three labels to
     * tell the user the functionality of each field, a text field for entering the word
     * to be updated, three buttons to select the attribute to change, a text field for input
     * the new value, and a button for confirming the update of the word.The leftPanel is
     * configured with a GridBagLayout, and its background is set to be
     * transparent.
     */

    private void setLeftPanel(){
        this.leftPanel = new JPanel(new GridBagLayout());
        this.leftPanel.setOpaque(false);

        JLabel labelWord = new JLabel("   Palabra: ");
        labelWord.setFont( customFont );
        this.textWord = new JTextField();
        this.textWord.setPreferredSize( new Dimension(280, 30) );
        this.textWord.setFont( customFont );

        JLabel labelAttribute = new JLabel("  Atributo a cambiar:");
        labelAttribute.setFont( customFont );

        Font buttonFont = new Font( customFont.getFontName(), customFont.getStyle(), 18);
        this.word = new JButton("Palabra");
        this.word.addActionListener(e -> election = 1);
        this.word.addActionListener(e -> {
            word.setBackground( new Color(143, 219, 143) );
            word.setForeground( Color.BLACK);
        } );
        this.word.addActionListener(e -> resetButtons() );
        this.word.setBackground(new Color(110, 110, 110));
        this.word.setForeground(Color.WHITE);
        this.word.setFont( buttonFont );


        this.meaningButton = new JButton("Significado");
        this.meaningButton.addActionListener(e -> election = 2);
        this.meaningButton.addActionListener(e -> {
            meaningButton.setBackground( new Color(143, 219, 143) );
            meaningButton.setForeground( Color.BLACK);
        } );
        this.meaningButton.addActionListener(e -> resetButtons() );
        this.meaningButton.setBackground(new Color(110, 110, 110));
        this.meaningButton.setForeground(Color.WHITE);
        this.meaningButton.setFont( buttonFont );

        this.translationButton = new JButton("Traduccion");
        this.translationButton.addActionListener(e -> election = 3);
        this.translationButton.addActionListener(e -> {
            translationButton.setBackground( new Color(143, 219, 143) );
            translationButton.setForeground(Color.BLACK);
        });
        this.translationButton.addActionListener(e -> resetButtons() );
        this.translationButton.setBackground(new Color(110, 110, 110));
        this.translationButton.setForeground(Color.WHITE);
        this.translationButton.setFont( buttonFont );

        JLabel labelNewAttribute = new JLabel("   Nuevo valor: ");
        labelNewAttribute.setFont( customFont );

        this.textNewValue = new JTextField();
        this.textNewValue.setPreferredSize( new Dimension(280, 30) );
        this.textNewValue.setFont( customFont );

        this.sendButton = new JButton("Actualizar");
        this.sendButton.addActionListener(this);
        this.sendButton.setBackground(new Color(110, 110, 110));
        this.sendButton.setForeground(Color.WHITE);
        this.sendButton.setFont( customFont );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 3, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(labelWord, gbc);

        gbc.insets = new Insets(5, 5, 25, 5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(textWord, gbc);

        gbc.insets = new Insets(5, 5, 3, 5);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add( labelAttribute, gbc );

        JPanel wordMeaningPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wordMeaningPanel.setOpaque(false);
        wordMeaningPanel.add(word);
        wordMeaningPanel.add(meaningButton);
        wordMeaningPanel.add(translationButton);

        gbc.insets = new Insets(5, 5, 25, 5);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(wordMeaningPanel, gbc);

        gbc.insets = new Insets(5, 5, 3, 5);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(labelNewAttribute, gbc);

        gbc.insets = new Insets(5, 5, 25, 5);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(textNewValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        leftPanel.add(sendButton, gbc);

    }

    /**
     * Sets up the right panel that shows the information of the word after being updated,
     * includes three text areas that shows the word, the meaning of
     * the word and the translation of the word in the English language.
     * The right Panel is configured with a GridBagLayout, and its background
     * is set to be transparent.
     */

    private void setRightPanel(){
        this.rightPanel = new JPanel(new GridBagLayout());
        this.rightPanel.setOpaque(false);

        this.name = new JTextArea();
        this.name.setFont( customFont );
        this.name.setMinimumSize(new Dimension(330, 20));
        this.name.setEditable(false);
        this.name.setOpaque(false);
        this.name.setLineWrap(true);
        this.name.setWrapStyleWord(true);
        this.name.setBackground(new Color(0, 0, 0, 0));


        this.meaning = new JTextArea();
        this.meaning.setFont( customFont );
        this.meaning.setMinimumSize(new Dimension(330, 70));
        this.meaning.setEditable(false);
        this.meaning.setOpaque(false);
        this.meaning.setLineWrap(true);
        this.meaning.setWrapStyleWord(true);
        this.meaning.setBackground(new Color(0, 0, 0, 0));


        this.translation = new JTextArea();
        this.translation.setFont( customFont );
        this.translation.setMinimumSize(new Dimension(330, 70));
        this.translation.setEditable(false);
        this.translation.setOpaque(false);
        this.translation.setLineWrap(true);
        this.translation.setWrapStyleWord(true);
        this.translation.setBackground(new Color(0, 0, 0, 0));

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
     * This method resets the background color of the attribute
     * selection buttons in the left panel
     */

    private void resetButtons() {
        this.word.setBackground(new Color(110, 110, 110));
        this.word.setForeground(Color.WHITE);

        this.meaningButton.setBackground(new Color(110, 110, 110));
        this.meaningButton.setForeground(Color.WHITE);

        this.translationButton.setBackground(new Color(110, 110, 110));
        this.translationButton.setForeground(Color.WHITE);
    }

    /**
     * This method resets the input fields, and the election
     * attribute of the left panel and calls the method to
     * reset the color of the buttons
     */

    private void resetValues(){
        this.textWord.setText("");
        this.textNewValue.setText("");
        this.election = 0;
        this.resetButtons();
    }

    /**
     * Handles button click events for the sendButton. It checks if the
     * textWord field is empty and displays an error message, after
     * that if the text field of hte new value is empty, displays a different
     * error message, and also do that if the user does not click any attribute button,
     * and if the controller's find method, does not find the word display another
     * error message, finally calls the controller's UpdateWord method, and updates
     * the text areas with the name, meaning and translation of the update word,
     * and reset the left inputs fields
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


            }else if( this.textNewValue.getText().isEmpty() ){
                this.meaning.setText("Porfavor digita el nuevo valor");
                this.name.setText("");
                this.translation.setText("");

            }else if(election == 0){
                this.meaning.setText("Porfavor seleccione el atributo a cambiar");
                this.name.setText("");
                this.translation.setText("");

            }else if ( controller.findWord(textWord.getText()) == null ) {
                this.meaning.setText("No se encontro la palabra " + textWord.getText());
                this.name.setText("");
                this.translation.setText("");

                this.resetValues();
            } else {
                String[] response = new String[3];
                switch (election){
                    case 1:
                        response = controller.updateWord(textWord.getText(),textNewValue.getText(), ATT_TYPE.WORD);
                        break;
                    case 2:
                        response = controller.updateWord(textWord.getText(),textNewValue.getText(), ATT_TYPE.MEANING);
                        break;
                    case 3:
                        response = controller.updateWord(textWord.getText(),textNewValue.getText(), ATT_TYPE.TRANSLATE);
                        break;
                }
                if( response.length < 2){
                    this.meaning.setText(response[0]);
                }else {
                    this.name.setText( response[0] );
                    this.meaning.setText("Definición: " + response[1] );
                    this.translation.setText("Traducción: " +  response[2] );
                    this.resetValues();
                }

            }
        }
    }
}
