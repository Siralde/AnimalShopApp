package es.uv.bd.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author diaz
 */
public class ClienteDAO {
    /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String DBURL = "jdbc:oracle:thin:@pokemon2.uv.es:1521:ORCL";
    public static final String USERNAME = "al016";
    public static final String PASSWORD = "al016";
    
    private static final String LCLIENTE = 
            "SELECT idcliente AS IdCliente, apellido || ', ' || nombre AS Cliente " +
            "  FROM mi_clientes " +
            " ORDER BY apellido";

    public ClienteDAO() {}
    
    public HashMap<String,Integer> getTListaClientes() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        
        HashMap<String, Integer> listaClientes = new HashMap<>();
        
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
        PreparedStatement s = oracleConn.prepareStatement(LCLIENTE);
        ResultSet rs = s.executeQuery();
        
        
        
        while (rs.next()) {
            listaClientes.put(rs.getString("Cliente"), rs.getInt("IdCliente"));
        }
        oracleConn.close();
        
        return listaClientes;
    
    }

}
