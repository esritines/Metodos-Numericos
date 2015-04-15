
package Vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Registros extends javax.swing.JFrame implements ActionListener{
    
    private Inicio regresar;
    private JPanel panelPrincipal;
    private JPanel panel1;
    private JLabel recordsTitulo;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel records[][];
    private JButton atras;
    
    public Registros() {
        initComponents();
        
        
        setTitle("Records");
        setVisible(false);
        
        setSize(200, 400);
        
        panel1 = new JPanel();
        panel1.setSize(200, 75);
        recordsTitulo = new JLabel("Records");
        panel1.add(recordsTitulo);

        panel2 = new JPanel();
        panel2.setSize(200, 250);
        panel2.setLayout(new GridLayout(6, 2));     
        records = new JLabel[6][2];
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                records[i][j] = new JLabel("        ");
                panel2.add(records[i][j]);
            }
        }

        panel3 = new JPanel();
        panel3.setSize(200, 75);
        atras = new JButton("Atras");
        atras.addActionListener(this);
        panel3.add(atras);
        
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setSize(200, 400);
        panelPrincipal.add("North", panel1);
        panelPrincipal.add("Center", panel2);
        panelPrincipal.add("South", panel3);
        
        setContentPane(panelPrincipal);
    }
    
    public void regresar(Inicio regresar){
        this.regresar = regresar;
    }
    
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        regresar.setVisible(true);
    }

    public void insertarRegistro(ArrayList usuarios, ArrayList puntos){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0){
                    records[i][j].setText(usuarios.get(j).toString());
                }else{
                    records[i][j].setText(usuarios.get(j).toString());
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
}
