package com.klebercs84.purrfectmatch;

import java.util.Random;

public class Tabuleiro {
    public static final int TAMANHO = 8; // grade 8x8

    private Gato[][] grade; // matriz de objetos Gato
    private Random random;

    // Construtor - cri i tabuleiro com gatos aleatórios
    public Tabuleiro(){
        random = new Random();
        grade = new Gato [TAMANHO][TAMANHO];
        inicializar();
    }

    // Retorna o gato numa posição específica
    public Gato getGato(int linha, int coluna){
        return grade[linha][coluna];
    }
}
