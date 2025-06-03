package vista;

import controlador.ControladorCliente;

import javax.swing.*;
import java.awt.*;

public class CrearVenta extends JPanel {
    private final ControladorCliente controlClientes = new ControladorCliente();
    JPanel eastPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JTable table;
    public CrearVenta(JPanel container) {
        setLayout(null);
        eastPanel.setLayout(new GridLayout(6, 1, 0, 0));
        westPanel.setLayout(new BorderLayout());

        westPanel.setBounds(0,0,400,600);
        eastPanel.setBounds(400,0,400,600);

        crearTabla();

        eastPanel.add(new JLabel("Ventas CLI"));
        eastPanel.add(new JLabel("Gestion de Clientes"));

        JButton crear = new JButton("Crear");
        crear.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel5");
        });

        JButton borrar = new JButton("Borrar");
        borrar.addActionListener(e -> {
            delete();
        });

        JButton editar = new JButton("Editar");
        editar.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel6");
        });

        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel1");
        });

        eastPanel.add(crear);
        eastPanel.add(borrar);
        eastPanel.add(editar);
        eastPanel.add(volver);

        add(westPanel);
        add(eastPanel);

        setVisible(true);
    }

    public Object[][] obtenerData(){
        var clientes = controlClientes.obtener();

        Object[][] data = new Object[clientes.size()][4];

        final int[] i = {0};
        clientes.forEach((clave, valor)->{
            data[i[0]][0] = valor.getNombre();
            data[i[0]][1] = valor.getEmail();
            data[i[0]][2] = valor.getEdad();
            data[i[0]][3] = valor.getNumDocumento();
            i[0]++;
        });

        return data;
    }

    private void crearTabla(){
        String[] clientColumnNames = {"Nombre", "Email", "Edad", "CI"};

        table = new JTable(obtenerData(), clientColumnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        westPanel.add(scrollPane, BorderLayout.CENTER);
        System.out.println("table created");
    }

    public void delete(){
        int row = table.getSelectedRow();
        System.out.println(row);

        int ci = (int) table.getValueAt(row, 3);
        controlClientes.eliminar(ci);
        refresh();
    }

    public void refresh() {
        westPanel.removeAll();
        crearTabla();
        westPanel.revalidate();
        westPanel.repaint();
    }
}
