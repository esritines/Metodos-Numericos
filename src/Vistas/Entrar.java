package Vistas;

import Controladores.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import javax.swing.*;

public class Entrar extends javax.swing.JFrame implements MouseListener {

    private Sound musica;
    private ReproducirSonido sonidoClic;

    private Datos datos;
    private Inicio inicio;

    private Imagenes imagen = new Imagenes(1);

    private static Font font = new Font("Courier", Font.BOLD, 66);
//    private Fuente fuente = new Fuente();

    private JPanel principal = new JPanel(new BorderLayout());
    private JPanel secundario = new JPanel(new GridLayout(4, 0));

    private JPanel panelLabel = new JPanel(new FlowLayout());
    private JLabel label = new JLabel("¡Slide Puzzle!");

    private JPanel panelUsuario = new JPanel(new FlowLayout());
    private JLabel usuario = new JLabel("Usuario:");
    private JTextField ingresarUsuario = new JTextField(15);

    private JPanel panelBotones = new JPanel(new FlowLayout());
    private JLabel aceptar = new JLabel("Aceptar");
    private JLabel salir = new JLabel("Salir");

    public Entrar()  {

        BufferedImage myPicture;
        

        conectar();
        initComponents();

        datos = new Datos();
        inicio = new Inicio();
        inicio.regresar(this);

        label.setFont(font);
        panelLabel.add(label);

        panelUsuario.add(usuario);
        panelUsuario.add(ingresarUsuario);

        panelBotones.add(salir);
        panelBotones.add(aceptar);

        secundario.add(new JPanel());
        secundario.add(panelLabel);
        secundario.add(panelUsuario);
        secundario.add(panelBotones);

        aceptar.addMouseListener(this);
        aceptar.setIcon(imagen.getImagen());
        aceptar.setHorizontalTextPosition(SwingConstants.CENTER);
        salir.addMouseListener(this);
        salir.setIcon(imagen.getImagen());
        salir.setHorizontalTextPosition(SwingConstants.CENTER);
        
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
            System.out.println("Conexion Cerrada");
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
                JOptionPane.showMessageDialog(panelBotones, "Usuario no existe", "", JOptionPane.ERROR_MESSAGE);
                ingresarUsuario.setText("");
            }
        } else {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
