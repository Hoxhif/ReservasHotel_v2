package org.iesalandalus.programacion.reservashotel.controlador;

import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controlador {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Modelo modelo, Vista vista){
        if (modelo == null)
            throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
        if (vista == null)
            throw new NullPointerException("ERROR: La vista no puede ser nulo");

        this.modelo=modelo;
        this.vista=vista;
        this.vista.setControlador(this);
        //Tenemos aliasing para poder asignar vista y modelo porque solo tenemos 1 vista y 1 modelo.
    }
    //Hemos copiado y pegado los m�todos de modelo, para poder hacer el controloador.

    public void comenzar(){
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar(){
        modelo.terminar();
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        modelo.insertar(huesped);
    }

    public Huesped buscar(Huesped huesped){
        return modelo.buscar(huesped);
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        modelo.borrar(huesped);
    }

    public ArrayList<Huesped> getHuespedes(){
        return modelo.getHuespedes();
    }

    public void insertar(Habitacion habitacion)throws OperationNotSupportedException{
        modelo.insertar(habitacion);
    }

    public Habitacion buscar(Habitacion habitacion){
        return modelo.buscar(habitacion);
    }

    public void borrar(Habitacion habitacion)throws OperationNotSupportedException{
        modelo.borrar(habitacion);
    }

    public ArrayList<Habitacion> getHabitaciones(){
        return modelo.getHabitaciones();
    }

    public ArrayList<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion){
        return modelo.getHabitaciones(tipoHabitacion);
    }

    public void insertar(Reserva reserva)throws OperationNotSupportedException{
        modelo.insertar(reserva);
    }

    public void borrar(Reserva reserva)throws OperationNotSupportedException{
        modelo.borrar(reserva);
    }

    public Reserva buscar(Reserva reserva){
        return modelo.buscar(reserva);
    }

    public ArrayList<Reserva> getReservas(){
        return modelo.getReservas();
    }

    public ArrayList<Reserva> getReserva(Huesped huesped){
        return modelo.getReserva(huesped);
    }

    public ArrayList<Reserva> getReserva(TipoHabitacion tipoHabitacion){
        return modelo.getReserva(tipoHabitacion);
    }

    public ArrayList<Reserva> getReserva(Habitacion habitacion){
        return modelo.getReserva(habitacion);
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha){
        modelo.realizarCheckin(reserva,fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha){
        modelo.realizarCheckout(reserva,fecha);
    }


}
