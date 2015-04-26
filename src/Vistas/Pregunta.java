package Vistas;

import Controladores.Datos;
import Controladores.Imagenes;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Pregunta extends javax.swing.JFrame implements MouseListener {

    private Imagenes imagen = new Imagenes(2);

    private JPanel principal = new JPanel(new GridLayout(3, 1));
    private JPanel panelPregunta = new JPanel(new FlowLayout());
    private JPanel panelRespuestas;
    private JPanel panelAceptar = new JPanel(new FlowLayout());

    private JLabel aceptarL = new JLabel("Aceptar");
    private JLabel preguntaL = new JLabel();
    
    private ArrayList<JRadioButton> respuestasRButton = new ArrayList<>();
    private ArrayList<JTextField> respuestasField = new ArrayList<>();
    private ArrayList<String> verdadera;

    private static boolean nuevoPuzzle = false;

    public boolean getNuevoPuzzle() {
        return nuevoPuzzle;
    }

    public void setNuevoPuzzle(boolean nuevoPuzzle) {
        this.nuevoPuzzle = nuevoPuzzle;
    }

    public Pregunta(String pregunta, ArrayList respuestas, ArrayList verdadera, ArrayList textoPregunta) {
        initComponents();

        setSize(700, 300);

        preguntaL.setText(pregunta);
        panelPregunta.add(preguntaL);

        if (isNumber(respuestas.get(1).toString())) {
            agregarRespuestas1(textoPregunta);
        } else {
            agregarRespuestas2(respuestas);
        }

        aceptarL.addMouseListener(this);
        aceptarL.setIcon(imagen.getImagen());
        aceptarL.setHorizontalTextPosition(SwingConstants.CENTER);
        panelAceptar.add(aceptarL);

        principal.add(panelPregunta);
        principal.add(panelRespuestas);
        principal.add(panelAceptar);

        setContentPane(principal);

        setVisible(true);
    }

    public void agregarRespuestas1(ArrayList textoPregunta) {

        panelRespuestas = new JPanel(new GridLayout(1, textoPregunta.size()));

        for (int i = 0; i < textoPregunta.size(); i++) {
            JPanel panel = new JPanel(new FlowLayout());
            JLabel label = new JLabel();
            respuestasField.add(new JTextField(11));
            label.setText(textoPregunta.get(i).toString());
            panel.add(label);
            panel.add(respuestasField.get(i));
            panelRespuestas.add(panel);
        }
    }

    public void agregarRespuestas2(ArrayList respuestas) {

        panelRespuestas = new JPanel(new GridLayout(1, respuestas.size()));

        for (int i = 0; i < respuestas.size(); i++) {
            JPanel panel = new JPanel(new FlowLayout());
            respuestasRButton.add(new JRadioButton(respuestas.get(i).toString()));
            panel.add(respuestasRButton.get(i));
            panelRespuestas.add(panel);
        }

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

    public void validar() {

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
