package com.iseeyou.util;

import java.util.ArrayList;

import link.fls.swipestacksample.R;

/**
 * Created by u197618 on 2/15/17.
 */

public class BDSingleton {
    private static final BDSingleton instance = new BDSingleton();
    private ArrayList personas = new ArrayList();
    private Persona personaActiva;
    private int posicionActual = 0;


    //private constructor to avoid client applications to use constructor
    private BDSingleton(){
        personas.add(armarPersona(R.drawable.sol, "Solange", "30", "Moron"));
        personas.add(armarPersona(R.drawable.mujer2, "Romina", "29", "Vicente Lopez"));
        personas.add(armarPersona(R.drawable.mujer3, "Marina", "32", "Ramos Mejia"));
        personas.add(armarPersona(R.drawable.mujer4, "Julieta", "24", "Floresta"));
        personas.add(armarPersona(R.drawable.mujer5, "Valeria", "30", "Monte Castro"));
        personas.add(armarPersona(R.drawable.mujer1, "Monica", "36", "Versalles"));
        personas.add(armarPersona(R.drawable.mujer2, "Marta", "44", "Monserrat"));
        personas.add(armarPersona(R.drawable.mujer3, "Marina", "32", "Ramos Mejia"));
        personas.add(armarPersona(R.drawable.mujer4, "Julieta", "24", "Floresta"));
        personas.add(armarPersona(R.drawable.mujer5, "Valeria", "30", "Monte Castro"));
    }

    public static BDSingleton getInstance(){
        return instance;
    }

    public Persona getPersonaPorPosition(int i){
        return (Persona) this.personas.get(i);
    }

    public Persona getPersonaActual(){
        return  this.getPersonaPorPosition(posicionActual);
    }

    public void avanzarPersona(){
        this.posicionActual++;
    }

    private Persona armarPersona(int imagen, String nombre, String edad, String ubicacion){
        Persona persona = new Persona(imagen, nombre, edad, ubicacion, true);
        Pregunta p1 = new Pregunta();
        p1.setTitulo("¿Aceptarias que tu pareja o novia tome vacaciones sola o con sus amigas?");

        Respuesta r1 = new Respuesta("Si", false);
        Respuesta r2 = new Respuesta("No", false);
        Respuesta r3 = new Respuesta("Depende del tiempo de relacion y los intereses personales", true);

        p1.agregarRespuesta(r1);
        p1.agregarRespuesta(r2);
        p1.agregarRespuesta(r3);

        persona.agregarPregunta(p1);

        Pregunta p2 = new Pregunta();
        p2.setTitulo("¿Crees importante actitudes como acompañar a tu pareja hasta la casa en un taxi?");
        Respuesta r4 = new Respuesta("No creo necesario acompañarla puede hacerlo sola", false);
        Respuesta r5 = new Respuesta("Si, es un gesto de querer al otro", true);
        Respuesta r6 = new Respuesta("Solo si me lo pide porque quizas quede invasivo", false);

        p2.agregarRespuesta(r4);
        p2.agregarRespuesta(r5);
        p2.agregarRespuesta(r6);



        Pregunta p3 = new Pregunta();
        p3.setTitulo("¿Cuando estas en pareja, te gusta compartir el grupo de amigos o crees que es mejor cada uno con su gente?");
        Respuesta r7 = new Respuesta("No me gusta que mis amigos se conviertan en sus amigos", false);
        Respuesta r8 = new Respuesta("Si estas en pareja, salis solo de a dos", false);
        Respuesta r9 = new Respuesta("Si, esta bueno conocer los amigoos de cada uno", true);

        p3.agregarRespuesta(r7);
        p3.agregarRespuesta(r8);
        p3.agregarRespuesta(r9);



        Pregunta p4 = new Pregunta();
        p4.setTitulo("¿Aceptarias que una mujer pague La primera cita la totalidad de la cuenta?");
        Respuesta r10 = new Respuesta("No aceptaria que pague, me Gusta invitar la primer cita", true);
        Respuesta r11 = new Respuesta("Si ella quiere pagar que lo haga", false);
        Respuesta r12 = new Respuesta("Le propongo mitad y mitad", false);

        p4.agregarRespuesta(r10);
        p4.agregarRespuesta(r11);
        p4.agregarRespuesta(r12);

        persona.agregarPregunta(p3);
        persona.agregarPregunta(p2);
        persona.agregarPregunta(p4);

        return persona;
    }

}
