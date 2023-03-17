package cadenadetiendas;

import java.util.Scanner;

/**
 *
 * @author Eduardo Bartel
 */
//Clase encargada de la interacción con el usuario
public class InteraccionUsuario {
    
    String respuesta;
    float precio;
    int cantidad;
    
    //método que devuelve un String introducido por el usuario
    public String escaner(){
        Scanner teclado = new Scanner(System.in);
        respuesta = teclado.nextLine();
        return respuesta;
    }
    
    //método que devuelve un número float introducido por el usuario
    public float escanerPrecio(){
        Scanner teclado = new Scanner(System.in);
        
        //Sino se inserta con un número float, volverá a intentarlo
        while(true){
            try{
                respuesta = teclado.nextLine();
                precio = Float.parseFloat(respuesta);
                break;
            
            }catch(NumberFormatException excepcion){
                System.out.println("No se ha insertado el formato correcto. Pruebe de nuevo:");
            }
        }
        
        return precio;
    }
    
    //método que devuelve un número int introducido por el usuario
    public int escanerCantidad(){
        Scanner teclado = new Scanner(System.in);
        
        //Sino se inserta con un número int, volverá a intentarlo
        while(true){
            try{
                respuesta = teclado.nextLine();
                cantidad = Integer.parseInt(respuesta);
                break;
            
            }catch(NumberFormatException excepcion){
                System.out.println("No se ha insertado el formato correcto. Pruebe de nuevo:");
            }
        }
        
        return cantidad;
    }
    
}
