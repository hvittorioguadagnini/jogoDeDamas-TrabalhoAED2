import java.util.ArrayList;

// implementar logica do jogo 1x1 e 1xPC

public class Jogo {
private String jogador1;
private String jogador2;
private Tabuleiro tabuleiro_jogo;
private Arvore arvore_decisao;
private boolean arvore_inicializada = false;
String sequenciaJogo = "";
private Entrada controleEntrada = new Entrada();

Jogo(String jogador1, String jogador2){ // INICIA UM JOGO 1X1
  this.jogador1 = jogador1;
  this.jogador2 = jogador2;
  
  tabuleiro_jogo = new Tabuleiro();
  tabuleiro_jogo.imprimeTabuleiro();
}

Jogo(String jogador1){ // INICIA UM JOGO 1XPC
	 this.jogador1 = jogador1;
	 tabuleiro_jogo = new Tabuleiro();
	 tabuleiro_jogo.imprimeTabuleiro();
}

Jogo(String jogador1, String jogador2, String arq){
  this.jogador1 = jogador1;
  this.jogador2 = jogador2;
  
  tabuleiro_jogo = new Tabuleiro(arq);
  tabuleiro_jogo.imprimeTabuleiro();
}

Jogo(String jogador1, int a){ // INICIA UM JOGO 1XPC
		  this.jogador1 = jogador1;
		  tabuleiro_jogo = new Tabuleiro(Aplicativo.arq);
		  tabuleiro_jogo.imprimeTabuleiro();
}

public void jogadaBrancas(){
  Jogadas jogada = new Jogadas();
  ArrayList<Jogadas> jogadasPossiveis = jogada.VerificaJogada(1, tabuleiro_jogo);
  /*for(int i=0; i<jogadasPossiveis.size(); i++){
    System.out.println(jogadasPossiveis.get(i));
  }*/

  if(jogadasPossiveis.isEmpty()){ 
    // nao tem jogadas possiveis brancas
    System.out.println("\n---- NAO HA JOGADAS DISPONIVEIS ----");

    tabuleiro_jogo.setQuantidadeBrancas(0);
  }
  
  Integer atualX = null, atualY = null, destinoX = null, destinoY = null;
  System.out.println("VEZ DE: "+jogador1);

  String linha_atual = ("DIGITE A LINHA DA CASA DA PECA QUE DESEJA MOVER");
  atualX = controleEntrada.insereInteiro(linha_atual, atualX);
  String coluna_atual = ("DIGITE A COLUNA DA CASA DA PECA QUE DESEJA MOVER");
  atualY = controleEntrada.insereInteiro(coluna_atual, atualY);
  String linha_destino = ("DIGITE A LINHA DA CASA DESTINO");
  destinoX = controleEntrada.insereInteiro(linha_destino, destinoX);
  String coluna_destino = ("DIGITE A COLUNA DA CASA DESTINO");
  destinoY = controleEntrada.insereInteiro(coluna_destino, destinoY);

  while (jogada.fazerJogada(1, tabuleiro_jogo, atualX, atualY, destinoX, destinoY) == false ){
    System.out.println("\n----- ESCOLHA UMA JOGADA VALIDA!!! ------\n");

    atualX = null; atualY = null; destinoX = null; destinoY = null;

    atualX = controleEntrada.insereInteiro(linha_atual, atualX);
    atualY = controleEntrada.insereInteiro(coluna_atual, atualY);
    destinoX = controleEntrada.insereInteiro(linha_destino, destinoX);
    destinoY = controleEntrada.insereInteiro(coluna_destino, destinoY);

    }
    
    if(arvore_inicializada){
      arvore_decisao.atualizaArvore(atualX, atualY, destinoX, destinoY, this.tabuleiro_jogo);
    }

    this.sequenciaJogo += "Peça branca movida da linha: "+Integer.toString(atualX) +
		  "coluna: "+Integer.toString(atualY) +"para linha: "
      +Integer.toString(destinoX) +"coluna: "+Integer.toString(destinoY)+"\n";
    
    
  tabuleiro_jogo.imprimeTabuleiro();

  //sc.close();
}

public void jogadaPretas(){
  Jogadas jogada = new Jogadas();

  ArrayList<Jogadas> jogadasPossiveis = jogada.VerificaJogada(0, tabuleiro_jogo);
  /*for(int i=0; i<jogadasPossiveis.size(); i++){
    System.out.println(jogadasPossiveis.get(i));
  }*/

  if(jogadasPossiveis.isEmpty()){ 
    // nao tem jogadas possiveis brancas
    System.out.println("\n---- NAO HA JOGADAS DISPONIVEIS ----");

    tabuleiro_jogo.setQuantidadePretas(0);
  }

  Integer atualX = null, atualY = null, destinoX = null, destinoY = null;
  System.out.println("VEZ DE: "+jogador2);
  String linha_atual = ("DIGITE A LINHA DA CASA DA PECA QUE DESEJA MOVER");
  atualX = controleEntrada.insereInteiro(linha_atual, atualX);
  String coluna_atual = ("DIGITE A COLUNA DA CASA DA PECA QUE DESEJA MOVER");
  atualY = controleEntrada.insereInteiro(coluna_atual, atualY);
  String linha_destino = ("DIGITE A LINHA DA CASA DESTINO");
  destinoX = controleEntrada.insereInteiro(linha_destino, destinoX);
  String coluna_destino = ("DIGITE A COLUNA DA CASA DESTINO");
  destinoY = controleEntrada.insereInteiro(coluna_destino, destinoY);

  while (jogada.fazerJogada(0, tabuleiro_jogo, atualX, atualY, destinoX, destinoY) == false ){
    System.out.println("\n------ Escolha uma jogada válida!!! ------\n");

    atualX = null; atualY = null; destinoX = null; destinoY = null;
    atualX = controleEntrada.insereInteiro(linha_atual, atualX);
    atualY = controleEntrada.insereInteiro(coluna_atual, atualY);
    destinoX = controleEntrada.insereInteiro(linha_destino, destinoX);
    destinoY = controleEntrada.insereInteiro(coluna_destino, destinoY);

  }

  this.sequenciaJogo += "Peça preta movida da linha: "+Integer.toString(atualX) +
		  "coluna: "+Integer.toString(atualY) +"para linha: "
  +Integer.toString(destinoX) +"coluna: "+Integer.toString(destinoY)+"\n";

  tabuleiro_jogo.imprimeTabuleiro();

  //sc.close();
}

public void iniciaPC(){
  arvore_decisao = new Arvore(this.tabuleiro_jogo);
  arvore_inicializada = true;
}


public void jogadaPC(/*Jogadas jogada_branca*/){ // criar metodo para o computador fazer uma jogada
  arvore_decisao.analisaJogadas();
  //arvore_decisao.imprimirArvore(arvore_decisao.getRaiz());
  Jogadas jogadaPc = new Jogadas();
  jogadaPc = arvore_decisao.escolheJogada();

  if(jogadaPc.getPosicaoAtualX() ==0 && jogadaPc.getPosicaoAtualY() == 0 && jogadaPc.getPosicaoDestinoX() ==0 && jogadaPc.getPosicaoDestinoY() ==0){
    // não tem jogada disponivel
    // adversario ganha

    System.out.println("\n---- NAO HA JOGADAS DISPONIVEIS ----");

    tabuleiro_jogo.setQuantidadePretas(0);
  }
  else{

  System.out.println("A jogada Escolhida pelo Pc : \n"+jogadaPc);

  jogadaPc.fazerJogada(this.tabuleiro_jogo, jogadaPc);
  this.tabuleiro_jogo.imprimeTabuleiro();

  } 
  
}



public Tabuleiro getTabuleiro_jogo() {
  return tabuleiro_jogo;
}

public void imprimirArvoreJogo(){
  arvore_decisao.imprimirArvore(arvore_decisao.getRaiz());
}



  
}
