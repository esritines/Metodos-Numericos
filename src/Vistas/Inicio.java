package Vistas;

//import Controladores.Fuente;
import Controladores.Datos;
import Controladores.Imagenes;
import Controladores.ReproducirSonido;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class Inicio extends javax.swing.JFrame implements MouseListener {

//    private static final Fuente fuente = new Fuente();
    
    private ReproducirSonido sonidoClic;
    
    private Imagenes imagen = new Imagenes(4);
    
    private static Font font = new Font("Courier", Font.BOLD, 46);
    
    private JPanel principal = new JPanel(new BorderLayout());
    private JPanel secundario = new JPanel(new GridLayout(5, 0));

    private JPanel panel1 = new JPanel(new FlowLayout());
    private JPanel panel2 = new JPanel(new FlowLayout());
    private JPanel panel3 = new JPanel(new FlowLayout());
    private JPanel panel4 = new JPanel(new FlowLayout());
    private JPanel panel5 = new JPanel(new FlowLayout());
    
    private JLabel bienvenido = new JLabel("¡Bienvenido!");
    private JLabel jugar = new JLabel("Jugar");
    private JLabel records = new JLabel("Records");
    private JLabel nuevoUsuario = new JLabel("Nuevo Usuario");
    private JLabel atras = new JLabel("Atras");

    private Entrar regresar;
    private Puzzle puzzle;
    private nuevoUsuario usuario;
    private Registros record;
    private Datos datos;
    private ArrayList<String> listaUsuarios;
    private ArrayList<Integer> listaPuntos;

    public Inicio() {

        setVisible(false);
        initComponents();
        
        bienvenido.setFont(font);
        
        jugar.addMouseListener(this);
        jugar.setIcon(imagen.getImagen());
        jugar.setHorizontalTextPosition(SwingConstants.CENTER);
        
        records.addMouseListener(this);
        records.setIcon(imagen.getImagen());
        records.setHorizontalTextPosition(SwingConstants.CENTER);
        
        nuevoUsuario.addMouseListener(this);
        nuevoUsuario.setIcon(imagen.getImagen());
        nuevoUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
        
        atras.addMouseListener(this);
        atras.setIcon(imagen.getImagen());
        atras.setHorizontalTextPosition(SwingConstants.CENTER);
        
        panel1.add(bienvenido);
        panel2.add(jugar);
        panel3.add(records);
        panel4.add(nuevoUsuario);
        panel5.add(atras);
        
        secundario.add(panel1);
        secundario.add(panel2);
        secundario.add(panel3);
        secundario.add(panel4);
        secundario.add(panel5);

        principal.add("Center", secundario);
        setContentPane(principal);

        usuario = new nuevoUsuario();
        usuario.regresar(this);
        record = new Registros();
        record.regresar(this);
        datos = new Datos();

        listaUsuarios = new ArrayList();
        listaPuntos = new ArrayList();

//        jugar.setFont(fuente.getFont());
//        records.setFont(fuente.getFont());
//        nuevoUsuario.setFont(fuente.getFont());
//        atras.setFont(fuente.getFont());
//        bienvenido.setFont(fuente.getFont());
//        repaint();
        
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
            datos.vaciarPreguntas();
            puzzle = new Puzzle(50, 0, 3);
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
        if (e.getSource().equals(nuevoUsuario)) {
            usuario.setVisible(true);
        }
        if (e.getSource().equals(atras)) {
            this.setVisible(false);
            regresar.setVisible(true);
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
