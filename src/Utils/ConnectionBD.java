/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author migue
 */
public class ConnectionBD {

    /**
     * Instancia del ServiceLocator
     */
    private static ConnectionBD instance = null;

    /**
     * Conexion compartida a la Base de Datos
     */
    private Connection conexion = null;

    /**
     * Bandera que indica el estado de la conexion
     */
    private boolean conexionLibre = true;

    /**
     * @return instancia del ServiceLocator para el manejo de la conexion
     */
    public static ConnectionBD getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionBD();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    private ConnectionBD() throws Exception {
        JSONParser parser = new JSONParser();

        try {
            //se crea el string el cual contendra la direccion del Json para las credenciales de la conexion
            String credentials_path = System.getProperty("user.dir") + "/src/utils/db_credentials.json";
            //se crea un objeto Json para leer y obtener sus atributos
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(credentials_path));
            //definicion de atributos en codigo mediante los atributos del objeto Json
            String host = (String) jsonObject.get("db_ip");
            String port = (String) jsonObject.get("dp_port");
            String username = (String) jsonObject.get("db_user");
            String password = (String) jsonObject.get("db_pssword");
            //Recordar adicionar en caso de que se tenga una instacia en el servidor poner en el codigo
            String dbURL = "jdbc:postgresql://" + host + ":" + port + "/ProyectoBD";

            //se crea la coneccion mediante el metodo getconnection y se le pasa como atributos
            // el url donde se debe conectar, el username y las password
            conexion = DriverManager.getConnection(dbURL, username, password);
            conexion.setAutoCommit(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getClass()
                    + "\nError :" + e.getMessage());
        }
    }

    /**
     * Toma la conexion para que ningun otro proceso la puedan utilizar
     *
     * @return da la conexion a la base de datos
     */
    public synchronized Connection tomarConexion() {
        while (!conexionLibre) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        conexionLibre = false;
        notify();
        return conexion;
    }

    /**
     * Libera la conexion de la bases de datos para que ningun otro proceso la
     * pueda utilizar
     */
    public synchronized void liberarConexion() {
        while (conexionLibre) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        conexionLibre = true;
        notify();
    }

    /**
     * Cierra la conexion a la base de datos cuando se termina de ejecutar el
     * programa
     */
    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza los cambios en la base de datos. Con este metodo se asegura que
     * no halla inconsitencias en el modelo relacional de la Base de datos.
     *
     * Se utiliza cuando el procedimiento de insercion es terminado
     * correctamente y se asegura que los datos en el modelo estan bien
     * relacionados.
     */
    public void commit() {
        try {
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deshace los cambios en la base de datos. Con este metodo se asegura que
     * no halla inconsitencias en el modelo relacional de la Base de datos.
     *
     * Se utiliza por lo general cuando se devuelve una Exepcion. Los
     * procedimientos intermedios no deberia quedar almacenados en la base de
     * datos.
     */
    public void rollback() {
        try {
            conexion.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
