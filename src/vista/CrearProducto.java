package vista;

import controlador.ControladorProducto;
import modelo.Producto;

import javax.swing.*;
import java.awt.*;

public class CrearProducto extends JPanel {
    private final ControladorProducto controlProd = new ControladorProducto();

    JTextField nombreField;
    JTextField categoriaField;
    JTextField nroSerieField;

    public CrearProducto(JPanel container){
        setLayout(new GridLayout(11, 1));

        JLabel Titulo = new JLabel("Crear Cliente");

        JLabel nombre = new JLabel("Nombre:");
        nombreField = new JTextField();

        JLabel email = new JLabel("Categoria:");
        categoriaField = new JTextField();

        JLabel edad = new JLabel("Numero de Serie:");
        nroSerieField = new JTextField();


        JButton create = new JButton("Crear");
        JButton back = new JButton("Volver");

        add(Titulo);
        add(nombre);
        add(nombreField);
        add(email);
        add(categoriaField);
        add(edad);
        add(nroSerieField);
        add(create);
        add(back);

        create.addActionListener(e -> {
            String nombreTexto = nombreField.getText();
            String categoriaTexto = categoriaField.getText();
            String nroSerieTexto = nroSerieField.getText();

            if(blank(nombreTexto, categoriaTexto, nroSerieTexto)){
                return;
            }

            try {
                Producto prod = new Producto(nombreTexto, categoriaTexto, Integer.parseInt(nroSerieTexto));
                controlProd.guardar(prod);
            } catch (Exception exc){
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }

            System.out.println(nombreTexto);
            System.out.println(categoriaTexto);
            System.out.println(nroSerieTexto);

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
            JOptionPane.showMessageDialog(null, "El categoría no puede quedar en blanco");
            return true;
        }
        if(nroSerieTexto.isBlank()){
            JOptionPane.showMessageDialog(null, "El numero de serie no puede quedar en blanco");
            return true;
        }
        return false;
    }

    public void refresh() {
        nombreField.setText("");
        categoriaField.setText("");
        nroSerieField.setText("");

        revalidate();
        repaint();
    }
}
