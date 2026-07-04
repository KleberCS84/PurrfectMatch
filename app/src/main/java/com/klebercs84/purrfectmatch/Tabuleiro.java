package com.klebercs84.purrfectmatch;

import java.util.Random;

public class Tabuleiro {
    public static final int TAMANHO = 8; // grade 8x8

    private Gato[][] grade; // matriz de objetos Gato
    private Random random;

    // Construtor - cria o tabuleiro com gatos aleatórios
    public Tabuleiro(){
        random = new Random();
        grade = new Gato [TAMANHO][TAMANHO];
        inicializar();
    }

    // Preenche cada célula com um Gato de tipo aleatório
    private void inicializar(){
        for (int linha = 0;linha<TAMANHO;linha++){
            for (int coluna = 0; coluna < TAMANHO;coluna ++){
                int tipoAleatorio = random.nextInt(Gato.TOTAL_TIPOS);
                grade[linha][coluna] = new Gato(tipoAleatorio);
            }
        }
    }

    // Retorna o gato numa posição específica
    public Gato getGato(int linha, int coluna){
        return grade[linha][coluna];
    }
}
