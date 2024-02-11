package org.iesalandalus.programacion.reservashotel.modelo.negocio;


import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.*;


public class Habitaciones {

    /*
    Los atributos pertenecientes a el array tampoco harán falta.
    private Habitacion coleccionHabitaciones[];
    private int capacidad;

    */


    // Usamos ArrayList porque nos lo pide en el enunciado

    private ArrayList<Habitacion> coleccionHabitaciones= new ArrayList<Habitacion>();

    public Habitaciones(){

    }

    /*
    Esto pertenecía a los Array normales, ya no harán falta con ArrayList.
    public Habitaciones (int capacidad){
        if (capacidad<=0)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        this.capacidad=capacidad;
        this.coleccionHabitaciones = new Habitacion[getCapacidad()];
        this.tamano=0;

    }*/

    public ArrayList<Habitacion> get(){
        return copiaProfundaHabitaciones();
    }

    // Ahora ya no se usa private Habitacion[] por que ya no queremos devolver un array, sino un Arraylist.
    private ArrayList<Habitacion> copiaProfundaHabitaciones(){
    // Este era el método implementado en Array normal
        /*int j=0;
        Habitacion[] copiaHabitaciones = new Habitacion[capacidad];
        for (int i = 0; i < capacidad; i++) {
            if (coleccionHabitaciones[i] != null) {
                copiaHabitaciones[j++] = new Habitacion(coleccionHabitaciones[i]);
            }

        }return Arrays.copyOf(copiaHabitaciones, j);*/
        ArrayList<Habitacion> copiaHabitaciones= new ArrayList<Habitacion>();
         //copiaHabitaciones.addAll(coleccionHabitaciones);
        /*for (Habitacion habitacion: coleccionHabitaciones){
            copiaHabitaciones.add(habitacion);
        }*/

        //Esta hubiera sido otra manera de hacerlo.
        //copiaHabitaciones.addAll(coleccionHabitaciones);

        Iterator<Habitacion> habitacionIterator= coleccionHabitaciones.iterator();
        while (habitacionIterator.hasNext()){
            Habitacion habitacion= new Habitacion(habitacionIterator.next());
            copiaHabitaciones.add(habitacion);
        }
        // Había usado reversed al principio porque pensaba que iría de mas a menos, pero parece ser que no, que va de menos a mas por defecto.
        Collections.sort(copiaHabitaciones, Comparator.comparing(Habitacion::getIdentificador));
        return copiaHabitaciones;
    }

    public ArrayList<Habitacion> get(TipoHabitacion tipoHabitacion){

        if (tipoHabitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo // Es posible que se haya equivocado al escribir el DNI.");
        //return Arrays.stream(copiaProfundaReservas()).filter(reserva -> reserva.getHuesped().equals(huesped)).toArray(Reserva[]::new);
        // lo he copiado de otro método y lo he remplazado a tipoHabitacion.
        /*int contador = 0;
        for (Habitacion contadorHabitacion : get()) {
            if (contadorHabitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                contador++;
            }
        }
        Habitacion[] habitacionesTipo = new Habitacion[contador];
        if (contador == 0) {
            System.out.println("El huesped no tiene ningúna reserva creada.");
        } else {

            contador = 0;
            for (Habitacion habitacionTipo : get()) {
                if (habitacionTipo.getTipoHabitacion().equals(tipoHabitacion)) {
                    habitacionesTipo[contador++] = habitacionTipo;
                }
            }

        }return habitacionesTipo;*/

        ArrayList<Habitacion> copiaHabitaciones= new ArrayList<>();
        /*for (Habitacion habitacion: get()){
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)){
                copiaHabitaciones.add(habitacion);
            }
        }*/
        Iterator<Habitacion> iteradorHabitacion= get().iterator();

        while(iteradorHabitacion.hasNext()){
            Habitacion comprobarHabitacion= iteradorHabitacion.next();
            if (comprobarHabitacion.getTipoHabitacion().equals(tipoHabitacion))
                copiaHabitaciones.add(comprobarHabitacion);
        }

    // Usamos reversed como en el método consultarDisponibilidad para que sea del número más bajo al más alto.

        return copiaHabitaciones;
    }
    /*
    Ya no hará falta los métodos get de los atributos para ArrayList.
    public int getCapacidad() {
        return capacidad;
    }
    */

// esto directamente deuvleve el tamaño.
    public int getTamano() {
        return get().size();
    }



    public void insertar (Habitacion habitacion) throws OperationNotSupportedException{
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        /*if (capacidadSuperada(indice)) No hace falta esto porque ArrayList permite añadir sin restricción.
            throw new OperationNotSupportedException("ERROR: No se aceptan más habitaciones.");*/
        //List<Habitacion> insertarHabitaciones= coleccionHabitaciones;
        //Iterator<Habitacion> iteradorHabitacion= insertarHabitaciones.listIterator();
        /*while (iteradorHabitacion.hasNext()){
            if (iteradorHabitacion.next().getIdentificador().equals(habitacion.getIdentificador())){
                throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
            }
        }*/
        // Esta es otra manera de que salte la excepción.
        if (get().contains(habitacion)){
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }

        coleccionHabitaciones.add(habitacion);
    }
/*
    Este método ya no es necesario con ArrayList.
    private int buscarIndice (Habitacion habitacion){
        if (habitacion == null)
            throw new NullPointerException("ERROR: la habitación indicada es nula.");
        for (int i=0; i<tamano; i++){
            if (habitacion.equals(coleccionHabitaciones[i])){
                return i;
            }
        }
        return -1;
    }

 */
/*
Estos métodos se usaban para controlar el array.
    private boolean tamanoSuperado (int indice){
        if (indice>-1)
            return true;
        else return false;
    }

    private boolean capacidadSuperada(int indice){
        indice=getTamano();
        if (indice>=getCapacidad())
            return true;
        else return false;
    }
    */

    public Habitacion buscar (Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        if (get().contains(habitacion)) {
            //return get().get(get().indexOf(habitacion)); // al final lo he dejado como estaba

            // Aquí en vez de lo que tenía hecho he usado el Iterator.

            Iterator<Habitacion> iteradorHabitacion = get().iterator();
            while (iteradorHabitacion.hasNext()) {
                if (habitacion.equals(iteradorHabitacion.next()))
                    return habitacion;
            }

        }return null;
    }

    public void borrar (Habitacion habitacion) throws OperationNotSupportedException{
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        if (!get().contains(habitacion))
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        else{
            coleccionHabitaciones.remove(habitacion);
        }
    }
/*
    Ya no hace falta usar este método con ArrayList.
    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i = indice; i<tamano-1;i++){
            coleccionHabitaciones[i] = coleccionHabitaciones[i+1];
        }
        coleccionHabitaciones[tamano-1] = null;

    }
    */



}