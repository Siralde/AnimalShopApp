package es.uv.bd.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author ajgs
 */
public class SalasDAO {
    
     /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String DBURL = "jdbc:oracle:thin:@pokemon2.uv.es:1521:ORCL";
    public static final String USERNAME = "al016";
    public static final String PASSWORD = "al016";
    /*
     * Consultas SALA
     *
     * CODIGO        NUMBER
     * NOMBRE_SALA   NUMBER
     * CAPACIDAD     VARCHAR2(20)
     */

    private static final String CREATE = 
            "INSERT INTO sala (codigo, nombre_sala, capacidad) " +
            "VALUES (?,?,?)";
    
    private static final String READ = 
            "SELECT codigo, nombre_sala, capacidad " +
            "FROM sala " +
            "WHERE codigo = ?";
    
    private static final String READALL = 
            "SELECT codigo, nombre_sala, capacidad " +
            "FROM sala";
    
    private static final String UPDATE =
            "UPDATE sala " +
            " SET nombre_sala = ?, capacidad = ? " +
            " WHERE codigo = ?";
    
    private static final String DELETE =
            "DELETE FROM sala " +
            " WHERE codigo = ?";

    public SalasDAO() {}
    
    public void crearSala(Salas sala) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement insert = oracleConn.prepareStatement(CREATE);
        insert.setInt(1, sala.getCodigo());
        insert.setString(2, sala.getNombreSala());
        insert.setInt(3, sala.getCapacidad());
        insert.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public Salas leerSala(int idSala) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        
        Salas sala = new Salas();
      
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        // Sentencia de insert
        PreparedStatement read = oracleConn.prepareStatement(READ);
        read.setInt(1, idSala);
        ResultSet rs = read.executeQuery();
        
        if (rs.next()) {
            sala.setCodigo(rs.getInt("codigo"));
            sala.setNombreSala(rs.getString("nombre_sala"));
            sala.setCapacidad(rs.getInt("capacidad"));
        }
        
        return sala;
    }
    
    public void actualizarSala(Salas sala) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
           
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement update = oracleConn.prepareStatement(UPDATE);
        
        update.setString(1, sala.getNombreSala());
        update.setInt(2, sala.getCapacidad());
        update.setInt(3, sala.getCodigo());
        update.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public void borrarSala(int idSala) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
        oracleConn.setAutoCommit(false);
        
        // Sentencia de borrado
        PreparedStatement delete = oracleConn.prepareStatement(DELETE);
        delete.setInt(1, idSala);
        delete.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    } 
}
