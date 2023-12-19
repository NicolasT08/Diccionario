package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * This panel collect and perform the word list action.
 * For this task, the Panel has some components mainly buttons
 * and a scrollbar to show the respective answer.
 * @author Nicolas Sarmiento
 */
public class ListPanel extends JPanel implements ActionListener {
    private JPanel left;
    private JPanel right;
    private JLabel result;
    private WordController controller;
    private Image background;
    private Font customFont;

    /**
     * This creates the panel and set the
     * controller and the font.
     * @param controller WordController to do the actions.
     * @param customFont Custom font
     */
    public ListPanel(WordController controller, Font customFont) {
        this.controller = controller;
        this.customFont = customFont;
        this.setUp();

    }

    /**
     * This method sets up the background and instanciates
     * the components.
     */
    public void setUp(){
        this.setBackground("imgs/Libro abierto.png");
        this.left = new JPanel();
        this.right = new JPanel();
        this.result = new JLabel();
        JScrollPane scroll = new JScrollPane( this.right );
        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout( new GridLayout(1, 2));

        this.setupLeft();
        this.setUpRight();
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder( BorderFactory.createEmptyBorder(25,25,25,25));
        this.left.setOpaque(false);
        this.add( this.left);
        this.add( scroll );
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
     * Sets the components of the right panel. By default,
     * it has only a label that shows a wrong answer for the
     * performed action. And this panel contains a scroll panel
     * with WordPanels that represents the answer of the query
     */
    private void setUpRight() {
       this.right.setOpaque(false);
       this.right.setLayout( new GridBagLayout());

       GridBagConstraints gbc = new GridBagConstraints();
       gbc.gridwidth = GridBagConstraints.REMAINDER;
       gbc.weighty = 1;
       JPanel empty = new JPanel();
       empty.setOpaque(false);
       this.right.add( empty, gbc);
    }


    /**
     * Sets up the components of the left panel.
     * The components are buttons. There are a button for all the words.
     * Also, the left panel has all the Spanish alphabet characters, to
     * perform a query for each spanish alphabet character.
     */
    private void setupLeft() {
        GridBagConstraints gbc = new GridBagConstraints();
        this.left.setLayout( new GridBagLayout());
        JButton atoZ = new JButton("A - Z");
        atoZ.addActionListener( this );
        atoZ.setActionCommand( "all" );
        atoZ.setBackground(new Color(110, 110, 110));
        atoZ.setForeground(Color.WHITE);
        atoZ.setFont( customFont );


        gbc.insets = new Insets( 10, 5,5, 5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        this.left.add( atoZ, gbc );

        JPanel alphabet = new JPanel();
        alphabet.setOpaque(false);
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.insets.right = 30;
        gbc.insets.left = 30;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.left.add( alphabet, gbc );
        alphabet.setLayout( new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets( 5,5,5,5);
        int asciiValue = 0;
        for ( int i = 0; i < 6; i++ ){
            for ( int j = 0; j < 5; j++ ){
                if ( asciiValue > 26 ) break;
                char alpha = asciiValue < 14 ? (char) (asciiValue + 65) : asciiValue > 14 ? (char) (asciiValue + 64): 'N';
                JButton characterAlpha = new JButton(String.valueOf(alpha));
                characterAlpha.setPreferredSize( new Dimension( 50,50));
                characterAlpha.addActionListener(this );
                characterAlpha.setActionCommand( String.valueOf(alpha));
                characterAlpha.setBackground(new Color(110, 110, 110));
                characterAlpha.setForeground(Color.WHITE);
                characterAlpha.setFont( customFont );
                gbc.gridx = j;
                gbc.gridy = i;

                gbc.fill = GridBagConstraints.BOTH;
                alphabet.add( characterAlpha, gbc);
                asciiValue++;
            }
        }
    }

    /**
     * Add WordPanels to the right panel when a query has
     * words to show. The panels are added in a GridBag layout
     * and stack one over one.
     * @param value is the query character.
     */
    private void showAnswers( String value){
       this.right.removeAll();
        String[][] words = value.compareTo("all") == 0 ? this.controller.showAllWords() : this.controller.listByFirstChar(value.charAt(0));
        if ( words == null ) {
            this.noAnswers( value );
            return;
        }
        this.setUpRight();
        for ( int i = 0; i < words.length ; i++ ){

            JPanel s = new WordPanel( words[i][0], words[i][1], words[i][2], customFont);


            int insertAt = Math.max(0, this.right.getComponentCount() - 1);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 50, 30,30);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 0.5;
            gbc.fill = GridBagConstraints.BOTH;
            s.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
            this.right.add( s , gbc, insertAt);
        }
    }

    /**
     * This method is for set the right panel label when
     * the query has no answer. So it sets a message
     *
     * @param query type of query
     */
    private void noAnswers( String query ){
        this.result.setText("<html>No se encontraron palabras" + ( query.compareTo("all") == 0 ? ".<br>Intenta añadir algunas.":" con " + query + ".") + "</html>");

        this.result.setFont( customFont );
        this.right.add( result );
    }

    /**
     * This is the action than performs when some button in
     * the left panel is clicked. The function to set
     * the right panel is called as well the method to repaint
     * the right panel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        showAnswers( e.getActionCommand());
        this.right.revalidate();
        this.right.repaint();
    }
}
