package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListPanel extends JPanel implements ActionListener {
    private JPanel left;
    private JPanel right;
    private JLabel result;
    private WordController controller;
    private Image background;

    public ListPanel(WordController controller) {
        this.controller = controller;
        this.setUp();

    }

    public void setUp(){
        this.setBackground("./imgs/Libro abierto.png");
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
        this.left.setOpaque(false);
        this.add( this.left);
        this.add( scroll );
    }

    public void paintComponent(Graphics g) {

        int width = this.getSize().width;
        int height = this.getSize().height;

        if (this.background != null) {
            g.drawImage(this.background, 0, 0, width, height, null);
        }

        super.paintComponent(g);
    }

    public void setBackground(String imagePath) {

        this.setOpaque(false);
        this.background = new ImageIcon(imagePath).getImage();
        repaint();
    }

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


    private void setupLeft() {
        GridBagConstraints gbc = new GridBagConstraints();
        this.left.setLayout( new GridBagLayout());
        JButton atoZ = new JButton("A - Z");
        atoZ.addActionListener( this );
        atoZ.setActionCommand( "all" );


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

                gbc.gridx = j;
                gbc.gridy = i;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.BOTH;
                alphabet.add( characterAlpha, gbc);
                asciiValue++;
            }
        }
    }

    private void showAnswers( String value){
       this.right.removeAll();
        String[][] words = value.compareTo("all") == 0 ? this.controller.showAllWords() : this.controller.listByFirstChar(value.charAt(0));
        if ( words == null ) {
            this.noAnswers( value );
            return;
        }
        this.setUpRight();
        for ( int i = 0; i < words.length ; i++ ){

            JPanel s = new WordPanel( words[i][0], words[i][1], words[i][2]);


            int insertAt = Math.max(0, this.right.getComponentCount() - 1);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(3, 2, 3,2);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            this.right.add( s , gbc, insertAt);
        }
    }

    private void noAnswers( String query){
        this.result.setText("No se encontraron palabras " + ( query.compareTo("all") == 0 ? ". Intenta añadir algunas.":" con " + query + "." ));
        this.right.add( result );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showAnswers( e.getActionCommand());
        this.right.revalidate();
        this.right.repaint();
    }
}
