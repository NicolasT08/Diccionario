package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class DeletePanel extends JPanel implements ActionListener{

    private WordController controller;
    private JTextField textWord;
    private JButton sendButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel rightLabel;
    private boolean successfully;
    private Image background;
    private Font customfont;

    DeletePanel(WordController controller, Font customfont){
        this.controller = controller;
        this.customfont = customfont;
        successfully = false;
        setDeleteTable();
    }

    private void setDeleteTable(){
        this.setLayout(new GridLayout(1,2));
        this.setBackground("./imgs/Libro abierto.png");

        this.setLeftPanel();

        rightPanel = new JPanel(new GridBagLayout());
        rightLabel = new JLabel();
        rightLabel.setFont( customfont );
        rightPanel.add(rightLabel);
        rightPanel.setOpaque(false);

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
        labelWord.setFont( customfont );
        this.textWord = new JTextField();
        this.textWord.setPreferredSize( new Dimension(190, 30) );
        this.textWord.setFont( customfont );

        this.sendButton = new JButton("Eliminar");
        this.sendButton.addActionListener( this );
        this.sendButton.setBackground(new Color(110, 110, 110));
        this.sendButton.setForeground(Color.WHITE);
        this.sendButton.setFont( customfont );

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
                    UIManager.put("OptionPane.messageFont", customfont);
                    UIManager.put("OptionPane.buttonFont", customfont);
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
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                rightLabel.setText("");
            }
        },2500);
    }

    private void updateRightLabel() {
        if (successfully) {
            this.rightLabel.setText("<html>La palabra " + textWord.getText() + "<br> ha sido borrada satisfactoriamente</html>");
        }else {
            this.rightLabel.setText("<html>La palabra " + textWord.getText() + "<br> no pudo ser eliminada</html>");
        }
        this.textWord.setText("");
    }
}
