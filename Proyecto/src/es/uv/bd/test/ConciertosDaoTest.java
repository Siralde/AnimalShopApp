/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uv.bd.test;

import es.uv.bd.model.Conciertos;
import es.uv.bd.model.ConciertosDAO;
import es.uv.bd.model.Mascota;
import es.uv.bd.model.MascotaDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ajgs
 */
public class ConciertosDaoTest {
    private static String modulo = "ConciertoDaoTest:";
    
    private ConciertosDAO conciertoDao;
    private Conciertos concierto1, concierto2;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConciertosDaoTest conciertoDaoTest = new ConciertosDaoTest();
        conciertoDaoTest.doTest();
    }

    public ConciertosDaoTest() {
        conciertoDao = new ConciertosDAO();
    }
    
    public void doTest() {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
        try {
            print("Test 1: Crear concierto");            
            concierto1 = new Conciertos(3,"La Bailarina", dateFormat.parse("30-12-2018"), dateFormat.parse("1-01-2019"), 1, 1, 3, 1, 60);
            conciertoDao.crearConcierto(concierto1);
            print("Test 1: " + concierto1 + " creada.");
            
            print("Test 2: Buscar concierto");
            concierto2 = conciertoDao.leerConcierto(concierto1.getIdConcierto());
            print("Test 2: " + concierto2 + " leída.");
            
            print("Test 3: Cambiar nombre del concierto");
            concierto2.setTitulo("La Gran Manzana");
            conciertoDao.actualizarConcierto(concierto2);
            concierto1 = conciertoDao.leerConcierto(concierto2.getIdConcierto());
            print("Test 3: " + concierto1 + " modificada.");
            
            print("Test 4: Borrar Concierto " + concierto1.getFechaInicio() + " (" + concierto1.getTitulo() + ")");
            conciertoDao.borrarConcierto(concierto1.getIdConcierto());
            print("Test 4: Concierto borrado");
            
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
