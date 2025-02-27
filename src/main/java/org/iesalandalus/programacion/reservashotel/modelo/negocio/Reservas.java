package org.iesalandalus.programacion.reservashotel.modelo.negocio;



import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Reservas {

    ArrayList<Reserva> coleccionReservas= new ArrayList<Reserva>();

    public Reservas (){

    }

    public ArrayList<Reserva> get(){
        return copiaProfundaReservas();
    }

    private ArrayList<Reserva> copiaProfundaReservas(){

        ArrayList<Reserva> copiaReservas= new ArrayList<Reserva>();
        /*for (Reserva reserva: coleccionReservas){
            copiaReservas.add(reserva);
        }*/

        Iterator<Reserva> iteradorReservas= coleccionReservas.iterator();

        while(iteradorReservas.hasNext()){
            Reserva reserva= new Reserva(iteradorReservas.next());
            copiaReservas.add(reserva);
        }


        // He mirado como hacerlo por interner, en este enlace da una solución oara ordenar por fecha de inicio y luego por fecha reserva.https://stackoverflow.com/questions/41402963/sort-an-arraylist-with-multiple-conditions

        // El problema es que lo tengo que hacer directamente en la copia profunda y no en las clases de vista.
        Comparator<Reserva> comparador= new Comparator<Reserva>() {
            @Override
            public int compare(Reserva o1, Reserva o2) {
                if (!o1.getFechaInicioReserva().equals(o2.getFechaInicioReserva()))
                    return o1.getFechaInicioReserva().compareTo(o2.getFechaInicioReserva());
                else return o1.getHabitacion().getIdentificador().compareTo(o2.getHabitacion().getIdentificador());
            }

        };
        Collections.sort(copiaReservas,comparador);

     return copiaReservas;
    }


    public int getTamano() {
        return get().size();
    }

    public void insertar (Reserva reserva) throws OperationNotSupportedException{
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        if (get().contains(reserva))
                throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        coleccionReservas.add(reserva);
    }


    public Reserva buscar (Reserva reserva){
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        if (get().contains(reserva))
            return reserva;
        else return null;
    }

    public void borrar (Reserva reserva)throws OperationNotSupportedException{
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        if (!get().contains(reserva))
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        coleccionReservas.remove(reserva);
    }



    public ArrayList<Reserva> getReservas (Huesped huesped) {
        if (huesped == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");

        ArrayList<Reserva> copiaReservaHuesped= new ArrayList<>();
        Iterator<Reserva> iteratorReserva= get().iterator();
        while (iteratorReserva.hasNext()){
            Reserva reserva= iteratorReserva.next();
            if (reserva.getHuesped().equals(huesped))
                copiaReservaHuesped.add(reserva);
        }
        /*for (Reserva reserva: get()){
            if (reserva.getHuesped().equals(huesped))
                copiaReservaHuesped.add(reserva);
        }*/

        return copiaReservaHuesped;
    }


    public ArrayList<Reserva> getReservas (TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        ArrayList<Reserva> copiaReservaTipoHabitacion= new ArrayList<>();
        Iterator<Reserva> iteratorReserva= get().iterator();
        while (iteratorReserva.hasNext()){
            Reserva reserva= iteratorReserva.next();
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion))
                copiaReservaTipoHabitacion.add(reserva);
        }
        /*for (Reserva reserva: get()){
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion))
                copiaReservaTipoHabitacion.add(reserva);
        }*/

        return copiaReservaTipoHabitacion;
    }

    /*
    En este caso, como necesitamos obtener las fechas posteriores a las que tenemos, primero inicializamos una variable de tipo LocalDate con el valor de la fecha actual y entonces hacemos lo mismo que antes verificando la fecha actual.
     */
    public ArrayList<Reserva> getReservasFuturas (Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        LocalDate fechaActual = LocalDate.now();
        ArrayList<Reserva> reservasHabitacionFuturas = new ArrayList<>();
        Iterator<Reserva> iteradorReservasFuturas= get().iterator();

        while (iteradorReservasFuturas.hasNext()){
            Reserva reservaFutura= iteradorReservasFuturas.next();
            if (reservaFutura.getHabitacion().equals(habitacion) && reservaFutura.getFechaInicioReserva().isAfter(fechaActual))
                reservasHabitacionFuturas.add(reservaFutura);
        }
        /*for (Reserva reservaFutura : get()) {
            if (reservaFutura.getHabitacion().equals(habitacion) && reservaFutura.getFechaInicioReserva().isAfter(fechaActual)) {
                reservasHabitacionFuturas.add(reservaFutura);
            }
        }*/
        return reservasHabitacionFuturas;
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null | fecha == null)
            throw new NullPointerException("Ni la reserva ni la fecha pueden ser nulas.");
        Iterator<Reserva> iteradorReservaCheckin = coleccionReservas.iterator();
        while (iteradorReservaCheckin.hasNext()){
            Reserva reservaCheckin = iteradorReservaCheckin.next();
            if (reservaCheckin!=null)
                if (reservaCheckin.equals(reserva))
                    reservaCheckin.setCheckIn(fecha);
        }

        /*for (Reserva reservaARealizarCheckin : coleccionReservas) {
            if (reservaARealizarCheckin != null) {
                if (reservaARealizarCheckin.equals(reserva)) {
                    reservaARealizarCheckin.setCheckIn(fecha);
                }
            }
        }*/
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha){
        if (reserva == null | fecha == null)
            throw new NullPointerException("Ni la reserva ni la fecha pueden ser nulas.");
        Iterator<Reserva> iteradorReservaCheckout= coleccionReservas.iterator();
        while (iteradorReservaCheckout.hasNext()){
            Reserva reservaCheckout = iteradorReservaCheckout.next();
            if (reservaCheckout!=null)
                if (reservaCheckout.equals(reserva))
                    reservaCheckout.setCheckOut(fecha);
        }
        /*for (Reserva reservaARealizarCheckout: coleccionReservas){
            if (reservaARealizarCheckout!=null){
            if (reservaARealizarCheckout.equals(reserva)){
                reservaARealizarCheckout.setCheckOut(fecha);
            }*/
            }
        }
