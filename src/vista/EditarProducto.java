package vista;

import controlador.ControladorCliente;
import controlador.ControladorProducto;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;

import javax.swing.*;
import java.awt.*;

public class EditarProducto extends JPanel {
    ControladorProducto controlProducto = new ControladorProducto();
    JTextField nombreField;
    JTextField categoriaField;
    JTextField nroSerieField;

    JLabel Titulo;
    JLabel nombre;
    JLabel categoria;
    JLabel nroSerie;

    JButton create;
    JButton back;

    public EditarProducto(JPanel container){
        setLayout(new GridLayout(11, 1));

        Titulo = new JLabel("Crear Cliente");

        nombre = new JLabel("Nombre:");
        nombreField = new JTextField();

        categoria = new JLabel("Categoría:");
        categoriaField = new JTextField();

        nroSerie = new JLabel("Nro de Serie:");
        nroSerieField = new JTextField();
        nroSerieField.setEditable(false);

        create = new JButton("Crear");
        back = new JButton("Volver");

        create.addActionListener(e -> {
            String nombreTexto = nombreField.getText();
            String categoriaTexto = categoriaField.getText();
            String nroSerieTexto = nroSerieField.getText();

            if(blank(nombreTexto, categoriaTexto, nroSerieTexto)){
                return;
            }

            try {
                Producto producto = new Producto(nombreTexto, categoriaTexto, Integer.parseInt(nroSerieTexto));
                controlProducto.guardar(producto);
            } catch (Exception exc){
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }

            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel3");
        });

        back.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel3");
        });

        setVisible(true);
    }

    private boolean blank(String nombreTexto, String categoriaTexto, String nroSerieTexto){
        if(nombreTexto.isBlank()){
            JOptionPane.showMessageDialog(null, "El nombre no puede quedar en blanco");
            return true;
        }
        if(categoriaTexto.isBlank()){
            JOptionPane.showMessageDialog(null, "La categoría no puede quedar en blanco");
            return true;
        }
        if(nroSerieTexto.isBlank()){
            JOptionPane.showMessageDialog(null, "El numero de serie no puede quedar en blanco");
            return true;
        }

        return false;
    }

    public void setFields(String nombre, String categoria, int nroSerie){
        refresh();

        this.nombreField.setText(nombre);
        this.categoriaField.setText(categoria);
        this.nroSerieField.setText(Integer.toString(nroSerie));
    }

    public void refresh() {
        removeAll();

        add(Titulo);
        add(nombre);
        add(nombreField);
        add(categoria);
        add(categoriaField);
        add(nroSerie);
        add(nroSerieField);
        add(create);
        add(back);

        revalidate();
        repaint();
    }
}
