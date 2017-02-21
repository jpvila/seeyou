package com.iseeyou.util;

/**
 * Created by u197618 on 2/16/17.
 */

public class Respuesta {
    private String valor;
    private boolean esCorrecta;

    public Respuesta(String valor, boolean esCorrecta){
        this.valor = valor;
        this.esCorrecta = esCorrecta;

    }
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }



    public boolean isEsCorrecta(){
        return esCorrecta;
    }

    public String isEsCorrectaString(){
        if(esCorrecta){
            return "1";
        } else{
            return "0";
        }

    }
}
