package com.klebercs84.purrfectmatch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View{
    private Tabuleiro tabuleiro;
    private Paint paint;
    private int tamanhoCell;
    private Bitmap[] sprites;

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
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // Fundo escuro
        canvas.drawColor(Color.rgb(20,20,40));

        for (int linha = 0; linha < Tabuleiro.TAMANHO; linha++){
            for (int coluna = 0; coluna < Tabuleiro.TAMANHO; coluna ++){
                Gato gato = tabuleiro.getGato(linha,coluna);
                Bitmap sprite = sprites[gato.getTipo()];

                // Posição da imagem na tela
                float x = coluna * tamanhoCell;
                float y = linha * tamanhoCell;

                // Redimesiona o sprite para caber na célula
                Bitmap redimendionado = Bitmap.createScaledBitmap(sprite, tamanhoCell, tamanhoCell, true);

                canvas.drawBitmap(redimendionado, x, y, paint);
            }
        }
    }
}