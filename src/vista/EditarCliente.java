package vista;

import controlador.ControladorCliente;
import modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class EditarCliente extends JPanel {
    private final ControladorCliente controlClientes = new ControladorCliente();

    JTextField nombreField;
    JTextField emailField;
    JTextField edadField;
    JTextField ciField;

    public EditarCliente(JPanel container){
        setLayout(new GridLayout(11, 1));

        JLabel Titulo = new JLabel("Crear Cliente");

        JLabel nombre = new JLabel("Nombre:");
        nombreField = new JTextField();

        JLabel email = new JLabel("Email:");
        emailField = new JTextField();

        JLabel edad = new JLabel("Edad:");
        edadField = new JTextField();

        JLabel ci = new JLabel("Cedula de Identidad:");
        ciField = new JTextField();
        ciField.setEditable(false);

        JButton create = new JButton("Crear");
        JButton back = new JButton("Volver");

        add(Titulo);
        add(nombre);
        add(nombreField);
        add(email);
        add(emailField);
        add(edad);
        add(edadField);
        add(ci);
        add(ciField);
        add(create);
        add(back);

        create.addActionListener(e -> {
            String nombreTexto = nombreField.getText();
            String emailTexto = emailField.getText();
            String edadTexto = edadField.getText();
            String ciTexto = ciField.getText();

            if(blank(nombreTexto, emailTexto, edadTexto, ciTexto)){
                return;
            }

            try {
                Cliente cliente = new Cliente(nombreTexto, emailTexto, Integer.parseInt(edadTexto), Integer.parseInt(ciTexto));
                controlClientes.guardar(cliente);
            } catch (Exception exc){
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }

            System.out.println(nombreTexto);
            System.out.println(emailTexto);
            System.out.println(edadTexto);
            System.out.println(ciTexto);

            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel2");
        });

        back.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel2");
        });

        setVisible(true);
    }

    private boolean blank(String nombreTexto, String emailTexto, String edadTexto, String ciTexto){
        if(nombreTexto.isBlank()){
            JOptionPane.showMessageDialog(null, "El nombre no puede quedar en blanco");
            return true;
        }
        if(emailTexto.isBlank()){
            JOptionPane.showMessageDialog(null, "El email no puede quedar en blanco");
            return true;
        }
        if(edadTexto.isBlank()){
            JOptionPane.showMessageDialog(null, "El edad no puede quedar en blanco");
            return true;
        }
        if(ciTexto.isBlank()){
            JOptionPane.showMessageDialog(null, "El ci no puede quedar en blanco");
            return true;
        }
        return false;
    }

    public void refresh() {
        nombreField.setText("");
        emailField.setText("");
        edadField.setText("");
        ciField.setText("");

        revalidate();
        repaint();
    }
}
