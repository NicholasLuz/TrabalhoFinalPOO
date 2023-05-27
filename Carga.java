public class Carga {
  private int identificador;
  private int peso;
  private double valorDeclarado;
  private int tempoMaximo;
  private Cliente cliente;
  private Porto portoOrigem, portoDestino;
  private TipoCarga tipoCarga;
  private String situacao;
  private Navio navio;
  private double valorFrete;

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

  public String toString() {
    return ("Id: " + identificador + "\nPorto de Origem: " + portoOrigem + "\nPorto de Destino: " + portoDestino
        + "\nCliente: " + cliente.toString() + "\nTipo de Carga: " + tipoCarga + "\nSituação: " + situacao
        + ((navio != null) ? ("\nNavio: " + navio.toString() + "\nValor do frete: " + valorFrete)
            : "\nNão há navios designados para esta carga."));
  }
}
