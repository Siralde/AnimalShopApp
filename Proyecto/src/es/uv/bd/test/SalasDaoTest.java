/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uv.bd.test;


import es.uv.bd.model.Salas;
import es.uv.bd.model.SalasDAO;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author ajgs
 */
public class SalasDaoTest {
    private static String modulo = "SalasDaoTest:";
    private SalasDAO salaDao;
    private Salas sala_mediana, sala_grande;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SalasDaoTest salaDaoTest = new SalasDaoTest();
        salaDaoTest.doTest();
    }

    public SalasDaoTest() {
        salaDao = new SalasDAO();
    }
    
    public void doTest() {
            
        try {
            
            print("Test 1: Crear sala");            
            sala_mediana = new Salas(107,"mediana",2000);
            salaDao.crearSala(sala_mediana);
            print("Test 1: " + sala_mediana + " creada.");
            
            print("Test 2: Buscar sala");
            sala_grande = salaDao.leerSala(sala_mediana.getCodigo());
            print("Test 2: " + sala_grande + " leída.");
       
            
            print("Test 3: Cambiar nombre de la sala");
            sala_grande.setNombreSala("grande");
            salaDao.actualizarSala(sala_grande);
            sala_mediana = salaDao.leerSala(sala_grande.getCodigo());
            print("Test 3: " + sala_mediana + " modificada.");
            
            print("Test 4: Borrar sala " + sala_mediana.getCodigo() + " (" + sala_mediana.getNombreSala() + ")");
            salaDao.borrarSala(sala_mediana.getCodigo());
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
