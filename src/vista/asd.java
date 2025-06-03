/*public MenuClientes(JPanel container) {
        setBackground(Color.ORANGE);
        add(new JLabel("Este es el Panel 2"));

        JButton backButton = new JButton("Volver al Panel 1");
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel1");
        });

        add(backButton);
    }
    package vista;

import javax.swing.*;
import java.awt.*;

public class MenuClientes extends JPanel {
    public MenuClientes(){
        JPanel eastPanel = new JPanel();
        JPanel panelOeste = new JPanel();

        panelOeste.setBounds(0,0,400,600);
        eastPanel.setBounds(400,0,400,600);

        panelOeste.setLayout(new GridLayout(3, 1, 0, 75));

        panelOeste.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(200, 100, 0, 100));



        JButton gestionClientes = new JButton("Gestion de Clientes");
        gestionClientes.setFocusable(false);
        gestionClientes.addActionListener(e -> {
            System.out.println("Gestion de Clientes");
        });


        JLabel label = new JLabel("Gestion Clientes");
        label.setFont(new Font("Sans-Serif", Font.PLAIN, 24));

        add(panelOeste);
        add(eastPanel);


        panelOeste.add(gestionClientes);

        eastPanel.add(label);

        setVisible(true);
    }
}

    */