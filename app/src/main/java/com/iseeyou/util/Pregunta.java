package com.iseeyou.util;

import java.util.ArrayList;

/**
 * Created by u197618 on 2/16/17.
 */

public class Pregunta {
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public Pregunta(){
//        this.titulo = "¿Votarias a Donal Trump?";
        this.respuestas = new ArrayList();
/*
        respuestas.add(new Respuesta("No lo votaria", true));
        respuestas.add(new Respuesta("Quizas", false));
        respuestas.add(new Respuesta("Si lo votaria", false));
*/
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private ArrayList respuestas;

    public void setRespuestas(ArrayList respuestas) {
        this.respuestas = respuestas;
    }

    public ArrayList getRespuestas(){
        return this.respuestas;
    }

    public void agregarRespuesta(Respuesta respuesta){
        this.respuestas.add(respuesta);
    }
}
