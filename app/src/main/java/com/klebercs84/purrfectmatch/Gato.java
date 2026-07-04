package com.klebercs84.purrfectmatch;

public class Gato {
    // Tipos de gato possíveis
    public static final int LARANJA = 0;
    public static final int PRETO = 1;
    public static final int BRANCO = 2;
    public static final int CINZA = 3;
    public static final int RAJADO = 4;
    public static final int CARAMELO = 5;

    public static final int TOTAL_TIPOS = 6;

    // Atributos do gato
    private int tipo;
    private boolean selecionado;

    // Construtor
    public Gato(int tipo){
        this.tipo = tipo;
        this.selecionado = false;
    }

    // Getter e Setter (encapsulamento)
    public int getTipo(){
        return tipo;
    }
    public void setTipo(int tipo){
        this.tipo = tipo;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
