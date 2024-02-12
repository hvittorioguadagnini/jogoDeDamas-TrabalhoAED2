import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tabuleiro {
  private Casa casas[][] = new Casa[8][8];
  private int quantidadeBrancas = 12;
  private int quantidadePretas = 12;
  private String pecas_capturadas_brancas = "";
  private String pecas_capturadas_pretas = "";
  String jogoSalvo = ""; 
  
  Tabuleiro(){
    
    //definindo as casas e suas cores
    
    for (int i = 0; i < casas.length; i++) {
      for (int j = 0; j < casas[i].length; j++) {
          // Se a soma das coordenadas for par, é uma casa branca (1), caso contrário é uma casa preta (0)
          if ((i + j) % 2 == 0) {
              casas[i][j] = new Casa(1);
          } else {
              casas[i][j] = new Casa(0);
          }
      }
  }


    //setando as pecas

    for(int i=0;i<3;i++){
      for(int j=0; j<8; j++){
        if(casas[i][j].getCor() == 0){
          casas[i][j].setPeca(1, false);
        }
      }
    }

    for(int i=5;i<8;i++){
      for(int j=0; j<8; j++){
        if(casas[i][j].getCor() == 0){
          casas[i][j].setPeca(0, false);
        }
      }
    }
}

// criar um construtor que crie uma copia

Tabuleiro(Tabuleiro tabuleiro_original){

  for (int i = 0; i < casas.length; i++) {
    for (int j = 0; j < casas[i].length; j++) {
        // Se a soma das coordenadas for par, é uma casa branca (1), caso contrário é uma casa preta (0)
        if ((i + j) % 2 == 0) {
            casas[i][j] = new Casa(1);
        } else {
            casas[i][j] = new Casa(0);
        }
    }
  }

  // setar as pecas conforme a posicao no tabuleiro original

  for(int i=0; i<8;i++){
    for(int j=0; j<8; j++){
      if(tabuleiro_original.getCasa(i, j).getOcupada()){
        this.casas[i][j].setPeca(tabuleiro_original.getCasa(i, j).getCorPeca(), tabuleiro_original.getCasa(i, j).getDama());
      }
    }
  }

  // paramentros de quantidade de pecas
  this.quantidadeBrancas = tabuleiro_original.getQuantidadeBrancas();
  this.quantidadePretas = tabuleiro_original.getQuantidadePretas();



}

// ciar um tabuleiro a partir de um arquivo
Tabuleiro(String arq) {
  for (int i = 0; i < casas.length; i++) {
      for (int j = 0; j < casas[i].length; j++) {
          if ((i + j) % 2 == 0) {
              casas[i][j] = new Casa(1);
          } else {
              casas[i][j] = new Casa(0);
          }
      }
  }

  try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
      String linha;
      while ((linha = br.readLine()) != null) {
          String[] valores = linha.split(";");
          int corPeca = Integer.parseInt(valores[0]);
          int linhaPeca = Integer.parseInt(valores[1]);
          int colunaPeca = Integer.parseInt(valores[2]);
          int dama = Integer.parseInt(valores[3]);
         
          if (linhaPeca != 9 && colunaPeca != 9 && corPeca != 9 && dama != 9) {
              if (corPeca == 1 && dama == 0) {
                  casas[linhaPeca][colunaPeca].setPeca(1, false);
              } else if (corPeca == 1 && dama == 1) {
                  casas[linhaPeca][colunaPeca].setPeca(1, true);
          
          } else if (corPeca == 0 && dama == 0) {
                  casas[linhaPeca][colunaPeca].setPeca(0, false);
              } else if (corPeca == 0 && dama == 1) {
                  casas[linhaPeca][colunaPeca].setPeca(0, true);
              }
          }
      }
  } catch (IOException e) {
      e.printStackTrace();
  }
}



public void imprimeTabuleiro(){

  System.out.println("\n\n==================================================\n\n");
  System.out.println("\t PECAS BRANCAS: "+quantidadeBrancas+" / PECAS PRETAS: "+quantidadePretas);
  System.out.println("\t ------------------------------------\n\n");
  int cont= 0; //97 = char
  //char linha;
  System.out.println("   0  1  2  3  4  5  6  7");
  for(int i=0;i<8;i++){
    //linha = (char)(cont);
    System.out.print(cont+" "); cont++;
    for(int j=0; j<8; j++){
      if(casas[i][j].getOcupada()){
        if(casas[i][j].getCorPeca()==1){

            if(casas[i][j].getDama()){
              System.out.print("[X]"); // dama branca
            }
            else{
              System.out.print("[Y]"); // peca normal branca
            }
          
        }
        else{
            if(casas[i][j].getDama()){
              System.out.print("[O]"); // dama pretas
            }
            else{
              System.out.print("[P]"); // peca normal pretas
            }
        }
      }
      else{
        System.out.print("[ ]");
      }
    }
    System.out.print("\n");
  }

  System.out.println("\n\n==================================================\n\n");

}

public Casa getCasa(int linha, int coluna){
  return casas[linha][coluna];
}

public void eliminaPeca(int x, int y){
  if (casas[x][y].getCorPeca() == 1){
    quantidadeBrancas--;
    pecas_capturadas_brancas += "Peca Branca capturada na posicao: "+x+":"+y+"\n";
  }
  else{
    quantidadePretas--;
    pecas_capturadas_pretas += "Peca Preta capturada na posicao: "+x+":"+y+"\n";
  }

  casas[x][y].desocupar();
}

public int getQuantidadeBrancas() {
  return quantidadeBrancas;
}

public int getQuantidadePretas() {
  return quantidadePretas;
}

public void setQuantidadePretas(int quantidadePretas) {
  this.quantidadePretas = quantidadePretas;
}

public void setQuantidadeBrancas(int quantidadeBrancas) {
  this.quantidadeBrancas = quantidadeBrancas;
}

public String getPecas_capturadas_brancas() {
  return pecas_capturadas_brancas;
}

public String getPecas_capturadas_pretas() {
  return pecas_capturadas_pretas;
}

public int quantDamas(int corPeca){
  int quant = 0;
  for(int i=0; i<8; i++){
    for(int j=0; j<8 ; j++){

      if(casas[i][j].getDama()){
        if(casas[i][j].getCorPeca() == corPeca){
          quant++;
        }
      }
    }
  }

  return quant;
}

public int verificaVencedor(){
  if(quantidadeBrancas==0){
    System.out.println("\nPRETAS VENCERAM!");
    return 0; // pretas vencem
  }
  else if( quantidadePretas==0){
    System.out.println("\nBRANCAS VENCERAM!");
    return 1; //brancas vencem
  }
  else{
    return -1; // ninguem venceu ainda
  }
}

public void salvaJogo() {
	for (int i = 0; i < casas.length; i++) {
	      for (int j = 0; j < casas[i].length; j++) {	       
	          if (casas[i][j].getOcupada()) {
	              jogoSalvo += Integer.toString(casas[i][j].getCorPeca()) + ";" + 
	              Integer.toString(i) + ";" +
	              Integer.toString(j) + ";";
	              if(casas[i][j].getDama() == false) {
	            	  jogoSalvo += "0;\n";
	              }
	              else {
	            	  jogoSalvo += "1;\n";
	              }
	          } 
	      }
	}
	jogoSalvo += "9;9;9;9;";
}


}