package Vistas;

import Controladores.Datos;
import Controladores.Fuente;
import Controladores.Imagenes;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public final class Pregunta extends javax.swing.JFrame implements MouseListener {

    private final Fuente fuente = new Fuente();
    private final Font sizedFuente = fuente.getFont().deriveFont(17f);
    ;

    private final JPanel principal = new JPanel(new GridLayout(5, 1));
    private final JPanel panelPregunta = new JPanel(new FlowLayout());
    private JLabel panelRespuestas;
    private final JPanel panelAceptar = new JPanel(new FlowLayout());

    private final JLabel aceptarL = new JLabel("Aceptar");
    private final JLabel preguntaL = new JLabel();

    private static JRadioButton radioButtons[] = new JRadioButton[4];
    private static ButtonGroup grupo = new ButtonGroup();
    private JTextField campo;

    private Puzzle puzzle;

    private boolean valor;

    public void pasarPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Pregunta() {
        initComponents();

        if (Datos.getAleatoria() != 2) {
            agregarRespuestas1();
            valor = true;
        } else {
            agregarRespuestas2();
            valor = false;
        }

        preguntaL.setText(Datos.getPregunta());
        preguntaL.setFont(sizedFuente);
        panelPregunta.add(preguntaL);

        aceptarL.setFont(sizedFuente);
        aceptarL.setForeground(Color.white);
        aceptarL.addMouseListener(this);
        aceptarL.setIcon(Imagenes.preguntas);
        aceptarL.setHorizontalTextPosition(SwingConstants.CENTER);
        panelAceptar.add(aceptarL);

        principal.add(new JLabel());
        principal.add(panelPregunta);
        principal.add(panelRespuestas);
        principal.add(panelAceptar);
        principal.add(new JLabel());

        setContentPane(principal);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    //JTextField
    public void agregarRespuestas1() {

        panelRespuestas = new JLabel();
        panelRespuestas.setLayout(new FlowLayout());

        JLabel label = new JLabel();
        label.setText(Datos.getTexto());
        label.setFont(sizedFuente);

        campo = new JTextField(10);
        campo.setFont(sizedFuente);

        panelRespuestas.add(label);
        panelRespuestas.add(campo);
    }

    //RadioButton
    public void agregarRespuestas2() {

        int temp[] = new int[4];
        boolean valorRandomRespuesta = false;

        for (int i = 0; i < 4; i++) {
            do {
                temp[i] = (int) ((Math.random() * 4) + 1);
                for (int j = 0; j != i; j++) {
                    if (temp[i] == temp[j]) {
                        valorRandomRespuesta = true;
                        break;
                    } else {
                        valorRandomRespuesta = false;
                    }
                }
            } while (valorRandomRespuesta);
        }
        
        panelRespuestas = new JLabel();
        panelRespuestas.setLayout(new GridLayout(1, 4));

        for (int i = 0; i < 4; i++) {
            JLabel label = new JLabel();
            label.setLayout(new FlowLayout());
            radioButtons[i] = new JRadioButton(Datos.getRespuestas().get(temp[i] - 1));
            radioButtons[i].setFont(sizedFuente);
            label.add(radioButtons[i]);
            grupo.add(radioButtons[i]);
            panelRespuestas.add(label);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

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
        boolean temp = false;
        String respuesta;
        if (valor) {
            if (campo.getText().equals(Datos.getRespuesta())) {
                JOptionPane.showMessageDialog(principal, "Acertaste");
                puzzle.crear();
                temp = true;
                dispose();
            }
            if (!temp) {
                JOptionPane.showMessageDialog(principal, "Fallaste");
                puzzle.perdiste();
                dispose();
            }
        } else {
            for (int i = 0; i < 4; i++) {
                respuesta = Arrays.toString(radioButtons[i].getSelectedObjects());
                respuesta = respuesta.substring(1, respuesta.length() - 1);
                if (respuesta.equals(Datos.getRespuestas().get(0))) {
                    temp = true;
                    break;
                } else {
                    temp = false;
                }
            }
            if (temp) {
                JOptionPane.showMessageDialog(principal, "Acertaste");
                puzzle.crear();
                dispose();
            } else {
                JOptionPane.showMessageDialog(principal, "Fallaste");
                puzzle.perdiste();
                dispose();
            }
        }
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
