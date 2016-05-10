package Vistas;

import Controladores.Datos;
import Controladores.Fuente;
import Controladores.Imagenes;
import Controladores.ReproducirSonido;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class nuevoUsuario extends javax.swing.JFrame implements MouseListener {

    private Fuente fuente;
    private Font sizedFuente;
    private String compararUsuarios;
    private ReproducirSonido sonidoClic;

    private Datos datos;
    private Entrar regresar;

    private JLabel comparar = new JLabel();

    private JLabel principal = new JLabel();
    private JLabel secundario = new JLabel();

    private JLabel panelUsuario = new JLabel();
    private JLabel usuario = new JLabel("Nuevo jugador:");
    private JTextField nuevoUsuario = new JTextField(15);

    private JLabel panelBotones = new JLabel();
    private JLabel aceptar = new JLabel("Aceptar");
    private JLabel atras = new JLabel("Atras");

    public nuevoUsuario() {

        principal.setLayout(new BorderLayout());
        principal.setIcon(Imagenes.fondo);
        secundario.setLayout(new GridLayout(4, 0));
        panelUsuario.setLayout(new FlowLayout());
        panelBotones.setLayout(new FlowLayout());

        fuente = new Fuente();
        sizedFuente = fuente.getFont().deriveFont(19f);

        setVisible(false);
        initComponents();
        datos = new Datos();

        usuario.setFont(sizedFuente);
        usuario.setForeground(Color.black);
        panelUsuario.add(usuario);
        nuevoUsuario.setFont(sizedFuente);
        panelUsuario.add(nuevoUsuario);
        nuevoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyPressed(java.awt.event.KeyEvent evt) {
                compararUsuarios = nuevoUsuario.getText();
                buscarUsuario(evt);
            }
        });
        panelUsuario.add(comparar);

        sizedFuente = fuente.getFont().deriveFont(17f);
        panelBotones.add(atras);
        atras.addMouseListener(this);
        atras.setFont(sizedFuente);
        atras.setIcon(Imagenes.atras);
        atras.setHorizontalTextPosition(SwingConstants.CENTER);
        atras.setForeground(Color.black);
        panelBotones.add(aceptar);
        aceptar.addMouseListener(this);
        aceptar.setFont(sizedFuente);
        aceptar.setIcon(Imagenes.si);
        aceptar.setHorizontalTextPosition(SwingConstants.CENTER);
        aceptar.setForeground(Color.black);

        secundario.add(new JLabel());
        secundario.add(panelUsuario);
        secundario.add(panelBotones);
        secundario.add(new JLabel());

        principal.add("Center", secundario);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(principal);
    }

    private void buscarUsuario(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() != java.awt.event.KeyEvent.VK_ENTER) {
            if (datos.compararUsuario(compararUsuarios)) {
                aceptar.setEnabled(false);
                comparar.setForeground(Color.red);
                comparar.setText("*Usuarios no disponible");
            } else {
                aceptar.setEnabled(true);
                comparar.setForeground(Color.green);
                comparar.setText("*Usuarios disponible");
            }
        }

    }

    public void regresar(Entrar regresar) {
        this.regresar = regresar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 105, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(aceptar)) {
            if (!nuevoUsuario.getText().isEmpty()) {
                if (datos.enviarDatos(nuevoUsuario.getText())) {
                    JOptionPane.showMessageDialog(principal, "Jugador nuevo agregado :) ", "", JOptionPane.INFORMATION_MESSAGE);
                    nuevoUsuario.setText("");
                    setVisible(false);
                    regresar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(principal, "Error al agregar un jugador :(", "", JOptionPane.INFORMATION_MESSAGE);
                    nuevoUsuario.setText("");
                }
            }else{
                JOptionPane.showMessageDialog(principal, "No has capturado un nombre :(", "", JOptionPane.INFORMATION_MESSAGE);
                    nuevoUsuario.setText("");
            }
        }
        if (e.getSource().equals(atras)) {
            regresar.setVisible(true);
            setVisible(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        sonidoClic = new ReproducirSonido(2);
        sonidoClic.getSonido().start();
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
