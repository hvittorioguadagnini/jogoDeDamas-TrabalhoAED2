# Jogo de Damas

Este é um jogo de Damas implementado em Java, utilizando as seguintes classes:

- `Aplicativo.java`: Classe principal que inicia o jogo.
- `Arquivo.java`: Classe responsável pela manipulação de arquivos, como salvar e carregar jogos.
- `Arvore.java`: Classe que implementa a estrutura de árvore utilizada para a inteligência artificial do computador.
- `Casa.java`: Representa uma casa do tabuleiro de damas.
- `Entrada.java`: Classe responsável pela entrada de dados, como solicitar movimentos aos jogadores.
- `Jogadas.java`: Classe que controla as jogadas possíveis em uma partida.
- `Jogo.java`: Classe que gerencia o estado atual do jogo, como o tabuleiro e os jogadores.
- `Nó.java`: Representa um nó na árvore de possibilidades de jogadas.
- `Tabuleiro.java`: Classe que representa o tabuleiro do jogo de Damas.
- `JogoDamasGUI.java`: Implementação da interface gráfica do jogo de Damas.

## Como Jogar

1. **Iniciar o Jogo:** Execute o arquivo `Aplicativo.java` para iniciar o jogo.
2. **Realizar Jogadas:** Durante sua vez, selecione uma peça para movimentar e escolha a casa de destino.
3. **Captura de Peças:** Se uma peça adversária estiver na posição de destino, ela será capturada.
4. **Promoção:** Quando uma peça chega à última linha do tabuleiro adversário, ela é promovida para uma dama.
5. **Vitória:** O jogo termina quando um dos jogadores captura todas as peças do oponente ou bloqueia todas as peças adversárias.

## Desenvolvimento

Este jogo de Damas foi desenvolvido em Java e utiliza uma abordagem orientada a objetos para representar as peças, o tabuleiro e as regras do jogo. A implementação inclui uma inteligência artificial para o computador jogar contra o jogador humano.

## Contribuindo

Se deseja contribuir para o desenvolvimento deste jogo, sinta-se à vontade para abrir problemas (issues) ou enviar solicitações de recebimento (pull requests) no repositório do projeto.

Divirta-se jogando Damas!
