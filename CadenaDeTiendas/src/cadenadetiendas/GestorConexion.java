package cadenadetiendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Eduardo Bartel
 */
//Clase encargada de gestionar la conexión y acceder a la BBDD
public class GestorConexion {
    
    //Se crea el atributo conn1 de la clase para así utilizar la conexión posteriormente
    Connection conn1 = null;
    
    public GestorConexion(){
        
        //Se establece la url de la BBDD, el usuario y la contraseña
        String urlBBDD = "jdbc:mysql://localhost:3306/Mercadona?serverTimezone=UTC";
        String user = "root";
        String password = "root";
        
        try {
            //Se crea la conexión
            conn1 = DriverManager.getConnection(urlBBDD, user, password);
            
            //Si conn1 es diferente de null, quiere decir que ha conectado correctamente
            if(conn1 != null){
                System.out.println("Conectado a la BBDD Marcadona.\n");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en la conexión a la BBDD Mercadona.\n");
        }
        
    }
    
    //método encargado de cerrar la conexión con la BBDD
    public void cerrar_conexion(){
        
        try {
            conn1.close();
            System.out.println("Conexión cerrada correctamente.\n¡Hasta pronto!\n");
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión.\n");
        }
        
    }
    
    //método encargado de mostrar los productos de la BBDD por pantalla
    public void mostrar(boolean ordenarPorPrecio){
        
        Statement sta;
        String query;
        try {
            sta = conn1.createStatement();
            //Si ordenarPorPrecio es true, la consulta ordenará los productos por precio ascendente
            if(ordenarPorPrecio){
                query = "SELECT * FROM PRODUCTOS ORDER BY precio;";
            }else{
                query = "SELECT * FROM PRODUCTOS;";
            }
            
            ResultSet rs = sta.executeQuery(query);
            
            String[] registros = new String[3];
            
            //Si ordenarPorPrecio es true, el mensaje que se muestra por pantalla será de una lista de productos ordenados por precio
            if(ordenarPorPrecio){
                System.out.println("\nLista de productos ordenada por precio ascendente:");
            }else{
                System.out.println("\nLista de productos existentes:");
            }
            
            //Mientras haya una siguiente fila, se guarda en el array "registros" los registros de esa fila
            while(rs.next()){
                registros[0] = rs.getString("nombre");
                //Se suma una cadena String para convertirlo a String, ya que el array es de tipo String
                registros[1] = rs.getFloat("precio")+"";
                registros[2] = rs.getInt("cantidad_en_stock")+"";
                System.out.println("Nombre: "+ registros[0] + ", Precio: " + registros[1] + " €, Cantidad en stock: " + registros[2]);
            }
            System.out.println("");
            
            rs.close();
            sta.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al realizar una consulta relacionada con la BBDD.");
        }
        
    }
    
    //método encargado de insertar un producto en la BBDD
    public void insertar(String nombre, float precio, int cantidad){
        
        Statement sta;
        
        try {
            sta = conn1.createStatement();
            //Se insertan los datos en la tabla PRODUCTOS
            sta.executeUpdate("INSERT INTO PRODUCTOS VALUE('"+ nombre +"', '"+ precio +"', '"+ cantidad+"');");

            sta.close();        
            System.out.println("Producto insertado correctamente.\n");
                     
        } catch (SQLException ex) {
             System.out.println("Eror al intentar agregar un producto en la BBDD.\n");
        }       
        
    }
    
    
    //método encargado de modificar un producto en la BBDD
    public void modificar(String nombre, String nombreNuevo, float precio, int cantidad){
        
        Statement sta;
        
        try {
            sta = conn1.createStatement();
            //Se insertan los datos en la tabla PRODUCTOS
            sta.executeUpdate("UPDATE PRODUCTOS SET nombre = '" + nombreNuevo + "', precio = "+ precio +", cantidad_en_stock = "+ cantidad +" WHERE nombre = '"+ nombre +"';");

            sta.close();        
            System.out.println("\nProducto editado correctamente.");
           
           
        } catch (SQLException ex) {
            //Lanzamos por pantalla un error al escribir los datos
             System.out.println("\nError al intentar editar un producto en la BBDD.\n");
        }       
        
    }
    
    //método encargado de borrar un producto en la BBDD
    public void borrar(String nombre){
        
        Statement sta;
        
        try {
            sta = conn1.createStatement();
            //Se elimina el producto recibido de la tabla PRODUCTOS
            sta.executeUpdate("DELETE FROM PRODUCTOS WHERE nombre = '"+ nombre +"';");

            sta.close();
            
            System.out.println("\nProducto "+ nombre + " eliminado correctamente.\n");
        } catch (SQLException ex) {
            System.out.println("Error al intentar eliminar un producto de la BBDD.\n");
        }
        
    }
    
    //método encargado de buscar un producto en la BBDD por su nombre y mostrarlo por pantalla
    public void buscarNombre(String nombre){
        
        Statement sta;
        String query;
        
        try {
            sta = conn1.createStatement();
            //Se define la consulta
            query = "SELECT * FROM PRODUCTOS WHERE nombre = '"+ nombre +"';";
            
            ResultSet rs = sta.executeQuery(query);
            
            String[] registros = new String[3];
            
            while(rs.next()){
                registros[0] = rs.getString("nombre");
                registros[1] = rs.getFloat("precio")+"";
                registros[2] = rs.getInt("cantidad_en_stock")+"";
                System.out.println("\nNombre: "+ registros[0] + ", Precio: " + registros[1] + " €, Cantidad en stock: " + registros[2]+"\n");
            }
            
            rs.close();
            sta.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al realizar una consulta relacionada con la BBDD.");
        }
        
    }
    
    //método encargado de comprobar si un nombre existe en la BBDD. Devuelve true si existe y false si no existe
    public boolean existeNombre(String nombre){
        
        boolean existe;
        Statement sta;
        String query;
        
        try {
            sta = conn1.createStatement();
            //Se define la consulta
            query = "SELECT * FROM PRODUCTOS WHERE nombre = '"+ nombre +"';";
            
            ResultSet rs = sta.executeQuery(query);
            
            String[] registros = new String[3];
            
            //Se comprueba si hay registros
            if(rs.next()){      
                existe = true;
            } else{
                existe = false;
            }
            
            rs.close();
            sta.close();
            
            return existe;
            
        } catch (SQLException ex) {
            System.out.println("Error al realizar una consulta relacionada con la BBDD.");
            return false;
        }
        
    }
    
}
