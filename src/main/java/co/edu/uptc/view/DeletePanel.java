package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePanel extends JPanel implements ActionListener{

    private WordController controller;
    private JTextField textWord;
    private JButton sendButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel rightLabel;
    private boolean successfully;

    DeletePanel(WordController controller){
        this.controller = controller;
        successfully = false;
        setDeleteTable();
    }

    private void setDeleteTable(){
        this.setLayout(null);
        this.setLayout(new GridLayout(1,2));

        this.setLeftPanel();

        rightPanel = new JPanel(new GridBagLayout());
        rightLabel = new JLabel();
        rightPanel.add(rightLabel);

        this.add(leftPanel);
        this.add(rightPanel);
    }

    private void setLeftPanel(){
        this.leftPanel = new JPanel( new GridBagLayout() );

        JLabel labelWord = new JLabel("Palabra: ");

        this.textWord = new JTextField();
        this.textWord.setPreferredSize( new Dimension(190, 30) );

        this.sendButton = new JButton("Eliminar");
        this.sendButton.addActionListener( this );


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == sendButton){
            if( textWord.getText().length() == 0 ){
                this.rightLabel.setText("Porfavor digita una palabra");
                this.textWord.setText("");
            } else {
                if ( !textWord.getText().isEmpty() ){
                    int result = JOptionPane.showConfirmDialog(null,"¿Seguro que quieres eliminar la palabra?", "Confirmación", JOptionPane.YES_NO_OPTION);

                    if ( result == JOptionPane.YES_OPTION && !textWord.getText().isEmpty() ) {
                        if( controller.deleteWord(textWord.getText()) != null ){
                            successfully = true;
                        }
                        updateRightLabel();
                    }
                    this.textWord.setText("");
                }
            }
        }
    }

    private void updateRightLabel() {
        if (successfully) {
            if ( textWord.getText().length() <= 20 ) {
                this.rightLabel.setText("La palabra " + textWord.getText() + " ha sido borrada satisfactoriamente");
            } else {
                this.rightLabel.setText("<html>La palabra " + textWord.getText() + "<br> ha sido borrada satisfactoriamente</html>");
            }
        }else {
            this.rightLabel.setText("No se pudo eliminar la palabra " + textWord.getText());
        }
        this.textWord.setText("");
    }
}
