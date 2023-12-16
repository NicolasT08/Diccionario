package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JTabbedPane tabs;
    private WordController controller;

    public MainView(){
        super("Minecraft Dictionary");
        this.controller = new WordController();
    }

    public void initialize(){
        this.setSize( 850, 600);
        this.setLayout(new BorderLayout(10,100));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        this.setTabs();

        this.add(this.tabs, BorderLayout.CENTER);
    }

    public void setTabs(){
        this.tabs = new JTabbedPane();
        this.tabs.setTabPlacement(SwingConstants.LEFT);
        this.tabs.setBorder( BorderFactory.createEmptyBorder(25,5,25,50));
        JPanel jp = new JPanel();
        jp.setBackground( new Color(120,120,30));

        this.tabs.addTab("Actualizar", null, jp);
    }
}
