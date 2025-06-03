package vista;

import modelo.Venta;

import javax.swing.*;
import java.awt.*;

public class DetallesVenta extends JPanel {
    private Venta venta;
    private JPanel eastPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JTable table;
    public DetallesVenta(JPanel container) {
        setLayout(null);
        eastPanel.setLayout(new GridLayout(6, 1, 0, 0));
        westPanel.setLayout(new BorderLayout());

        eastPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        westPanel.setBounds(0,0,400,600);
        eastPanel.setBounds(400,0,400,600);

        crearTabla();

        eastPanel.add(new JLabel("Ventas Swing"));
        eastPanel.add(new JLabel("Detalles Venta"));

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

    public void setVenta(Venta venta){
        this.venta = venta;
    }

    public Object[][] obtenerData(){
        if (venta == null){
            return new Object[0][5];
        }
        Object[][] data = new Object[venta.getProductos().size()][5];

        data[0][0] = venta.getId();
        data[0][1] = venta.getCliente().getNombre();

        int[] i = {0};
        venta.getProductos().forEach((prod, cant) -> {
            data[i[0]][2] = prod.getNombre();
            data[i[0]][3] = cant;
            i[0]++;
        });

        data[0][4] = venta.getFecha();

        return data;
    }

    private void crearTabla(){
        String[] clientColumnNames = {"ID", "Cliente", "Productos", "Cantidad", "Fecha"};

        table = new JTable(obtenerData(), clientColumnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        westPanel.add(scrollPane, BorderLayout.CENTER);
        System.out.println("table created");
    }

    public void refresh() {
        westPanel.removeAll();
        crearTabla();
        westPanel.revalidate();
        westPanel.repaint();
    }
}
