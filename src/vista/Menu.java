package vista;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class Menu extends JFrame{
    public Menu() {
        setTitle("Ventas Swing");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new CardLayout());

        MenuPrincipal menuPrincipal = new MenuPrincipal(mainPanel);

        CrearCliente crearCliente = new CrearCliente(mainPanel);
        EditarCliente editarCliente = new EditarCliente(mainPanel);
        CrearProducto crearProducto = new CrearProducto(mainPanel);
        MenuCrearVenta crearVenta = new MenuCrearVenta(mainPanel);
        ListaAnulados listaAnulados = new ListaAnulados(mainPanel);
        DetallesVenta detallesVenta = new DetallesVenta(mainPanel);
        MenuVentas menuVentas = new MenuVentas(mainPanel, detallesVenta);
        MenuClientes menuClientes = new MenuClientes(mainPanel, editarCliente);
        EditarProducto editarProducto = new EditarProducto(mainPanel);
        MenuProductos menuProductos = new MenuProductos(mainPanel, editarProducto);

        mainPanel.add(menuPrincipal, "Panel1");
        mainPanel.add(menuClientes, "Panel2");
        mainPanel.add(menuProductos, "Panel3");
        mainPanel.add(menuVentas, "Panel4");
        mainPanel.add(crearCliente, "Panel5");
        mainPanel.add(editarCliente, "Panel6");
        mainPanel.add(crearProducto, "Panel7");
        mainPanel.add(crearVenta, "Panel8");
        mainPanel.add(listaAnulados, "Panel9");
        mainPanel.add(detallesVenta, "Panel10");
        mainPanel.add(editarProducto, "Panel11");

        menuClientes.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                menuClientes.refresh();
            }
        });

        menuProductos.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                menuProductos.refresh();
            }
        });

        menuVentas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                menuVentas.refresh();
            }
        });

        crearCliente.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                crearCliente.refresh();
            }
        });

        editarCliente.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                editarCliente.refresh();
            }
        });

        crearProducto.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                crearProducto.refresh();
            }
        });

        crearVenta.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                crearVenta.refreshEastPanel();
                crearVenta.refreshWestPanel();
            }
        });

        listaAnulados.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                listaAnulados.refresh();
            }
        });

        detallesVenta.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                detallesVenta.refresh();
            }
        });

        add(mainPanel);

        setVisible(true);
    }

}
