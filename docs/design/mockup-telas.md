# Design — Purrfect Match

Documento de decisões de design das telas do jogo, criado antes da
implementação para guiar o desenvolvimento visual e de UX.

## Identidade visual

### Paleta de cores

| Nome | Hex | Uso |
|---|---|---|
| Fundo principal | `#12112A` | Background de todas as telas |
| Fundo secundário | `#1E1C3A` | Cards, HUD, painéis internos |
| Roxo vibrante | `#7B5FFF` | Botão primário, cor de destaque |
| Roxo suave | `#9F8FFF` | Textos secundários, labels |
| Roxo escuro | `#6B5FA8` | Textos de apoio, labels menores |
| Borda sutil | `#4A3D8F` | Bordas de botões secundários |
| Texto principal | `#F0EEFF` | Títulos, valores, texto em destaque |
| Selecionado | `#FFD700` | Borda amarela do gato selecionado |

**Justificativa**: o tema roxo escuro remete ao céu noturno urbano — cenário
onde gatos perdidos precisam ser resgatados. É incomum em jogos match-3
(que costumam usar paletas vibrantes e coloridas), o que diferencia
visualmente o Purrfect Match.

### Tipografia

- **Display (título do jogo)**: 20px, weight 500, cor `#F0EEFF`
- **Subtítulo**: 9px, uppercase, letter-spacing 2px, cor `#9F8FFF`
- **Valores do HUD** (pontos, jogadas): 14px, weight 500, cor `#F0EEFF`
- **Labels do HUD**: 8px, uppercase, letter-spacing 1px, cor `#6B5FA8`
- **Corpo / objetivos**: 9–10px, cor `#9F8FFF`

---

## Telas

### 1. Tela inicial

**Objetivo**: apresentar o jogo com identidade clara e convidar o jogador
a começar com o mínimo de fricção.

**Elementos**:
- Ícone central de gato (âncora visual)
- Título "Purrfect Match" + subtítulo "cat rescue" em uppercase
- Mini-grid 4×2 de gatos como decoração animada (mostra o gameplay)
- Botão primário "Jogar" (roxo vibrante)
- Botão secundário "Fases" (apenas borda)

**Decisão de design**: a mini-grid de gatos serve como prévia do gameplay
logo na tela inicial — o jogador entende o jogo antes de apertar qualquer
botão.

---

### 2. Tela de jogo

**Objetivo**: mostrar toda a informação necessária sem poluir a área do
tabuleiro.

**Elementos**:
- HUD superior: pontos (esquerda) · número da fase (centro) · jogadas
  restantes (direita)
- Tabuleiro 8×8 de gatos (área principal)
- Barra de progresso do objetivo atual
- Lista de objetivos da fase com contadores e check quando concluído

**Decisão de design**: o HUD foi colocado acima do tabuleiro (não abaixo)
porque o jogador olha naturalmente para cima ao checar informações,
mantendo os olhos próximos do início do tabuleiro onde a ação começa.

**Destaque de seleção**: borda amarela (`#FFD700`) ao redor do gato
selecionado — contrasta com o roxo escuro e é universalmente reconhecido
como "selecionado" em jogos.

---

### 3. Tela de fase concluída

**Objetivo**: recompensar o jogador, mostrar o desempenho e motivar a
continuar.

**Elementos**:
- Label "FASE X CONCLUÍDA" em uppercase
- Sistema de 3 estrelas (avaliação de desempenho)
- Gatos "resgatados" como recompensa emocional (tema narrativo)
- Resumo de desempenho: matches feitos, jogadas usadas, bônus
- Botão "Próxima fase" (primário) e "Menu" (secundário)

**Decisão de design**: mostrar os gatos resgatados reforça a narrativa de
resgate — o jogador não "ganhou pontos", ele "salvou gatinhos". Isso
cria apego emocional e motiva a continuar jogando (relevante para
monetização: jogador engajado é mais propenso a comprar vidas extras).

---

## Elemento de assinatura

O elemento visual mais memorável do Purrfect Match é o **contraste entre
o fundo roxo escuro e os sprites de gatos coloridos** — enquanto outros
jogos match-3 usam fundos brancos ou coloridos que competem com as peças,
aqui o fundo recua completamente, fazendo os gatos "saltarem" da tela.

---

## Próximos passos de implementação

- [ ] Tela inicial com botões funcionais
- [ ] HUD de pontos e jogadas na tela de jogo
- [ ] Sistema de objetivos por fase
- [ ] Barra de progresso do objetivo
- [ ] Tela de fase concluída com estrelas
