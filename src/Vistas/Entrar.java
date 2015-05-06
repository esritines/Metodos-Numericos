package Vistas;

import Controladores.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Entrar extends javax.swing.JFrame implements MouseListener {

    private Sound musica;
    private ReproducirSonido sonidoClic;

    private Datos datos;
    private Inicio inicio;
    
    private Fuente fuente;
    private Font sizedFuente;

//    private JPanel principal = new JPanel(new BorderLayout());
    private JLabel principal = new JLabel();
    private JPanel secundario = new JPanel(new GridLayout(5, 0));

    private JPanel panelLabel = new JPanel(new FlowLayout());
    private JLabel titulo = new JLabel("SCROLL NUMBER");

    private JPanel panelUsuario = new JPanel(new FlowLayout());
    private JLabel usuario = new JLabel("Jugador:");
    private JTextField ingresarUsuario = new JTextField(15);

    private JPanel panelBotones = new JPanel(new FlowLayout());
    private JLabel aceptar = new JLabel("Aceptar");
    private JLabel salir = new JLabel("Salir");

    private JPanel panelNuevoUsuario = new JPanel(new FlowLayout());
    private JLabel nuevoUsuario = new JLabel("Nuevo jugador");

    private nuevoUsuario nuevo;

    public Entrar() {

        principal.setLayout(new BorderLayout());
        
        fuente = new Fuente();
        sizedFuente = fuente.getFont().deriveFont(66f);

        conectar();
        initComponents();

        datos = new Datos();
        inicio = new Inicio();
        inicio.regresar(this);
        nuevo = new nuevoUsuario();
        nuevo.regresar(this);

        titulo.setFont(sizedFuente);

        sizedFuente = fuente.getFont().deriveFont(11f);
        usuario.setFont(sizedFuente);
        aceptar.setFont(sizedFuente);
        salir.setFont(sizedFuente);
        ingresarUsuario.setFont(sizedFuente);
        nuevoUsuario.setFont(sizedFuente);

        panelLabel.add(titulo);

        panelUsuario.add(usuario);
        panelUsuario.add(ingresarUsuario);

        panelBotones.add(salir);
        panelBotones.add(aceptar);

        panelNuevoUsuario.add(nuevoUsuario);

        secundario.add(new JPanel());
        secundario.add(panelLabel);
        secundario.add(panelUsuario);
        secundario.add(panelNuevoUsuario);
        secundario.add(panelBotones);

        aceptar.addMouseListener(this);
        aceptar.setIcon(Imagenes.si);
        aceptar.setHorizontalTextPosition(SwingConstants.CENTER);
        aceptar.setForeground(Color.white);
        salir.addMouseListener(this);
        salir.setIcon(Imagenes.no);
        salir.setHorizontalTextPosition(SwingConstants.CENTER);
        salir.setForeground(Color.white);
        nuevoUsuario.addMouseListener(this);
        nuevoUsuario.setIcon(Imagenes.usuarios);
        nuevoUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
        nuevoUsuario.setForeground(Color.white);

        principal.add("Center", secundario);
        setContentPane(principal);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        repaint();
        setVisible(true);

        musica = new Sound();
        musica.loop();
    }

    public static void conectar() {
        try {
            String BaseDeDatos = "jdbc:ucanaccess://./src/Base de Datos/puzzle.accdb";
            ConexionBD.conexion = DriverManager.getConnection(BaseDeDatos, "", "");
            if (ConexionBD.conexion != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Entrar");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        musica.stop();
        try {
            ConexionBD.conexion.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        sonidoClic = new ReproducirSonido(2);
        sonidoClic.getSonido().start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(aceptar)) {
            if (datos.traerDatos(ingresarUsuario.getText())) {
                inicio.setTitle("Inicio (" + Datos.getUsuario() + ")");
                setVisible(false);
                inicio.setVisible(true);
                ingresarUsuario.setText("");
            } else {
                JOptionPane.showMessageDialog(panelBotones, "Este jugador no existe", "", JOptionPane.ERROR_MESSAGE);
                ingresarUsuario.setText("");
            }
        }
        if (e.getSource().equals(salir)) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        if (e.getSource().equals(nuevoUsuario)) {
            nuevo.setVisible(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
