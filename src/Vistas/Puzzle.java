
package Vistas;

import Controladores.Datos;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Puzzle extends javax.swing.JFrame {

    private JButton[][] botones;
    private static int alto;
    private static int ancho;
    private static int n = 3;
    
    public Puzzle() {
        
        initComponents();
        
        this.setTitle("Puzzle" + Datos.getUsuario());
        this.setVisible(true);
        
        botones = new JButton[n][n];     
        botones[n-1][n-1] = null;
        
        GridLayout capa = new GridLayout(n, n);

        this.setLayout(capa);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                if(botones[i][j] != null){
                    this.add(new JButton("Boton " + i));
                }
            }
        }    
        
        
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
