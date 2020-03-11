/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.supplier.model.event;

import com.wissensalt.sinarelektronik.masterdata.supplier.entity.supplier;
import com.wissensalt.sinarelektronik.masterdata.supplier.model.supplierModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface supplierListener {
    public void onChange(supplierModel model);
    
    public void onInsert(supplier supplier);
    
    public void onUpdate(supplier supplier);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}