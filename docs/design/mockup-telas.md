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

O arquivo `mockup-escolhido.png` nesta pasta mostra o mockup de referência.

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

## Tipografia

- **Display (título do jogo)**: 20px, weight 500, cor `#F0EEFF`
- **Subtítulo**: 9px, uppercase, letter-spacing 2px, cor `#9F8FFF`
- **Valores do HUD** (pontos, jogadas): 14px, weight 500, cor `#F0EEFF`
- **Labels do HUD**: 8px, uppercase, letter-spacing 1px, cor `#6B5FA8`
- **Objetivos da fase**: texto descritivo claro, ex: "Collect 10 Orange Tabbies"

---

## Telas

### 1. Tela inicial

**Objetivo**: apresentar o jogo com identidade clara e convidar o jogador
a começar com o mínimo de fricção.

**Elementos**:
- Cenário noturno de cidade ao fundo (telhados, chaminés, estrelas)
- Gatos silhueta nos telhados como decoração
- Título "Purrfect Match" + subtítulo "cat rescue"
- Mini-grid de gatos como prévia do gameplay
- Botão primário "Jogar" (roxo vibrante)
- Botão secundário "Fases"

---

### 2. Tela de jogo

**Objetivo**: mostrar toda a informação necessária sem poluir o tabuleiro.

**Elementos confirmados pelo mockup da IA**:
- HUD superior: Score (esquerda) + Moves Left (direita) com ícone de patinha
- Tabuleiro 8x8 com gatos sobre fundo azul/roxo escuro
- Borda dourada #FFD700 no gato selecionado
- Barra de progresso com ícone de gato deslizante e ícones de patinha
- Objetivos da fase descritos em texto: "Collect 10 Orange Tabbies / Rescue 3 Black Cats"
- Boosters: Yarn Ball (novelo de lã) e Fish Treat (petisco de peixe)
- Botões inferiores: Shop · Boosters · Menu

**Novos elementos descobertos nos mockups da IA** (implementar em etapas futuras):
- Boosters com ícones temáticos — relevantes para monetização
- Barra de progresso com marcadores percentuais (40%, 50%, 120%)
- Objetivos escritos por extenso na tela (mais claro para o jogador)

---

### 3. Tela de fase concluída

**Objetivo**: recompensar o jogador e motivar a continuar.

**Elementos**:
- Label "FASE X CONCLUÍDA"
- Sistema de 3 estrelas
- Gatos resgatados como recompensa emocional
- Resumo: matches feitos, jogadas usadas, bônus de jogadas sobrando
- Botão "Próxima fase" (primário) e "Menu" (secundário)

---

## Elemento de assinatura

O cenário noturno de cidade com gatos nas chaminés e janelas iluminadas
é o elemento mais memorável do Purrfect Match. Diferencia o jogo de outros
match-3 e reforça a narrativa de resgate noturno.

---

## Roadmap de implementação

- [x] Tabuleiro 8x8 com sprites de gatos
- [x] Toque e troca de gatos adjacentes
- [x] Detecção de match-3 e remoção
- [x] Queda de gatos (gravidade)
- [x] Pontuação básica
- [x] Destaque visual do gato selecionado
- [ ] HUD com score e moves left
- [ ] Cenário noturno de cidade ao fundo
- [ ] Barra de progresso com objetivos da fase
- [ ] Tela inicial com botões funcionais
- [ ] Tela de fase concluída com estrelas
- [ ] Boosters (Yarn Ball, Fish Treat)
- [ ] AdMob para monetização
