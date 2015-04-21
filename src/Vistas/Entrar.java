package Vistas;

import Controladores.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import sonidos.AePlayWave;

public class Entrar extends javax.swing.JFrame implements MouseListener {

    private static AePlayWave sonido;
    private static final String sonidoClic = "C:/Users/Abraham/Documents/NetBeansProjects/puzzle/src/Diseño/clic.wav";
    
    private Datos datos;
    private Inicio inicio;

    ImageIcon image = new ImageIcon("C:/Users/Abraham/Documents/NetBeansProjects/Puzzle/src/Diseño/01.png");

    private static Font font2 = new Font("Courier", Font.BOLD, 66);
    
    private JPanel principal = new JPanel(new BorderLayout());
    private JPanel secundario = new JPanel(new GridLayout(3, 0));

    private JPanel panelLabel = new JPanel(new FlowLayout());
    private JLabel label = new JLabel("¡Slide Puzzle!");

    private JPanel panelUsuario = new JPanel(new FlowLayout());
    private JLabel usuario = new JLabel("Usuario:");
    private JTextField ingresarUsuario = new JTextField(15);

    private JPanel panelBotones = new JPanel(new FlowLayout());
    private JLabel aceptar = new JLabel("Aceptar");
    private JLabel salir = new JLabel("Salir");

    public Entrar() {

        conectar();
        initComponents();

        datos = new Datos();
        inicio = new Inicio();
        inicio.regresar(this);
        
        label.setFont(font2);
        panelLabel.add(label);
                
        panelUsuario.add(usuario);
        panelUsuario.add(ingresarUsuario);
        
        panelBotones.add(salir);
        panelBotones.add(aceptar);
        
        secundario.add(panelLabel);
        secundario.add(panelUsuario);
        secundario.add(panelBotones);
        
        aceptar.addMouseListener(this);
        aceptar.setIcon(image);
        aceptar.setHorizontalTextPosition(SwingConstants.CENTER);
        salir.addMouseListener(this);
        salir.setIcon(image);
        salir.setHorizontalTextPosition(SwingConstants.CENTER);

        principal.add("Center", secundario);
        setContentPane(principal);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        repaint();
        setVisible(true);
    }

    public static void conectar() {
        try {
            String BaseDeDatos = "jdbc:ucanaccess://C:/Users/Abraham/Documents/NetBeansProjects/Puzzle/src/Base de Datos/puzzle.accdb";
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
        setResizable(false);
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
        try {
            ConexionBD.conexion.close();
            System.out.println("Conexion Cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(Entrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent e) {
        sonido = new AePlayWave(sonidoClic);
        sonido.start();
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
