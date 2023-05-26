public class Carga {
  private int identificador;
  private int peso;
  private double valorDeclarado;
  private int tempoMaximo;
  private Cliente cliente;
  private Porto portoOrigem, portoDestino;

  public Carga(int identificador, int peso, double valorDeclarado, int tempoMaximo) {
    this.identificador = identificador;
    this.peso = peso;
    this.valorDeclarado = valorDeclarado;
    this.tempoMaximo = tempoMaximo;
  }

  public int getId() {
    return identificador;
  }

  public int getPeso() {
    return peso;
  }

  public double getValorDeclarado() {
    return valorDeclarado;
  }

  public int getTempoMaximo() {
    return tempoMaximo;
  }

}
