package Vistas;

import Controladores.Datos;
import Controladores.Fuente;
import Controladores.Imagenes;
import Controladores.ReproducirSonido;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Puzzle extends javax.swing.JFrame implements MouseListener {

    private Fuente fuente = new Fuente();
    private Font sizedFuente;

    private JPanel panelPrincipal = new JPanel();
    private JPanel secundario = new JPanel();
    private JPanel panelBotones = new JPanel();
    private JPanel panelTimer = new JPanel();
    private GridBagConstraints c = new GridBagConstraints();
    
    private final JLabel salir = new JLabel("Salir");
    private final JLabel tiempoRestante = new JLabel("Tiempo Restante:");
    private JLabel segundosLabel = new JLabel("                    ");

    private JLabel[][] botones;
    public int segundos;

    private String comparaGanar = "";
    private static Inicio regresar;
    private Datos registro;

    public static int n;
    private static int puntos;
    private static boolean valor = false;

    private static ImageIcon imagen1;
    private static ImageIcon imagen2;
    private static ImageIcon imagen3;
    private static Icon imagenTemp;
    private static final String imagenFondo = "./src/Diseño/";

    private static ReproducirSonido sonidoClic;

    private static int movidaI;
    private static int movidaJ;

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public Puzzle(int segundos, int n) {
        movidaI = 0;
        movidaJ = movidaI;
        this.segundos = segundos;
        Puzzle.n = n;

        registro = new Datos();

        initComponents();

        setTitle("Puzzle " + Datos.getUsuario());
        iniciar();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        tiempo.start();
    }

    public void iniciar() {

        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(Color.black);     
        secundario.setLayout(new GridBagLayout());
        secundario.setBackground(Color.black);
        panelBotones.setBackground(Color.black);
        panelTimer.setLayout(new FlowLayout());
        panelTimer.setBackground(Color.black);
        
        agregarBotones();
//        desacomodar();
             
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
              
        sizedFuente = fuente.getFont().deriveFont(17f).deriveFont(Font.BOLD);

        salir.addMouseListener(this);
        salir.setHorizontalTextPosition(SwingConstants.CENTER);
        salir.setFont(sizedFuente);
        salir.setIcon(Imagenes.no);
        salir.setForeground(Color.white);

        sizedFuente = fuente.getFont().deriveFont(20f).deriveFont(Font.BOLD);

        tiempoRestante.setFont(sizedFuente);
        tiempoRestante.setForeground(Color.white);

        segundosLabel.setForeground(Color.RED);
        segundosLabel.setFont(sizedFuente);

        panelTimer.add(salir);
        panelTimer.add(tiempoRestante);
        panelTimer.add(segundosLabel);

        secundario.add(panelBotones, c);  
        panelPrincipal.add("Center", secundario);
        panelPrincipal.add("South", panelTimer);

        setContentPane(panelPrincipal);
        repaint();
    }

    public final void agregarBotones() {

        int uno, dos;

        sizedFuente = fuente.getFont().deriveFont(30f);

        int c = 0;

        do {
            uno = (int) (Math.random() * 7);
            dos = (int) (Math.random() * 7);
        } while (uno == dos);

        imagen1 = new ImageIcon(imagenFondo + uno + ".png");
        imagen2 = new ImageIcon(imagenFondo + dos + ".png");
        imagen3 = new ImageIcon(imagenFondo + "vacio.png");
        
        botones = new JLabel[n][n + 2];
        panelBotones.setLayout(new GridLayout(n, n + 2));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 2; j++) {
                botones[i][j] = new JLabel();
                botones[i][j].setForeground(Color.WHITE);
                botones[i][j].setFont(sizedFuente);
                botones[i][j].addMouseListener(this);
                panelBotones.add(botones[i][j]);
                c++;
                if ((i != (n - 1)) || (j != (n + 1))) {
                    if ((c % 2) == 0) {
                        botones[i][j].setIcon(imagen1);
                    } else {
                        botones[i][j].setIcon(imagen2);
                    }
                    botones[i][j].setText("" + c);
                } else {
                    botones[i][j].setIcon(imagen3);
                    botones[i][j].setText(".");
                }
                comparaGanar += botones[i][j].getText();
                botones[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
            }
        }
    }

    public final void desacomodar() {
        int rnd, rnd2, i, j, k = 0, h = 0;
        boolean valor = false, valor2 = true;

        do {
            rnd = (int) (Math.random() * (Math.pow(n, 3) + 1));
            System.out.println("El numero random es: " + rnd);
            if (rnd > n * n) {
                while (k < rnd) {
                    for (i = 0; i < n; i++) {
                        for (j = 0; j < n + 2; j++) {
                            if (botones[i][j].getText().equals(".")) {
                                valor = true;
                                do {
                                    rnd2 = (int) (Math.random() * 4);
                                    System.out.print((++h) + " Movi: ");
                                    switch (rnd2) {
                                        case 0:
                                            valor2 = moverBotonVacio(i, j - 1, i, j);
                                            if (!valor2) {
                                                System.out.print("Izquierda\n");
                                                k++;
                                            }
                                            break;
                                        case 1:
                                            valor2 = moverBotonVacio(i - 1, j, i, j);
                                            if (!valor2) {
                                                System.out.print("Arriba\n");
                                                k++;
                                            }
                                            break;
                                        case 2:
                                            valor2 = moverBotonVacio(i, j + 1, i, j);
                                            if (!valor2) {
                                                System.out.print("Derecha\n");
                                                k++;
                                            }
                                            break;
                                        case 3:
                                            valor2 = moverBotonVacio(i + 1, j, i, j);
                                            if (!valor2) {
                                                System.out.print("Abajo\n");
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
                sonidoClic = new ReproducirSonido(1);
                sonidoClic.getSonido().start();

                temporal = botones[k][l].getText();
                imagenTemp = botones[k][l].getIcon();
                botones[k][l].setText(".");
                botones[k][l].setIcon(botones[i][j].getIcon());
                botones[i][j].setIcon(imagenTemp);
                botones[i][j].setText(temporal);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }

    public boolean moverBotonVacio(int i, int j, int k, int l) {

        try {
            imagenTemp = botones[k][l].getIcon();           //imagen negra
            botones[k][l].setText(botones[i][j].getText());
            botones[k][l].setIcon(botones[i][j].getIcon());
            botones[i][j].setText(".");
            botones[i][j].setIcon(imagenTemp);
            if (botones[movidaI][movidaJ].getText().equals(".")) {
                botones[i][j].setIcon(botones[k][l].getIcon());
                botones[i][j].setText(botones[k][l].getText());
                botones[k][l].setIcon(imagenTemp);
                botones[k][l].setText(".");
                System.out.println("Me regrese");
                return true;
            }
            movidaI = k;
            movidaJ = l;
            return false;

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.print("No\n");
            return true;
        }
    }

    public void ganaste() {

        String temporal = "";

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 2; j++) {
                temporal += botones[i][j].getText();
            }
        }

        if (temporal.equals(comparaGanar)) {
            tiempo.stop();
            puntos += segundos * 10;
            valor = true;
            setVisible(false);
            registro.crearPregunta();
            Pregunta pregunta = new Pregunta();
            pregunta.pasarPuzzle(this);
            pregunta.setVisible(true);
        }
    }

    public void crear() {
        Puzzle puzzle = new Puzzle(segundos = 65 * (n - 1), ++n);
    }

    public void perdiste() {
        capturarRegistro();
        this.setVisible(false);
        regresar.setVisible(true);
    }

    public void capturarRegistro() {
        if (valor) {
            registro.enviarRegistro(Datos.getUsuario(), puntos);
            valor = false;
            puntos = 0;
        }
    }

    public void regresar(Inicio inicio) {
        regresar = inicio;
    }

    Timer tiempo = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            segundosLabel.setText(Integer.toString(segundos));
            segundos--;
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
        capturarRegistro();
        regresar.setVisible(true);
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(salir)) {
            sonidoClic = new ReproducirSonido(2);
            sonidoClic.getSonido().start();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (e.getSource().equals(botones[i][j])) {
                    moverBoton(i, j - 1, i, j);
                    moverBoton(i - 1, j, i, j);
                    moverBoton(i, j + 1, i, j);
                    moverBoton(i + 1, j, i, j);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        repaint();
        ganaste();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
