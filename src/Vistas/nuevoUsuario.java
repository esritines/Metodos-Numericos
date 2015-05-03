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
    
    private ReproducirSonido sonidoClic;

    private Datos datos;
    private Entrar regresar;

    private JPanel principal = new JPanel(new BorderLayout());
    private JPanel secundario = new JPanel(new GridLayout(4, 0));

    private JPanel panelUsuario = new JPanel(new FlowLayout());
    private JLabel usuario = new JLabel("Nuevo usuario:");
    private JTextField nuevoUsuario = new JTextField(15);

    private JPanel panelBotones = new JPanel(new FlowLayout());
    private JLabel aceptar = new JLabel("Aceptar");
    private JLabel atras = new JLabel("Atras");

    public nuevoUsuario() {
        
        fuente = new Fuente();
        sizedFuente = fuente.getFont().deriveFont(14f);
        
        setVisible(false);
        initComponents();
        datos = new Datos();

        usuario.setFont(sizedFuente);
        usuario.setForeground(Color.white);
        panelUsuario.add(usuario);
        nuevoUsuario.setFont(sizedFuente);
        panelUsuario.add(nuevoUsuario);

        panelBotones.add(atras);
        atras.addMouseListener(this);
        atras.setFont(sizedFuente);
        atras.setIcon(Imagenes.atras);
        atras.setHorizontalTextPosition(SwingConstants.CENTER);
        atras.setForeground(Color.white);
        panelBotones.add(aceptar);
        aceptar.addMouseListener(this);
        aceptar.setFont(sizedFuente);
        aceptar.setIcon(Imagenes.si);
        aceptar.setHorizontalTextPosition(SwingConstants.CENTER);
        aceptar.setForeground(Color.white);

        secundario.add(new JPanel());
        secundario.add(panelUsuario);
        secundario.add(panelBotones);
        secundario.add(new JPanel());
        
        principal.add("Center", secundario);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(principal);
    }

    public void regresar(Entrar regresar) {
        this.regresar = regresar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

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
            if (datos.enviarDatos(nuevoUsuario.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Se agrego al nuevo usuario con exito", "", JOptionPane.INFORMATION_MESSAGE);
                nuevoUsuario.setText("");
                setVisible(false);
                regresar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Error al agregar nuevo usuario", "", JOptionPane.INFORMATION_MESSAGE);
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
