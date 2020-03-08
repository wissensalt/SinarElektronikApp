package Sinarelektronikapp.masterdata.barangbesar.controller;

import Sinarelektronikapp.penjualan.barangbesar.view.JIFKasirView2;
import Sinarelektronikapp.retur.barangbesar.view.ProsesRetur;
import Sinarelektronikapp.transfer.barangbesar.view.JIFTransferBB;
import Sinarelektronikapp.inventory.barangbesar.view.JIFInventoryBB;
import Sinarelektronikapp.masterdata.barangbesar.entity.barang;
import Sinarelektronikapp.masterdata.barangbesar.error.BarangException;
import Sinarelektronikapp.masterdata.barangbesar.model.barangModel;
import Sinarelektronikapp.masterdata.barangbesar.view.BarangView;
import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.view.JIFBarangBonusKaryawanBBView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class BarangController {
    private barangModel model;

    public BarangController(barangModel model) {
        this.model = model;
    }

    public BarangController() {
        
    }

    public void setModel(barangModel model) {
        this.model = model;
    }    
        
    public void resetBarang(BarangView view){
        model.resetBarang();
    }
    
    
    private barang barangEntity;

    public barang getBarangEntity() {
        return barangEntity;
    }      
  public void deleteBarang(BarangView view){
        if(view.getTabelBarang().getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(view, "pilih data terlebih dahulu");
            return;
        }else{
        if(JOptionPane.showConfirmDialog(view, "apakah pelanggan dengan id "+view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0) +" akan dihapus ?") == JOptionPane.YES_OPTION){
                String id=view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0).toString();
                JOptionPane.showMessageDialog(view, "Id  = "+id);
                model.setIdBarang(id);
                JOptionPane.showMessageDialog(view, "Sudah di set Id Barang");
                try {
                    model.deleteBarang();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BarangException ex) {
                    Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    JOptionPane.showMessageDialog(view, "Sudah di delete barangkecil");
            }
        }
    }
    public void cari(BarangView  view, BarangView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();       
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
        
    }
    
    
    
/*    public void cari(Kasirview view, Kasirview v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }    */
        
/*    public void cari2(ReturView view, ReturView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }                
    }    */
   
    
    public void cariProsesReturBB(ProsesRetur view, ProsesRetur v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }                
    }            
    
    
    public void cari4(Sinarelektronikapp.retur.barangbesar.view.JIFReturViewBarangBesar view, Sinarelektronikapp.retur.barangbesar.view.JIFReturViewBarangBesar v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }                
    }            
    
    public void cari6(JIFInventoryBB view, JIFInventoryBB v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }                
    }            
    
    public void sort(BarangView view) throws SQLException, BarangException{        
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }
    }        
    
    public void sortProsesRetur(ProsesRetur view) throws SQLException, BarangException{        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }
    }            
    
    public void sort2(BarangView view) throws SQLException, BarangException{        
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }
    }            
    
/*    public void sort(Kasirview view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }*/
    
    public void sort(JIFKasirView2 view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }    
    
    public void sortBBKBB(JIFBarangBonusKaryawanBBView view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }        
    
    public void sort(JIFTransferBB view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }        
    
/*    public void sort(ReturView view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }    */
    
    public void sort(Sinarelektronikapp.retur.barangbesar.view.JIFReturViewBarangBesar view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }        
    
    
    public void sort6(JIFInventoryBB view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }    
    }            
    
    
    public void cari(JIFKasirView2 view, JIFKasirView2 v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }               
    
    public void cariBBKBGudang(JIFBarangBonusKaryawanBBView view, JIFBarangBonusKaryawanBBView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }                   
    
    public void cari(JIFTransferBB view, JIFTransferBB v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }                   

    
    
}