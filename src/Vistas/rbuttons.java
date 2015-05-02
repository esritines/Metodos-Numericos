package Vistas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class rbuttons extends javax.swing.JFrame implements ActionListener {

    private final JPanel principal;
    private final JPanel panelButton;

    private final ButtonGroup grupo = new ButtonGroup();
    ;
    private final ArrayList<JRadioButton> listaRadioButton = new ArrayList<>();

    private final JButton button;

    public rbuttons() {
        initComponents();

        principal = new JPanel(new GridLayout(6, 1));

        for (int i = 0; i < 5; i++) {
            listaRadioButton.add(new JRadioButton("Soy " + (i + 1)));
            grupo.add(listaRadioButton.get(i));
            JPanel panel = new JPanel(new FlowLayout());
            panel.add(listaRadioButton.get(i));
            principal.add(panel);
        }

        panelButton = new JPanel(new FlowLayout());
        button = new JButton("Aceptar");
        button.addActionListener(this);
        panelButton.add(button);
        principal.add(button);

        setContentPane(principal);
        setVisible(true);
    }

    public static void main(String[] args) {
        rbuttons rbutton = new rbuttons();
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
    public void actionPerformed(ActionEvent e) {
        boolean valor = false;
        String respuesta;

        for (int i = 0; i < listaRadioButton.size(); i++) {
            respuesta = Arrays.toString(listaRadioButton.get(i).getSelectedObjects());
            respuesta = respuesta.substring(1, respuesta.length() - 1);
            System.out.println(respuesta);
            if (respuesta.equals("Soy 3")) {
                valor = true;
                break;
            } else {
                valor = false;
            }     
        }
        if (valor) {
                JOptionPane.showMessageDialog(panelButton, "Acertaste");
            } else {
                JOptionPane.showMessageDialog(panelButton, "Fallaste");
            }
    }
}
