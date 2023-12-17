package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class AddView extends JPanel implements ActionListener {
    private WordController controller;
    private JPanel left;
    private JPanel right;
    private JTextField word;
    private JTextField translate;
    private JTextArea meaning;
    private JLabel result;
    private JButton addButton;
    public AddView( WordController controller){
        super();
        this.controller = controller;
        this.setupPanel();
    }

    public void setupPanel(){
        this.setLayout( new GridLayout(1,2) );
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

        this.add(this.left);
        this.add(this.right );
    }

    private void setupRight() {

        this.right.setLayout( new BorderLayout());
        this.result.setPreferredSize( new Dimension(150,150));
        this.result.setHorizontalTextPosition(SwingConstants.LEFT);
        this.result.setBorder( BorderFactory.createEmptyBorder(0,30,0,30));
        this.result.setHorizontalAlignment(SwingConstants.CENTER);
        this.right.add(this.result, BorderLayout.CENTER);
    }

    private void setupLeft() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,30,5,30);
        this.left.setLayout( layout );

        this.word.setPreferredSize( new Dimension(200, 30));
        this.meaning.setPreferredSize( new Dimension(200,80));
        this.translate.setPreferredSize( new Dimension(200,30));
        this.addButton.setText(" Añadir ");
        this.addButton.addActionListener(this);
        this.addButton.setPreferredSize(new Dimension( 200, 30));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        JLabel wordLabel = new JLabel("Palabra");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.left.add( wordLabel, gbc );


        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.left.add( this.word, gbc );

        gbc.insets.top = 10;

        JLabel meaningLabel = new JLabel("Definición");
        gbc.gridy =2;
        gbc.anchor = GridBagConstraints.WEST;
        this.left.add( meaningLabel, gbc );

        gbc.insets.top = 0;

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        this.left.add( this.meaning, gbc );

        gbc.insets.top = 10;

        JLabel translateLabel = new JLabel("Traducción al inglés ");
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
            },2500);
        }
    }
}
