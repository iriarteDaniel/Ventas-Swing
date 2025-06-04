package vista;


import controlador.ControladorCliente;
import controlador.ControladorProducto;
import controlador.ControladorVentas;

import javax.swing.*;
import java.awt.*;

import java.util.HashMap;

public class MenuCrearVenta extends JPanel {
    private final ControladorVentas controlVentas = new ControladorVentas();
    private final ControladorCliente controlCliente = new ControladorCliente();
    private final ControladorProducto controlProducto = new ControladorProducto();

    JPanel eastPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JTable table;

    private int idCliente = 0;
    private HashMap<Integer, Integer> idProductos = new HashMap<>();
    private int idVenta = 0;

    JTextField clienteField;
    JTextField prodField;
    JTextField cantField;
    JTextField idField;

    public MenuCrearVenta(JPanel container) {
        setLayout(null);
        eastPanel.setLayout(new GridLayout(17, 1, 0, 10));
        westPanel.setLayout(new BorderLayout());

        westPanel.setBounds(0,0,400,600);
        eastPanel.setBounds(400,0,400,600);

        eastPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        crearTabla();

        eastPanel.add(new JLabel("Ventas Swing"));
        eastPanel.add(new JLabel("Gestion de Ventas"));

        JLabel clienteText = new JLabel("CI del Cliente");
        clienteField = new JTextField();
        JButton clienteBtn = new JButton("Guardar");
        clienteBtn.addActionListener(e -> {
            guardarCliente(clienteField);
            refreshWestPanel();
        });

        JLabel prodText = new JLabel("Numero de Serie del Producto");
        prodField = new JTextField();
        JLabel cantText = new JLabel("Cant.");
        cantField = new JTextField();
        JButton prodBtn = new JButton("Guardar");
        prodBtn.addActionListener(e -> {
            guardarProducto(prodField, cantField);
            refreshWestPanel();
        });

        JLabel idText = new JLabel("ID de la venta");
        idField = new JTextField();
        JButton idBtn = new JButton("Guardar");
        idBtn.addActionListener(e -> {
            guardarId(idField);
            refreshWestPanel();
        });

        JButton guardarBtn = new JButton("Finalizar Venta");
        guardarBtn.addActionListener(e -> {
            guardarVenta();
            refreshWestPanel();
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel4");
        });

        JButton borrar = new JButton("Borrar");
        borrar.addActionListener(e -> {
            delete();
        });

        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel4");
        });

        eastPanel.add(clienteText);
        eastPanel.add(clienteField);
        eastPanel.add(clienteBtn);
        eastPanel.add(prodText);
        eastPanel.add(prodField);
        eastPanel.add(cantText);
        eastPanel.add(cantField);
        eastPanel.add(prodBtn);
        eastPanel.add(idText);
        eastPanel.add(idField);
        eastPanel.add(idBtn);
        eastPanel.add(guardarBtn);
        eastPanel.add(borrar);
        eastPanel.add(volver);

        add(westPanel);
        add(eastPanel);

        setVisible(true);
    }

    public Object[][] obtenerData(){
        Object[][] data = new Object[this.idProductos.size()+1][5];

        String nombreCliente;
        if (idCliente == 0){
            nombreCliente = "Cliente";
        } else{
            nombreCliente = controlCliente.obtenerPorID(idCliente).getNombre();
        }


        data[0][0] = nombreCliente;
        data[0][4] = idVenta;

        final int[] i = {0};
        idProductos.forEach((id, cant)->{
            String nombreProducto = controlProducto.obtenerPorID(id).getNombre();
            data[i[0]][1] = nombreProducto;
            data[i[0]][2] = id;
            data[i[0]][3] = cant;
            i[0]++;
        });

        return data;
    }

    private void crearTabla(){
        String[] clientColumnNames = {"Cliente", "Producto", "ID Producto", "Cantidad", "ID"};

        table = new JTable(obtenerData(), clientColumnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        westPanel.add(scrollPane, BorderLayout.CENTER);
        System.out.println("table created");
    }

    public void delete(){
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();
        System.out.println(row);
        System.out.println(column);

        if(row == -1){
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna celda");
            return;
        }

        switch (column) {
            case 0:
                this.idCliente = 0;
                break;
            case 1, 2, 3:
                try{
                    String idProducto = table.getValueAt(row, 2).toString();
                    this.idProductos.remove(Integer.parseInt(idProducto));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                this.idVenta = 0;
                break;
        }

        refreshWestPanel();
    }

    private void guardarCliente(JTextField textField) {
        String idCliente = textField.getText();
        if (idCliente.isBlank()) {
            JOptionPane.showMessageDialog(null, "El CI del cliente no puede quedar en blanco");
            return;
        }
        try {
            this.idCliente = Integer.parseInt(idCliente);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"El CI debe ser un numero");
        }
    }

    private void guardarProducto(JTextField prodField, JTextField cantField){
        String prod = prodField.getText();
        String cant = cantField.getText();

        if (prod.isBlank()) {
            JOptionPane.showMessageDialog(null, "El ID del producto no puede quedar en blanco");
            return;
        }
        if (cant.isBlank()) {
            JOptionPane.showMessageDialog(null, "La cantidad no puede quedar en blanco");
            return;
        }

        Integer prodInt;
        try {
            prodInt = Integer.parseInt(prod);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El id del producto debe ser un numero");
            return;
        }

        Integer cantInt;
        try {
            cantInt = Integer.parseInt(cant);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La cantidad del producto debe ser un numero");
            return;
        }

        try {
            this.idProductos.put(prodInt, cantInt);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"El Id y la cantidad deben ser numeros");
        }
    }

    private void guardarId(JTextField textField){
        String text = textField.getText();
        if (text.isBlank()) {
            JOptionPane.showMessageDialog(null, "El ID de la venta no puede quedar en blanco");
            return;
        }

        int idVenta;
        try {
            idVenta = Integer.parseInt(text);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "El id debe ser un numero");
            return;
        }

        this.idVenta = idVenta;
    }

    private void guardarVenta(){
        try{
            controlVentas.guardarVenta(this.idCliente, this.idProductos, this.idVenta);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void refreshEastPanel() {
        clienteField.setText("");
        prodField.setText("");
        cantField.setText("");
        idField.setText("");

        idCliente = 0;
        idProductos = new HashMap<>();
        idVenta = 0;

        eastPanel.revalidate();
        eastPanel.repaint();
    }

    public void refreshWestPanel(){
        westPanel.removeAll();
        crearTabla();
        westPanel.revalidate();
        westPanel.repaint();
    }
}
