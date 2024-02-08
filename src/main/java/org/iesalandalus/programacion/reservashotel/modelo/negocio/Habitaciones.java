package org.iesalandalus.programacion.reservashotel.modelo.negocio;


import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;


public class Habitaciones {

    private Habitacion coleccionHabitaciones[];
    private int capacidad;
    private int tamano;

    public Habitaciones (int capacidad){
        if (capacidad<=0)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        this.capacidad=capacidad;
        this.coleccionHabitaciones = new Habitacion[getCapacidad()];
        this.tamano=0;

    }

    public Habitacion[] get(){
        return copiaProfundaHabitaciones();
    }

    private Habitacion[] copiaProfundaHabitaciones(){
        // Esto lo he escrito con el proposito de que me pase los test, mirando en la informaci�n que pide que pase los test he creado objetos del mismo, pero no est� bien as�.
    /* coleccionHabitaciones[0] = new Habitacion(1,0,50, TipoHabitacion.SIMPLE);
        coleccionHabitaciones[1] = new Habitacion(2,10,50,TipoHabitacion.DOBLE);
        if (getCapacidad()>=3)
            coleccionHabitaciones[2] = new Habitacion(3,10,50,TipoHabitacion.SUITE);

        return Arrays.copyOf(coleccionHabitaciones, tamano);*/


        // Realic� esta modificaci�n de ultima hora ya que de la otra manera me aceptaba los test pero era para salir del paso, pero realmente no funcionan bien.
        /*Habitacion copiaHabitaciones [] = new Habitacion[capacidad];
        for (int i=0; !tamanoSuperado(i);i++){
            copiaHabitaciones[i] = new Habitacion((coleccionHabitaciones[i]));
        }


        return copiaHabitaciones;*/

        int j=0;
        Habitacion[] copiaHabitaciones = new Habitacion[capacidad];
        for (int i = 0; i < capacidad; i++) {
            if (coleccionHabitaciones[i] != null) {
                copiaHabitaciones[j++] = new Habitacion(coleccionHabitaciones[i]);
            }

        }return Arrays.copyOf(copiaHabitaciones, j);

    }

    public Habitacion[] get(TipoHabitacion tipoHabitacion){
        /*int contador=0;

        for (Habitacion habitacion: get()) {

            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                contador++;
            }

            Habitacion[] habitacionPorTipos = new Habitacion[contador];
            contador = 0;

            for (Habitacion habitaciones: get()){
                if (habitacion.getTipoHabitacion().equals(tipoHabitacion)){
                    habitacionPorTipos[contador++]=habitaciones;
                }

            }

        }
        return habitacionPorTipos;*/


        if (tipoHabitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo // Es posible que se haya equivocado al escribir el DNI.");
        //return Arrays.stream(copiaProfundaReservas()).filter(reserva -> reserva.getHuesped().equals(huesped)).toArray(Reserva[]::new);
        // lo he copiado de otro m�todo y lo he remplazado a tipoHabitacion.
        int contador = 0;
        for (Habitacion contadorHabitacion : get()) {
            if (contadorHabitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                contador++;
            }
        }
        Habitacion[] habitacionesTipo = new Habitacion[contador];
        if (contador == 0) {
            System.out.println("El huesped no tiene ning�na reserva creada.");
        } else {

            contador = 0;
            for (Habitacion habitacionTipo : get()) {
                if (habitacionTipo.getTipoHabitacion().equals(tipoHabitacion)) {
                    habitacionesTipo[contador++] = habitacionTipo;
                }
            }

        }return habitacionesTipo;


    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar (Habitacion habitacion) throws OperationNotSupportedException{
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede insertar una habitaci�n nula.");
        int indice = buscarIndice(habitacion);
        if (capacidadSuperada(indice))
            throw new OperationNotSupportedException("ERROR: No se aceptan m�s habitaciones.");
        if (tamanoSuperado(indice))
            throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");
        /*for (int i=0; i< tamano; i++){
            if (habitacion.getIdentificador().equals(coleccionHabitaciones[i].getIdentificador()))
                throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");
        }*/

        coleccionHabitaciones[tamano]=habitacion;
        tamano++;
    }

    private int buscarIndice (Habitacion habitacion){
        if (habitacion == null)
            throw new NullPointerException("ERROR: la habitaci�n indicada es nula.");
        for (int i=0; i<tamano; i++){
            if (habitacion.equals(coleccionHabitaciones[i])){
                return i;
            }
        }
        return -1;
    }

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

    public Habitacion buscar (Habitacion habitacion){
        int indice = buscarIndice(habitacion);
        if (indice != -1)
            return new Habitacion(coleccionHabitaciones[indice]);
        else return null;
    }

    public void borrar (Habitacion habitacion) throws OperationNotSupportedException{
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede borrar una habitaci�n nula.");
        if (buscarIndice(habitacion)<0)
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitaci�n como la indicada.");
        int indice = buscarIndice(habitacion);
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i = indice; i<tamano-1;i++){
            coleccionHabitaciones[i] = coleccionHabitaciones[i+1];
        }
        coleccionHabitaciones[tamano-1] = null;

    }



}