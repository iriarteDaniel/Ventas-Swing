package vista;

import controlador.ControladorProducto;
import modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MenuProductos extends JPanel{
    private final ControladorProducto controlProductos = new ControladorProducto();
    JPanel eastPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JTable table;

    Object[][] tableData = obtenerData(new ArrayList<Producto>(controlProductos.obtener().values()));
    boolean reversedNombre = false;
    boolean reversedCategoria = false;
    boolean reversedId = false;

    public MenuProductos(JPanel container, EditarProducto editarProducto) {
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
        eastPanel.add(new JLabel("Gestion de Productos"));


        // Buttons
        JButton crear = new JButton("Crear");
        crear.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel7");
        });

        JButton borrar = new JButton("Borrar");
        borrar.addActionListener(e -> {
            delete();
        });

        JButton editar = new JButton("Editar");
        editar.addActionListener(e -> {
            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(null, "No se selecciono ningun producto");
                return;
            }

            String nombre = table.getValueAt(row, 0).toString();
            String categoria = table.getValueAt(row, 1).toString();
            int nroSerie = (int) table.getValueAt(row, 2);
            editarProducto.setFields(nombre, categoria, nroSerie);

            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel11");
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

    public Object[][] obtenerData(ArrayList<Producto> prods){
        Object[][] data = new Object[prods.size()][4];

        final int[] i = {0};
        prods.forEach((valor)->{
            data[i[0]][0] = valor.getNombre();
            data[i[0]][1] = valor.getCategoria();
            data[i[0]][2] = valor.getNroDeSerie();
            i[0]++;
        });

        return data;
    }

    private void crearTabla(){
        String[] prodsColumnNames = {"Nombre", "Categoría", "Nro. Serie"};

        table = new JTable(new DefaultTableModel(tableData, prodsColumnNames) {
            // Evita que las celdas sean editables
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
                    tableData = obtenerData(controlProductos.getSortedByNameList(reversedNombre));
                    controlProductos.getSortedByNameList(reversedNombre).forEach(System.out::println);
                    reversedNombre = !reversedNombre;
                }

                if (columnName.equals("Categoría")){
                    tableData = obtenerData(controlProductos.getSortedByCategoriaList(reversedCategoria));
                    controlProductos.getSortedByCategoriaList(reversedCategoria).forEach(System.out::println);
                    reversedCategoria = !reversedCategoria;
                }

                if (columnName.equals("Nro. Serie")){
                    tableData = obtenerData(controlProductos.getSortedByIDList(reversedId));
                    controlProductos.getSortedByIDList(reversedId).forEach(System.out::println);
                    reversedId = !reversedId;
                }

                westPanel.removeAll();
                crearTabla();
                westPanel.revalidate();
                westPanel.repaint();
            }});

        westPanel.add(scrollPane, BorderLayout.CENTER);
        System.out.println("table created");
    }

    public void delete(){
        int row = table.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(null,"No se selecciono ningun producto");
            return;
        }
        System.out.println(row);

        int ci = (int) table.getValueAt(row, 2);

        controlProductos.eliminar(ci);

        refresh();
    }

    public void refresh() {
        westPanel.removeAll();
        tableData = obtenerData(new ArrayList<Producto>(controlProductos.obtener().values()));
        crearTabla();
        westPanel.revalidate();
        westPanel.repaint();
    }
}
