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
    private Font customFont;

    public MainView(){
        super("Minecraft Dictionary");
        this.setFont();
        this.controller = new WordController();
        this.deletePanel = new DeletePanel(controller, customFont);
        this.findPanel = new FindPanel(controller, customFont);
        this.updatePanel = new UpdatePanel(controller, customFont);
        this.add = new AddPanel(controller, customFont);
        this.listPanel = new ListPanel(controller, customFont);
    }

    public void initialize(){
        this.setSize( 950, 700);
        this.setResizable(false);
        this.setLayout(new BorderLayout(10,100));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setTabs();

        this.add(this.tabs, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void setTabs(){
        UIManager.put("TabbedPane.selected", new Color(53, 215, 46));
        this.tabs = new JTabbedPane();
        this.tabs.setTabPlacement(SwingConstants.LEFT);
        this.tabs.setBorder( BorderFactory.createEmptyBorder(25,5,25,50));
        this.tabs.setFont( customFont );

        this.tabs.addTab("Añadir", this.add);
        this.tabs.addTab("Buscar",this.findPanel);
        this.tabs.addTab("Actualizar",this.updatePanel);
        this.tabs.addTab("Listar",  this.listPanel);
        this.tabs.addTab("Borrar",this.deletePanel);

        this.tabs.setBackgroundAt( 0, new Color(232, 250, 221));
        this.tabs.setBackgroundAt( 1, new Color(199, 199, 250));
        this.tabs.setBackgroundAt( 2, new Color(251, 204, 255));
        this.tabs.setBackgroundAt( 3, new Color(253, 236, 216));
        this.tabs.setBackgroundAt( 4, new Color(253, 223, 221));


    }

    public void setFont(){
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/pixels.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            this.customFont = customFont;
        } catch (IOException|FontFormatException e) {
            this.customFont = new Font( "Dialog", Font.PLAIN, 12);
        }
    }



}
