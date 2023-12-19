package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class creates a JPanel to remove words. It includes user
 * interface components to enter the word to delete and a button
 * to start the deletion process. The class communicates with a
 * WordController in order to perform word deletion.
 * @author Nicolas Tinjaca
 */

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

    /**
     * Set up the layout, background, and components of the Delete
     * Panel. The DeletePanel consists of two main panels: the
     * left panel, and the right panel
     */

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
     * Sets up the leftPanel with user input components, including a label, a text field
     * for entering the word to be deleted, and a button for confirming the deletion.
     * The leftPanel is configured with a GridBagLayout, and its background is set to be
     * transparent.
     */

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

    /**
     * Handles button click events for the sendButton. It checks if the
     * textWord field is empty and displays an error message. If the field
     * is not empty, it shows a confirmation dialog. If the user confirms deletion, it
     * calls the controller's deleteWord method, updates the rightLabel, and starts a
     * timer to clear the message after a certain delay.
     *
     * @param e ActionEvent object representing the button click event.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == sendButton){
            if( textWord.getText().length() == 0 ){
                this.rightLabel.setText("Porfavor digita una palabra");
                this.textWord.setText("");
            } else if ( controller.findWord( textWord.getText() )[0].equals("") ) {
                String[] response = controller.findWord(textWord.getText());
                this.rightLabel.setText( "<html>La palabra " + textWord.getText() + " debe contener <br>solamente caracteres alfabéticos!" );
            }else {
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

    /**
     * Updates the rightLabel message in the right panel with a
     * success or failure message depending on the result of the
     * delete word process.
     */

    private void updateRightLabel() {
        if (successfully) {
            this.rightLabel.setText("<html>La palabra " + textWord.getText() + "<br> ha sido borrada satisfactoriamente</html>");
        }else {
            this.rightLabel.setText("<html>La palabra " + textWord.getText() + "<br> no pudo ser eliminada</html>");
        }
        this.textWord.setText("");
    }
}
