/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.report.view;

/**
 *
 * @author Fauzi
 */
public class JIFReportMaster extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFReportMaster
     */
    public JIFReportMaster() {
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

        reportView1 = new Sinarelektronikapp.masterData.report.view.ReportMaster();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Laporan Data master");
        getContentPane().add(reportView1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Sinarelektronikapp.masterData.report.view.ReportMaster reportView1;
    // End of variables declaration//GEN-END:variables
}
