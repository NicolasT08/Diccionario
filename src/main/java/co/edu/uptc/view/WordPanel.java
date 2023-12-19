package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a Word Object as a Panel
 * Showing the word, the meaning and the translate.
 * @author Nicolas Sarmiento
 */
public class WordPanel extends JPanel {

    /**
     * Create a new panel design for a word. To alocate the word itself,
     * the meaning and the English translation.
     * @param id String that contains the word.
     * @param meaning The meaning of the word
     * @param translate The English translation
     * @param font The custom font
     */
    public WordPanel(String id, String meaning, String translate, Font font) {
        this.setLayout( new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setOpaque(false);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JTextArea idLabel = new JTextArea(id);
        gbc.gridy = 0;
        idLabel.setMinimumSize(new Dimension(330, 20));
        idLabel.setOpaque(false);
        idLabel.setLineWrap(true);
        idLabel.setWrapStyleWord(true);
        idLabel.setBackground(new Color(0, 0, 0, 0));
        idLabel.setFont( font );
        this.add( idLabel , gbc );

        JTextArea meaningLabel = new JTextArea( "Definición: " + meaning );
        gbc.gridy = 1;
        meaningLabel.setMinimumSize(new Dimension(330, 50));
        meaningLabel.setOpaque(false);
        meaningLabel.setLineWrap(true);
        meaningLabel.setWrapStyleWord(true);
        meaningLabel.setBackground(new Color(0, 0, 0, 0));
        meaningLabel.setFont( font );
        this.add( meaningLabel , gbc );

        JTextArea translateLabel = new JTextArea( "Traducción: " + translate );
        gbc.gridy = 2;
        translateLabel.setMinimumSize(new Dimension(330, 20));
        translateLabel.setOpaque(false);
        translateLabel.setLineWrap(true);
        translateLabel.setWrapStyleWord(true);
        translateLabel.setBackground(new Color(0, 0, 0, 0));
        translateLabel.setFont( font );
        this.add( translateLabel , gbc );
    }
}
