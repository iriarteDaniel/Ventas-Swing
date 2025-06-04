package vista;

import controlador.ControladorCliente;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuClientes extends JPanel {
    private final ControladorCliente controlClientes = new ControladorCliente();
    JPanel eastPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JTable table;
    Object[][] tableData = obtenerData(new ArrayList<Cliente>(controlClientes.obtener().values()));
    boolean reversedNombre = false;
    boolean reversedEmail = false;
    boolean reversedEdad = false;
    boolean reversedCi = false;

    public MenuClientes(JPanel container, EditarCliente editarPanel) {
        setLayout(null);

        // Panels
        eastPanel.setLayout(new GridLayout(7, 1, 0, 10));
        westPanel.setLayout(new BorderLayout());

        westPanel.setBounds(0,0,400,600);
        eastPanel.setBounds(400,0,400,600);

        eastPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        // Table
        crearTabla();

        // Labels
        eastPanel.add(new JLabel("Ventas Swing"));
        eastPanel.add(new JLabel("Gestion de Clientes"));


        // Buttons
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
            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(null, "No se selecciono ningun cliente");
                return;
            }

            String nombre = table.getValueAt(row, 0).toString();
            String email = table.getValueAt(row, 1).toString();
            int edad = (int) table.getValueAt(row, 2);
            int ci = (int) table.getValueAt(row, 3);
            editarPanel.setFields(nombre, email, edad, ci);

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

    public Object[][] obtenerData(ArrayList<Cliente> clientes) {
        Object[][] data = new Object[clientes.size()][4];

        final int[] i = {0};
        clientes.forEach(( valor)->{
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

        table = new JTable(new DefaultTableModel(tableData, clientColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                String columnName = table.getColumnName(column);

                System.out.println("Clic en la columna: " + columnName);

                if (columnName.equals("Nombre")){
                    tableData = obtenerData(controlClientes.getSortedByNameList(reversedNombre));
                    controlClientes.getSortedByNameList(reversedNombre).forEach(System.out::println);
                    reversedNombre = !reversedNombre;
                }

                if (columnName.equals("Email")){
                    tableData = obtenerData(controlClientes.getSortedByEmailList(reversedEmail));
                    controlClientes.getSortedByEmailList(reversedEmail).forEach(System.out::println);
                    reversedEmail = !reversedEmail;
                }

                if (columnName.equals("Edad")){
                    tableData = obtenerData(controlClientes.getSortedByEdadList(reversedEdad));
                    controlClientes.getSortedByEdadList(reversedEdad).forEach(System.out::println);
                    reversedEdad = !reversedEdad;
                }

                if (columnName.equals("CI")){
                    tableData = obtenerData(controlClientes.getSortedByCiList(reversedCi));
                    controlClientes.getSortedByCiList(reversedCi).forEach(System.out::println);
                    reversedCi = !reversedCi;
                }
                westPanel.removeAll();
                crearTabla();
                westPanel.revalidate();
                westPanel.repaint();
            }
        });

        westPanel.add(scrollPane, BorderLayout.CENTER);
        System.out.println("table created");
    }

    public void delete(){
        int row = table.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(null,"No se selecciono ningun cliente");
            return;
        }
        System.out.println(row);

        int ci = (int) table.getValueAt(row, 3);
        controlClientes.eliminar(ci);
        refresh();
    }

    public void refresh() {
        westPanel.removeAll();
        tableData = obtenerData(new ArrayList<Cliente>(controlClientes.obtener().values()));
        crearTabla();
        westPanel.revalidate();
        westPanel.repaint();
    }
}
