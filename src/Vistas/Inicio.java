package Vistas;

import Controladores.Datos;
import Controladores.Fuente;
import Controladores.Imagenes;
import Controladores.ReproducirSonido;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class Inicio extends javax.swing.JFrame implements MouseListener {
    
    private Fuente fuente;
    private Font sizedFuente;
    
    private ReproducirSonido sonidoClic;

    private JLabel principal = new JLabel();
    private JLabel secundario = new JLabel();

    private JLabel panel1 = new JLabel();
    private JLabel panel2 = new JLabel();
    private JLabel panel3 = new JLabel();
    private JLabel panel4 = new JLabel();
    private JLabel panel5 = new JLabel();

    private JLabel bienvenido = new JLabel("Bienvenido");
    private JLabel jugar = new JLabel("Jugar");
    private JLabel records = new JLabel("Records");
    private JLabel preguntaNueva = new JLabel("Pregunta nueva");
    private JLabel atras = new JLabel("Atras");

    private Entrar regresar;
    private Puzzle puzzle;
    
    private Registros record;
    private Datos datos;
    private AgregarPregunta nuevaPregunta;
    
    private ArrayList<String> listaUsuarios;
    private ArrayList<Integer> listaPuntos;

    public Inicio() {

        principal.setLayout(new BorderLayout());
        principal.setIcon(Imagenes.fondo);
        secundario.setLayout(new GridLayout(5, 0));
        
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout());
//        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());
        
        fuente = new Fuente();
        sizedFuente = fuente.getFont().deriveFont(56f);
        
        setVisible(false);
        initComponents();

        bienvenido.setFont(sizedFuente);
        bienvenido.setForeground(Color.black);
        
        sizedFuente = fuente.getFont().deriveFont(17f);

        jugar.addMouseListener(this);
        jugar.setFont(sizedFuente);
        jugar.setIcon(Imagenes.si);
        jugar.setHorizontalTextPosition(SwingConstants.CENTER);
        jugar.setForeground(Color.black);

        records.addMouseListener(this);
        records.setFont(sizedFuente);
        records.setIcon(Imagenes.usuarios);
        records.setHorizontalTextPosition(SwingConstants.CENTER);
        records.setForeground(Color.black);

        atras.addMouseListener(this);
        atras.setFont(sizedFuente);
        atras.setIcon(Imagenes.atras);
        atras.setHorizontalTextPosition(SwingConstants.CENTER);
        atras.setForeground(Color.black);
        
        sizedFuente = fuente.getFont().deriveFont(15f);
//        preguntaNueva.addMouseListener(this);
//        preguntaNueva.setFont(sizedFuente);
//        preguntaNueva.setIcon(Imagenes.preguntas);
//        preguntaNueva.setHorizontalTextPosition(SwingConstants.CENTER);
//        preguntaNueva.setForeground(Color.black);

        panel1.add(bienvenido);
        panel2.add(jugar);
        panel3.add(records);
//        panel4.add(preguntaNueva);
        panel5.add(atras);

        secundario.add(panel1);
        secundario.add(panel2);
        secundario.add(panel3);
//        secundario.add(panel4);
        secundario.add(new JLabel());
        secundario.add(panel5);

        principal.add("Center", secundario);
        setContentPane(principal);
        
        record = new Registros();
        record.regresar(this);
        datos = new Datos();
        nuevaPregunta = new AgregarPregunta();
        nuevaPregunta.regresar(this);

        listaUsuarios = new ArrayList();
        listaPuntos = new ArrayList();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
            .addGap(0, 293, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(jugar)) {
            setVisible(false);          
            puzzle = new Puzzle(85, 3);
            puzzle.setValor(false);
            puzzle.regresar(this);
        }
        if (e.getSource().equals(records)) {
            datos.traerRegistros(listaUsuarios, listaPuntos);
            record.insertarRegistro(listaUsuarios, listaPuntos);
            listaUsuarios.clear();
            listaPuntos.clear();
            setVisible(false);
            record.setVisible(true);
        }
        if (e.getSource().equals(atras)) {
            this.setVisible(false);
            regresar.setVisible(true);
        }
        if (e.getSource().equals(preguntaNueva)) {
            this.setVisible(false);
            nuevaPregunta.setVisible(true);
            JOptionPane.showMessageDialog(principal, "Recuerda que la primera respuesta sera la verdadera", "", JOptionPane.INFORMATION_MESSAGE);
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
