/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.barang2.model;

import Sinarelektronikapp.masterData.barang2.database.barangDatabase;
import Sinarelektronikapp.masterData.barang2.entity.barang;
import Sinarelektronikapp.masterData.barang2.error.BarangException;
import Sinarelektronikapp.masterData.barang2.model.event.barangListener;
import Sinarelektronikapp.masterData.barang2.service.BarangDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public class barangModel {

    private String cari, cmbcari, cmbsort;

    public barangModel() {
    }

    private String idBarang, idBarcode, namaBarang, tipe, merek, satuan, supplier, keterangan, kategori;

    private int stok, stokMinimum, harga;
    

    private barangListener listener ;

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
        fireOnChange();
    }
    

    public barangListener getListener() {
        return listener;
    }

    public void setListener(barangListener listener) {
        this.listener = listener;
    }

    ////////////////////

        
    public String getCari() {
        return cari;
    }

    public void setCari(String cari) {
        this.cari = cari;
        fireOnChange();
    }

    public String getCmbcari() {
        return cmbcari;
    }

    public void setCmbcari(String cmbcari) {
        this.cmbcari = cmbcari;
        fireOnChange();
    }

    public String getCmbsort() {
        return cmbsort;
    }

    public void setCmbsort(String cmbsort) {
        this.cmbsort = cmbsort;
    }

    ///////////////////////////////

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
        fireOnChange();
    }

    public String getIdBarcode() {
        return idBarcode;
    }

    public void setIdBarcode(String idBarcode) {
        this.idBarcode = idBarcode;
        fireOnChange();
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
        fireOnChange();
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
        fireOnChange();
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
        fireOnChange();
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
        fireOnChange();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
        fireOnChange();
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
        fireOnChange();
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
        fireOnChange();
    }

    public int getStokMinimum() {
        return stokMinimum;
    }

    public void setStokMinimum(int stokMinimum) {
        this.stokMinimum = stokMinimum;
        fireOnChange();
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
        fireOnChange();
    }

    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }

    protected void fireOnInsert(barang barang){
        if(listener!=null){
            listener.onInsert(barang);
        }
    }

    protected void fireOnUpdate(barang barang){
        if(listener!=null){
            listener.onUpdate(barang);
        }
    }

    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }

    protected void fireOnSearch(List list){
        if(listener!=null){
            listener.onSearch(list);
        }
    }

    protected void fireOnSort(List list){
        if(listener!=null){
            listener.onSort(list);
        }
    }

    public void resetBarang(){
       setCari("");
       setCmbcari(cmbcari);
       setCmbsort(cmbsort);
    }
    
    
    /*public void resetTambahBarang(){
        setIdBarang("");
        setIdBarcode("");
        setNamaBarang("");
        setTipe("");
        setMerek("");
        setHarga(0);
        setSatuan("");
        setStok(0);
        setStokMinimum(0);
        setSupplier("");
        setKeterangan("");
    }
    public void insertBarang() throws SQLException, barangException{
        BarangDao dao = barangDatabase.getBarangDao();

        barang barang = new barang();
        //barang.setIdBarang(idBarang);
        barang.setIdBarcode(idBarcode);
        barang.setNamaBarang(namaBarang);
        barang.setTipe(tipe);
        barang.setMerek(merek);
        barang.setHarga(harga);
        barang.setSatuan(satuan);
        barang.setStok(stok);
        barang.setStokMinimum(stokMinimum);
        barang.setSupplier(supplier);
        barang.setKeterangan(keterangan);

        dao.insertBarang(barang);
        fireOnInsert(barang);
    }
    //private barang barang=null;
    public barang updateBarang() throws SQLException, barangException{
        barang barang=new barang();
        barang barangin=new barang();        
        barangController controller=new barangController();
        barangin = controller.getBarangEntity();
        barang.setIdBarang(barangin.getIdBarang());
        barang.setIdBarcode(barangin.getIdBarcode());
        barang.setNamaBarang(barangin.getNamaBarang());
        barang.setTipe(barangin.getTipe());
        barang.setMerek(barangin.getMerek());
        barang.setHarga(barangin.getHarga());
        barang.setSatuan(barangin.getSatuan());
        barang.setStok(barangin.getStok());
        barang.setStokMinimum(barangin.getStokMinimum());
        barang.setSupplier(barangin.getSupplier());
        barang.setKeterangan(barangin.getKeterangan());

        /*dao.updateBarang(barang);
        fireOnUpdate(barang);
        return barang;
    }*/

    public void deleteBarang() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        dao.deleteBarang(idBarang);
        fireOnDelete();
    }

    public void searchBarangbyId(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list = dao.getBarangbyId(kataKunci);
        fireOnSearch(list);
    }


     public void searchBarangbyNama(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.getBarangbyNama(kataKunci);
        fireOnSearch(list);
    }

     public void searchBarangbyTipe(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.getBarangbyTipe(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyMerek(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.getBarangbyMerek(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyHarga(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.getBarangbyHarga(kataKunci);
        fireOnSearch(list);
    }


    public void searchBarangbyStok(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.getBarangbyStok(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyStokMin(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.getBarangbyStok_min(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbySupplier(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.getBarangbySupplier(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyKeterangan(String kataKunci) throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.getBarangbyKeterangan(kataKunci);
        fireOnSort(list);
    }
    public void sortBarangbyId() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list = dao.sortBarangbyId();
        fireOnSort(list);
    }

     public void sortBarangbyBarcode() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbyBarcode();
        fireOnSort(list);
    }

     public void sortBarangbyNama() throws SQLException, BarangException{         
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbyNama();
        fireOnSort(list);
    }

     public void sortBarangbyTipe() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbyTipe();
        fireOnSort(list);
    }

    public void sortBarangbyMerek() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbyMerek();
        fireOnSort(list);
    }

    public void sortBarangbyHarga() throws SQLException, BarangException{      
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbyHarga();
        fireOnSort(list);
    }


    public void sortBarangbyStok() throws SQLException, BarangException{        
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbyStok();
        fireOnSort(list);
    }

    public void sortBarangbyStokMin() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbyStok_min();
        fireOnSort(list);
    }

    public void sortBarangbySupplier() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbySupplier();
        fireOnSort(list);
    }

    public void sortBarangbyKeterangan() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        List<barang> list =dao.sortBarangbyKeterangan();
        fireOnSort(list);
    }   
}
