package Vistas;

import Controladores.Datos;
import sonidos.AePlayWave;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Puzzle extends javax.swing.JFrame implements MouseListener {

    private JLabel[][] botones;
    private JPanel panelPrincipal;
    private JPanel panelBotones;
    private JPanel panelTimer;
    private final Label tiempoRestante = new Label("Tiempo Restante:");
    private Label segundosLabel = new Label("   ");
    private GridLayout capa;
    private int segundos;
    private int reponerSegundos;
    private static int n;
    private static int resolucion;
    private final int resolucion2 = 25;
    private String comparaGanar = "";
    private static Inicio regresar;
    private Datos registro;
    private int puntos;
    private static boolean valor = false;
    private static Font font = new Font("Courier", Font.BOLD, 12);
    private static Font font2 = new Font("Courier", Font.BOLD, 16);
    private static ImageIcon imagen[][];
    private static AePlayWave sonido;
    private static final String sonidoClic = "C:/Users/Abraham/Documents/NetBeansProjects/puzzle/src/Diseño/clic.wav";
    private static final String imagenFondo = "C:/Users/Abraham/Documents/NetBeansProjects/puzzle/src/Diseño/";

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public Puzzle(int segundos, int reponerSegundos, int n, int resolucion) {
        this.segundos = segundos;
        this.reponerSegundos = reponerSegundos;
        Puzzle.n = n;
        Puzzle.resolucion = resolucion;

        
        registro = new Datos();
        initComponents();

        setTitle("Puzzle " + Datos.getUsuario());

        iniciar();

        setVisible(true);
        tiempo.start();
    }

    public void iniciar() {
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
        segundosLabel.setForeground(Color.RED);
        segundosLabel.setFont(font);
        panelTimer.add(segundosLabel);
        panelPrincipal.add("South", panelTimer);

        setContentPane(panelPrincipal);
        repaint();      
    }

    public final void agregarBotones() {

        int c = 0;
        imagen = new ImageIcon[n][n];
        botones = new JLabel[n][n];
        capa = new GridLayout(n, n);

        panelBotones.setLayout(capa);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                imagen[i][j] = new ImageIcon(imagenFondo + (int) (Math.random() * 12) + ".png");
                botones[i][j] = new JLabel(imagen[i][j]);
                botones[i][j].setForeground(Color.WHITE);
                botones[i][j].setFont(font2);
                botones[i][j].addMouseListener(this);
                panelBotones.add(botones[i][j]);
                c++;
                if ((i != (n - 1)) || (j != (n - 1))) {
                    botones[i][j].setText("" + c);
                } else {
                    botones[i][j].setText(".");
                }
                comparaGanar += botones[i][j].getText();
                botones[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
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

    public void moverBoton(int i, int j, int k, int l) {
        String temporal;
        try {
            if (botones[i][j].getText().equals(".")) {
                sonido = new AePlayWave(sonidoClic);
                sonido.start();
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
            puntos += segundos * 10;
            valor = true;
            JOptionPane.showMessageDialog(rootPane, "Ganaste", "", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            Puzzle puzzle = new Puzzle(segundos += 50 + reponerSegundos, 0, ++n, resolucion += 100);
        }
    }

    public void perdiste() {
        this.setVisible(false);
        regresar.setVisible(true);
    }

    public void regresar(Inicio inicio) {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (valor) {
            System.out.println("estoy enviando el record");
            registro.enviarRegistro(Datos.getUsuario(), puntos);
            valor = false;
        }
        regresar.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent e) {
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

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}