# Design — Purrfect Match

Documento de decisões de design das telas do jogo, criado antes da
implementação para guiar o desenvolvimento visual e de UX.

## Decisão final de identidade visual

**Visual escolhido: Noturno Roxo** (Imagem 3 gerada pelo Nanobanana)

Paleta azul/roxo escuro com fundo de cenário noturno (telhados de cidade,
gatos nas chaminés, janela iluminada). Essa direção foi escolhida por:

- Identidade visual única — poucos jogos match-3 usam paleta noturna fria
- Reforça a narrativa de resgate: gatos perdidos à noite na cidade
- Contraste alto entre o fundo escuro e os sprites coloridos dos gatos
- Atmosfera emocional que cria apego ao tema

---

## Paleta de cores

| Nome | Hex | Uso |
|---|---|---|
| Fundo principal | `#12112A` | Background de todas as telas |
| Fundo secundário | `#1E1C3A` | Cards, HUD, painéis internos |
| Roxo vibrante | `#7B5FFF` | Botões primários, destaques |
| Roxo suave | `#9F8FFF` | Textos secundários, labels |
| Roxo escuro | `#6B5FA8` | Textos de apoio, labels menores |
| Borda sutil | `#4A3D8F` | Bordas de botões secundários |
| Texto principal | `#F0EEFF` | Títulos, valores em destaque |
| Selecionado | `#FFD700` | Borda dourada do gato selecionado |
| Azul noturno | `#1A1A3E` | Fundo do cenário de cidade |

---

## Telas implementadas

### 1. Tela inicial ✅
- Background noturno com constelações (`bg_tela_inicial.png`)
- Logo "Purrfect Match" animado subindo de fora da tela
- Botões PLAY e LEVELS com fade-in após o logo chegar
- Toque em PLAY inicia o jogo

### 2. Tela de jogo ✅
- Background noturno de cidade com grade 7x7 integrada (`bg_night_city.png`)
- HUD superior: Score (patinha dourada) + Moves Left (estrela roxa)
- Tabuleiro 7x7 com 6 tipos de sprites de gatos
- Borda dourada `#FFD700` no gato selecionado
- Barra de progresso com gato deslizante e ícones de patinha
- Card de objetivos com sprites dinâmicos e contadores
- Botões inferiores: Shop · Boosters · Menu

### 3. Tela de vitória ✅
- Card com troféu e coroa dourada (`card_vitoria.png`)
- Score final em destaque
- Botão "NEXT LEVEL" dourado

### 4. Tela de game over ✅
- Card com gato triste vermelho (`card_game_over.png`)
- Score final em destaque
- Botão "TRY AGAIN" roxo neon

---

## Fluxo de navegação implementado

```
Tela Inicial
    → [PLAY] → Tela de Jogo
                   → [objetivos concluídos] → Tela de Vitória
                                                   → [NEXT LEVEL] → Tela de Jogo
                   → [jogadas = 0] → Tela de Game Over
                                         → [TRY AGAIN] → Tela de Jogo
```

---

## Assets gerados (Nanobanana + remove.bg)

### Sprites do tabuleiro
`gato_laranja.png` · `gato_preto.png` · `gato_branco.png`
`gato_cinza.png` · `gato_rajado.png` · `gato_caramelo.png`

### Backgrounds
`bg_night_city.png` (jogo) · `bg_tela_inicial.png` (menu)

### HUD e UI
`hud_cards.png` · `hud_progress_bar.png` · `hud_card_fase.png`
`ic_paw_gold.png` · `ic_star_purple.png` · `ic_cat_progress.png`
`ic_paw_bar.png` · `ic_booster_yarn.png` · `ic_booster_fish.png`

### Botões
`btn_shop.png` · `btn_boosters.png` · `btn_menu.png`
`btn_play.png` · `btn_levels.png`
`btn_next_level.png` · `btn_try_again.png`

### Logo e cards
`logo_purrfect.png` · `card_vitoria.png` · `card_game_over.png`

---

## Roadmap de implementação

- [x] Tabuleiro 7x7 com sprites de gatos
- [x] Toque e troca de gatos adjacentes
- [x] Detecção de match-3 e remoção
- [x] Queda de gatos (gravidade)
- [x] Pontuação básica
- [x] Destaque visual do gato selecionado
- [x] HUD com score e moves left
- [x] Background noturno de cidade
- [x] Barra de progresso com objetivos da fase
- [x] Card de objetivos com sprites dinâmicos
- [x] Botões Shop · Boosters · Menu
- [x] Refatoração do código (onDraw → 6 métodos)
- [x] Tela inicial com logo animado e botões funcionais
- [x] Tela de vitória com troféu e score
- [x] Tela de game over com gato triste e score
- [x] Fluxo completo de navegação entre telas
- [ ] Boosters funcionais (Yarn Ball, Fish Treat)
- [ ] AdMob para monetização
- [ ] Publicação na Play Store
