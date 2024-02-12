import java.util.Scanner;

public class Aplicativo {
  static String arq = "jogoSalvo.txt";
  static String arq2 = "jogoSalvoPt2.txt";
  static String jogoSalvoPt2 = "";
  static boolean jogoSalvoMaquina;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Entrada controleEntrada = new Entrada();

    int encerra = 0;

    while(encerra != 1){

      String opcoes = "DIGITE 1 PARA COMECAR UM NOVO JOGO\nDIGITE 2 PARA CARREGAR UM JOGO\nDIGITE 3 PARA SAIR";

      Integer escolha_inicio = null;

      escolha_inicio = controleEntrada.insereInteiro(opcoes, escolha_inicio, 1, 3);


      switch(escolha_inicio){

        case 1: // COMECA UM NOVO JOGO

        Integer escolha_jogo = null;

        String opcoes_jogo ="DIGITE 1 PARA INICIAR UM JOGO COM 2 JOGADORES\nDIGITE 2 PARA INICIAR UM JOGO CONTRA O COMPUTADOR";

        escolha_jogo = controleEntrada.insereInteiro(opcoes_jogo, escolha_jogo, 1, 2);
        

        if(escolha_jogo == 1){  // implementa jogo com 2 jogadores humanos

          System.out.println("DIGITE O NOME DO JOGADOR 1");
          String jogador1 = sc.nextLine();
          System.out.println("DIGITE O NOME DO JOGADOR 2");
          String jogador2 = sc.nextLine();

          Jogo jogo1x1 = new Jogo(jogador1, jogador2);

          int vezJogada = 1;

          while(jogo1x1.getTabuleiro_jogo().verificaVencedor() == -1){

            System.out.println("[1] Fazer jogada          [2] Sair ");
            System.out.println("[3] Ver peças capturadas  [4] Interromper jogo  [5] Salvar jogo");
              String opcao = sc.nextLine();

              if(opcao.equals("1")){
            	  if(vezJogada == 1){
                      jogo1x1.jogadaBrancas();
                      vezJogada = 0;
                    }
                    else{
                      jogo1x1.jogadaPretas();
                      vezJogada = 1;
                    }
              }              
                           
              if(opcao.equals("2")){
            	  System.out.println("###JOGO ENCERRADO###");
                  encerra = 1;
                  break;
              }           
              if(opcao.equals("3")){

                  if(vezJogada == 1){
                    System.out.println(jogo1x1.getTabuleiro_jogo().getPecas_capturadas_brancas());
                  }
                  else{
                    System.out.println(jogo1x1.getTabuleiro_jogo().getPecas_capturadas_pretas());
                  }
            	  
              }
              if(opcao.equals("4")){
            	  break;
              }
              if(opcao.equals("5")){
            	  jogoSalvoPt2 += "pvp;";
            	  jogoSalvoPt2 += jogador1 + ";";
                  jogoSalvoPt2 += jogador2 + ";";
                  jogoSalvoPt2 += vezJogada + ";";
         
                  
            	  jogo1x1.getTabuleiro_jogo().salvaJogo();
            	  if(Arquivo.Write(arq, jogo1x1.getTabuleiro_jogo().jogoSalvo) && Arquivo.Write(arq2, jogoSalvoPt2)){
            		  System.out.println("Jogo salvo com sucesso!");
            	  }
            	  else {
            		  System.out.println("Erro ao salvar o jogo");
            	  }
            	  jogoSalvoPt2 = "";
            	  jogo1x1.getTabuleiro_jogo().jogoSalvo = "";
              }
          }

        }
        else{

          System.out.println("DIGITE O NOME DO JOGADOR 1");
          String jogador1 = sc.nextLine();

          Jogo jogo1xPC = new Jogo(jogador1);
          jogo1xPC.jogadaBrancas();
          jogo1xPC.iniciaPC();

          jogo1xPC.jogadaPC();

          int vezJogada = 1;

          while(jogo1xPC.getTabuleiro_jogo().verificaVencedor() == -1){
            System.out.println("[1] Fazer jogada         [2] Visualizar árvore [3] Sair ");
              System.out.println("[4] Ver peças capturadas [5] Interromper jogo  [6] Salvar jogo");
              String opcao = sc.nextLine();

            if(opcao.equals("1")){
            	  if(vezJogada == 1){
                      jogo1xPC.jogadaBrancas();
                      vezJogada = 0;
                    }
                    else{
                      jogo1xPC.jogadaPC();
                      vezJogada = 1;
                    }
              }              
              if(opcao.equals("2")){
                jogo1xPC.imprimirArvoreJogo();

              }              
              if(opcao.equals("3")){
            	  System.out.println("###JOGO ENCERRADO###");
                  encerra = 1;
                  break;
              }           
              if(opcao.equals("4")){ // implementar forma de ver pecas capturadas
            	  if(vezJogada == 1){
                  System.out.println(jogo1xPC.getTabuleiro_jogo().getPecas_capturadas_brancas());
                }
                else{
                  System.out.println(jogo1xPC.getTabuleiro_jogo().getPecas_capturadas_pretas());
                }
              }
              if(opcao.equals("5")){
            	  break;
              }
              if(opcao.equals("6")){
            	    jogoSalvoPt2 += "pvm;";
            	    jogoSalvoPt2 += jogador1 + ";";
                  jogoSalvoPt2 += "PC" + ";";
                  jogoSalvoPt2 += vezJogada + ";";
                  
            	  jogo1xPC.getTabuleiro_jogo().salvaJogo();
            	  if(Arquivo.Write(arq, jogo1xPC.getTabuleiro_jogo().jogoSalvo) && Arquivo.Write(arq2, jogoSalvoPt2)){
            		  System.out.println("Jogo salvo com sucesso!");
            	  }
            	  else {
            		  System.out.println("-----Erro ao salvar o jogo------");
            	  }
            	  jogoSalvoPt2 = "";
            	  jogo1xPC.getTabuleiro_jogo().jogoSalvo = "";
              }
          }


        }
        
          break;

        case 2:  // IMPLEMENTAR FUNCAO PARA CARREGAR UM JOGO E RODAR ELE

        String jogoSalvo = Arquivo.Read(arq2);
        String[] valores = jogoSalvo.split(";");
        String tipoJogoSalvo = valores[0];
        String jogador1Salvo = valores[1];
        String jogador2Salvo = valores[2];            
        int vezJogadaSalva = Integer.parseInt(valores[3]);
        
        if(valores[4].equals("1")) {
            Aplicativo.jogoSalvoMaquina = true;
        }
        else {
          Aplicativo.jogoSalvoMaquina = false;
        }
      
      
      if(tipoJogoSalvo.equals("pvp")) {
        String jogador1 = jogador1Salvo;
        String jogador2 = jogador2Salvo;
        
        Jogo jogo1x1 = new Jogo(jogador1, jogador2, arq);

            int vezJogada = vezJogadaSalva;

            while(jogo1x1.getTabuleiro_jogo().verificaVencedor() == -1){

              System.out.println("[1] Fazer jogada         [2] Sair ");
              System.out.println("[3] Ver peças capturadas [4] Interromper jogo  [5] Salvar jogo");
              String opcao = sc.nextLine();

                if(opcao.equals("1")){
                  if(vezJogada == 1){
                       jogo1x1.jogadaBrancas();
                       vezJogada = 0;
                  }
                  else{
                       jogo1x1.jogadaPretas();
                       vezJogada = 1;
                  }
                }              
                              
                if(opcao.equals("2")){
                  System.out.println("###JOGO ENCERRADO###");
                    encerra = 1;
                    break;
                }           
                if(opcao.equals("3")){

                  if(vezJogada == 1){
                    System.out.println(jogo1x1.getTabuleiro_jogo().getPecas_capturadas_brancas());
                  }
                  else{
                    System.out.println(jogo1x1.getTabuleiro_jogo().getPecas_capturadas_pretas());
                  }
                  
                }
                if(opcao.equals("4")){
                  break;
                }
                if(opcao.equals("5")){
                    jogoSalvoPt2 += "pvp;";
                    jogoSalvoPt2 += jogador1 + ";";
                    jogoSalvoPt2 += jogador2 + ";";
                    jogoSalvoPt2 += vezJogada + ";";

                    
                    jogo1x1.getTabuleiro_jogo().salvaJogo();
                    if(Arquivo.Write(arq, jogo1x1.getTabuleiro_jogo().jogoSalvo) && Arquivo.Write(arq2, jogoSalvoPt2)){
                      System.out.println("Jogo salvo com sucesso!");
                    }
                    else {
                      System.out.println("Erro ao salvar o jogo");
                    }
                    jogoSalvoPt2 = "";
                    jogo1x1.getTabuleiro_jogo().jogoSalvo = "";
               }
            }
      }
       else {
          String jogador1 = jogador1Salvo;
          
          Jogo jogo1xPC = new Jogo(jogador1, 1);
              jogo1xPC.jogadaBrancas(); // analisar erro aqui
              jogo1xPC.iniciaPC();//

              jogo1xPC.jogadaPC();//

              int vezJogada = vezJogadaSalva;

              while(jogo1xPC.getTabuleiro_jogo().verificaVencedor() == -1){
                System.out.println("[1] Fazer jogada         [2] Visualizar árvore [3] Sair ");
                System.out.println("[4] Ver peças capturadas [5] Interromper jogo  [6] Salvar jogo");
                String opcao = sc.nextLine();

                if(opcao.equals("1")){
                    if(vezJogada == 1){
                          jogo1xPC.jogadaBrancas();
                          vezJogada = 0;
                          vezJogadaSalva = vezJogada;
                        }
                        else{
                          jogo1xPC.jogadaPC();
                          vezJogada = 1;
                          vezJogadaSalva = vezJogada;
                        }
                  }              
                  if(opcao.equals("2")){
                    jogo1xPC.imprimirArvoreJogo();

                  }              
                  if(opcao.equals("3")){
                    System.out.println("###JOGO ENCERRADO###");
                      encerra = 1;
                      break;
                  }           
                  if(opcao.equals("4")){

                    if(vezJogada == 1){
                      System.out.println(jogo1xPC.getTabuleiro_jogo().getPecas_capturadas_brancas());
                    }
                    else{
                      System.out.println(jogo1xPC.getTabuleiro_jogo().getPecas_capturadas_pretas());
                    }
                    
                  }
                  if(opcao.equals("5")){
                    break;
                  }
                  if(opcao.equals("6")){
                    jogoSalvoPt2 += "pvm;";
                    jogoSalvoPt2 += jogador1 + ";";
                    jogoSalvoPt2 += "PC" + ";";
                    jogoSalvoPt2 += vezJogada + ";";
                      
                    jogo1xPC.getTabuleiro_jogo().salvaJogo();
                    if(Arquivo.Write(arq, jogo1xPC.getTabuleiro_jogo().jogoSalvo) && Arquivo.Write(arq2, jogoSalvoPt2)){
                      System.out.println("Jogo salvo com sucesso!");
                    }
                    else {
                      System.out.println("Erro ao salvar o jogo");
                    }
                    jogoSalvoPt2 = "";
                    jogo1xPC.getTabuleiro_jogo().jogoSalvo = "";
                  }
                  
              }
        }

          break;

        case 3:
          System.out.println("###JOGO ENCERRADO###");
          encerra = 1;
          break;

        default:
        System.out.println("ESCOLHA UMA OPCAO VALIDA");

      }
    }

    sc.close();
  }
}
