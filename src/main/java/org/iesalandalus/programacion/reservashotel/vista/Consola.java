package org.iesalandalus.programacion.reservashotel.vista;



import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.iesalandalus.programacion.reservashotel.MainApp.*;
import static org.iesalandalus.programacion.reservashotel.modelo.Modelo.*;
import static org.iesalandalus.programacion.reservashotel.modelo.Modelo.*;


public class Consola {
    private Consola(){

    }

    public static void mostrarMenu(){
        System.out.println("Bienvenido al programa de gesti�n de reservas de un Hotel, creado por Jos� Antonio Guirado Gonz�lez");
        System.out.println("Opciones: ");

        Opcion menuOpciones[]= Opcion.values();

        for (Opcion opcion: menuOpciones){
            if (opcion.ordinal()<menuOpciones.length) //De esta manera quito la opcion de consultar disponibilidad del menu.
                System.out.println(opcion);
        }


        /*System.out.println(Opcion.SALIR);
        System.out.println(Opcion.INSERTAR_HUESPED);
        System.out.println(Opcion.BUSCAR_HUESPED);
        System.out.println(Opcion.BORRAR_HUESPED);
        System.out.println(Opcion.MOSTRAR_HUESPEDES);
        System.out.println(Opcion.INSERTAR_HABITACION);
        System.out.println(Opcion.BUSCAR_HABITACION);
        System.out.println(Opcion.BORRAR_HABITACION);
        System.out.println(Opcion.MOSTRAR_HABITACIONES);
        System.out.println(Opcion.INSERTAR_RESERVA);
        System.out.println(Opcion.ANULAR_RESERVA);
        System.out.println(Opcion.MOSTRAR_RESERVAS);
        System.out.println(Opcion.CONSULTAR_DISPONIBILIDAD);*/
    }


    public static Opcion elegirOpcion(){
        int opcion;
        do {
            System.out.println("Elija una opci�n: ");
            opcion = Entrada.entero();
            if (opcion<=0 || opcion>Opcion.values().length)
                System.out.println("Opci�n no v�lida.");
        }while (opcion<=0 || opcion>Opcion.values().length); //De esta manera si ingresa un valor mayor a el ordinal de un enum se repite el bucle.
        switch (opcion){
            case 1: return Opcion.SALIR;
            case 2: return Opcion.INSERTAR_HUESPED;
            case 3: return Opcion.BUSCAR_HUESPED;
            case 4: return Opcion.BORRAR_HUESPED;
            case 5: return Opcion.MOSTRAR_HUESPEDES;
            case 6: return Opcion.INSERTAR_HABITACION;
            case 7: return Opcion.BUSCAR_HABITACION;
            case 8: return Opcion.BORRAR_HABITACION;
            case 9: return Opcion.MOSTRAR_HABITACIONES;
            case 10: return Opcion.INSERTAR_RESERVA;
            case 11: return Opcion.ANULAR_RESERVA;
            case 12: return Opcion.MOSTRAR_RESERVAS;
            case 13: return(Opcion.REALIZAR_CHECKIN);
            case 14: return (Opcion.REALIZAR_CHECKOUT);
            case 15: return (Opcion.CONSULTAR_DISPONIBILIDAD);
            default:return Opcion.SALIR;

            // Esto lo tenemos que mejorar.
        }
    }

    public static Huesped leerHuesped(){
        String nombre, dni, telefono, correo;

        System.out.println("Escriba el nombre del Huesped: ");
        nombre = Entrada.cadena();
        System.out.println("Escriba el DNI del Huesped: ");
        dni = Entrada.cadena();
        System.out.println("Escriba el telefono del Huesped: ");
        telefono = Entrada.cadena();
        System.out.println("Escriba el correo del Huesped: ");
        correo = Entrada.cadena();

        try{
            return new Huesped(nombre,dni,correo,telefono,leerFecha("Escriba su fecha de nacimiento: "));
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public static Huesped getClientePorDni(){
        // m�todo sujeto a cambios ...
        // Este algoritmo ha sido creado gracias a que en la clase MainApp tengo acceso public en los atributos.
        // Si no no podr�a hacerlo de otra mnaera.
        try{
            String dni;
            System.out.println("Inserte el DNI del huesped: ");
            dni = Entrada.cadena();

            for (Huesped huespedConDni: huespedes.get()){
                if (huespedConDni.getDni().equals(dni)){
                    return huespedConDni;
                }
            }
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());

        }return null;


        // Aqu�, leyendo el enunciado, yo entend�a que, introduciendo un DNI, podamos obtener los datos de un huesped ya existente en el sistema, su nombre, correo, telefono, etc...
        // Sin embargo, no se como hacerlo, ya que no consigo entender como a�adir el array dentro de esta clase para que lo recorre y hacer la comparacion del DNI de un huesped con el DNI introducido.
        // pregunt� a chatGPT sobre este metodo en concreto y me dio una soluci�n que, no se si podr�a funcionar porque no llegu� a implementar ya que era una solucion en la que creaba el metodo con par�metros, cosa que
        // no pod�a hacer porque este m�etodo no se le pasan par�metros. Pregunte a compa�eros de la clase que hab�an hecho este m�todo sobre que es lo que hicieron mas o menos para tener una idea de que hacer y me dijeron
        // que lo que habian hecho era pedirle el DNI y crear un objeto con los parametros inventados menos el dni que ser�a el que nosotros insertamos...


    /*String dni;
        System.out.println("Inserte el Dni del huesped: ");
        dni = Entrada.cadena();
        try{
            return new Huesped("Jos� Juan",dni, "josejuan@gmail.com","654432143", LocalDate.of(2001,01,01));
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }return null;*/

    }

    public static LocalDate leerFecha(String mensaje){
        // En este m�todo tampoco supe bien que habia realmente que hacer, asi que lo que hice fue pasar el tipo String de fecha que preguntamos al crear el huesped y lo convertimos en LocalDate
        // haciendo un return de el array separado por el "-" pasado a entero y luego ese valor se lo asignamos a la fecha de nacimiento del huesped.
        /*String fecha;

        do{
            System.out.println(mensaje);
            fecha = Entrada.cadena();
            if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) { //he tenido que usar esto en vez de dd/MM/yyyy para poder usar el matches, seg�n en un foro, no se puede usar el matches con dd/MM/yyyy porque no entiende el matches el pattern de esa manera.
                System.out.println("La fecha introducida tiene un formato incorrecto (Usar dd/MM/yyyy)");
                fecha=null;
            }
        }while (fecha==null);
        String fechaConvertida [] = fecha.split("/");

        LocalDate fechaFinal = LocalDate.of(Integer.parseInt(fechaConvertida[2]), Integer.parseInt(fechaConvertida[1]), Integer.parseInt(fechaConvertida[0]));
        if (fechaFinal.isLeapYear()){
            return fechaFinal;
        }
        return fechaFinal;*/
        // Este m�todo lo he obtenido a trav�s de chatGPT porque tengo un problema que no supe solucionar con el hecho de que es un a�o bisiesto y me da un error cuando crea una reserva.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;

        do {
            try {
                System.out.println(mensaje);
                String fechaString = Entrada.cadena();
                fecha = LocalDate.parse(fechaString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("La fecha introducida tiene un formato incorrecto (Usar dd/MM/yyyy)");
            }
        } while (fecha == null);

        return fecha;
    }

    public static LocalDateTime leerFechaHora(String mensaje){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime fecha = null;

        do {
            try {
                System.out.println(mensaje);
                String fechaString = Entrada.cadena();
                fecha = LocalDateTime.parse(fechaString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("La fecha introducida tiene un formato incorrecto (Usar dd/MM/yyyy HH:mm:ss)");
            }
        } while (fecha == null);

        return fecha;
    }


    public static Habitacion leerHabitacion(){
        int numeroPlanta, numeroPuerta;
        double precio;
    // Esto lo hago porque con las expceciones el programa se me apaga por completo y tengo que reiniciar el programa cada vez que hago un fallo, por lo que para ahorrarme eso tengo que hacer comprobaciones con el Do While.
            do {
                System.out.println("Escriba el n�mero de la planta: ");
                numeroPlanta = Entrada.entero();
                if (numeroPlanta < 1 || numeroPlanta > 3)
                    System.out.println("El numero de la planta debe ser entre 1 y 3.");
            } while (numeroPlanta < 1 || numeroPlanta > 3);
            do {
                System.out.println("Escriba el n�mero de la puerta: ");
                numeroPuerta = Entrada.entero();
                if (numeroPuerta < 1 || numeroPuerta > 14)
                    System.out.println("El numero de la puerta debe ser entre 1 y 14.");
            } while (numeroPuerta < 1 || numeroPuerta > 14);
        do {
            System.out.println("Escriba el precio de la habitaci�n: ");
            precio = Entrada.realDoble();
            if (precio<40 || precio>150) System.out.println("El precio de una habitaci�n no debe ser superior a 40 o 150.");
        }while(precio<40 || precio>150);
        System.out.println("Indique el tipo de habitaci�n: ");
        TipoHabitacion tipo = leerTipoHabitacion();
        try {
            return new Habitacion(numeroPlanta, numeroPuerta, precio, tipo);
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
        /*do {
            System.out.println("�Desea indicar el tipo de habitaci�n?");
            System.out.println("1.- S�");
            System.out.println("2.- No");
            opcion = Entrada.entero();
            if (opcion<1 || opcion>2) System.out.println("Debe seleccionar una opci�n adecuada");
        }while (opcion<1 || opcion>2);
        if (opcion == 2){
            try{
                return new Habitacion(numeroPlanta,numeroPuerta,precio);
            }catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }else if (opcion == 1){
            TipoHabitacion tipo = leerTipoHabitacion();
            try {
                return new Habitacion(numeroPlanta, numeroPuerta, precio, tipo);
            }catch (NullPointerException | IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

*/

    }

    public static Habitacion leerHabitacionPorIdentificador(){

        try{
            int numPlanta, numPuerta;
            String combinacion;
            do {
                System.out.println("Indique el numero de la planta: ");
                numPlanta = Entrada.entero();
                if (numPlanta<1 || numPlanta>3) System.out.println("El numero de la planta no puede ser superior a 3 o menor de 1.");
            }while (numPlanta<1 || numPlanta>3);
            do {
                System.out.println("Indique el numero de la puerta: ");
                numPuerta = Entrada.entero();
                if (numPuerta<1 || numPuerta>14) System.out.println("El numero de puerta no puede ser menor de 1 o mayor de 14.");
            }while (numPuerta<1 || numPuerta>14);
            combinacion = ""+numPlanta+numPuerta;
            for (Habitacion habitacionCorrespondiente: habitaciones.get()){
                if (habitacionCorrespondiente.getIdentificador().equals(combinacion))
                    return habitacionCorrespondiente;
            }
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return null;

        // En este m�todo entend� un poco lo m�smo que en el m�todo del Dni, por lo que hice lo mismo.
        /*int planta, puerta;
        System.out.println("Inserte la planta de la habitaci�n: ");
        planta = Entrada.entero();
        System.out.println("Inserte la puerta de la habitaci�n: ");
        puerta = Entrada.entero();

        // m�todo sujeto a cambios ...

        try{
            return new Habitacion(planta,puerta,50,TipoHabitacion.SIMPLE);
        }catch (NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }return null;*/

    }

    public static TipoHabitacion leerTipoHabitacion(){
        int opcion;
        do {
            System.out.println("Tipos de habitaci�n: ");

            TipoHabitacion menuOpciones[]= TipoHabitacion.values();

            for (TipoHabitacion tipo: menuOpciones){
                System.out.println(tipo.toString());
            }

            System.out.println("Elije un tipo de habitaci�n: ");
            opcion = Entrada.entero();
            if (opcion<1 || opcion>TipoHabitacion.values().length) System.out.println("Elija una opci�n adecuada, por favor.");
        }while (opcion<1 || opcion>TipoHabitacion.values().length);
        switch (opcion){
            case 1: return TipoHabitacion.SIMPLE;
            case 2: return TipoHabitacion.DOBLE;
            case 3: return TipoHabitacion.TRIPLE;
            case 4: return TipoHabitacion.SUITE;
            default: return TipoHabitacion.SIMPLE;
        }
    }

    public static Regimen leerRegimen(){
        int opcion;
        do {
            System.out.println("Tipos de r�gimen: ");
            Regimen menuOpciones[]= Regimen.values();

            for (Regimen regimen: menuOpciones){
                System.out.println(regimen.toString());
            }
            System.out.println("Elije un tipo de r�gimen: ");
            opcion = Entrada.entero();
            if (opcion<1 || opcion>Regimen.values().length+1) System.out.println("Elija una opci�n adecuada, por favor.");
        }while (opcion<1 || opcion>Regimen.values().length+1);
        switch (opcion){
            case 1: return Regimen.SOLO_ALOJAMIENTO;
            case 2: return Regimen.ALOJAMIENTO_DESAYUNO;
            case 3: return Regimen.MEDIA_PENSION;
            case 4: return Regimen.PENSION_COMPLETA;
            default: return Regimen.SOLO_ALOJAMIENTO;
        }
    }

    public static Reserva leerReserva(){

        int numPersonas;


        Huesped huesped = getClientePorDni();

        Habitacion habitacion = leerHabitacionPorIdentificador();

        System.out.println("Seleccione un tipo de r�gimen: ");
        Regimen regimen = leerRegimen();

        /*do {
            System.out.println("Inserte la fecha de inicio de reserva: ");
            fechaInicio = Entrada.cadena();
            if (!fechaInicio.matches("\\d{2}/\\d{2}/\\d{4}")) System.out.println("Por favor, inserte la fecha en formato correcto (dd/MM/yyy)");
        }while(!fechaInicio.matches("\\d{2}/\\d{2}/\\d{4}"));
        do {
            System.out.println("Inserte la fecha de fin de reserva: ");
            fechaFin = Entrada.cadena();
            if (!fechaFin.matches("\\d{2}/\\d{2}/\\d{4}")) System.out.println("Por favor, inserte la fecha en formato correcto (dd/MM/yyy)");
        }while(!fechaFin.matches("\\d{2}/\\d{2}/\\d{4}"));
        */
        // Esto no hace falta porque ya insertamos la fecha cuando hacemos leerFecha en el constructor.


        System.out.println("Indique el n�mero de personas en la reserva: ");
        numPersonas = Entrada.entero();


        try {
            return new Reserva(huesped, habitacion, regimen, leerFecha("Inserte la fecha de inicio de reserva: "), leerFecha("Inserte la fecha de fin de reserva: "), numPersonas);
        }catch(NullPointerException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }


    }

}
