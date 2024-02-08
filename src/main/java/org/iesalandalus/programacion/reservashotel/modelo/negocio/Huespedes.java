package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;


import java.util.ArrayList;
import java.util.Arrays;

public class Huespedes {

    ArrayList<Huesped> coleccionHuespedes= new ArrayList<>();
    public Huespedes(){


    }


    public ArrayList<Huesped> get() {
        return copiaProfundaHuespedes();
    }

    private ArrayList<Huesped> copiaProfundaHuespedes() {

        ArrayList<Huesped> copiaHuespedes= new ArrayList<Huesped>();
        for (Huesped huesped: coleccionHuespedes){
            copiaHuespedes.add(huesped);
        }

        return copiaHuespedes;
    }


    public int getTamano() {
        return get().size();
    }


    public void insertar(Huesped huesped) throws OperationNotSupportedException{
        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
        if (get().contains(huesped))
            throw new OperationNotSupportedException("ERROR: Ya existe un hu�sped con ese dni.");
        coleccionHuespedes.add(huesped);
    }


    public Huesped buscar (Huesped huesped){
        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");

        if (get().contains(huesped))
            return huesped;
        else return null;
    }

    public void borrar (Huesped huesped) throws OperationNotSupportedException{
        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        if (!get().contains(huesped))
            throw new OperationNotSupportedException("ERROR: No existe ning�n hu�sped como el indicado.");

        coleccionHuespedes.remove(huesped);

    }


}