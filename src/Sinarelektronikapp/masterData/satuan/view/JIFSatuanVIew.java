/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.satuan.view;

/**
 *
 * @author Fauzi
 */
public class JIFSatuanVIew extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFSatuanVIew
     */
    public JIFSatuanVIew() {
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

        try {
            satuanView1 = new Sinarelektronikapp.masterData.satuan.view.SatuanView();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        } catch (Sinarelektronikapp.masterData.satuan.error.SatuanException e2) {
            e2.printStackTrace();
        }

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manajemen Satuan");
        setPreferredSize(new java.awt.Dimension(400, 300));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().add(satuanView1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Sinarelektronikapp.masterData.satuan.view.SatuanView satuanView1;
    // End of variables declaration//GEN-END:variables
}
