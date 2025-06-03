package vista;

import controlador.ControladorCliente;
import controlador.ControladorVentas;
import modelo.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ListaAnulados extends JPanel {
    private final ControladorVentas controlVentas = new ControladorVentas();
    JPanel eastPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JTable table;
    Object[][] tableData = obtenerData(new ArrayList(controlVentas.obtener().values()));
    public ListaAnulados(JPanel container) {
        setLayout(null);
        eastPanel.setLayout(new GridLayout(7, 1, 0, 10));
        westPanel.setLayout(new BorderLayout());

        westPanel.setBounds(0,0,400,600);
        eastPanel.setBounds(400,0,400,600);

        eastPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        crearTabla();

        eastPanel.add(new JLabel("Ventas Swing"));
        eastPanel.add(new JLabel("Ventas Anuladas"));

        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.show(container, "Panel4");
        });

        eastPanel.add(volver);

        add(westPanel);
        add(eastPanel);

        setVisible(true);
    }
    public Object[][] obtenerData(ArrayList<Venta> ventas){
        Object[][] data = new Object[ventas.size()][4];

        int[] i = {0};
        ventas.forEach(venta->{
            if(!venta.getAnulado()){
                return;
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

    public void refresh() {
        westPanel.removeAll();
        tableData = obtenerData(new ArrayList<Venta>(controlVentas.obtener().values()));
        crearTabla();
        westPanel.revalidate();
        westPanel.repaint();
    }
}
