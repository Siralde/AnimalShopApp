package es.uv.bd.model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diaz
 */
public class MascotaDAO {
    /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String DBURL = "jdbc:oracle:thin:@pokemon2.uv.es:1521:ORCL";
    public static final String USERNAME = "al016";
    public static final String PASSWORD = "al016";
    /*
     * Consultas MI_MASCOTAS
     *
     * IDMASCOTA        NUMBER
     * IDCLIENTE        NUMBER
     * NOMBREMASCOTA    VARCHAR2(20)
     * TIPOANIMAL       NUMBER
     * FECHANAC         DATE
     */

    private static final String CREATE = 
            "INSERT INTO mi_mascotas (idmascota, idcliente, nombremascota, " +
            "                         tipoanimal, fechanac) " +
            "VALUES (?,?,?,?,TO_DATE(?,'DD-MM-YYYY'))";
    
    private static final String READ = 
            "SELECT idmascota, idcliente, nombremascota, tipoanimal, " +
            "       TO_CHAR(fechanac,'DD-MM-YYYY') AS fecha" +
            "  FROM mi_mascotas " +
            " WHERE idmascota = ?";
    
    private static final String READALL = 
            "SELECT m.idmascota AS Id, m.nombremascota AS Nombre, " +
            "       a.tipoanimal AS IdTipoAnimal, a.nombre AS Animal, " +
            "       TO_CHAR(m.fechanac, 'DD-MM-YYYY') AS Nacimiento, " +
            "       c.idcliente AS IdCliente, " +
            "       c.nombre || ' ' || c.apellido as Cliente " +
            "  FROM mi_mascotas m, mi_clientes c, mi_animales a " +
            " WHERE m.idcliente = c.idcliente AND m.tipoanimal = a.tipoanimal " +
            " ORDER BY m.nombremascota";
    
    private static final String UPDATE =
            "UPDATE mi_mascotas " +
            "   SET idcliente = ?, nombremascota = ?, tipoanimal = ?, " +
            "       fechanac = TO_DATE(?,'DD-MM-YYYY') " +
            " WHERE idmascota = ?";
    
    private static final String DELETE =
            "DELETE FROM mi_mascotas " +
            " WHERE idmascota = ?";

    public MascotaDAO() {}
    
    public void crearMascota(Mascota mascota) throws 
            ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement insert = oracleConn.prepareStatement(CREATE);
        insert.setInt(1, mascota.getIdMascota());
        insert.setInt(2, mascota.getIdCliente());
        insert.setString(3, mascota.getNombreMascota());
        insert.setInt(4, mascota.getTipoAnimal());
        insert.setString(5, sdf.format(mascota.getFechaNacimiento()));
        insert.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public Mascota leerMascota(int idMascota) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        
        Mascota mascota = new Mascota();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        // Sentencia de insert
        PreparedStatement read = oracleConn.prepareStatement(READ);
        read.setInt(1, idMascota);
        ResultSet rs = read.executeQuery();
        
        if (rs.next()) {
            mascota.setIdMascota(rs.getInt("idmascota"));
            mascota.setIdCliente(rs.getInt("idcliente"));
            mascota.setNombreMascota(rs.getString("nombremascota"));
            mascota.setTipoAnimal(rs.getInt("tipoanimal"));
            Date fechaNacimiento = sdf.parse(rs.getString("fecha"));
            mascota.setFechaNacimiento(fechaNacimiento);
        }
        return mascota;
    }
    
    public void actualizarMascota(Mascota mascota) throws ClassNotFoundException, 
           InstantiationException, IllegalAccessException, SQLException {
           
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement update = oracleConn.prepareStatement(UPDATE);
        
        update.setInt(1, mascota.getIdCliente());
        update.setString(2, mascota.getNombreMascota());
        update.setInt(3, mascota.getTipoAnimal());
        update.setString(4, sdf.format(mascota.getFechaNacimiento()));
        update.setInt(5, mascota.getIdMascota());
        update.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public void borrarMascota(int idMascota) throws ClassNotFoundException, 
           InstantiationException, IllegalAccessException, SQLException {
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
        oracleConn.setAutoCommit(false);
        
        // Sentencia de borrado
        PreparedStatement delete = oracleConn.prepareStatement(DELETE);
        delete.setInt(1, idMascota);
        delete.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }

    public DefaultTableModel getTablaMascotas() {
        
        DefaultTableModel tablaMascotas = new DefaultTableModel();
        
        try {
            /*
            * Conexion a la base de datos
            */
            Class.forName(DRIVER).newInstance();
            Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
            PreparedStatement s = oracleConn.prepareStatement(READALL);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int numeroColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= numeroColumnas; i++) {
                tablaMascotas.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[numeroColumnas];
                fila[0] = rs.getInt("Id");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getInt("IdTipoAnimal");
                fila[3] = rs.getString("Animal");
                fila[4] = rs.getString("Nacimiento");
                fila[5] = rs.getInt("IdCliente");
                fila[6] = rs.getString("Cliente");

                tablaMascotas.addRow(fila);
            }
            oracleConn.close();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("MascotasDAO::getTablaMascotas -- " + e.getMessage());
        }
        finally {
            return tablaMascotas;
        }
    }
}
