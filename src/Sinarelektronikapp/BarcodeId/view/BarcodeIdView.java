/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.BarcodeId.view;

/**
 *
 * @author Fauzi
 */
public class BarcodeIdView extends javax.swing.JPanel {

    /**
     * Creates new form BarcodeIdView
     */
    public BarcodeIdView() {
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

        up = new javax.swing.JPanel();
        atas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbKodeBarang = new javax.swing.JComboBox();
        tengah = new javax.swing.JPanel();
        lblPreviewBarang = new javax.swing.JLabel();
        bawah = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarcodeId = new javax.swing.JTable();
        down = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        up.setLayout(new java.awt.BorderLayout());

        atas.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Pilih Kode Barang :");
        atas.add(jLabel1, java.awt.BorderLayout.WEST);

        cmbKodeBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        atas.add(cmbKodeBarang, java.awt.BorderLayout.CENTER);

        up.add(atas, java.awt.BorderLayout.PAGE_START);

        tengah.setLayout(new java.awt.BorderLayout());
        tengah.add(lblPreviewBarang, java.awt.BorderLayout.CENTER);

        up.add(tengah, java.awt.BorderLayout.CENTER);

        bawah.setLayout(new java.awt.BorderLayout());

        tabelBarcodeId.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelBarcodeId);

        bawah.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        up.add(bawah, java.awt.BorderLayout.PAGE_END);

        add(up, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout downLayout = new javax.swing.GroupLayout(down);
        down.setLayout(downLayout);
        downLayout.setHorizontalGroup(
            downLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        downLayout.setVerticalGroup(
            downLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(down, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atas;
    private javax.swing.JPanel bawah;
    private javax.swing.JComboBox cmbKodeBarang;
    private javax.swing.JPanel down;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPreviewBarang;
    private javax.swing.JTable tabelBarcodeId;
    private javax.swing.JPanel tengah;
    private javax.swing.JPanel up;
    // End of variables declaration//GEN-END:variables
}
