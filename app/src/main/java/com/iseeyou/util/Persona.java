package com.iseeyou.util;

import java.util.ArrayList;

/**
 * Created by u197618 on 2/15/17.
 */

public class Persona {
    private int idResourceImagen;
    private String edad;
    private String ubicacion;
    private int countOk = 0;
    private int countError = 0;
    private String nombre;

    public void setCountOk(int countOk) {
        this.countOk = countOk;
    }

    private int posicionPregunta = 0;
    private ArrayList preguntas;


    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }



    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }


    public Persona(int idResourceImagen, String nombre, String edad, String ubicacion){
        this.nombre = nombre;
        this.edad = edad;
        this.ubicacion = ubicacion;
        this.idResourceImagen = idResourceImagen;
        preguntas = new ArrayList();
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());

    }
    public Persona(int idResourceImagen, String nombre, String edad, String ubicacion, boolean tieneSusPreguntas){
        this.nombre = nombre;
        this.edad = edad;
        this.ubicacion = ubicacion;
        this.idResourceImagen = idResourceImagen;
        preguntas = new ArrayList();

    }

    public int getIdResourceImagen() {
        return idResourceImagen;
    }

    public void setIdResourceImagen(int idResourceImagen) {
        this.idResourceImagen = idResourceImagen;
    }

    public ArrayList getPreguntas() {
        return preguntas;
    }

    public int getPosicionPregunta(){
        return this.posicionPregunta;
    }

    public void avanzarPregunta(){
        posicionPregunta = posicionPregunta + 1;
    }

    public Pregunta getPreguntaActual(){
        if(posicionPregunta == 0){
            return (Pregunta) this.preguntas.get(posicionPregunta);
        } else{
            return (Pregunta) this.preguntas.get(posicionPregunta);
        }

    }
    public void sumarAcierto(){
        this.countOk = this.countOk + 1;
    }

    public void sumarError(){
        this.countError = this.countError + 1;
    }

    public int getCountOk(){
        return this.countOk;
    }

    public int getCountError(){
        return this.countError;
    }

    public String getNombre(){
        return this.nombre;
    }

    public boolean tieneMasIntentos(){
        if(posicionPregunta < 3) {
            return true;
        } else{
            if(countOk > 0){
                return true;
            } else{
                return false;
            }
        }
    }
    public boolean requiereBonusTrack(){
        if((posicionPregunta  == 3) && (countOk > 0)) {
            return true;
        } else{
            return false;
        }
    }

    public String getTituloPosicion(){
        if(posicionPregunta < 3){
            return " " + (posicionPregunta + 1) + " / 3";
        } else{
            return " 4 / 4";
        }
    }
    public String getNombreEdadUbicacionFormateada(){
        return this.nombre + ", " + this.edad + " (" + this.ubicacion + ")";
    }

    public void agregarPregunta(Pregunta pregunta){
        this.preguntas.add(pregunta);
    }
}

