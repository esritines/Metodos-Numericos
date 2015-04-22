package Vistas;

import Controladores.Imagenes;
import Controladores.ReproducirSonido;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class Registros extends javax.swing.JFrame implements MouseListener {

    private ReproducirSonido sonidoClic;
    private Imagenes imagen = new Imagenes(3);
    private static Font font = new Font("Courier", Font.BOLD, 46);

    private Inicio regresar;

    private JPanel panelPrincipal;

    private JPanel panel1;
    private JLabel recordsTitulo;

    private JPanel panel2;
    private JLabel records[][];
    private JPanel paneles[][];

    private JPanel panel3;
    private JLabel atras;

    public Registros() {
        initComponents();

        setTitle("Records");
        setVisible(false);

        setSize(200, 400);

        panel1 = new JPanel(new FlowLayout());
        panel1.setSize(200, 75);
        recordsTitulo = new JLabel("Records");
        recordsTitulo.setFont(font);
        panel1.add(recordsTitulo);

        panel2 = new JPanel();
        panel2.setSize(200, 250);
        panel2.setLayout(new GridLayout(6, 4));
        records = new JLabel[6][2];
        paneles = new JPanel[6][4];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                paneles[i][j] = new JPanel();          
                if (j == 1 || j == 2) {
                    paneles[i][j].setLayout(new FlowLayout());
                    records[i][j-1] = new JLabel("        ");
                    paneles[i][j].add(records[i][j-1]);
                }
                panel2.add(paneles[i][j]);
            }
        }

        panel3 = new JPanel(new FlowLayout());
        panel3.setSize(200, 75);
        atras = new JLabel("Atras");
        atras.addMouseListener(this);
        atras.setIcon(imagen.getImagen());
        atras.setHorizontalTextPosition(SwingConstants.CENTER);
        panel3.add(atras);

        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setSize(200, 400);

        panelPrincipal.add("North", panel1);
        panelPrincipal.add("Center", panel2);
        panelPrincipal.add("South", panel3);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(panelPrincipal);
    }

    public void regresar(Inicio regresar) {
        this.regresar = regresar;
    }

    public void insertarRegistro(ArrayList usuarios, ArrayList puntos) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    records[i][j].setText(usuarios.get(usuarios.size() - i - 1).toString());
                } else {
                    records[i][j].setText(puntos.get(usuarios.size() - i - 1).toString());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        sonidoClic = new ReproducirSonido(2);
        sonidoClic.getSonido().start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setVisible(false);
        regresar.setVisible(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
