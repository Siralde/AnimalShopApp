package es.uv.bd.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ajgs
 */
public class ConciertosDAO {
    /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String DBURL = "jdbc:oracle:thin:@pokemon2.uv.es:1521:ORCL";
    public static final String USERNAME = "al016";
    public static final String PASSWORD = "al016";
    /*
     * Consultas Concierto
     *
     * ID_CONCIERTO     NUMBER
     * TITULO           VARCHAR2(20)
     * FECHA_INI        DATE
     * FECHA_FIN        DATE
     * ID_ORQUESTA      NUMBER
     * ID_DIRECTOR      NUMBER
     * ID_PROGRAMA      NUMBER
     * NUM_OBRAS        NUMBER
     * DURACION         NUMBER
     * 
     */

    private static final String CREATE = 
            "INSERT INTO conciertos (id_concierto, titulo, fecha_ini, " +
            "fecha_fin, id_orquesta, id_director, id_programa, num_obras, duracion) " +
            "VALUES (?, ?, TO_DATE(?,'DD-MM-YYYY'), TO_DATE(?,'DD-MM-YYYY'), ?, ?, ?, ?, ?)";
    
    private static final String READ = 
            "SELECT id_concierto, titulo, " + 
            "TO_CHAR(fecha_ini,'DD-MM-YYYY') AS fecha_ini, " +
            "TO_CHAR(fecha_fin,'DD-MM-YYYY') AS fecha_fin, " + 
            "id_orquesta, id_director, id_programa, num_obras, duracion " +
            "FROM conciertos " +
            "WHERE id_concierto = ?";
    
    private static final String READALL = 
            "SELECT id_concierto AS id, titulo, " + 
            "TO_CHAR(fecha_ini,'DD-MM-YYYY') AS fecha_ini, " +
            "TO_CHAR(fecha_fin,'DD-MM-YYYY') AS fecha_fin, " + 
            "id_orquesta, id_director, id_programa, num_obras, duracion " +
            "FROM conciertos";
    
    private static final String UPDATE =
            "UPDATE conciertos " +
            "SET titulo = ?, " +
            "fecha_ini = TO_DATE(?,'DD-MM-YYYY'), " +
            "fecha_fin = TO_DATE(?,'DD-MM-YYYY'), " +
            "id_orquesta = ?, id_director = ?, id_programa = ?, num_obras = ?, duracion = ?" +
            "WHERE id_concierto = ?";
    
    private static final String DELETE =
            "DELETE FROM conciertos " +
            " WHERE id_concierto = ?";

    public ConciertosDAO() {}
    
    public void crearConcierto(Conciertos concierto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement insert = oracleConn.prepareStatement(CREATE);
        insert.setInt(1, concierto.getIdConcierto());
        insert.setString(2, concierto.getTitulo());
        insert.setString(3, sdf.format(concierto.getFechaInicio()));
        insert.setString(4, sdf.format(concierto.getFechaFin()));
        insert.setInt(5, concierto.getIdOrquesta());
        insert.setInt(6, concierto.getIdDirector());
        insert.setInt(7, concierto.getIdPrograma());
        insert.setInt(8, concierto.getNumObras());
        insert.setInt(9, concierto.getDuracion());
        insert.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public Conciertos leerConcierto(int idConcierto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        
        Conciertos concierto = new Conciertos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        // Sentencia de insert
        PreparedStatement read = oracleConn.prepareStatement(READ);
        read.setInt(1, idConcierto);
        ResultSet rs = read.executeQuery();
        
        if (rs.next()) {
            
            concierto.setIdConcierto(rs.getInt("id_concierto"));
            concierto.setTitulo(rs.getString("titulo"));
            
            Date fechaInicio = sdf.parse(rs.getString("fecha_ini"));
            concierto.setFechaInicio(fechaInicio);
       
            Date fechaFin = sdf.parse(rs.getString("fecha_fin"));
            concierto.setFechaFin(fechaFin);
            
            concierto.setIdOrquesta(rs.getInt("id_orquesta"));
            concierto.setIdDirector(rs.getInt("id_director"));
            concierto.setIdPrograma(rs.getInt("id_programa"));
            concierto.setNumObras(rs.getInt("num_obras"));
            concierto.setDuracion(rs.getInt("duracion"));
            
        }
        
        return concierto;
    }
    
    public void actualizarConcierto(Conciertos concierto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
           
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement update = oracleConn.prepareStatement(UPDATE);
        
        update.setString(1, concierto.getTitulo());
        update.setString(2, sdf.format(concierto.getFechaInicio()));
        update.setString(3, sdf.format(concierto.getFechaFin()));
        update.setInt(4, concierto.getIdOrquesta());
        update.setInt(5, concierto.getIdDirector());
        update.setInt(6, concierto.getIdPrograma());
        update.setInt(7, concierto.getNumObras());
        update.setInt(8, concierto.getDuracion());
        update.setInt(9, concierto.getIdConcierto());
        update.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public void borrarConcierto(int idConcierto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
        oracleConn.setAutoCommit(false);
        
        // Sentencia de borrado
        PreparedStatement delete = oracleConn.prepareStatement(DELETE);
        delete.setInt(1, idConcierto);
        delete.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
}
