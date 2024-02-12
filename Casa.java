
  public class Casa {
    private int cor;
    private boolean ocupada;
    private int corPeca = 2; // 2 = nao tem peca
    private boolean dama = false;
  
    Casa(int cor){
      this.cor = cor;
      ocupada = false;
    }
  
    public void setPeca(int corPeca, boolean dama) {
      this.corPeca = corPeca;
      this.dama = dama;
      ocupada = true;
    }
  
    public void setCor(int cor) {
      this.cor = cor;
    }

    public void setDama(boolean dama) {
      this.dama = dama;
    }

  
    public void desocupar(){
      ocupada = false;
      corPeca = 2;
    }
  
    public int getCor() {
      return cor;
    }
  
    public boolean getOcupada(){
      return ocupada;
    }

    public boolean getDama(){
      return dama;
    }

    public int getCorPeca() {
      return corPeca;
    }

    public void setCorPeca(int corPeca) {
      this.corPeca = corPeca;
    }

  }
