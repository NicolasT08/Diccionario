package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    FindPanel(WordController controller){
        this.controller = controller;
        setFindPanel();
    }

    private void setFindPanel(){
        this.setLayout(new GridLayout(1,2));

        this.setBackground("./src/main/java/co/edu/uptc/imgs/Libro abierto.png");

        this.setLeftPanel();

        this.setRightPanel();

        this.add(leftPanel);
        this.add(rightPanel);
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

    private void setLeftPanel(){
        this.leftPanel = new JPanel( new GridBagLayout() );
        leftPanel.setOpaque(false);

        JLabel labelWord = new JLabel("Palabra: ");

        this.textWord = new JTextField();
        this.textWord.setPreferredSize( new Dimension(190, 30) );

        this.sendButton = new JButton("Buscar");
        this.sendButton.addActionListener( this );
        this.sendButton.setBackground(new Color(110, 110, 110));
        this.sendButton.setForeground(Color.WHITE);


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

        this.meaning = new JTextArea();

        this.meaning.setMinimumSize(new Dimension(330, 70));
        this.meaning.setEditable(false);
        this.meaning.setOpaque(false);
        this.meaning.setLineWrap(true);
        this.meaning.setWrapStyleWord(true);
        this.meaning.setBackground(new Color(0, 0, 0, 0));


        this.translation = new JTextArea();

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
