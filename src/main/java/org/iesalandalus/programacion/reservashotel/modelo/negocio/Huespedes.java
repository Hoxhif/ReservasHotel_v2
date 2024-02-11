package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;

import javax.naming.OperationNotSupportedException;


import java.util.*;

public class Huespedes {

    ArrayList<Huesped> coleccionHuespedes= new ArrayList<>();
    public Huespedes(){


    }


    public ArrayList<Huesped> get() {
        return copiaProfundaHuespedes();
    }

    private ArrayList<Huesped> copiaProfundaHuespedes() {

        ArrayList<Huesped> copiaHuespedes= new ArrayList<Huesped>();
        /*for (Huesped huesped: coleccionHuespedes){
            copiaHuespedes.add(huesped);
        }*/

        Iterator<Huesped> iteradorHuesped= coleccionHuespedes.iterator();

        while(iteradorHuesped.hasNext()){
            Huesped huesped= new Huesped(iteradorHuesped.next());
            copiaHuespedes.add(huesped);
        }

        // el método sort nos ordena por defecto alfabéticamente por los nombres usando getNombre.
        //Collections.sort(copiaHuespedes, Comparator.comparing(Huesped::getNombre));

        return copiaHuespedes;
    }


    public int getTamano() {
        return get().size();
    }


    public void insertar(Huesped huesped) throws OperationNotSupportedException{
        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        if (get().contains(huesped))
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        coleccionHuespedes.add(huesped);
    }


    public Huesped buscar (Huesped huesped){
        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede buscar un huésped nulo.");
        if (get().contains(huesped))
            return huesped;
        else return null;
    }

    public void borrar (Huesped huesped) throws OperationNotSupportedException{
        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        if (!get().contains(huesped))
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");

        coleccionHuespedes.remove(huesped);

    }


}