package Inventory;
import Manufacturing.GUI_Produksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author PDA
 */
public class GUI_Penyimpanan extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Login
     */

    public GUI_Penyimpanan() {
        initComponents();
        polishing();
        foaming();
        moistener();
        pengikat();
        pemanis();
        pemberiRasa();
        pengawet();
        flouride();
    }
    
    public Connection conn;
    public void koneksi() throws SQLException {
        try {
            conn=null;
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost/db_inventory?user=root&password=");
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Penyimpanan.class.getName()).log(Level.SEVERE,null, ex);
        }catch (SQLException e) {
            Logger.getLogger(GUI_Penyimpanan.class.getName()).log(Level.SEVERE,null, e);
        }catch (Exception es) {
            Logger.getLogger(GUI_Penyimpanan.class.getName()).log(Level.SEVERE,null, es);
        }
    }
    
    public void polishing() {
        try{
            koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `barang` WHERE kode_bhn = 'A0001'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
             txt_polishing.setText(rs.getString(3));   
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
        }
    }
    
    public void foaming() {
        try{
            koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `barang` WHERE kode_bhn = 'A0002'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
             txt_foaming.setText(rs.getString(3));   
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
        }
    }
    
    public void moistener() {
        try{
            koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `barang` WHERE kode_bhn = 'A0003'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
             txt_moistener.setText(rs.getString(3));   
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
        }
    }
    
    public void pengikat() {
        try{
            koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `barang` WHERE kode_bhn = 'A0004'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
             txt_pengikat.setText(rs.getString(3));   
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
        }
    }
    
    public void pemanis() {
        try{
            koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `barang` WHERE kode_bhn = 'A0005'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
             txt_pemanis.setText(rs.getString(3));   
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
        }
    }
    
    public void pemberiRasa() {
        try{
            koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `barang` WHERE kode_bhn = 'A0006'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
             txt_rasa.setText(rs.getString(3));   
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
        }
    }
    
    public void pengawet() {
        try{
            koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `barang` WHERE kode_bhn = 'A0007'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
             txt_pengawet.setText(rs.getString(3));   
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
        }
    }
    
    public void flouride() {
        try{
            koneksi();
            Statement statement = conn.createStatement();
            String sql="SELECT * FROM `barang` WHERE kode_bhn = 'A0008'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
             txt_flouride.setText(rs.getString(3));   
            }
            statement.close();
        }catch (Exception ex){
           System.out.println("Error."+ex);
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
        jLabel2 = new javax.swing.JLabel();
        txt_polishing = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_foaming = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_moistener = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_pengikat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_pemanis = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_rasa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_pengawet = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_flouride = new javax.swing.JTextField();
        btn_pesan = new javax.swing.JButton();
        btn_buat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Data Penyimpanan Barang");

        jLabel2.setText(" Bahan Polishing                     :");

        jLabel3.setText(" Bahan Foaming                     :");

        jLabel4.setText(" Bahan Moistener                  :");

        jLabel5.setText(" Bahan Pengikat                    :");

        jLabel6.setText(" Bahan Pemanis                    :");

        jLabel7.setText(" Bahan Pemberi Rasa           :");

        jLabel8.setText(" Bahan Pengawet                 :");

        jLabel9.setText(" Bahan Flouride                    :");

        btn_pesan.setText("Pesan");
        btn_pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesanActionPerformed(evt);
            }
        });

        btn_buat.setText("Buat");
        btn_buat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_polishing))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btn_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_buat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_foaming)
                                    .addComponent(txt_moistener)
                                    .addComponent(txt_pengikat)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(17, 17, 17)
                                .addComponent(txt_flouride))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_pengawet)
                                    .addComponent(txt_rasa)
                                    .addComponent(txt_pemanis))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_polishing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_foaming, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_moistener, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_pengikat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_pemanis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_rasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_pengawet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_flouride, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesanActionPerformed
        // TODO add your handling code here:
        GUI_PemesananBB obj = new GUI_PemesananBB();
        obj.show();
        this.dispose();
    }//GEN-LAST:event_btn_pesanActionPerformed

    private void btn_buatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buatActionPerformed
        // TODO add your handling code here:
        GUI_Produksi obj = new GUI_Produksi();
        obj.show();
        this.dispose();
    }//GEN-LAST:event_btn_buatActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Penyimpanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Penyimpanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Penyimpanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Penyimpanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Penyimpanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buat;
    private javax.swing.JButton btn_pesan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txt_flouride;
    private javax.swing.JTextField txt_foaming;
    private javax.swing.JTextField txt_moistener;
    private javax.swing.JTextField txt_pemanis;
    private javax.swing.JTextField txt_pengawet;
    private javax.swing.JTextField txt_pengikat;
    private javax.swing.JTextField txt_polishing;
    private javax.swing.JTextField txt_rasa;
    // End of variables declaration//GEN-END:variables
}
