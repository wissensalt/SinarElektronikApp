/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.report.view;

import com.wissensalt.sinarelektronik.masterdata.report.core.ReportParam;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class ReportProfit extends javax.swing.JPanel {

    /**
     * Creates new form ReportProfit
     */
    public ReportProfit() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTransaksi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTanggalAwal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalAkhir = new com.toedter.calendar.JDateChooser();
        btProfit = new javax.swing.JButton();
        btDetailProfit = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        panelTransaksi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Laporan Profit"));
        panelTransaksi.setMinimumSize(new java.awt.Dimension(400, 200));
        panelTransaksi.setPreferredSize(new java.awt.Dimension(400, 200));
        panelTransaksi.setLayout(new java.awt.GridLayout(3, 2));

        jLabel1.setText("Tanggal Awal :");
        panelTransaksi.add(jLabel1);
        panelTransaksi.add(txtTanggalAwal);

        jLabel2.setText("Tanggal Akhir :");
        panelTransaksi.add(jLabel2);
        panelTransaksi.add(txtTanggalAkhir);

        btProfit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/profitTombol.png"))); // NOI18N
        btProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProfitActionPerformed(evt);
            }
        });
        panelTransaksi.add(btProfit);

        btDetailProfit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/detailprofit.png"))); // NOI18N
        btDetailProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDetailProfitActionPerformed(evt);
            }
        });
        panelTransaksi.add(btDetailProfit);

        add(panelTransaksi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btDetailProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDetailProfitActionPerformed
        if(txtTanggalAwal.getDate()==null){
            txtTanggalAwal.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Awal masih kosong");
        }else if (txtTanggalAkhir.getDate() == null){
            txtTanggalAkhir.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Akhir masih kosong");
        }
        else{
            ReportParam rp=new ReportParam();
            rp.buildReportDoubleParam("profitdetail", txtTanggalAwal.getDate(), txtTanggalAkhir.getDate());
        }
    }//GEN-LAST:event_btDetailProfitActionPerformed

    private void btProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProfitActionPerformed
        if(txtTanggalAwal.getDate()==null){
            txtTanggalAwal.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Awal masih kosong");
            return;
        }else if (txtTanggalAkhir.getDate() == null){
            txtTanggalAkhir.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Akhir masih kosong");
            return;
        }
        else{
            ReportParam rp=new ReportParam();
            rp.buildReportDoubleParam("Profit", txtTanggalAwal.getDate(), txtTanggalAkhir.getDate());
        }
    }//GEN-LAST:event_btProfitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDetailProfit;
    private javax.swing.JButton btProfit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelTransaksi;
    private com.toedter.calendar.JDateChooser txtTanggalAkhir;
    private com.toedter.calendar.JDateChooser txtTanggalAwal;
    // End of variables declaration//GEN-END:variables
}