package Vistas;

import Controladores.Fuente;
import Controladores.Imagenes;
import Controladores.ReproducirSonido;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private JTextField respuesta = new JTextField(30);
    private JLabel respuestasL[] = new JLabel[4];
    private JTextField respuestasT[] = new JTextField[4];

    private JLabel campoBotones = new JLabel();
    private JLabel atras = new JLabel("Atras");
    private JLabel aceptar = new JLabel("Aceptar");

    private Inicio regresar;

    public AgregarPregunta() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        sizedFuente = fuente.getFont().deriveFont(11f);

        principal.setLayout(new BorderLayout());

        secundario.setLayout(new GridLayout(8, 1));

        pregunta.setFont(sizedFuente);

        respuesta.setFont(sizedFuente);

        campoBotones.setLayout(new FlowLayout());
        campoBotones.add(atras);
        campoBotones.add(aceptar);

        atras.addMouseListener(this);
        atras.setIcon(Imagenes.atras);
        atras.setHorizontalTextPosition(SwingConstants.CENTER);
        atras.setFont(sizedFuente);

        aceptar.addMouseListener(this);
        aceptar.setIcon(Imagenes.si);
        aceptar.setHorizontalTextPosition(SwingConstants.CENTER);
        aceptar.setFont(sizedFuente);

        campoPregunta.setLayout(new FlowLayout());
        campoPregunta.add(pregunta);
        campoPregunta.add(respuesta);

        secundario.add(new JLabel());
        secundario.add(campoPregunta);

        for (int i = 0; i < 4; i++) {
            JLabel tempL = new JLabel();
            tempL.setLayout(new FlowLayout());
            respuestasL[i] = new JLabel("Respuesta " + (i + 1));
            respuestasT[i] = new JTextField(10);
            respuestasL[i].setFont(sizedFuente);
            respuestasT[i].setFont(sizedFuente);
            tempL.add(respuestasL[i]);
            tempL.add(respuestasT[i]);
            secundario.add(tempL);
        }

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
            respuesta.setText("");
            for (int i = 0; i < 4; i++) {
                respuestasT[i].setText("");
            }
        }
        if (e.getSource().equals(aceptar)) {

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
