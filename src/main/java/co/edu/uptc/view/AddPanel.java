package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This panel is for add words to
 * the dictionary
 * @author Nicolas Sarmiento
 */
public class AddPanel extends JPanel implements ActionListener {
    private WordController controller;
    private JPanel left;
    private JPanel right;
    private JTextField word;
    private JTextField translate;
    private JTextArea meaning;
    private JLabel result;
    private JButton addButton;
    private Image background;
    private Font customFont;
    public AddPanel(WordController controller, Font font){
        super();
        this.controller = controller;
        this.customFont = font;
        this.setupPanel();

    }

    /**
     * This method sets the components of the
     * whole panel. The panel contains two sub-panels.
     * Also in this method instances all components.
     */
    public void setupPanel(){
        this.setLayout( new GridLayout(1,2) );
        this.setBackground("./imgs/Libro abierto.png");
        this.setPreferredSize( new Dimension(450,300));
        this.left = new JPanel();
        this.right = new JPanel();
        this.word = new JTextField();
        this.translate = new JTextField();
        this.meaning = new JTextArea();
        this.result = new JLabel();
        this.addButton = new JButton();



        this.setupLeft();
        this.setupRight();

        this.right.setOpaque(false);
        this.left.setOpaque(false);
        this.add(this.left);
        this.add(this.right );
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
     * This method sets up the right panel. It contains only a label
     * that show information about the add operation. The label is located
     * in the middle of the panel.
     */
    private void setupRight() {

        this.right.setLayout( new BorderLayout());
        this.result.setPreferredSize( new Dimension(150,150));
        this.result.setHorizontalTextPosition(SwingConstants.LEFT);
        this.result.setBorder( BorderFactory.createEmptyBorder(0,30,0,30));
        this.result.setHorizontalAlignment(SwingConstants.CENTER);
        this.result.setFont( this.customFont );
        this.right.add(this.result, BorderLayout.CENTER);

    }

    /**
     * This method sets up the left panel. This panel contains:
     * label and a text field for the word.
     * label and a text area for the meaning.
     * label and text field for word translation.
     * Finally, a button to perform the add action.
     * This panel has a GridBag layout
     */
    private void setupLeft() {

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,30,5,30);
        this.left.setLayout( layout );

        this.word.setPreferredSize( new Dimension(200, 30));
        this.word.setFont( customFont);
        this.meaning.setLineWrap(true);
        this.meaning.setWrapStyleWord(true);
        this.meaning.setFont( customFont );
        this.translate.setPreferredSize( new Dimension(200,30));
        this.translate.setFont( customFont );
        this.addButton.setText(" Añadir ");
        this.addButton.addActionListener(this);
        this.addButton.setPreferredSize(new Dimension( 200, 30));

        this.addButton.setBackground(new Color(110, 110, 110));
        this.addButton.setForeground(Color.WHITE);



        JLabel wordLabel = new JLabel("Palabra");
        wordLabel.setFont( customFont );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.left.add( wordLabel, gbc );


        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.left.add( this.word, gbc );

        gbc.insets.top = 10;

        JLabel meaningLabel = new JLabel("Definición");
        meaningLabel.setFont( customFont );
        gbc.gridy =2;
        gbc.anchor = GridBagConstraints.WEST;
        this.left.add( meaningLabel, gbc );

        gbc.insets.top = 0;

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        JScrollPane scroll = new JScrollPane( this.meaning );
        scroll.setPreferredSize( new Dimension(200,100));
        this.left.add( scroll, gbc );

        gbc.insets.top = 10;

        JLabel translateLabel = new JLabel("Traducción al inglés ");
        translateLabel.setFont( customFont );
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        this.left.add(translateLabel, gbc);

        gbc.insets.top = 0;

        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        this.left.add( this.translate, gbc );

        gbc.insets.top = 15;

        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        this.left.add( this.addButton, gbc );
    }


    /**
     * This method override the action to perform when
     * the button is pressed. The action is add the
     * word to the dictionary, first the fields are
     * checked and then the label in the right panel is
     * set to the answer than came from the controller.
     * @param e event from the send button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.addButton ){
            if ( this.word.getText().isBlank() ){
                this.result.setText("<html> Digita el valor para la palabra </html>");
            } else if ( this.meaning.getText().isBlank()) {
                this.result.setText("<html> Digita el valor para el significado </html>");
            } else if ( this.translate.getText().isBlank()) {
                this.result.setText("<html> Digita el valor para la traducción al inglés </html>");
            }else {
                this.result.setText( "<html>" + this.controller.addWord( word.getText(), meaning.getText(), translate.getText()) + "</html>");
                this.word.setText("");
                this.meaning.setText("");
                this.translate.setText("");
            }
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    result.setText("");
                }
            },4500);
        }
    }
}
