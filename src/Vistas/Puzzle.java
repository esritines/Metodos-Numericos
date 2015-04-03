package Vistas;

import Controladores.Datos;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Puzzle extends javax.swing.JFrame {

    private JButton[][] botones;
    private ArrayList<Integer> numeros = new ArrayList<>();
    private JPanel panel;
    private GridLayout capa;
    private static int n = 3;
    private String comparaGanar = new String();

    public Puzzle() {
        this.comparaGanar = "";

        initComponents();

        this.setTitle("Puzzle (" + Datos.getUsuario() + ")");
        this.setSize(300, 300);

        agregarBotones();
        random();

        this.setVisible(true);

    }

    public void random() {

        int k = (n * n) - 1, r;

        for (int i = 1; i < n * n; i++) {
            numeros.add(i);
            comparaGanar += i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i != (n-1)) || (j != (n-1))) {
                    r = (int) (Math.random() * k);
                    botones[i][j].setText(Integer.toString(numeros.get(r)));
                    numeros.remove(r);
                    k--;
                }        
            }
        }

        botones[n-1][n-1].setText("");
    }

    public void agregarBotones() {

        botones = new JButton[n][n];
        capa = new GridLayout(n, n);
        panel = new JPanel();
        panel.setSize(300, 300);
        panel.setLayout(capa);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                botones[i][j] = new JButton();
                panel.add(botones[i][j]);
            }
        }

        this.setContentPane(panel);
        this.repaint();
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
