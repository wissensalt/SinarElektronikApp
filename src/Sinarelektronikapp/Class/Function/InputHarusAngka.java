/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.Class.Function;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Fauzi
 */
public class InputHarusAngka {

    public InputHarusAngka() {
    }
    
    public Boolean cek(JTextField jTextField){
        Boolean cek = false;
        if(!jTextField.getText().matches("[0-9]*")){
            cek = false;
            //tidak sesuai / bukan angka
        }else{
            cek = true;
            //sesuai / angka
        }        
        return cek;
    }
}
