import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Arvore {
  private No raiz;

  Arvore(Tabuleiro tabuleiro_atual){ // cria a raiz da arvore
    raiz = new No(tabuleiro_atual);
    raiz.setCor_peca(1);
  }

  public void analisaJogadas(){

    if(raiz.filhosVazio()){
      analisaProximaJogada(raiz);
    }

    // adicionar brancas e depois pretas
    // receber folhas -> adicionar jogada das brancas -> recebr folhas -> adicionar jogada das pretas

    for(int i=0; i<2 ; i++){
      ArrayList<No> folhas = obterFolhas(raiz);

      for(int j=0; j<folhas.size(); j++){
        analisaProximaJogada(folhas.get(j));
      }

    }
    
  }

  public void analisaProximaJogada(No no){ // recebe um no cria filhos para as proximas jogadas
    Jogadas jogada = new Jogadas();
    int cor_peca;

    if(no.getCor_peca() == 1){
      cor_peca = 0;
    }
    else{
      cor_peca = 1;
    }
      ArrayList<Jogadas> arrayJogadas = jogada.VerificaJogada(cor_peca, no.getTabuleiroNo());

      for(int i=0; i<arrayJogadas.size();i++){
        no.criaFilho(no.getTabuleiroNo(), arrayJogadas.get(i),cor_peca );

      }
  }

  public  ArrayList<No> obterFolhas(No no) {
    ArrayList<No> folhas = new ArrayList<>();

    // Verificar se o nó é uma folha
    if (no.filhosVazio()) {
        folhas.add(no);
    } else {
        // Navegar pelos nós filhos
        LinkedList<No> filhos = no.getFilhos();
        for (No filho : filhos) {
            ArrayList<No> folhasFilho = obterFolhas(filho);
            folhas.addAll(folhasFilho);
        }
    }

    return folhas;
}

public Set<No> obterPaisDasFolhas(No no) {
  Set<No> paisDasFolhas = new HashSet<>();

  if(raiz.getFilhos().isEmpty()){
    this.analisaJogadas();
  }

  // Verificar se o nó é uma folha
  if (no.filhosVazio()) {
      // Adicionar o pai ao conjunto
      No pai = no.getPai();
      if (pai != null) {
          paisDasFolhas.add(pai);
      }
  } else {
      // Navegar pelos nós filhos
      LinkedList<No> filhos = no.getFilhos();
      for (No filho : filhos) {
          Set<No> paisFilho = obterPaisDasFolhas(filho);
          paisDasFolhas.addAll(paisFilho);
      }
  }

  return paisDasFolhas;
}


public No obterNoPaiComMaiorMedia() {
  Set<No> paisDasFolhas = obterPaisDasFolhas(raiz);
  No melhorPai = null;
  double melhorMedia = Double.NEGATIVE_INFINITY;

  if(paisDasFolhas.isEmpty()){
    return melhorPai;
  }

  for (No pai : paisDasFolhas) {
      // Obter as folhas do nó pai
      LinkedList<No> folhas = pai.getFilhos();
      double somaNotas = 0;
      int numFolhas = folhas.size();

      // Calcular a média das notas das folhas
      for (No folha : folhas) {
          somaNotas += folha.getNota();
      }

      double media = somaNotas / numFolhas;

      // Verificar se a média atual é maior do que a melhor média encontrada até agora
      if (media > melhorMedia) {
          melhorMedia = media;
          melhorPai = pai;
      }
  }

  return melhorPai;
}




public void imprimirArvore(No no) {
  imprimirArvoreRecursivo(no,  "");
}

public static void imprimirArvoreRecursivo(No no, String nivel) {
  if (no == null) {
      return;
  }

  // Imprimir identação para representar o nível
  /*for (int i = 0; i < nivel; i++) {
      System.out.print("  ");
  }*/

  System.out.println(nivel + "> " + no);
  String nivelFilho = nivel + "  ";

  // Imprimir o nó atual
  //System.out.println(no);

  // Imprimir os nós filhos
  for (No filho : no.getFilhos()) {
      imprimirArvoreRecursivo(filho, nivelFilho);
  }
}

public No getRaiz() { // retorna a raiz
  return raiz;
}

//escolher jogada e excluir nos que nao derivem daquela jogada

public Jogadas escolheJogada(){

  imprimirArvore(raiz);

  No melhor_cenario = obterNoPaiComMaiorMedia();

  if(melhor_cenario == null || melhor_cenario.getPai() == null || melhor_cenario.getPai().getDisponivel() == false ){
    // não há jogadas disponiveis
    Jogadas jogada = new Jogadas(0, 0, 0, 0);
    return jogada;
  }

  Jogadas jogada_escolhida = melhor_cenario.getPai().getJogada_feita();
  melhor_cenario.getPai().setDisponivel(false);

  LinkedList<No> antecessor_filhos = melhor_cenario.getPai().getPai().getFilhos();

  Iterator<No> iter = antecessor_filhos.iterator();

  while(iter.hasNext()){
    No no = iter.next();
    if(no != melhor_cenario.getPai()){
      iter.remove();
    }
  }

  return jogada_escolhida;
}

//Criar metodo para atualizar a arvore após a jogada das brancas

public void atualizaArvore(int atualx, int atualy, int destinox, int destinoy, Tabuleiro tabuleiro_pos_jogada) {
  ArrayList<No> folhas = obterFolhas(this.raiz);
  LinkedList<No> jogadas_brancas = folhas.get(0).getPai().getPai().getFilhos();

  // Criar uma lista temporária para armazenar os nós que serão mantidos
  LinkedList<No> jogadasBrancasMantidas = new LinkedList<>();

  for (No no : jogadas_brancas) {
      int AtualBX = no.getJogada_feita().getPosicaoAtualX();
      int AtualBY = no.getJogada_feita().getPosicaoAtualY();
      int DestinoBX = no.getJogada_feita().getPosicaoDestinoX();
      int DestinoBY = no.getJogada_feita().getPosicaoDestinoY();

      if (AtualBX == atualx && AtualBY == atualy && DestinoBX == destinox && DestinoBY == destinoy) {
          jogadasBrancasMantidas.add(no);
      }
  }

  // Atualizar a lista de jogadas brancas com os nós mantidos
  jogadas_brancas.clear();
  jogadas_brancas.addAll(jogadasBrancasMantidas);

  // Marcar o primeiro nó como não disponível
  if (!jogadas_brancas.isEmpty()) {
      jogadas_brancas.get(0).setDisponivel(false);
  }
}


  


}


