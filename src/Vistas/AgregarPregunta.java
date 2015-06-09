package Vistas;

import Controladores.Datos;
import Controladores.Fuente;
import Controladores.Imagenes;
import Controladores.ReproducirSonido;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AgregarPregunta extends JFrame implements MouseListener {

    private ReproducirSonido sonido;
    private Fuente fuente = new Fuente();
    private Font sizedFuente;

    private JLabel principal = new JLabel();
    private JLabel secundario = new JLabel();
    private JLabel campoPregunta = new JLabel();

    private final JLabel pregunta = new JLabel("Pregunta");
    private final JLabel atras = new JLabel("Atras");
    private final JLabel aceptar = new JLabel("Aceptar");
    private JTextField preguntaT = new JTextField(80);
    private JLabel respuestasL[] = new JLabel[4];
    private JTextField respuestasT[] = new JTextField[4];

    private JLabel campoBotones = new JLabel();

    private Inicio regresar;

    public AgregarPregunta() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        sizedFuente = fuente.getFont().deriveFont(42f).deriveFont(Font.BOLD);

        principal.setLayout(new BorderLayout());
        principal.setIcon(Imagenes.fondo);
        secundario.setLayout(new GridLayout(8, 1));

        pregunta.setForeground(Color.white);
        pregunta.setFont(sizedFuente);

        sizedFuente = fuente.getFont().deriveFont(23f).deriveFont(Font.BOLD);
        preguntaT.setFont(sizedFuente);

        campoBotones.setLayout(new FlowLayout());
        campoBotones.add(atras);
        campoBotones.add(aceptar);

        campoPregunta.setLayout(new FlowLayout());
        campoPregunta.add(pregunta);
        campoPregunta.add(preguntaT);

        secundario.add(new JLabel());
        secundario.add(campoPregunta);

        for (int i = 0; i < 4; i++) {
            JLabel tempL = new JLabel();
            tempL.setLayout(new FlowLayout());
            respuestasL[i] = new JLabel("Respuesta " + (i + 1));
            respuestasT[i] = new JTextField(30);
            respuestasL[i].setFont(sizedFuente);
            respuestasL[i].setForeground(Color.white);
            respuestasT[i].setFont(sizedFuente);
            tempL.add(respuestasL[i]);
            tempL.add(respuestasT[i]);
            secundario.add(tempL);
        }

        sizedFuente = fuente.getFont().deriveFont(20f);

        atras.addMouseListener(this);
        atras.setIcon(Imagenes.atras);
        atras.setHorizontalTextPosition(SwingConstants.CENTER);
        atras.setFont(sizedFuente);
        atras.setForeground(Color.white);

        aceptar.addMouseListener(this);
        aceptar.setIcon(Imagenes.si);
        aceptar.setHorizontalTextPosition(SwingConstants.CENTER);
        aceptar.setFont(sizedFuente);
        aceptar.setForeground(Color.white);
        secundario.add(campoBotones);
        secundario.add(new JLabel());

        principal.add("Center", secundario);

        setContentPane(principal);
        setTitle("Agregar pregunta");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        repaint();
    }

    public void regresar(Inicio regresar) {
        this.regresar = regresar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        sonido = new ReproducirSonido(2);
        sonido.getSonido().start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getSource().equals(atras)) {
            setVisible(false);
            regresar.setVisible(true);

        }
        if (e.getSource().equals(aceptar)) {
            String temp[] = new String[4];
            for (int i = 0; i < respuestasT.length; i++) {
                temp[i] = respuestasT[i].getText();
            }
            if (Datos.insertarPregunta(preguntaT.getText(), temp)) {
                JOptionPane.showMessageDialog(principal, "Se agrego la pregunta con exito");
            } else {
                JOptionPane.showMessageDialog(principal, "No se encuentra la base de datos");
            }
        }
        preguntaT.setText("");
        for (int i = 0; i < 4; i++) {
            respuestasT[i].setText("");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}