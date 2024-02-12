// comceco da implementação de uma interface grafica, não esta senfo usada atualmente


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDamasGUI extends JFrame {
    private Tabuleiro tabuleiro;

    public JogoDamasGUI() {
        super("Jogo de Damas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criar um objeto Tabuleiro
        tabuleiro = new Tabuleiro();

        // Criar um painel para o tabuleiro
        JPanel tabuleiroPanel = new JPanel(new GridLayout(8, 8));

        // Preencher o painel com botões representando as casas do tabuleiro
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(60, 60));

                // Adicionar um ActionListener para tratar os cliques nas casas
                button.addActionListener(new CasaClickListener(i, j));

                // Definir a cor do botão de acordo com a cor da casa no Tabuleiro
                if (tabuleiro.getCasa(i, j).getCor() == 0) {
                    button.setBackground(Color.BLACK);
                } else {
                    button.setBackground(Color.WHITE);
                }

                tabuleiroPanel.add(button);
            }
        }

        // Adicionar o painel do tabuleiro ao JFrame
        add(tabuleiroPanel);

        // Redimensionar a janela para ajustar o tamanho do tabuleiro
        pack();

        // Tornar a janela visível
        setVisible(true);
    }

    // ActionListener para lidar com os cliques nas casas do tabuleiro
    private class CasaClickListener implements ActionListener {
        private int linha;
        private int coluna;

        public CasaClickListener(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
        }

        private Icon createCircleIcon(Color color, int size) {
            return new Icon() {
                @Override
                public void paintIcon(Component c, Graphics g, int x, int y) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(color);
                    g2d.fillOval(x, y, size, size);
                    g2d.dispose();
                }
        
                @Override
                public int getIconWidth() {
                    return size;
                }
        
                @Override
                public int getIconHeight() {
                    return size;
                }
            };
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            // Realizar a lógica do jogo ao clicar em uma casa
            Casa casaClicada = tabuleiro.getCasa(linha, coluna);
            // Implemente a lógica adicional do jogo aqui

            // Atualizar a aparência do botão da casa clicada
            JButton button = (JButton) e.getSource();
            if (casaClicada.getOcupada()) {
                // Definir a aparência do botão com base na peça ocupando a casa
                if (casaClicada.getCorPeca() == 1) {
                    button.setText("Branca");
                    button.setForeground(Color.GRAY); // Define a cor do texto como cinza
                    button.setIcon(createCircleIcon(Color.GRAY, 40)); // Adiciona um ícone de círculo cinza
                    // Definir outras propriedades visuais para uma peça branca
                } else {
                    button.setText("Preta");
                     button.setForeground(new Color(87, 48, 35)); // Define a cor do texto como marrom escuro
                     button.setIcon(createCircleIcon(new Color(87, 48, 35), 40)); // Adiciona um ícone de círculo marrom escuro
                     // Definir outras propriedades visuais para uma peça preta
                }
            } 

            // Atualizar a interface gráfica
            revalidate();
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoDamasGUI());
    }
}


/*
 * 
 * public void atualizaArvore(int atualx, int atualy, int destinox, int destinoy, Tabuleiro tabuleiro_pos_jogada){

  ArrayList<No> folhas = obterFolhas(this.raiz);
  LinkedList<No> jogadas_brancas = folhas.get(0).getPai().getPai().getFilhos();

  Iterator<No> iter = jogadas_brancas.iterator();

  while(iter.hasNext()){
    No no = iter.next();
    int AtualBX = no.getJogada_feita().getPosicaoAtualX();
    int AtualBY = no.getJogada_feita().getPosicaoAtualY();
    int DestinoBX = no.getJogada_feita().getPosicaoDestinoX();
    int DestinoBY = no.getJogada_feita().getPosicaoDestinoY();
    //System.out.println("--- Jogada Branca: "+AtualBX+":"+AtualBY+"-->"+DestinoBX+":"+DestinoBY);
    //System.out.println("\n");

    if(AtualBX != atualx || AtualBY!=atualy || DestinoBX!=destinox || DestinoBY!=destinoy){
      //System.out.println("Excluindo Jogada Branca: "+AtualBX+":"+AtualBY+"-->"+DestinoBX+":"+DestinoBY);
      iter.remove();
    }
  }
  jogadas_brancas.get(0).setDisponivel(false);
}
 */
