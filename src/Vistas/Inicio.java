package Vistas;

//import Controladores.Fuente;
import Controladores.Datos;
import java.util.ArrayList;

public class Inicio extends javax.swing.JFrame {

//    private static final Fuente fuente = new Fuente();
    private Entrar regresar;
    private Puzzle puzzle;
    private nuevoUsuario usuario;
    private Registros record;
    private Datos datos;
    private ArrayList<String> listaUsuarios;
    private ArrayList<Integer> listaPuntos;

    public Inicio() {

        initComponents();
        setVisible(false);
        setTitle("Inicio");

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
    }

    public void regresar(Entrar regresar) {
        this.regresar = regresar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jugar = new javax.swing.JButton();
        nuevoUsuario = new javax.swing.JButton();
        records = new javax.swing.JButton();
        atras = new javax.swing.JButton();
        bienvenido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jugar.setText("Jugar");
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });

        nuevoUsuario.setText("Nuevo Usuario");
        nuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoUsuarioActionPerformed(evt);
            }
        });

        records.setText("Records");
        records.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordsActionPerformed(evt);
            }
        });

        atras.setText("Atras");
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(atras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nuevoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(records, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jugar)
                .addGap(18, 18, 18)
                .addComponent(records)
                .addGap(18, 18, 18)
                .addComponent(nuevoUsuario)
                .addGap(18, 18, 18)
                .addComponent(atras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bienvenido.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        bienvenido.setText("¡Bienvenido!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(bienvenido))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bienvenido)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed

        this.setVisible(false);
        regresar.setVisible(true);
    }//GEN-LAST:event_atrasActionPerformed

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        setVisible(false);
        puzzle = new Puzzle(50, 0, 3, 300);
        puzzle.setValor(false);
        puzzle.regresar(this);

    }//GEN-LAST:event_jugarActionPerformed

    private void nuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoUsuarioActionPerformed
        setVisible(false);
        usuario.setVisible(true);
    }//GEN-LAST:event_nuevoUsuarioActionPerformed

    private void recordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordsActionPerformed
        datos.traerRegistros(listaUsuarios, listaPuntos);
        record.insertarRegistro(listaUsuarios, listaPuntos);
        listaUsuarios.clear();
        listaPuntos.clear();
        setVisible(false);
        record.setVisible(true);
    }//GEN-LAST:event_recordsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras;
    private javax.swing.JLabel bienvenido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jugar;
    private javax.swing.JButton nuevoUsuario;
    private javax.swing.JButton records;
    // End of variables declaration//GEN-END:variables
}
