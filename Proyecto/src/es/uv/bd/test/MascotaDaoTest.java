package es.uv.bd.test;

import es.uv.bd.model.Mascota;
import es.uv.bd.model.MascotaDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diaz
 */
public class MascotaDaoTest {
    private static String modulo = "MascotaDaoTest::";
    private MascotaDAO mascotaDao;
    private Mascota tobi, pipo;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MascotaDaoTest mascotaDaoTest = new MascotaDaoTest();
        mascotaDaoTest.doTest();
    }

    public MascotaDaoTest() {
        mascotaDao = new MascotaDAO();
        
    }
    
    public void doTest() {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
        try {
            print("Test 1: Crear mascota");            
            tobi = new Mascota(10,1,"Tobi",2,dateFormat.parse("12-12-2012"));
            mascotaDao.crearMascota(tobi);
            print("Test 1: " + tobi + " creada.");
            
            print("Test 2: Buscar mascota");
            pipo = mascotaDao.leerMascota(tobi.getIdMascota());
            print("Test 2: " + pipo + " leída.");
            
            print("Test 3: Cambiar nombre de la mascota");
            pipo.setNombreMascota("pipo");
            mascotaDao.actualizarMascota(pipo);
            tobi = mascotaDao.leerMascota(pipo.getIdMascota());
            print("Test 3: " + tobi + " modificada.");
            
            print("Test 4: Borrar mascota " + tobi.getIdMascota() + " (" + tobi.getNombreMascota() + ")");
            mascotaDao.borrarMascota(tobi.getIdMascota());
            print("Test 4: Mascota borrada");
            
            print("Todos los test correctos");
            
        } catch (ParseException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            print("Error ejecutando tests *************************");
            print(" Excepción: " + ex.getMessage());
            print("");
            ex.printStackTrace();
        }
    }
    
    private void print(String string) {
        System.out.println(modulo + string);
    }
}
