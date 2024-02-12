import java.util.LinkedList;

public class No {
  private Tabuleiro tabuleiro_copia;
  private Jogadas jogada_feita;
  private int cor_peca;
  private int nota;
  private LinkedList<No> filhos = new LinkedList<>();
  private No pai;
  private boolean disponivel; // atributo que diz se um no ja foi usado no jogo ou não


  No(Tabuleiro tabuleiro_atual){ // construtor para raiz
    this.tabuleiro_copia = new Tabuleiro(tabuleiro_atual);
    this.disponivel = false;
  }

  No(Tabuleiro tabuleiro_atual, Jogadas jogada_feita, int cor_peca, No pai){ // construtor para filhos
    this.tabuleiro_copia = new Tabuleiro(tabuleiro_atual);
    this.jogada_feita = jogada_feita;
    this.tabuleiro_copia = jogada_feita.getTabuleiroAposJogada(this.tabuleiro_copia, jogada_feita);
    this.nota = (this.tabuleiro_copia.getQuantidadePretas() - this.tabuleiro_copia.getQuantidadeBrancas() ) - this.tabuleiro_copia.quantDamas(1) ;
    this.cor_peca = cor_peca;
    this.pai = pai;
    this.disponivel = true;
  }

  public void criaFilho(Tabuleiro tabuleiro, Jogadas jogada_feita, int cor_peca){
    No novo_filho = new No(tabuleiro, jogada_feita,  cor_peca, this);
    filhos.add(novo_filho);
  }

  public void setJogada_feita(Jogadas jogada_feita) {
    this.jogada_feita = jogada_feita;
  }

  public Jogadas getJogada_feita() {
    return jogada_feita;
  }

  public Tabuleiro getTabuleiroNo() {
    return tabuleiro_copia;
  }

  public No getFilhos(int indice) { // retorna um No específico dos nos filhos
    return filhos.get(indice);
  }

  public LinkedList<No> getFilhos(){ // retorna toda a lista de filhos
    return this.filhos;
  }

  public boolean filhosVazio(){
    return filhos.isEmpty();
  }

  public void removeFilhos(int index){
    this.filhos.remove(index);
  }

  public int getNota() {
    return nota;
  }

  public void setCor_peca(int cor_peca) {
    this.cor_peca = cor_peca;
  }

  public int getCor_peca() {
    return cor_peca;
  }

  public No getPai() {
    return pai;
  }

  public boolean getDisponivel(){
    return disponivel;
  }

  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }

  // Implementar metodo to string para imprimir a arvore

 
  public String toString() {
    
    return ("Cor da peca: "+cor_peca+"Nota do No: "+nota+"Jogada do No: "+jogada_feita);
  }

}
