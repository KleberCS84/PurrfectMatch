package com.klebercs84.purrfectmatch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.MotionEvent;

public class GameView extends View{
    private Tabuleiro tabuleiro;
    private Paint paint;
    private int tamanhoCell;
    private Bitmap[] sprites;
    private int linhaSelecionada = -1;
    private int colunaSelecionada = -1;
    private int alturaView;
    private int offsetY;


    public GameView(Context context){
        super(context);
        tabuleiro = new Tabuleiro();
        paint = new Paint();
        carregarSprites (context);
    }

    private void carregarSprites(Context context){
        sprites = new Bitmap[Gato.TOTAL_TIPOS];
        sprites[Gato.LARANJA] = BitmapFactory.decodeResource(context.getResources(),R.drawable.gato_laranja);
        sprites[Gato.PRETO] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gato_preto);
        sprites[Gato.BRANCO] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gato_branco);
        sprites[Gato.CINZA] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gato_cinza);
        sprites[Gato.RAJADO] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gato_rajado);
        sprites[Gato.CARAMELO] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gato_caramelo);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        tamanhoCell = w / Tabuleiro.TAMANHO;
        alturaView = h;
        int alturaTotal = tamanhoCell * Tabuleiro.TAMANHO;
        offsetY = (alturaView - alturaTotal) / 2; // centraliza verticalmente
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // Fundo escuro
        canvas.drawColor(Color.rgb(20,20,40));

        for (int linha = 0; linha < Tabuleiro.TAMANHO; linha++){
            for (int coluna = 0; coluna < Tabuleiro.TAMANHO; coluna ++){
                Gato gato = tabuleiro.getGato(linha,coluna);
                if (gato == null) continue; // pula células vazias
                Bitmap sprite = sprites[gato.getTipo()];

                // Posição da imagem na tela
                float x = coluna * tamanhoCell;
                float y = linha * tamanhoCell + offsetY;

                // Redimesiona o sprite para caber na célula
                Bitmap redimendionado = Bitmap.createScaledBitmap(sprite, tamanhoCell, tamanhoCell, true);

                canvas.drawBitmap(redimendionado, x, y, paint);

                // Borda de destaque no gato selecionado
                if (gato.isSelecionado()){
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.rgb(255, 255, 0)); // amarelo
                    paint.setStrokeWidth(6);
                    canvas.drawRect(x, y, x + tamanhoCell, y + tamanhoCell, paint);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction()!= MotionEvent.ACTION_DOWN){
            return true;
        }

        // Calcula qual céllula foi tocada
        int coluna = (int) (event.getX() / tamanhoCell);
        int linha = (int) ((event.getY() - offsetY) / tamanhoCell);

        // Ignora toque fora do tabuleiro
        if (linha < 0 || linha >= Tabuleiro.TAMANHO || coluna < 0 || coluna >= Tabuleiro.TAMANHO){
            return true;
        }

        if (linhaSelecionada == -1){
            // Primeiro toque -- seleciona o gato
            linhaSelecionada = linha;
            colunaSelecionada = coluna;
            tabuleiro.getGato(linha, coluna).setSelecionado(true);
        } else {
            // segundo toque -- tenta trocar
            tabuleiro.getGato(linhaSelecionada, colunaSelecionada).setSelecionado(false);
            tabuleiro.trocar(linhaSelecionada, colunaSelecionada, linha, coluna);
            tabuleiro.verificarMatches();
            tabuleiro.aplicarGravidade();
            linhaSelecionada = -1;
            colunaSelecionada = -1;
        }

        invalidate(); // redesenha a tela
        return true;
    }
}