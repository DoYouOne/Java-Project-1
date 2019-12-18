/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Manufacturing.GUI_Login;
import Manufacturing.Koneksi;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MikuniTensai
 */
public class SDM extends javax.swing.JFrame {

    //Connection con;
    Statement stat;
    ResultSet rs;
    String sql;

    /**
     * Creates new form SDM
     */
    public SDM() {
        initComponents();
    }

    public Connection conn;

    public void Koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_inventory?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SDM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(SDM.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(SDM.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    
/*
    //Open Gate di initComponents
    public void gate() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("Username");
        tabelhead.addColumn("Password");
        tabelhead.addColumn("Hak Akses");
        try {
            Koneksi();
            String sql = "SELECT * FROM permission";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3),});
            }
            table.setModel(tabelhead);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }*/
    
    public void refresh() {
        new SDM().setVisible(true);
        this.setVisible(false);
    }

    public void insert() {
        String user = txt_username.getText();
        String pass = txt_password.getText();
        try {
            Koneksi();
            String permission = Integer.toString(txt_pilih.getSelectedIndex());
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO permission(username, password, permission)" + "values('" + user + "','" + pass + "','" + permission + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Register successfully, please login");
            GUI_Login obj = new GUI_Login();
            obj.show();
            this.dispose();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "There is something wrong");
            refresh();
        }
    }

    public void cari() {
        String akses = null;
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("Username");
        tabelhead.addColumn("Password");
        tabelhead.addColumn("Hak Akses");
        if (jComboBox2.getSelectedIndex() == 0) {
            String usernm = jTextField3.getText();
            try {
                Koneksi();
                Statement statement = conn.createStatement();
                String sql = "SELECT * FROM permission WHERE username like '%" + usernm + "%'";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    txt_username.setText(rs.getString(2));
                    txt_password.setText(rs.getString(3));
                    if (Integer.parseInt(rs.getString(4)) == 0) {
                        akses = "Admin";
                    } else if (Integer.parseInt(rs.getString(4)) == 1) {
                        akses = "Marketing";
                    } else if (Integer.parseInt(rs.getString(4)) == 2) {
                        akses = "Produksi";
                    } else if (Integer.parseInt(rs.getString(4)) == 3) {
                        akses = "Inventory";
                    } else if (Integer.parseInt(rs.getString(4)) == 4) {
                        akses = "Finance";
                    } else if (Integer.parseInt(rs.getString(4)) == 5) {
                        akses = "HRD";
                    }
                    tabelhead.addRow(new Object[]{rs.getString(2), rs.getString(3), akses});
                }
                table.setModel(tabelhead);
                statement.close();
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                System.out.println("Error." + e);
            }
        }
    }

    public void delete() {
        int Confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (Confirmation == 0) {
            try {
                String sql = "DELETE FROM permission WHERE username='" + txt_username.getText() + "'";
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

    public void update() {
        String cari = jTextField3.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();
        try {
            String permission = Integer.toString(txt_pilih.getSelectedIndex());
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE permission SET username='" + username + "'," + "password='" + password + "',permission='" + permission + "' " + "WHERE username = '" + username + "'");
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Berhasil Merubah Transaksi!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        refresh();
    }

    public void clear() {
        txt_username.setText("");
        txt_password.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_username = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_password = new javax.swing.JTextField();
        txt_pilih = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txt_input = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("DATA MANAGEMENT");

        txt_id.setText("Username");

        jLabel2.setText("Password");

        txt_pilih.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Marketing", "Produksi", "Inventory", "Finance", "HRD" }));
        txt_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pilihActionPerformed(evt);
            }
        });

        jLabel3.setText("Hak Akses");

        txt_input.setText("Input");
        txt_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inputActionPerformed(evt);
            }
        });

        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Cancel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel8.setText("Cari Data");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Username", "Password", "Hak Akses" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Data");

        jButton9.setText("Cari");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Password", "Hak Akses"
            }
        ));
        jScrollPane3.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_id)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_username)
                                    .addComponent(txt_password)
                                    .addComponent(txt_pilih, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5))
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField3)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_input, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton8)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel6)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_pilih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_input)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton6))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        cari();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        delete();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        update();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inputActionPerformed
        insert();
    }//GEN-LAST:event_txt_inputActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pilihActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pilihActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(SDM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SDM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SDM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SDM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SDM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable table;
    private javax.swing.JLabel txt_id;
    private javax.swing.JButton txt_input;
    private javax.swing.JTextField txt_password;
    private javax.swing.JComboBox<String> txt_pilih;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables

}
