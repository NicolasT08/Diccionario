package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainView extends JFrame{

    private JTabbedPane tabs;
    private WordController controller;
    private DeletePanel deletePanel;
    private FindPanel findPanel;

    public MainView(){
        super("Minecraft Dictionary");
        this.controller = new WordController();
        this.deletePanel = new DeletePanel(controller);
        this.findPanel = new FindPanel(controller);
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


        this.tabs.addTab("Buscar", this.findPanel);
        this.tabs.addTab("Borrar",null,this.deletePanel);
    }
}
