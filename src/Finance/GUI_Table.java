/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author MikuniTensai
 */
public class GUI_Table extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Table
     */
    public GUI_Table() {
        initComponents();
        pesanan();
    }
    
    public Connection conn;
    public void Koneksi() throws SQLException {
        try {
            conn=null;
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost/db_inventory?user=root&password=");
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Table.class.getName()).log(Level.SEVERE,null, ex);
        }catch (SQLException e) {
            Logger.getLogger(GUI_Table.class.getName()).log(Level.SEVERE,null, e);
        }catch (Exception es) {
            Logger.getLogger(GUI_Table.class.getName()).log(Level.SEVERE,null, es);
        }
    }
    
    public void refresh(){
        new GUI_Table().setVisible(true);
        this.setVisible(false);
    }
    
    public void pesanan(){
        try{
            Koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `pesanan`";
            ResultSet rs = statement.executeQuery(sql);
            cmb_pesan.addItem("Pilih salah satu");
            while(rs.next()){
                Object[] obj = new Object[3];
                obj[0] = rs.getString("id_pesanan");
                
                cmb_pesan.addItem((String)obj[0]);
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
        }
    }
    
    public void tabel(){
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("ID Pesanan");
        tabelhead.addColumn("Nama Pembeli");
        tabelhead.addColumn("Ukuran Barang");
        tabelhead.addColumn("Alamat");
        tabelhead.addColumn("Jumlah");
        try {
            Koneksi();
            Statement stt = conn.createStatement();
            String sql = "select * from pesanan where id_pesanan='"+cmb_pesan.getSelectedItem()+"'";  
            ResultSet res = stt.executeQuery(sql);

            if(res.next()){
               tabelhead.addRow(new Object[]{res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)});
            }
            tb_pesan.setModel(tabelhead);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }              
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pesan = new javax.swing.JTable();
        cmb_pesan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID Pesanan");

        tb_pesan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Pesanan", "Nama Pembeli", "Ukuran Barang", "Alamat", "Jumlah"
            }
        ));
        jScrollPane1.setViewportView(tb_pesan);

        cmb_pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_pesanActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Data Pemesanan Barang");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addComponent(cmb_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmb_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_pesanActionPerformed
        // TODO add your handling code here:
        tabel();
    }//GEN-LAST:event_cmb_pesanActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Table().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_pesan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_pesan;
    // End of variables declaration//GEN-END:variables
}
