package com.klebercs84.purrfectmatch;

import java.util.Random;

public class Tabuleiro {
    public static final int TAMANHO = 7; // grade 7x7

    private Gato[][] grade; // matriz de objetos Gato
    private Random random;
    private int pontuacao;
    private int jogadasRestantes;
    private Fase fase;


    // Construtor - cria o tabuleiro com gatos aleatórios
    public Tabuleiro(){
        random = new Random();
        grade = new Gato [TAMANHO][TAMANHO];
        jogadasRestantes = 20;

        // Fase 1: coletar 10 gatos laranja e 8 gatos cinza
        fase = new Fase(1, 20);
        fase.adicionarObjetivo(Gato.LARANJA, 10);
        fase.adicionarObjetivo(Gato.CINZA, 8);

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

    public int getPontuacao(){
        return pontuacao;
    }

    public int getJogadasRestantes(){
        return jogadasRestantes;
    }

    public Fase getFase() {
        return fase;
    }

    public boolean usarJogada(){
        if (jogadasRestantes > 0){
            jogadasRestantes--;
            return true;
        }
        return false;
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

    public boolean verificarMatches(){
        boolean encontrou = false;
        boolean[][] paraRemover = new boolean[TAMANHO][TAMANHO];

        // Verifica matches horizontais
        for (int linha = 0; linha < TAMANHO; linha++){
            for (int coluna = 0; coluna < TAMANHO -2; coluna++){
                int tipo = grade[linha][coluna].getTipo();
                if (tipo == grade[linha][coluna + 1].getTipo() && tipo == grade[linha][coluna + 2].getTipo()){
                    paraRemover[linha][coluna] = true;
                    paraRemover[linha][coluna + 1] = true;
                    paraRemover[linha][coluna + 2] = true;
                    encontrou = true;
                }
            }
        }

        // Verifica matches verticais
        for (int linha = 0; linha < TAMANHO - 2; linha++){
            for (int coluna = 0; coluna < TAMANHO; coluna++){
                int tipo = grade[linha][coluna].getTipo();
                if (tipo == grade[linha + 1][coluna].getTipo() && tipo == grade[linha + 2][coluna].getTipo()){
                    paraRemover[linha][coluna] = true;
                    paraRemover[linha + 1][coluna] = true;
                    paraRemover[linha + 2][coluna] = true;
                    encontrou = true;
                }
            }
        }

        // Remove os gatos que fazem match
        if (encontrou){
            removerMatches(paraRemover);
        }
        return encontrou;
    }

    private void removerMatches(boolean[][] paraRemover){

        for (int linha = 0; linha<TAMANHO;linha++){
            for (int coluna = 0; coluna<TAMANHO; coluna++){
                if (paraRemover[linha][coluna]){
                    fase.registrarColeta(grade[linha][coluna].getTipo());
                    // Substitui por um gato novo aleatório
                    //grade[linha][coluna] = new Gato(random.nextInt(Gato.TOTAL_TIPOS)); linha substituida pela linha abaixo para queda dos gatos
                    grade[linha][coluna] = null; // marca como vazio
                    pontuacao += 10;
                }
            }
        }
    }

    public void aplicarGravidade(){
        // Para cada coluna, empurra os gatos para baixo
        for (int coluna=0; coluna<TAMANHO; coluna++){
            // Começa de baixo para cima
            for (int linha = TAMANHO -1; linha >= 0; linha--){
                if (grade[linha][coluna] == null){
                    // Procura o próximo gato acima para cair
                    for (int acima = linha -1; acima >= 0; acima--){
                        if (grade[acima][coluna] != null){
                            grade[linha][coluna] = grade[acima][coluna];
                            grade[acima][coluna] = null;
                            break;
                        }
                    }
                }
            }
        }

        // Preenche os espaços vazios no topo com novos gatos

        for (int linha = 0; linha < TAMANHO; linha++){
            for (int coluna = 0; coluna < TAMANHO; coluna++){
                if (grade[linha][coluna] == null){
                    grade[linha][coluna] = new Gato(random.nextInt(Gato.TOTAL_TIPOS));
                }
            }
        }
    }


}
