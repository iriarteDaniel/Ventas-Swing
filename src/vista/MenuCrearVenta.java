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
        JTextField clienteField = new JTextField();
        JButton clienteBtn = new JButton("Guardar");
        clienteBtn.addActionListener(e -> {
            guardarCliente(clienteField);
            refresh();
        });

        JLabel prodText = new JLabel("Numero de Serie del Producto");
        JTextField prodField = new JTextField();
        JLabel cantText = new JLabel("Cant.");
        JTextField cantField = new JTextField();
        JButton prodBtn = new JButton("Guardar");
        prodBtn.addActionListener(e -> {
            guardarProducto(prodField, cantField);
            refresh();
        });

        JLabel idText = new JLabel("ID de la venta");
        JTextField idField = new JTextField();
        JButton idBtn = new JButton("Guardar");
        idBtn.addActionListener(e -> {
            guardarId(idField);
            refresh();
        });

        JButton guardarBtn = new JButton("Finalizar Venta");
        guardarBtn.addActionListener(e -> {
            guardarVenta();
            refresh();
        });

        JButton borrar = new JButton("Borrar");
        borrar.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel1");
        });

        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel1");
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
        Object[][] data = new Object[this.idProductos.size()+1][4];

        String nombreCliente;
        if (idCliente == 0){
            nombreCliente = "Cliente";
        } else{
            nombreCliente = controlCliente.obtenerPorID(idCliente).getNombre();
        }


        data[0][0] = nombreCliente;
        data[0][3] = idVenta;

        final int[] i = {0};
        idProductos.forEach((id, cant)->{
            String nombreProducto = controlProducto.obtenerPorID(id).getNombre();
            data[i[0]][1] = nombreProducto;
            data[i[0]][2] = cant;
            i[0]++;
        });

        return data;
    }

    private void crearTabla(){
        String[] clientColumnNames = {"Cliente", "Producto", "Cantidad", "ID"};

        table = new JTable(obtenerData(), clientColumnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        westPanel.add(scrollPane, BorderLayout.CENTER);
        System.out.println("table created");
    }

    public void delete(){
        /*int row = table.getSelectedRow();
        int column = table.getSelectedColumn();
        System.out.println(row);
        System.out.println(column);

        switch (column){
            case 1:
                this.idCliente = 0;
                break;
            case 2:
                this.idProductos.remove();
        }

        refresh();*/
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

    public void refresh() {
        westPanel.removeAll();
        crearTabla();
        westPanel.revalidate();
        westPanel.repaint();
    }
}
