package Vistas;

import Controladores.Datos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Puzzle extends javax.swing.JFrame implements ActionListener {

    private  JButton[][] botones;
    private  JPanel panelPrincipal;
    private  JPanel panelBotones;
    private  JPanel panelTimer;
    private  final Label tiempoRestante = new Label("Tiempo Restante:");
    private  Label segundosLabel = new Label("   ");
    private  GridLayout capa;
    private  int segundos;
    private  int reponerSegundos;
    private  static int n;
    private  static int resolucion;
    private  final int resolucion2 = 25;
    private  String comparaGanar = "";
    private  static Inicio regresar;
    

    public Puzzle(int segundos, int reponerSegundos, int n, int resolucion) {
        
        this.segundos = segundos;
        this.reponerSegundos = reponerSegundos;
        Puzzle.n = n;
        Puzzle.resolucion = resolucion; 
        
        initComponents();

        setTitle("Puzzle" + Datos.getUsuario());
        
        iniciar();
        
    }
    
    public Puzzle(){
        
    }
    
    public void iniciar(){
        setSize(resolucion, resolucion + resolucion2);
  
        panelBotones = new JPanel();
        panelBotones.setSize(resolucion, resolucion);

        agregarBotones();
        desacomodar();
    
        panelPrincipal = new JPanel();
        panelPrincipal.setSize(resolucion, resolucion + resolucion2);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add("Center", panelBotones);
      
        panelTimer = new JPanel();
        panelTimer.setSize(resolucion, resolucion2);
        panelTimer.add(tiempoRestante);
        panelTimer.add(segundosLabel);
        panelPrincipal.add("South", panelTimer);

        setContentPane(panelPrincipal);
        repaint();
        setVisible(true);

        tiempo.start();
    }
    
    public final void agregarBotones() {

        int c = 0;
        botones = new JButton[n][n];
        capa = new GridLayout(n, n);

        panelBotones.setLayout(capa);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                botones[i][j] = new JButton();
                botones[i][j].addActionListener(this);
                panelBotones.add(botones[i][j]);
                c++;
                if ((i != (n - 1)) || (j != (n - 1))) {
                    botones[i][j].setText("" + c);
                } else {
                    botones[i][j].setText(".");
                }
                comparaGanar += botones[i][j].getText();
            }
        }
    }

    public final void desacomodar() {
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
                                            if (valor2) {
                                                System.out.println("Case 0");
                                                k++;
                                            }
                                            break;
                                        case 1:
                                            valor2 = moverBotonVacio(i - 1, j, i, j);
                                            if (valor2) {
                                                System.out.println("Case 1");
                                                k++;
                                            }
                                            break;
                                        case 2:
                                            valor2 = moverBotonVacio(i, j + 1, i, j);
                                            if (valor2) {
                                                System.out.println("Case 2");
                                                k++;
                                            }
                                            break;
                                        case 3:
                                            valor2 = moverBotonVacio(i + 1, j, i, j);
                                            if (valor2) {
                                                System.out.println("Case 3");
                                                k++;
                                            }
                                            break;
                                    }
                                } while (valor2);
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
        repaint();
        ganaste();
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

    public void ganaste() {

        String temporal = "";
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temporal += botones[i][j].getText();
            }
        }

        if (temporal.equals(comparaGanar)) {        
            tiempo.stop();
            JOptionPane.showMessageDialog(rootPane, "Ganaste", "", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            Puzzle puzzle = new Puzzle(segundos += 40 + reponerSegundos, 0, ++n, resolucion += 100);
        }
    }

    public void perdiste(){
        this.setVisible(false);
        regresar.setVisible(true);
    }
    
    public void regresar(Inicio inicio){
        regresar = inicio;
    }
    
    Timer tiempo = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            segundosLabel.setText(Integer.toString(segundos));
            segundos--;
            reponerSegundos++;
            if (segundos == 0) {
                tiempo.stop();
                perdiste();
            }
        }
    });

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
