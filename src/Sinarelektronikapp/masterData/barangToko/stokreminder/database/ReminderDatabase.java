/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barangToko.stokreminder.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterData.barangToko.stokreminder.service.ReminderDao;
import Sinarelektronikapp.masterData.barangToko.stokreminder.service.impl.ReminderDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class ReminderDatabase {
    private static Connection connection;
    
    private static ReminderDao dao;    
    
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

    public static ReminderDao getReminderDao() throws SQLException{
        if(dao == null){
            dao = new ReminderDaoImpl(getConnection());
        }
        return dao;
    }
    
}
