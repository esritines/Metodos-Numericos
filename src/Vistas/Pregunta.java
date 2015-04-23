package Vistas;

import Controladores.Datos;
import Controladores.Imagenes;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Pregunta extends javax.swing.JFrame implements MouseListener {

    private static Datos datos = new Datos();
    private Imagenes imagen = new Imagenes(2);

    private JPanel principal = new JPanel(new BorderLayout(2, 0));
    private JPanel panelPregunta = new JPanel(new FlowLayout());
    private JPanel panelRespuestas;
    private JPanel panelAceptar = new JPanel(new FlowLayout());

    private JLabel aceptarL = new JLabel("Aceptar");
    private JLabel preguntaL = new JLabel("                                                                   ");

    private ArrayList<JLabel> respuestasLabel = new ArrayList<>();
    private ArrayList<JTextField> respuestasField = new ArrayList<>();
    
    private static boolean nuevoPuzzle = false;

    public boolean getNuevoPuzzle() {
        return nuevoPuzzle;
    }

    public void setNuevoPuzzle(boolean nuevoPuzzle) {
        this.nuevoPuzzle = nuevoPuzzle;
    }
    
    public Pregunta(String pregunta, ArrayList respuestas) {
        initComponents();

        setSize(600, 300);

        preguntaL.setText(pregunta);
        panelPregunta.add(preguntaL);

        if (isNumber(respuestas.get(1).toString())) {
            agregarRespuestas1(respuestas);
        } else {
            agregarRespuestas2(respuestas);
        }

        aceptarL.addMouseListener(this);
        aceptarL.setIcon(imagen.getImagen());
        aceptarL.setHorizontalTextPosition(SwingConstants.CENTER);

        panelAceptar.add(aceptarL);

        principal.add("North", panelPregunta);
        principal.add("Center", panelRespuestas);
        principal.add("South", panelAceptar);
        
        setContentPane(principal);

        setVisible(true);
    }

    public void agregarRespuestas1(ArrayList respuestas) {

        panelRespuestas = new JPanel(new GridLayout(1, respuestas.size()));

        for (int i = 0; i < respuestas.size(); i++) {
            JPanel panel = new JPanel(new FlowLayout());
            respuestasField.add(new JTextField());
            panel.add(respuestasField.get(i));
            panelRespuestas.add(panel);
        }
    }

    public void agregarRespuestas2(ArrayList respuestas) {

    }

    public boolean isNumber(String respuesta) {
        try {
            Float.parseFloat(respuesta);
            return true;
        } catch (NumberFormatException e) {
            return false;
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
    @Override
    public void mouseClicked(MouseEvent e) {
        
       validar();
    }

    public void validar(){
        
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
