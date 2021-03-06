/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.barang2.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterData.barang2.service.impl.BarangDaoImpl;
import Sinarelektronikapp.masterData.barang2.service.BarangDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class barangDatabase {
    private static Connection connection;
    
    private static BarangDao dao;    
    
    static InternetProtocol ip1 = new InternetProtocol();
            
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("P@ssw0rd");
            
            connection = dataSource.getConnection();
        }
        return connection;
    }

    public static BarangDao getBarangDao() throws SQLException{
        if(dao == null){
            dao = new BarangDaoImpl(getConnection());
        }
        return dao;
    }
    
}
