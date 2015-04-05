package Vistas;

import Controladores.Datos;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Puzzle extends javax.swing.JFrame implements ActionListener {

    private JButton[][] botones;
    private JPanel panel;
    private GridLayout capa;
    private static int n = 3;
    private static int resolucion = 300;
    private String comparaGanar = "";

    public Puzzle() {

        initComponents();

        this.setTitle("Puzzle (" + Datos.getUsuario() + ")");
        this.setSize(resolucion, resolucion);

        agregarBotones();
        random();

        this.setVisible(true);
    }

    public final void random() {
        int rnd, rnd2, i, j, k = 0;
        boolean valor = false, valor2 = true;

        do {
            rnd = (int) (Math.random() * (Math.pow(n, 3) + 1));
            System.out.println("El numero random es: " + rnd);
            if (rnd > n * n) {
                while (k < rnd) {         
                    for (i = 0; i < n; i++) {
                        for (j = 0; j < n; j++) {
                            if (botones[i][j].getText().equals(".")) {
                                valor = true;
                                do {
                                    rnd2 = (int) (Math.random() * 4);
                                    switch (rnd2) {
                                        case 0:
                                            valor2 = moverBotonVacio(i, j - 1, i, j);
                                            if(valor2){
                                                k++;
                                                System.out.println("Case 0");
                                            }                                    
                                            break;
                                        case 1:
                                            valor2 = moverBotonVacio(i - 1, j, i, j);
                                            if(valor2){
                                                k++;
                                                System.out.println("Case 1");
                                            }  
                                            break;
                                        case 2:
                                            valor2 = moverBotonVacio(i, j + 1, i, j);
                                            if(valor2){
                                                System.out.println("Case 2");
                                                k++;
                                            }  
                                            break;
                                        case 3:
                                            valor2 = moverBotonVacio(i + 1, j, i, j); 
                                            if(valor2){
                                                System.out.println("Case 3");
                                                k++;
                                            } 
                                            break;
                                    }
                                } while (valor2);
                                this.repaint();
                                break;
                            }
                        }
                        if (valor) {
                            valor = false;
                            break;
                        }
                    }
                }
            }
        } while (rnd <= n * n);
    }

    public final void agregarBotones() {

        int c = 0;

        botones = new JButton[n][n];
        capa = new GridLayout(n, n);
        panel = new JPanel();
        panel.setSize(resolucion, resolucion);
        panel.setLayout(capa);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                botones[i][j] = new JButton();
                botones[i][j].addActionListener(this);
                panel.add(botones[i][j]);
                c++;
                if ((i != (n - 1)) || (j != (n - 1))) {
                    botones[i][j].setText("" + c);         
                } else {
                    botones[i][j].setText(".");
                }
                comparaGanar += botones[i][j].getText();
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
            if (botones[i][j].getText().equals(".")) {
                temporal = botones[k][l].getText();
                botones[k][l].setText(".");
                botones[i][j].setText(temporal);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        this.repaint();
    }

    public boolean moverBotonVacio(int i, int j, int k, int l) {
        try {
            botones[k][l].setText(botones[i][j].getText());
            botones[i][j].setText(".");
            return false;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return true;
        }
    }

    public void ganar() {

        String temporal = "";

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temporal += botones[i][j].getText();
            }
        }

        if (temporal.equals(comparaGanar)) {
            JOptionPane.showMessageDialog(rootPane, "Ganaste", "", JOptionPane.INFORMATION_MESSAGE);
            n++;
            resolucion += 100;
            this.setVisible(false);
            Puzzle puzzle = new Puzzle();
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
