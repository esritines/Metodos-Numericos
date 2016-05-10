package Vistas;

import Controladores.Fuente;
import Controladores.Imagenes;
import Controladores.ReproducirSonido;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class Registros extends javax.swing.JFrame implements MouseListener {

    private Fuente fuente;
    private Font sizedFuente;

    private ReproducirSonido sonidoClic;

    private Inicio regresar;

    private JLabel panelPrincipal;

    private JLabel panel1;
    private JLabel recordsTitulo;
    private JLabel panel2;
    private JLabel records[][];
    private JLabel paneles[][];

    private JLabel panel3;
    private JLabel atras;

    public Registros() {
        initComponents();

        setTitle("Records");
        setVisible(false);

        fuente = new Fuente();
        sizedFuente = fuente.getFont().deriveFont(66f);
        
        panel1 = new JLabel();
        panel1.setLayout(new FlowLayout());
        recordsTitulo = new JLabel("Records");
        recordsTitulo.setForeground(Color.black);
        recordsTitulo.setFont(sizedFuente);
        panel1.add(recordsTitulo);

        panel2 = new JLabel();
        panel2.setLayout(new GridLayout(6, 4));
        records = new JLabel[6][2];
        paneles = new JLabel[6][4];

        sizedFuente = fuente.getFont().deriveFont(25f).deriveFont(Font.BOLD);
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                paneles[i][j] = new JLabel();          
                if (j == 1 || j == 2) {
                    paneles[i][j].setLayout(new FlowLayout());
                    records[i][j-1] = new JLabel("        ");
                    records[i][j-1].setFont(sizedFuente);
                    records[i][j-1].setForeground(Color.black);
                    paneles[i][j].add(records[i][j-1]);
                }
                panel2.add(paneles[i][j]);
            }
        }
        
        sizedFuente = fuente.getFont().deriveFont(20f);
        panel3 = new JLabel();
        panel3.setLayout(new FlowLayout());
        atras = new JLabel("Atras");
        atras.addMouseListener(this);
//        atras.setIcon(Imagenes.atras);
        atras.setFont(sizedFuente);
        atras.setHorizontalTextPosition(SwingConstants.CENTER);
        atras.setForeground(Color.black);
        panel3.add(atras);

        panelPrincipal = new JLabel();
        panelPrincipal.setLayout(new GridLayout(4, 1));
//        panelPrincipal.setIcon(Imagenes.fondo);

        panelPrincipal.add( panel1);
        panelPrincipal.add( panel2);
        panelPrincipal.add(new JLabel());
        panelPrincipal.add( panel3);

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
                    records[i][j].setText(usuarios.get(i).toString());
                } else {
                    records[i][j].setText(puntos.get(i).toString());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

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
//        sonidoClic = new ReproducirSonido(2);
//        sonidoClic.getSonido().start();
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
