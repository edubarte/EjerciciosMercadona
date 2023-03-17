package cadenadetiendas;

/**
 *
 * @author Eduardo Bartel
 */
public class CadenaDeTiendas {
    
    static GestorConexion gestor;
    static InteraccionUsuario interaccion;

    public static void main(String[] args) {
        
        //Creamos el objeto GestorConexion para llamar al constructor que establece la conexión con la BBDD
        gestor = new GestorConexion();
        interaccion = new InteraccionUsuario();

        System.out.println("Bienvenido, ¿qué desea hacer? Pulse del 1 al 6 la opción que corresponda:\n1. Mostrar lista de productos existentes\n2. Agregar un nuevo producto\n3. Editar un producto\n4. Eliminar un producto\n5. Buscar un producto por nombre\n6. Mostrar lista de productos ordenada por precio ascendente\n7. Salir");
        listarOpciones();

    }

    //método que lista las opciones disponibles con las que puede interactuar el usuario
    private static void listarOpciones() {

        String respuesta;
            
        respuesta = interaccion.escaner();
        
        //se controla que puse una opción listada
        while(!"1".equals(respuesta) && !"2".equals(respuesta) && !"3".equals(respuesta) && !"4".equals(respuesta) && !"5".equals(respuesta) && !"6".equals(respuesta) && !"7".equals(respuesta)){
            
            System.out.println("Por favor, pulse del 1 al 7 la opción que corresponda:");
            respuesta = interaccion.escaner();
            
        }
        
        switch (respuesta) {
            case "1":
                mostrarLista(false);
                break;
            case "2":
                agregar();
                break;
            case "3":
                editar();
                break;
            case "4":
                eliminar();
                break;
            case "5":
                buscarPorNombre();
                break;
            case "6":
                mostrarLista(true);
                break;
            case "7":
                gestor.cerrar_conexion();
                break;
        }

    }
    
    //método encargado de llamar al método nostrar del objeto gestor
    private static void mostrarLista(boolean ordenarPorPrecio){
        
        gestor.mostrar(ordenarPorPrecio);
        gestor.cerrar_conexion();
        
    }
    
    //método encargado de llamar al método insertar de la clase gestor
    private static void agregar(){
        
        String nombre;
        float precio;
        int cantidad;
        
        System.out.println("\nInserte el nombre del producto:");
        nombre = interaccion.escaner();
        
        //se comprueba que el nombre ingresado por el usuario no exista para poder continuar
        while(gestor.existeNombre(nombre)){
            System.out.println("El nombre del producto ingresado ya existe, por lo tanto no es único. Por favor, inserte otro nombre:");
            nombre = interaccion.escaner();
        }

        System.out.println("Inserte el precio del producto en euros (utilizar \".\" para los decimales):");
        precio = interaccion.escanerPrecio();

        System.out.println("Inserte la cantidad en stock del producto:");
        cantidad = interaccion.escanerCantidad();
        
        System.out.println("\nNombre: "+ nombre +", Precio: "+ precio +", Cantidad en stock: "+ cantidad);

        gestor.insertar(nombre, precio, cantidad);
        gestor.cerrar_conexion();
        
    }
    
    //método encargado de llamar al método modificar de la clase gestor
    public static void editar(){
        
        String nombre;
        String nombreNuevo;
        float precio;
        int cantidad;
        
        System.out.println("\nInserte el nombre del producto a editar:");
        nombre = interaccion.escaner();
        
        //se comprueba que el nombre ingresado por el usuario exista para poder continuar
        while(!gestor.existeNombre(nombre)){
            System.out.println("El nombre del producto no existe en la BBDD. Por favor, inserte un producto existente:");
            nombre = interaccion.escaner();
        }
        
        System.out.println("Inserte el nuevo nombre del producto:");
        nombreNuevo = interaccion.escaner();

        System.out.println("Inserte el nuevo precio del producto en euros (utilizar \".\" para los decimales):");
        precio = interaccion.escanerPrecio();

        System.out.println("Inserte la cantidad actualizada en stock del producto:");
        cantidad = interaccion.escanerCantidad();

        gestor.modificar(nombre, nombreNuevo, precio, cantidad);
        System.out.println("Nombre: "+ nombreNuevo +", Precio: "+ precio +", Cantidad en stock: "+ cantidad +"\n");
        
        gestor.cerrar_conexion();
        
    }
    
    //método encargado de llamar al método borrar de la clase gestor
    public static void eliminar(){
        
        String nombre;
        
        System.out.println("\nInserte el nombre del producto a eliminar:");
        nombre = interaccion.escaner();
        
        //se comprueba que el nombre ingresado por el usuario exista para poder continuar
        while(!gestor.existeNombre(nombre)){
            System.out.println("El nombre del producto no existe en la BBDD. Por favor, inserte un producto existente:");
            nombre = interaccion.escaner();
        }

        gestor.borrar(nombre);
        gestor.cerrar_conexion();
        
    }
    
    //método encargado de llamar al método buscarNombre de la clase gestor
    public static void buscarPorNombre(){
        
        String nombre;
        
        System.out.println("\nInserte el nombre del producto que quiere su información completa:");
        nombre = interaccion.escaner();
        
        //se comprueba que el nombre ingresado por el usuario exista para poder continuar
        while(!gestor.existeNombre(nombre)){
            System.out.println("El nombre del producto no existe en la BBDD. Por favor, inserte un producto existente:");
            nombre = interaccion.escaner();
        }

        gestor.buscarNombre(nombre);
        gestor.cerrar_conexion();
        
    }

}
