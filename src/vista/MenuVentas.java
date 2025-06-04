package vista;

import controlador.ControladorVentas;
import modelo.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MenuVentas extends JPanel {
    private final ControladorVentas controlVentas = new ControladorVentas();
    JPanel eastPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JTable table;
    Object[][] tableData = obtenerData(new ArrayList(controlVentas.obtener().values()));

    LocalDate fechaFiltro = null;
    String nombreFiltro = null;
    public MenuVentas(JPanel container, DetallesVenta detalles) {
        setLayout(null);
        eastPanel.setLayout(new GridLayout(10, 1, 0, 10));
        westPanel.setLayout(new BorderLayout());

        westPanel.setBounds(0,0,400,600);
        eastPanel.setBounds(400,0,400,600);

        eastPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        crearTabla();

        eastPanel.add(new JLabel("Ventas Swing"));
        eastPanel.add(new JLabel("Gestion de Ventas"));

        JButton crear = new JButton("Crear");
        crear.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel8");
        });

        JButton detallesBtn = new JButton("Detalles");
        detallesBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna venta");
                return;
            }

            int id = (int) table.getValueAt(row, 0);
            Venta venta = controlVentas.obtenerPorID(id);

            detalles.setVenta(venta);

            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel10");
        });

        JButton anular = new JButton("Anular");
        anular.addActionListener(e -> {
            anularVenta();
        });

        JButton listarAnulados = new JButton("Listar Anulados");
        listarAnulados.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel9");
        });

        JButton filtrarFecha = new JButton("Filtrar por Fecha");
        filtrarFecha.addActionListener(e -> {
            filtrarFecha();
        });

        JButton filtrarCliente = new JButton("Filtrar por Cliente");
        filtrarCliente.addActionListener(e -> {
            filtrarCliente();
        });

        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel1");
        });

        eastPanel.add(crear);
        eastPanel.add(detallesBtn);
        eastPanel.add(anular);
        eastPanel.add(listarAnulados);
        eastPanel.add(filtrarCliente);
        eastPanel.add(filtrarFecha);
        eastPanel.add(volver);

        add(westPanel);
        add(eastPanel);

        setVisible(true);
    }

    public Object[][] obtenerData(ArrayList<Venta> ventas){
        Object[][] data = new Object[ventas.size()][4];

        int[] i = {0};
        ventas.forEach(venta->{
            if(venta.getAnulado()){
                return;
            }
            if(fechaFiltro != null){
                if (!fechaFiltro.equals(venta.getFecha())){
                    return;
                }
            }
            if (nombreFiltro != null){
                if (!nombreFiltro.equals(venta.getCliente().getNombre())){
                    return;
                }
            }
            data[i[0]][0] = venta.getId();
            data[i[0]][1] = venta.getCliente().getNombre();
            data[i[0]][2] = venta.getFecha().toString();
            i[0]++;
        });

        return data;
    }

    private void crearTabla(){
        String[] clientColumnNames = {"ID", "Cliente", "Fecha"};

        table = new JTable(new DefaultTableModel(tableData, clientColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        westPanel.add(scrollPane, BorderLayout.CENTER);
        System.out.println("table created");
    }

    private void anularVenta(){
        int row = table.getSelectedRow();
        System.out.println(row);

        if(row == -1){
            JOptionPane.showMessageDialog(null,"No se selecciono ninguna venta");
            return;
        }

        int id = (int) table.getValueAt(row, 0);
        controlVentas.anularVenta(id);
        refreshTable();
    }

    private void filtrarFecha(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Elija la fecha: "));
        JComboBox<Integer> dia = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            dia.addItem(i);
        }

        JComboBox<Integer> mes = new JComboBox();
        for (int i = 1; i <= 12; i++) {
            mes.addItem(i);
        }

        JComboBox<Integer> anho = new JComboBox();
        for (int i = 1900; i <= 2100; i++) {
            anho.addItem(i);
        }

        panel.add(dia);
        panel.add(mes);
        panel.add(anho);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Filtrar por fecha", JOptionPane.YES_OPTION);

        if(resultado == JOptionPane.YES_OPTION){
            try {
                fechaFiltro = LocalDate.of((int) anho.getSelectedItem(), (int) mes.getSelectedItem(), (int) dia.getSelectedItem());
                System.out.println(fechaFiltro);
                refreshTable();

            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Fecha Invalida");
            }
        }else {
            fechaFiltro = null;
        }

    }

    private void filtrarCliente(){
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente", "Filtrar por cliente", JOptionPane.PLAIN_MESSAGE);
        this.nombreFiltro = nombre;
        refreshTable();
        System.out.println(nombreFiltro);
    }

    public void refreshTable() {
        westPanel.removeAll();
        tableData = obtenerData(new ArrayList<Venta>(controlVentas.obtener().values()));
        crearTabla();
        westPanel.revalidate();
        westPanel.repaint();
    }

    public void refreshAll(){
        this.fechaFiltro = null;
        this.nombreFiltro = null;
        refreshTable();
    }
}
