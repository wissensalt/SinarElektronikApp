/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.namabarang.service;

import Sinarelektronikapp.masterdata.namabarang.Entity.NamaBarang;
import Sinarelektronikapp.masterdata.namabarang.error.NamaBarangException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface NamaBarangDao {
    public void insertNamaBarang(NamaBarang namaBarang) throws NamaBarangException;
    
    public void updateNamaBarang(NamaBarang namaBarang) throws NamaBarangException;
    
    public void deleteNamaBarang(int id) throws NamaBarangException;
    
    public List<NamaBarang> getNamaBarangById(int id) throws NamaBarangException;
    
    public List<NamaBarang> selectAllNamaBarang() throws NamaBarangException;
    
    public int getLastId()throws  NamaBarangException;    
}