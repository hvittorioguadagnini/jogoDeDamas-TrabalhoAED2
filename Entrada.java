// Classe para fazer controle de entrada e saida no prompt

import java.util.Scanner;

public class Entrada {
  Scanner sc = new Scanner(System.in);
  Entrada(){

  }

  public Integer insereInteiro(String mensagem, Integer variavel, int min, int max ){

    while(variavel == null){
      System.out.println(mensagem);

      String leitura = sc.nextLine();

      try{
        variavel = Integer.parseInt(leitura);

        if(variavel<min || variavel>max){
          System.out.println("\n----ENTRADA INVALIDA. POR FAVOR ENTRE COM UM NUMERO DE "+min+" a "+max+"-----\n");
          variavel = null;
        }
      } catch (NumberFormatException e){
        System.out.println("\n-----ENTRADA INVALIDA POR FAVOR ENTRE COM UM NUMERO------\n");
      }

    }

    return variavel;
  }


  public Integer insereInteiro(String mensagem, Integer variavel){

    while(variavel == null){
      System.out.println(mensagem);

      String leitura = sc.nextLine();

      try{
        variavel = Integer.parseInt(leitura);
      } catch (NumberFormatException e){
        System.out.println("\n------ENTRADA INVALIDA POR FAVOR ENTRE COM UM NUMERO-----\n");
      }

    }

    return variavel;
  }

  //criar metodo para conferir entrada de string

}
