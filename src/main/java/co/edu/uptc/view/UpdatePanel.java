package co.edu.uptc.view;

import co.edu.uptc.controller.ATT_TYPE;
import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    UpdatePanel(WordController controller){
        this.controller = controller;
        this.setFindPanel();
    }

    private void setFindPanel(){
        this.setLayout(null);
        this.setLayout(new GridLayout(1,2));

        this.setLeftPanel();

        this.setRightPanel();

        this.add(leftPanel);
        this.add(rightPanel);
    }

    private void setLeftPanel(){
        this.leftPanel = new JPanel(new GridBagLayout());

        JLabel labelWord = new JLabel("Palabra: ");

        this.textWord = new JTextField();
        this.textWord.setPreferredSize( new Dimension(290, 30) );

        JLabel labelAttribute = new JLabel("Atributo a cambiar:");

        this.word = new JButton("Palabra");
        this.word.addActionListener(e -> election = 1);
        this.word.addActionListener(e -> word.setBackground( new Color(143, 219, 143) ) );
        this.word.addActionListener(e -> resetButtons() );

        this.meaningButton = new JButton("Significado");
        this.meaningButton.addActionListener(e -> election = 2);
        this.meaningButton.addActionListener(e -> meaningButton.setBackground( new Color(143, 219, 143) ) );
        this.meaningButton.addActionListener(e -> resetButtons() );

        this.translationButton = new JButton("Traduccion");
        this.translationButton.addActionListener(e -> election = 3);
        this.translationButton.addActionListener(e -> translationButton.setBackground( new Color(143, 219, 143) ) );
        this.translationButton.addActionListener(e -> resetButtons() );

        JLabel labelNewAttribute = new JLabel("Nuevo valor: ");

        this.textNewValue = new JTextField();
        this.textNewValue.setPreferredSize( new Dimension(290, 30) );

        this.sendButton = new JButton("Actualizar");
        this.sendButton.addActionListener(this);

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

    private void setRightPanel(){
        this.rightPanel = new JPanel(new GridBagLayout());

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
    private void resetButtons() {
        word.setBackground(null);
        meaningButton.setBackground(null);
        translationButton.setBackground(null);
    }

    private void resetValues(){
        this.textWord.setText("");
        this.textNewValue.setText("");
        this.election = 0;
        this.resetButtons();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == sendButton){
            if( textWord.getText().isEmpty() ){
                this.meaning.setText("Porfavor digita una palabra");
                this.name.setText("");
                this.translation.setText("");

                this.resetValues();

            }else if( this.textNewValue.getText().isEmpty() ){
                this.meaning.setText("Porfavor digita el nuevo valor");
                this.name.setText("");
                this.translation.setText("");

                this.resetValues();
            }else if(election == 0){
                this.meaning.setText("Porfavor digita el nuevo valor");
                this.name.setText("");
                this.translation.setText("");

                this.resetValues();
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
                this.name.setText( response[0] );
                this.meaning.setText("Definición: " + response[1] );
                this.translation.setText("Traducción: " +  response[2] );

                this.resetValues();
            }
        }
    }
}
