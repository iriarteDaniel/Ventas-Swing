package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JPanel {
    JPanel eastPanel = new JPanel();
    JPanel westPanel = new JPanel();
    public MenuPrincipal(JPanel container) {
        setLayout(null);

        // Panels
        westPanel.setLayout(new GridLayout(3, 1, 0, 10));
        eastPanel.setLayout(new GridLayout(2, 1, 0, 0));

        westPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(80, 75, 100, 75));

        westPanel.setBounds(0,0,400,600);
        eastPanel.setBounds(400,0,400,600);

        eastPanel.setBackground(Color.GRAY);


        // Labels
        JLabel titulo = new JLabel("Ventas Swing");
        titulo.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel menuText = new JLabel("Menu Principal");
        menuText.setFont(new Font("Arial", Font.BOLD, 30));

        eastPanel.add(titulo);
        eastPanel.add(menuText);


        // Buttons
        JButton gestionClientes = new JButton("Gestion de Clientes");
        gestionClientes.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel2");
        });

        JButton gestionProductos = new JButton("Gestion de Productos");
        gestionProductos.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel3");
        });

        JButton gestionVentas = new JButton("Gestion de Ventas");
        gestionVentas.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel4");
        });

        westPanel.add(gestionClientes);
        westPanel.add(gestionProductos);
        westPanel.add(gestionVentas);

        add(westPanel);
        add(eastPanel);

        setVisible(true);
    }

}
