import java.util.ArrayList;

// verificar de quem é a vez de jogar
// verificar jogadas possiveis --> casa ocupada --> casa do jogador 
// fazer uma jogada
// verificar se a peça pode comer mais uma peça


public class Jogadas{

  private int posicaoAtualX;
  private int posicaoAtualY;
  private int posicaoDestinoX;
  private int posicaoDestinoY;
  private boolean come;
  private int posicaoComidaX;
  private int posicaoComidaY;

  Jogadas(){}

  Jogadas(int atualX, int atualY, int destinoX, int destinoY){
    this.posicaoAtualX = atualX;
    this.posicaoAtualY = atualY;
    this.posicaoDestinoX = destinoX;
    this.posicaoDestinoY = destinoY;
  }

  public void setCome(boolean come) {
    this.come = come;
  }

  public boolean getCome(){
    return this.come;
  }

  public int getPosicaoAtualX() {
    return posicaoAtualX;
  }

  public int getPosicaoAtualY() {
    return posicaoAtualY;
  }

  public int getPosicaoDestinoX() {
    return posicaoDestinoX;
  }

  public int getPosicaoDestinoY() {
    return posicaoDestinoY;
  }

  public int getPosicaoComidaX() {
    return posicaoComidaX;
  }

  public int getPosicaoComidaY() {
    return posicaoComidaY;
  }

  public void setPosicaoComidaX(int posicaoComidaX) {
    this.posicaoComidaX = posicaoComidaX;
  }

  public void setPosicaoAtualY(int posicaoAtualY) {
    this.posicaoAtualY = posicaoAtualY;
  }

  public void setPosicaoComida(int posicaoComidaX,int posicaoComidaY) {
    this.posicaoComidaX = posicaoComidaX;
    this.posicaoComidaY = posicaoComidaY;
  }

  private ArrayList<Jogadas> verificaJogadaDama(int i, int j, Tabuleiro tabuleiro){
    ArrayList<Jogadas> jogada = new ArrayList<>();

    if(i-1>=0 && j-1>=0){
                
      int k=1; int h=1; 
      while(i-k>=0 && j-h>=0){

        if(tabuleiro.getCasa(i-k, j-h).getOcupada()){
          if(tabuleiro.getCasa(i-k, j-h).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
            if(i-k-1>=0 && j-h-1>=0){
              if(tabuleiro.getCasa(i-k-1, j-h-1).getOcupada() == false){
                Jogadas nova_Jogada = new Jogadas(i, j, i-k-1, j-h-1);
                nova_Jogada.setCome(true); nova_Jogada.setPosicaoComida(i-k, j-h);
                jogada.add(nova_Jogada);
              }   
            }
          } 
            break;
        }
        else{
          Jogadas nova_Jogada = new Jogadas(i, j, i-k, j-h);
          jogada.add(nova_Jogada);
        }

        k++; h++;
      }
      
    }

    if(i-1>=0 && j+1<8){

      int k=1; int h=1; 
      while(i-k>=0 && j+h<8){

        if(tabuleiro.getCasa(i-k, j+h).getOcupada()){
          if(tabuleiro.getCasa(i-k, j+h).getCorPeca()!= tabuleiro.getCasa(i, j).getCorPeca()){
            if(i-k-1>=0 && j+h+1<8){

              if(tabuleiro.getCasa(i-k-1, j+h+1).getOcupada() == false){
                Jogadas nova_Jogada = new Jogadas(i, j, i-k-1, j+h+1);
                nova_Jogada.setCome(true); nova_Jogada.setPosicaoComida(i-k, j+h);
                jogada.add(nova_Jogada);
              }
                
            }
          }
            break;
        }
        else{
          Jogadas nova_Jogada = new Jogadas(i, j, i-k, j+h);
          jogada.add(nova_Jogada);
        }

        k++; h++;
      }
    }

    if(i+1<8 && j+1<8){

      int k=1; int h=1; 
      while(i+k<8 && j+h<8){

        if(tabuleiro.getCasa(i+k, j+h).getOcupada()){
          if(tabuleiro.getCasa(i+k, j+h).getCorPeca()!= tabuleiro.getCasa(i, j).getCorPeca()){
            if(i+k+1<8 && j+h+1<8){

              if(tabuleiro.getCasa(i+k+1, j+h+1).getOcupada() == false){
                Jogadas nova_Jogada = new Jogadas(i, j, i+k+1, j+h+1);
                nova_Jogada.setCome(true); nova_Jogada.setPosicaoComida(i+k, j+h);
                jogada.add(nova_Jogada);
              }
            }
          }
                                 
            break;
        }
        else{
          Jogadas nova_Jogada = new Jogadas(i, j, i+k, j+h);
          jogada.add(nova_Jogada);
        }

        k++; h++;
      }

    }

    if(i+1<8 && j-1>=0){

      int k=1; int h=1; 
      while(i+k<8 && j-h>=0){

        if(tabuleiro.getCasa(i+k, j-h).getOcupada()){
          if(tabuleiro.getCasa(i+k, j-h).getCorPeca()!= tabuleiro.getCasa(i, j).getCorPeca()){
            if(i+k+1<8 && j-h-1>=0){
              if(tabuleiro.getCasa(i+k+1, j-h-1).getOcupada() == false){
                Jogadas nova_Jogada = new Jogadas(i, j, i+k+1, j-h-1);
                nova_Jogada.setCome(true); nova_Jogada.setPosicaoComida(i+k, j-h);
                jogada.add(nova_Jogada);
              } 
            }
          }
                               
            break;
        }
        else{
          Jogadas nova_Jogada = new Jogadas(i, j, i+k, j-h);
          jogada.add(nova_Jogada);
        }

        k++; h++;
      }

    }

    return jogada;
  }

  private ArrayList<Jogadas> verificaJogadaNormal(int i, int j, Tabuleiro tabuleiro){

    ArrayList<Jogadas> jogada = new ArrayList<>();

    if(i-1>=0 && j-1>=0){

      if(tabuleiro.getCasa(i, j).getCorPeca() == 1){// está movendo para trás -> branca
        if(tabuleiro.getCasa(i-1, j-1).getOcupada()){
          if(tabuleiro.getCasa(i-1, j-1).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
            if(i-2>=0 && j-2>=0){
              if(tabuleiro.getCasa(i-2, j-2).getOcupada() == false){
                Jogadas jogada1 = new Jogadas(i,j,i-2, j-2);
                jogada1.setCome(true); jogada1.setPosicaoComida(i-1,j-1);
                jogada.add(jogada1);
              }
            } 
          }
        }
      }
      else{
        if(tabuleiro.getCasa(i-1, j-1).getOcupada() == false){
          Jogadas jogada2 = new Jogadas(i, j ,i-1, j-1);
          jogada2.setCome(false);
          jogada.add(jogada2);
        }
        else{
          if(tabuleiro.getCasa(i-1, j-1).getOcupada()){
            if(tabuleiro.getCasa(i-1, j-1).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
              if(i-2>=0 && j-2>=0){
                if(tabuleiro.getCasa(i-2, j-2).getOcupada() == false){
                  Jogadas jogada3 = new Jogadas(i, j, i-2, j-2);
                  jogada3.setCome(true); jogada3.setPosicaoComida(i-1, j-1);
                  jogada.add(jogada3);
                }
              }
            }
          }
        }
      }

    }


    if(i-1>=0 && j+1<8){

      if(tabuleiro.getCasa(i, j).getCorPeca() == 1){  // está movendo para trás -> branca
        if(tabuleiro.getCasa(i-1, j+1).getOcupada()){
          if(tabuleiro.getCasa(i-1, j+1).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
            if(i-2>=0 && j+2<8){
              if(tabuleiro.getCasa(i-2, j+2).getOcupada() == false){
                Jogadas jogada1 = new Jogadas(i, j, i-2, j+2);
                jogada1.setCome(true); jogada1.setPosicaoComida(i-1, j+1);
                jogada.add(jogada1);
              }
            }
          }
        }
      }
      else{
        if(tabuleiro.getCasa(i-1, j+1).getOcupada() == false){
          Jogadas jogada2 = new Jogadas(i, j,i-1, j+1);
          jogada2.setCome(false);
          jogada.add(jogada2);
        }
        else{
          if(tabuleiro.getCasa(i-1, j+1).getOcupada()){
            if(tabuleiro.getCasa(i-1, j+1).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
              if(i-2>=0 && j+2<8){
                if(tabuleiro.getCasa(i-2, j+2).getOcupada() == false){
                  Jogadas jogada3 = new Jogadas(i, j, i-2, j+2);
                  jogada3.setCome(true); jogada3.setPosicaoComida(i-1, j+1);
                  jogada.add(jogada3);
                }
              }
            }
          }
        }
      }

    }

    if(i+1<8 && j-1>=0){

      if(tabuleiro.getCasa(i, j).getCorPeca() == 0){  // está movendo para trás -> preta
        if(tabuleiro.getCasa(i+1, j-1).getOcupada()){
          if(tabuleiro.getCasa(i+1, j-1).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
            if(i+2<8 && j-2>=0){
              if(tabuleiro.getCasa(i+2, j-2).getOcupada() == false){
                Jogadas jogada1 = new Jogadas(i, j, i+2, j-2);
                jogada1.setCome(true); jogada1.setPosicaoComida(i+1, j-1);
                jogada.add(jogada1);
              }
            }
          }
        }
      }
      else{
        if(tabuleiro.getCasa(i+1, j-1).getOcupada() == false){
          Jogadas jogada2 = new Jogadas(i, j, i+1, j-1);
          jogada2.setCome(false);
          jogada.add(jogada2);
        }
        else{
          if(tabuleiro.getCasa(i+1, j-1).getOcupada()){
            if(tabuleiro.getCasa(i+1, j-1).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
              if(i+2<8 && j-2>=0){
                if(tabuleiro.getCasa(i+2, j-2).getOcupada() == false){
                  Jogadas jogada3 = new Jogadas(i, j, i+2, j-2);
                  jogada3.setCome(true); jogada3.setPosicaoComida(i+1, j-1);
                  jogada.add(jogada3);
                }
              }
            }
          }
        }
      }

    }

    if(i+1<8 && j+1<8){

      if(tabuleiro.getCasa(i, j).getCorPeca() == 0){  // está movendo para trás -> preta
        if(tabuleiro.getCasa(i+1, j+1).getOcupada()){
          if(tabuleiro.getCasa(i+1, j+1).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
            if(i+2<8 && j+2<8){
              if(tabuleiro.getCasa(i+2, j+2).getOcupada() == false){
                Jogadas jogada1 = new Jogadas(i, j, i+2, j+2);
                jogada1.setCome(true); jogada1.setPosicaoComida(i+1, j+1);
                jogada.add(jogada1);
              }
            }
          }
        }
      }
      else{
        if(tabuleiro.getCasa(i+1, j+1).getOcupada() == false){
          Jogadas jogada2 = new Jogadas(i, j, i+1, j+1);
          jogada2.setCome(false);
          jogada.add(jogada2);
        }
        else{
          if(tabuleiro.getCasa(i+1, j+1).getOcupada()){
            if(tabuleiro.getCasa(i+1, j+1).getCorPeca() != tabuleiro.getCasa(i, j).getCorPeca()){
              if(i+2<8 && j+2<8){
                if(tabuleiro.getCasa(i+2, j+2).getOcupada() == false){
                  Jogadas jogada3 = new Jogadas(i, j, i+2, j+2);
                  jogada3.setCome(true); jogada3.setPosicaoComida(i+1, j+1);
                  jogada.add(jogada3);
                }
              }
            }
          }
        }
      }

    }

    return jogada;
  }

  public ArrayList<Jogadas> verificaJogadaPeca(int i, int j, Tabuleiro tabuleiro){

    ArrayList<Jogadas> jogada = new ArrayList<>();

    if(tabuleiro.getCasa(i, j).getOcupada()){

      if(tabuleiro.getCasa(i, j).getDama()){
        jogada.addAll(verificaJogadaDama(i, j, tabuleiro));
      }
      else{
        jogada.addAll(verificaJogadaNormal(i, j, tabuleiro));
      }
    }

    return jogada;
  }

  // verifica todas as jogadas possiveis de uma cor
  public ArrayList<Jogadas> VerificaJogada(int corPeca,  Tabuleiro tabuleiro){
    ArrayList<Jogadas> jogada = new ArrayList<>();

    for(int i=0; i<8; i++){
      for(int j=0; j<8; j++){

        if(tabuleiro.getCasa(i, j).getOcupada()){
          if(tabuleiro.getCasa(i, j).getCorPeca() == corPeca){
            jogada.addAll(verificaJogadaPeca(i,j,tabuleiro));
          }
        }

      }
    }

    return jogada;
  }

  // verifica se tem alguma jogada que come
  public ArrayList<Jogadas> verificaJogadaCome(int i, int j, Tabuleiro tabuleiro){
    ArrayList<Jogadas> jogada = new ArrayList<>();

    jogada.addAll(verificaJogadaPeca(i,j,tabuleiro));

    for(int x=jogada.size()-1; x>=0; x--){
      if(jogada.get(x).come != true){
        jogada.remove(x);
      }
    }

    return jogada;
  }

  
  // faz uma jogada com a entrada das coordenadas da jogada
  public boolean fazerJogada(int corPeca, Tabuleiro tabuleiro, int posicaoAtualX, int posicaoAtualY, int posicaoDestinoX, int posicaoDestinoY){
    ArrayList<Jogadas> jogadas_posssiveis = VerificaJogada(corPeca, tabuleiro);

    int tam_jogadas = jogadas_posssiveis.size();

    for(int i=0; i<tam_jogadas; i++){
      if(posicaoAtualX == jogadas_posssiveis.get(i).getPosicaoAtualX() && posicaoAtualY == jogadas_posssiveis.get(i).getPosicaoAtualY() && posicaoDestinoX == jogadas_posssiveis.get(i).getPosicaoDestinoX() && posicaoDestinoY == jogadas_posssiveis.get(i).getPosicaoDestinoY()){
        Casa atual = tabuleiro.getCasa(posicaoAtualX, posicaoAtualY);
        Casa destino = tabuleiro.getCasa(posicaoDestinoX, posicaoDestinoY);
        destino.setPeca(atual.getCorPeca(), atual.getDama());
        atual.desocupar();

        if(jogadas_posssiveis.get(i).come){
          tabuleiro.eliminaPeca( jogadas_posssiveis.get(i).getPosicaoComidaX(), jogadas_posssiveis.get(i).getPosicaoComidaY() );
        }

        // identifica se a peca vai virar dama na jogada
        if(posicaoDestinoX == 0 && corPeca ==0){
          destino.setDama(true);
        }
        else if(posicaoDestinoX == 7 && corPeca == 1){
          destino.setDama(true);
        }

        //verificar se tem mais alguma jogada seguinte que come

        if(jogadas_posssiveis.get(i).come){
          ArrayList<Jogadas> jogada_come = verificaJogadaCome(posicaoDestinoX, posicaoDestinoY, tabuleiro);

          if(!jogada_come.isEmpty()){
            if(jogada_come.size()>1){
              Integer escolha = defineJogada(jogada_come);
              fazerJogada(corPeca, tabuleiro, jogada_come.get(escolha).posicaoAtualX, jogada_come.get(escolha).posicaoAtualY, jogada_come.get(escolha).posicaoDestinoX, jogada_come.get(escolha).posicaoDestinoY);
            }
            else{
              fazerJogada(corPeca, tabuleiro, jogada_come.get(0).posicaoAtualX, jogada_come.get(0).posicaoAtualY, jogada_come.get(0).posicaoDestinoX, jogada_come.get(0).posicaoDestinoY);
            }
          }

        }
        
        return true;
      }
    }

    return false;
  }

  // recebe um array de jogadas e pede para o usuario escolher qual quer fazer e retorna o indice

  public Integer defineJogada(ArrayList<Jogadas> jogada){
    Integer escolha =  null;
    Entrada controleEntrada = new Entrada();
    System.out.println("QUAL JOGADA QUER FAZER?");
    for(int i=0; i<jogada.size(); i++){
      System.out.println("CLIQUE "+i+" para "+jogada.get(i));
      
    }

    escolha = controleEntrada.insereInteiro("", escolha, 0, jogada.size()-1);

    return escolha;

  }

  //executa uma jogada sobre o tabuleiro
  public void fazerJogada(Tabuleiro tabuleiro, Jogadas jogada){

  
        Casa atual = tabuleiro.getCasa(jogada.getPosicaoAtualX(), jogada.getPosicaoAtualY());
        Casa destino = tabuleiro.getCasa(jogada.getPosicaoDestinoX(), jogada.getPosicaoDestinoY());
        destino.setPeca(atual.getCorPeca(), atual.getDama());
        atual.desocupar();

        if(jogada.getCome()){
          tabuleiro.eliminaPeca( jogada.getPosicaoComidaX(), jogada.getPosicaoComidaY() );
          tabuleiro.getCasa(jogada.getPosicaoComidaX(), jogada.getPosicaoComidaY()).desocupar();
        }

        // identifica se a peca vai virar dama na jogada
        if(posicaoDestinoX == 0 && destino.getCorPeca() ==0){
          destino.setDama(true);
        }
        else if(posicaoDestinoX == 7 && destino.getCorPeca() == 1){
          destino.setDama(true);
        }


        if(jogada.getCome()){
          ArrayList<Jogadas> jogada_come = verificaJogadaCome(jogada.posicaoDestinoX, jogada.posicaoDestinoY, tabuleiro);

          if(!jogada_come.isEmpty()){
            if(jogada_come.size()>1){
              Integer escolha = escolherMelhorJogada(jogada_come, tabuleiro);
              fazerJogada(tabuleiro, jogada_come.get(escolha));
            }
            else{
              fazerJogada(tabuleiro, jogada_come.get(0));
            }
          }

        }

  }

  //metodo para ver qual a melhor jogada para o PC
  public Integer escolherMelhorJogada(ArrayList<Jogadas> jogadas, Tabuleiro tabuleiro_original){
    Integer melhor = 0;
    Integer  maior_nota = 0;
    for(Integer i=0; i<jogadas.size(); i++){
      Tabuleiro tabuleiro_copia = new Tabuleiro(tabuleiro_original);
      tabuleiro_copia = jogadas.get(i).getTabuleiroAposJogada(tabuleiro_original, jogadas.get(i));
      if(tabuleiro_copia.getQuantidadePretas() - tabuleiro_copia.getQuantidadeBrancas() > maior_nota ){
        melhor = i;
        maior_nota = tabuleiro_copia.getQuantidadePretas() - tabuleiro_copia.getQuantidadeBrancas();
      }
    }
    
    return melhor;
  }
  
  public Tabuleiro getTabuleiroAposJogada(Tabuleiro tabuleiro_original, Jogadas jogada){ // retorna um tabuleiro após a jogada especídifca
    Tabuleiro tabuleiro_copia = new Tabuleiro(tabuleiro_original);

    fazerJogada(tabuleiro_copia, jogada);

    return tabuleiro_copia;
  }

  @Override
  public String toString() {
    return("Posicao atual: "+posicaoAtualX+":"+posicaoAtualY+" --> Posicao Destino:"+posicaoDestinoX+":"+posicaoDestinoY);
  }

  
}
