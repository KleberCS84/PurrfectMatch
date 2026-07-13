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
import com.google.android.material.tabs.TabLayout;
import java.util.Map;

public class GameView extends View{
    private Tabuleiro tabuleiro;
    private Paint paint;
    private int tamanhoCell;
    private Bitmap[] sprites;
    private Bitmap icPawGold;
    private Bitmap icStarPurple;
    private Bitmap hudCards;
    private Bitmap hudCardObjetivos;
    private Bitmap hudProgressBar;
    private Bitmap hudCardFase;
    private Bitmap btnShop;
    private Bitmap btnBoosters;
    private Bitmap btnMenu;
    private Bitmap bgNightCity;
    private int linhaSelecionada = -1;
    private int colunaSelecionada = -1;
    private int alturaView;
    private int offsetY;
    private int offsetX;


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
        icPawGold = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_paw_gold);
        icStarPurple = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_star_purple);
        hudCards = BitmapFactory.decodeResource(context.getResources(), R.drawable.hud_cards);
        bgNightCity = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg_night_city);
        hudCardObjetivos = BitmapFactory.decodeResource(context.getResources(), R.drawable.hud_card_objetivos);
        hudProgressBar = BitmapFactory.decodeResource(context.getResources(), R.drawable.hud_progress_bar);
        hudCardFase = BitmapFactory.decodeResource(context.getResources(), R.drawable.hud_card_fase);
        btnShop = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_shop);
        btnBoosters = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_boosters);
        btnMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_menu);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        tamanhoCell = (int) (w * 0.94f) / Tabuleiro.TAMANHO;
        alturaView = h;
        int alturaTotal = tamanhoCell * Tabuleiro.TAMANHO;
        //offsetY = (alturaView - alturaTotal) / 2; // centraliza verticalmente
        offsetY = (int) (h * 0.29f);
        offsetX = (w - tamanhoCell * Tabuleiro.TAMANHO) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // Fundo escuro
        //canvas.drawColor(Color.rgb(20,20,40));
        // Background noturno
        if (bgNightCity !=null){
            Bitmap bg = Bitmap.createScaledBitmap(bgNightCity, getWidth(), getHeight(), true);
            canvas.drawBitmap(bg, 0,0,null);
        } else {
            canvas.drawColor(Color.rgb(20,20,40));
        }

        for (int linha = 0; linha < Tabuleiro.TAMANHO; linha++){
            for (int coluna = 0; coluna < Tabuleiro.TAMANHO; coluna ++){
                Gato gato = tabuleiro.getGato(linha,coluna);
                if (gato == null) continue; // pula células vazias
                Bitmap sprite = sprites[gato.getTipo()];

                // Posição da imagem na tela
                int padding = (int) (tamanhoCell * 0.02f);
                float x = coluna * tamanhoCell + offsetX + padding;
                float y = linha * tamanhoCell + offsetY + padding;
                int tamanhoSprite = tamanhoCell - padding * 2;

                // Redimesiona o sprite para caber na célula
                Bitmap redimensionado = Bitmap.createScaledBitmap(sprite, tamanhoSprite, tamanhoSprite, true);

                canvas.drawBitmap(redimensionado, x, y, paint);

                // Borda de destaque no gato selecionado
                if (gato.isSelecionado()){
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.rgb(255, 255, 0)); // amarelo
                    paint.setStrokeWidth(6);
                    canvas.drawRect(x, y, x + tamanhoCell, y + tamanhoCell, paint);
                }
            }
        }

        // === HUD ===
        int hudH = (int) (offsetY * 0.4f);
        int hudY = (offsetY - hudH) / 2;

        // Desenha os dois cards como uma única imagem
        if (hudCards !=null){
            Bitmap cards = Bitmap.createScaledBitmap(hudCards, getWidth(), hudH, true);
            canvas.drawBitmap(cards, 0, hudY, null);
        }

        // Números sobre os cards
        Paint hudPaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        hudPaint.setColor(Color.rgb(240, 238,255));
        hudPaint.setFakeBoldText(true);
        hudPaint.setTextAlign(Paint.Align.CENTER);
        hudPaint.setTextSize(hudH * 0.35f);

        // Score - centro da área escura do card esquerdo ( ~30% da largura)
        canvas.drawText(String.valueOf(tabuleiro.getPontuacao()), getWidth() * 0.30f, hudY + hudH * 0.78f, hudPaint);

        // Moves - centro da área escura do card direito (~78% da largura)
        canvas.drawText(String.valueOf(tabuleiro.getJogadasRestantes()), getWidth() * 0.78f, hudY + hudH * 0.78f, hudPaint);

        // === ÁREA INFERIOR ===
        Fase fase = tabuleiro.getFase();
        int areaX          = 16;
        int areaW          = getWidth() - areaX * 2;
        int tabuleiroBottom = offsetY + tamanhoCell * Tabuleiro.TAMANHO;
        int espacoTotal    = getHeight() - tabuleiroBottom;

// --- Barra de progresso (card vazio + preenchimento via código) ---
        int barH = (int)(espacoTotal * 0.20f);
        int barY = tabuleiroBottom + (int)(espacoTotal * 0.03f);

// Fundo da barra (asset vazio com patinhas)
        if (hudProgressBar != null) {
            Bitmap bar = Bitmap.createScaledBitmap(hudProgressBar, areaW, barH, true);
            canvas.drawBitmap(bar, areaX, barY, null);
        }

// Preenchimento roxo proporcional ao progresso
        float progresso = fase.getProgressoTotal();
        Paint barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        int barInnerX = areaX + (int)(areaW * 0.08f);  // margem das patinhas
        int barInnerW = (int)(areaW * 0.84f);           // largura interna
        int barInnerY = barY + (int)(barH * 0.25f);
        int barInnerH = (int)(barH * 0.50f);

// Fundo escuro interno
        barPaint.setColor(Color.rgb(20, 16, 48));
        barPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(barInnerX, barInnerY,
                barInnerX + barInnerW, barInnerY + barInnerH,
                barInnerH / 2f, barInnerH / 2f, barPaint);

// Preenchimento roxo vibrante
        if (progresso > 0) {
            barPaint.setColor(Color.rgb(123, 95, 255));
            canvas.drawRoundRect(barInnerX, barInnerY,
                    barInnerX + (int)(barInnerW * progresso), barInnerY + barInnerH,
                    barInnerH / 2f, barInnerH / 2f, barPaint);
        }

// Gato deslizante no topo da barra
        int catSize = (int)(barH * 0.90f);
        int catX    = barInnerX + (int)(barInnerW * progresso) - catSize / 2;
        catX = Math.max(barInnerX, Math.min(catX, barInnerX + barInnerW - catSize));
        int catY = barY + (barH - catSize) / 2;

        Bitmap catIcon = Bitmap.createScaledBitmap(sprites[Gato.LARANJA], catSize, catSize, true);
        canvas.drawBitmap(catIcon, catX, catY, null);

// --- Card de objetivos (card vazio + conteúdo via código) ---
        int cardFaseH = (int)(espacoTotal * 0.28f);
        int cardFaseY = barY + barH + (int)(espacoTotal * 0.02f);

        if (hudCardFase != null) {
            Bitmap card = Bitmap.createScaledBitmap(hudCardFase, areaW, cardFaseH, true);
            canvas.drawBitmap(card, areaX, cardFaseY, null);
        }

// Texto "PHASE X OBJECTIVES:"
        Paint labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setColor(Color.rgb(240, 238, 255));
        labelPaint.setFakeBoldText(true);
        labelPaint.setTextSize(cardFaseH * 0.22f);
        labelPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("PHASE " + fase.getNumero(), areaX + 14, cardFaseY + cardFaseH * 0.38f, labelPaint);
        canvas.drawText("OBJECTIVES:", areaX + 14, cardFaseY + cardFaseH * 0.72f, labelPaint);

// Objetivos: sprite do gato + contador
        int icX    = areaX + (int)(areaW * 0.38f);
        int icSize = (int)(cardFaseH * 0.65f);
        int icY    = cardFaseY + (cardFaseH - icSize) / 2;

        Paint contPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        contPaint.setFakeBoldText(true);
        contPaint.setTextAlign(Paint.Align.CENTER);
        contPaint.setTextSize(cardFaseH * 0.28f);

        int i = 0;
        for (Map.Entry<Integer, Integer> entry : fase.getObjetivos().entrySet()) {
            int tipo     = entry.getKey();
            int meta     = entry.getValue();
            int coletado = fase.getProgresso().get(tipo);
            boolean feito = coletado >= meta;

            // Sprite do gato
            Bitmap sprite = Bitmap.createScaledBitmap(sprites[tipo], icSize, icSize, true);
            int spX = icX + i * (int)(areaW * 0.35f);
            canvas.drawBitmap(sprite, spX, icY, null);

            // Contador abaixo do sprite
            contPaint.setColor(feito
                    ? Color.rgb(80, 200, 80)
                    : Color.rgb(240, 238, 255));
            canvas.drawText(coletado + "/" + meta,
                    spX + icSize / 2f,
                    cardFaseY + cardFaseH * 0.92f,
                    contPaint);
            i++;
        }

// --- Botões Shop / Boosters / Menu ---
        int btnH   = (int)(espacoTotal * 0.26f);
        int btnY   = cardFaseY + cardFaseH + (int)(espacoTotal * 0.02f);
        int btnW   = (int)(areaW * 0.28f);
        int gapBtn = (areaW - btnW * 3) / 4;

        if (btnShop != null) {
            Bitmap b = Bitmap.createScaledBitmap(btnShop, btnW, btnH, true);
            canvas.drawBitmap(b, areaX + gapBtn, btnY, null);
        }
        if (btnBoosters != null) {
            Bitmap b = Bitmap.createScaledBitmap(btnBoosters, btnW, btnH, true);
            canvas.drawBitmap(b, areaX + gapBtn + btnW + gapBtn, btnY, null);
        }
        if (btnMenu != null) {
            Bitmap b = Bitmap.createScaledBitmap(btnMenu, btnW, btnH, true);
            canvas.drawBitmap(b, areaX + gapBtn + (btnW + gapBtn) * 2, btnY, null);
        }



    }




    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction()!= MotionEvent.ACTION_DOWN){
            return true;
        }

        // Calcula qual céllula foi tocada
        int coluna = (int) ((event.getX() - offsetX)/ tamanhoCell);
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
            //tabuleiro.trocar(linhaSelecionada, colunaSelecionada, linha, coluna);
            //tabuleiro.verificarMatches();
            //tabuleiro.aplicarGravidade();
            if (tabuleiro.usarJogadas()){
                tabuleiro.trocar(linhaSelecionada, colunaSelecionada, linha, coluna);
                tabuleiro.verificarMatches();
                tabuleiro.aplicarGravidade();
            }
            linhaSelecionada = -1;
            colunaSelecionada = -1;
        }

        invalidate(); // redesenha a tela
        return true;
    }
}