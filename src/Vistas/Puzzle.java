package Vistas;

import Controladores.Datos;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Puzzle extends javax.swing.JFrame {

    private JButton[][] botones;
    private JPanel panel;
    private GridLayout capa;
    private static int alto;
    private static int ancho;
    private static int n = 3;

    public Puzzle() {

        initComponents();

        this.setTitle("Puzzle (" + Datos.getUsuario() + ")");
        this.setSize(300, 300);
        
        botones = new JButton[n][n];

        capa = new GridLayout(n, n); 
        panel = new JPanel();
        panel.setSize(300, 300);
        panel.setLayout(capa);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == (n-1) && j == (n-1)) {
                    
                }else{
                    botones[i][j] = new JButton();
                    panel.add(botones[i][j]);
                }
            }
        }

        
        this.setContentPane(panel);
        this.repaint();
        this.setVisible(true);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
