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

    public void trocar(int linha1, int coluna1, int linha2, int coluna2){
        // Só permite troca de gatos adjacentes
        boolean mesmaLinha = (linha1 == linha2) && (Math.abs(coluna1 - coluna2) == 1);
        boolean mesmaColuna = (coluna1 == coluna2) && (Math.abs(linha1-linha2) == 1);
        if (!mesmaLinha && !mesmaColuna){
            return; // ignora troca inválida
        }

        // Troca os dois gatos de posição
        Gato temp = grade[linha1][coluna1];
        grade [linha1][coluna1] = grade[linha2][coluna2];
        grade [linha2][coluna2] = temp;
    }
}
