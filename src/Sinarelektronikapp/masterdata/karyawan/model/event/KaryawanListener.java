/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.karyawan.model.event;

import Sinarelektronikapp.masterdata.karyawan.entity.Karyawan;
import Sinarelektronikapp.masterdata.karyawan.model.KaryawanModel;

/**
 *
 * @author Fauzi
 */
public interface KaryawanListener {
    
    public void onChange(KaryawanModel model);
    
    public void onInsert(Karyawan karyawan);
    
    public void onUpdate(Karyawan karyawan);
    
    public void onDelete();
    
}