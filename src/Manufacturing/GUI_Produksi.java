/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manufacturing;

import Finance.GUI_Pemesanan;
import Inventory.GUI_PemesananBB;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mikunitensai
 */
public class GUI_Produksi extends javax.swing.JFrame {

    /**
     * Creates new form Produksi
     */
    public GUI_Produksi() {
        initComponents();
        id_pesan();
        bb();
    }

    public Connection conn;

    public void Koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_inventory?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Produksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_Produksi.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_Produksi.class.getName()).log(Level.SEVERE, null, es);
        }
    }

    public void refresh() {
        new GUI_Produksi().setVisible(true);
        this.setVisible(false);
    }

    public void id_pesan() {
        try {
            Koneksi();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM `pesanan` order by id_pesanan desc";
            ResultSet rs = statement.executeQuery(sql);
            cmb_pesan.addItem("Pilih salah satu");
            while (rs.next()) {
                Object[] obj = new Object[3];
                obj[0] = rs.getString("id_pesanan");
                cmb_pesan.addItem(obj[0]);
            }
            statement.close();
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }

    public void detail() {
        try {
            Koneksi();
            Statement stt = conn.createStatement();
            String sql = "select * from pesanan where id_pesanan='" + cmb_pesan.getSelectedItem() + "'";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(3);
                ob[1] = res.getString(4);
                txt_pembeli.setText((String) ob[0]);
                if ("Kecil".equals(ob[1])) {
                    txt_ongkos.setText("120000");
                } else if ("Seang".equals(ob[1])) {
                    txt_ongkos.setText("150000");
                } else if ("Besar".equals(ob[1])) {
                    txt_ongkos.setText("200000");
                }
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void bb() {
        try {
            Koneksi();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM `barang`";
            ResultSet rs = statement.executeQuery(sql);
            cmb_bb.addItem("Pilih salah satu");
            while (rs.next()) {
                Object[] obj = new Object[3];
                obj[0] = rs.getString("kode_bhn");
                obj[1] = "Bahan " + rs.getString("nama_bhn");
                cmb_bb.addItem(obj[1]);
            }
            statement.close();
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }

    public void process() {
        String nama = txt_pembeli.getText();
        int ongkos = Integer.parseInt(txt_ongkos.getText());
        Object pesan = cmb_pesan.getSelectedItem();
        Object bb = cmb_bb.getSelectedItem();
        try {
            Koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Insert into rencana_produksi set id_pesanan = '" + pesan + "', ongkos= '" + ongkos + "', bahan_baku = '" + bb + "', nama = '" + nama + "'");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil memesan!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            refresh();
        }
    }
    
    public void search(){
        String barang = null;
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("ID Pesanan");
        tabelhead.addColumn("Ongkos");
        tabelhead.addColumn("Bahan Baku");
        tabelhead.addColumn("Nama");
        try {
            try (Statement statement = conn.createStatement()) {
                Koneksi();
                String sql = "select * from rencana_produksi where `id_pesanan` like '%" + txt_cari.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    txt_pembeli.setText(rs.getString(1));
                    txt_ongkos.setText(rs.getString(2));
                    while (rs.next()) {
                        tabelhead.addRow(new Object[]{rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)});
                    }
                    table1.setModel(tabelhead);
                    statement.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Barang yang Anda cari tidak ada");
                }
            }
        } catch (HeadlessException | SQLException ex) {
            System.out.println("Error." + ex);
        }
    }

    public void delete() {
        int Confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (Confirmation == 0) {
            try {
                String sql = "DELETE FROM rencana_produksi WHERE id_pesanan='" + txt_cari.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Delete successful");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Can not be deleted");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please check again");
        }
        refresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_pembeli = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_ongkos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmb_pesan = new javax.swing.JComboBox();
        cmb_bb = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("PERENCAAN PRODUKSI");

        jLabel2.setText("Nama Pembeli            :");

        jLabel3.setText("ID Pemesanan            :");

        txt_pembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pembeliActionPerformed(evt);
            }
        });

        jButton1.setText("PROSES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Ongkos Kerja             :");

        txt_ongkos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ongkosActionPerformed(evt);
            }
        });

        jLabel5.setText("Bahan Baku                :");

        cmb_pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_pesanActionPerformed(evt);
            }
        });

        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setText("__________________________________________________________________");

        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pesanan", "Nama", "Bahan Baku", "Ongkos"
            }
        ));
        jScrollPane2.setViewportView(table1);

        jButton2.setText("Cari ID Pesanan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_ongkos, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_pesan, javax.swing.GroupLayout.Alignment.LEADING, 0, 260, Short.MAX_VALUE)
                            .addComponent(txt_pembeli, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_bb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(29, 29, 29))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(123, 123, 123))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel2)
                    .addContainerGap(325, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(txt_pembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cmb_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ongkos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmb_bb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(jLabel2)
                    .addContainerGap(422, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_pembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pembeliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        process();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_ongkosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ongkosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ongkosActionPerformed

    private void cmb_pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_pesanActionPerformed
        detail();
    }//GEN-LAST:event_cmb_pesanActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handlin code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        search();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
    }//GEN-LAST:event_txt_cariActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        delete();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Produksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Produksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Produksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Produksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Produksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_bb;
    private javax.swing.JComboBox cmb_pesan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_ongkos;
    private javax.swing.JTextField txt_pembeli;
    // End of variables declaration//GEN-END:variables
}