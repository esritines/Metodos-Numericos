package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class rbuttons extends javax.swing.JFrame implements ActionListener {

    JPanel panel;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    JPanel panel6;

    ButtonGroup grupo;
    JRadioButton r1;
    JRadioButton r2;
    JRadioButton r3;
    JRadioButton r4;
    JRadioButton r5;

    JButton button;

    public rbuttons() {
        initComponents();

        panel = new JPanel(new GridLayout(6, 1));
        panel1 = new JPanel(new FlowLayout());
        panel2 = new JPanel(new FlowLayout());
        panel3 = new JPanel(new FlowLayout());
        panel4 = new JPanel(new FlowLayout());
        panel5 = new JPanel(new FlowLayout());
        panel6 = new JPanel(new FlowLayout());
        
        grupo = new ButtonGroup();

        r1 = new JRadioButton("Soy 1");
        r2 = new JRadioButton("Soy 2");
        r3 = new JRadioButton("Soy 3");
        r4 = new JRadioButton("Soy 4");
        r5 = new JRadioButton("Soy 5");

        button = new JButton("Aceptar");
        button.addActionListener(this);
        
        panel1.add(r1);
        panel2.add(r2);
        panel3.add(r3);
        panel4.add(r4);
        panel5.add(r5);
        panel6.add(button);

        grupo.add(r1);
        grupo.add(r2);
        grupo.add(r3);
        grupo.add(r4);
        grupo.add(r5);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);

        setContentPane(panel);

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
        String temp = Arrays.toString(r1.getSelectedObjects());
        
        if(temp.equals("Soy 3")){
            System.out.println("Acertaste");
        }   
    }
}
