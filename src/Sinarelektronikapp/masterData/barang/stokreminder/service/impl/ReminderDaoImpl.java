/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barang.stokreminder.service.impl;

import Sinarelektronikapp.masterData.barang.entity.barang;
import Sinarelektronikapp.masterData.barang.error.BarangException;
import Sinarelektronikapp.masterData.barang.stokreminder.service.ReminderDao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ReminderDaoImpl implements ReminderDao {

    private Connection connection;    

    final String updateBarang = "UPDATE barang SET idbarang = ?, idbarcode = ?, namabarang = ?, tipe = ?, merek = ?, hargamodal = ?, grosir, eceran, satuan = ?, stok = ?, stok_minimum = ?, supplier = ?, keterangan = ?, gambar=? kategori = ? WHERE idbarang = ?";

    final String deleteBarang = "DELETE FROM barang where idbarang = ?";

    final String getById = "SELECT * FROM barang where idbarang like ?";

    final String getByBarcode = "SELECT * FROM barang where idbarcode like ?";
    
    final String getByNama = "SELECT * FROM barang where namabarang like ?";
    
    final String getBytipe = "SELECT * FROM barang where tipe like ?";
    
    final String getBymerek = "SELECT * FROM barang where merek like ?";
    
    final String getByharga = "SELECT * FROM barang where harga like ?";
    
    final String getBysatuan = "SELECT * FROM barang where satuan like ?";
    
    final String getBystok = "SELECT * FROM barang where stok like ?";
    
    final String getBystok_min = "SELECT * FROM barang where stok_minimum like ?";
    
    final String getBysupplier = "SELECT * FROM barang where supplier like ?";
    
    final String getByket = "SELECT * FROM barang where keterangan like ?";
    
    final String sortById = "SELECT * FROM barang order by idbarang WHERE stok<stok_minimum";

    final String sortByBarcode = "SELECT * FROM barang order by idbarcode WHERE stok<stok_minimum";
    
    final String sortByNama = "SELECT * FROM barang order by namabarang WHERE stok<stok_minimum";
    
    final String sortBytipe = "SELECT * FROM barang order by tipe WHERE stok<stok_minimum";
    
    final String sortBymerek = "SELECT * FROM barang order by merek WHERE stok<stok_minimum";
    
    final String sortByharga = "SELECT * FROM barang order by harga WHERE stok<stok_minimum";
    
    final String sortBysatuan = "SELECT * FROM barang order by satuan WHERE stok<stok_minimum";
    
    final String sortBystok = "SELECT * FROM barang order by stok WHERE stok<stok_minimum";
    
    final String sortBystok_min = "SELECT * FROM barang order by stok_minimum WHERE stok<stok_minimum";
    
    final String sortBysupplier = "SELECT * FROM barang order by supplier WHERE stok<stok_minimum";
    
    final String sortByket = "SELECT * FROM barang order by keterangan WHERE stok<stok_minimum";

    final String selectAll = "SELECT * FROM barang WHERE stok<stok_minimum";
    
    final String getLastId = "SELECT COUNT(idbarang) total from barang";

    public ReminderDaoImpl(Connection connection) {
        this.connection = connection;
    }       

    @Override
    public void updateBarang(barang barang) throws BarangException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateBarang);
            statement.setString(1, barang.getIdBarang());
            statement.setString(2, barang.getIdBarcode());
            statement.setString(3, barang.getNamaBarang());
            statement.setString(4, barang.getTipe());
            statement.setString(5, barang.getMerek());
            statement.setInt(6, barang.getHargamodal());
            statement.setInt(7, barang.getEceran());
            statement.setInt(8, barang.getGrosir());
            statement.setString(9, barang.getSatuan());
            statement.setInt(10, barang.getStok());
            statement.setInt(11, barang.getStokMinimum());
            statement.setString(12, barang.getSupplier());
            statement.setString(13, barang.getKeterangan());            
            try {
                statement.setBlob(14, new FileInputStream(barang.getGambar()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            statement.setString(14, barang.getKategori());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Insert barang gagal karena "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }


    @Override
    public List<barang> getBarangbyId(String idBarang) throws BarangException {
        PreparedStatement statement = null;
        List<barang> list = new ArrayList<barang>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getById);
            statement.setString(1, "%"+idBarang+"%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));                
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new BarangException(exception.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
        }
    }
    

    @Override
    public List<barang> getBarangbyBarcode(String idBarcode) throws BarangException {
        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByBarcode);            
            statement.setString(1, "%"+idBarcode+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }    
    }

    @Override
    public List<barang> selectAllBarang() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<barang>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(selectAll);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new barangException(exception.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;

    }

    @Override
    public List<barang>  getBarangbyNama(String nama) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByNama);
            statement.setString(1, "%"+nama+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang>  getBarangbyTipe(String tipe) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBytipe);
            statement.setString(1, "%"+tipe+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();            
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> getBarangbyMerek(String merek) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBymerek);
            statement.setString(1, "%"+merek+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));                
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> getBarangbyHarga(String Harga) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByharga);
            statement.setString(1, "%"+Harga+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> getBarangbySatuan(String satuan) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBysatuan);
            statement.setString(1, "%"+satuan+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> getBarangbyStok(String stok) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBystok);
            statement.setString(1, "%"+stok+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> getBarangbyStok_min(String stok_min) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBystok_min);
            statement.setString(1, "%"+stok_min+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> getBarangbySupplier(String supplier) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBysupplier);
            statement.setString(1, "%"+supplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> getBarangbyKeterangan(String keterangan) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByket);
            statement.setString(1, "%"+keterangan+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> sortBarangbyId() throws BarangException {
        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortById);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> sortBarangbyBarcode() throws BarangException {
        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByBarcode);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<barang> sortBarangbyNama() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByNama);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<barang> sortBarangbyTipe() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBytipe);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<barang> sortBarangbyMerek() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBymerek);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<barang> sortBarangbyHarga() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByharga);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<barang> sortBarangbySatuan() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBysatuan);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<barang> sortBarangbyStok() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBystok);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<barang> sortBarangbyStok_min() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBystok_min);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<barang> sortBarangbySupplier() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBysupplier);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<barang> sortBarangbyKeterangan() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByket);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setHargamodal(rs.getInt("hargamodal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setStok(rs.getInt("stok"));
                barang.setStokMinimum(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setKategori(rs.getString("kategori"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public int getBarangLastId() throws BarangException {

        Statement statement = null;
        int total = 0;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(getLastId);
            while (rs.next()) {                
                total = rs.getInt("total");
            }
            connection.commit();
            return total;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }
    
}
