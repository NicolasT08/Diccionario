package co.edu.uptc.view;

import co.edu.uptc.controller.WordController;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainView extends JFrame{

    private JTabbedPane tabs;
    private AddPanel add;
    private WordController controller;
    private DeletePanel deletePanel;
    private UpdatePanel updatePanel;
    private ListPanel listPanel;
    private FindPanel findPanel;


    public MainView(){
        super("Minecraft Dictionary");
        this.controller = new WordController();
        this.deletePanel = new DeletePanel(controller);
        this.findPanel = new FindPanel(controller);
        this.updatePanel = new UpdatePanel(controller);
        this.add = new AddPanel(controller);
        this.listPanel = new ListPanel(controller);
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





        this.tabs.addTab("Añadir", null, this.add);
        this.tabs.addTab("Buscar", null,this.findPanel);
        this.tabs.addTab("Actualizar",null,this.updatePanel);
        this.tabs.addTab("Listar", null, this.listPanel);
        this.tabs.addTab("Borrar",null,this.deletePanel);

    }

}
