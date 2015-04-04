package Vistas;

import Controladores.Datos;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Puzzle extends javax.swing.JFrame implements ActionListener {
    
    private JButton[][] botones;
    private ArrayList<String> numeros = new ArrayList<>();
    private JPanel panel;
    private GridLayout capa;
    private static int n = 3;
    private String comparaGanar = "";
    
    public Puzzle() {
        
        initComponents();
        
        this.setTitle("Puzzle (" + Datos.getUsuario() + ")");
        this.setSize(300, 300);
        
        agregarBotones();
        random();
        
        this.setVisible(true);
    }
    
    public final void random() {
        
        int k = n * n, r;
        
        for (int i = 1; i < n * n; i++) {
            numeros.add(Integer.toString(i));
            comparaGanar += i;
        }
        
        numeros.add((int) (Math.random()* 9 ), "");
        
        for (int i = 0; i < n*n; i++) {
            System.out.println(numeros.get(i));
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                if ((i != (n - 1)) || (j != (n - 1))) {
                    r = (int) (Math.random() * k);
                    botones[i][j].setText(numeros.get(r));
                    numeros.remove(r);
                    k--;
//                }
            }
        }
    }
    
    public final void agregarBotones() {
        
        botones = new JButton[n][n];
        capa = new GridLayout(n, n);
        panel = new JPanel();
        panel.setSize(300, 300);
        panel.setLayout(capa);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                botones[i][j] = new JButton();
                botones[i][j].addActionListener(this);
                panel.add(botones[i][j]);
            }
        }
        
        this.setContentPane(panel);
        this.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (e.getSource().equals(botones[i][j])) {
                    moverBoton(i, j - 1, i, j);
                    moverBoton(i - 1, j, i, j);
                    moverBoton(i, j + 1, i, j);
                    moverBoton(i + 1, j, i, j);
                }
            }
        }
        ganar();
    }
    
    public void moverBoton(int i, int j, int k, int l) {
        String temporal;
        try {
            if (botones[i][j].getText().isEmpty()) {
                temporal = botones[k][l].getText();
                botones[k][l].setText("");
                botones[i][j].setText(temporal);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        this.repaint();
    }
    
    public void ganar() {
        
        String temporal = "";
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temporal += botones[i][j].getText();
            }
        }
        
        if (temporal.equals(comparaGanar)) {
            System.out.println("Ganaste");
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
