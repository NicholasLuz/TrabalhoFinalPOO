public class Carga {
  private int identificador, idCliente, idPortoOrigem, idPortoDestino, peso, tempoMaximo, idTipoCarga;
  private double valorDeclarado, valorFrete, freteSemDist;
  private String prioridade, situacao;
  private Navio navio;

  public Carga(int identificador, int idCliente, int idPortoOrigem, int idPortoDestino, int peso, double valorDeclarado,
      int tempoMaximo, int idTipoCarga, String prioridade, String situacao) {
    this.identificador = identificador;
    this.idCliente = idCliente;
    this.idPortoOrigem = idPortoOrigem;
    this.idPortoDestino = idPortoDestino;
    this.peso = peso;
    this.valorDeclarado = valorDeclarado;
    this.tempoMaximo = tempoMaximo;
    this.idTipoCarga = idTipoCarga;
    this.prioridade = prioridade;
    this.situacao = situacao;
  }

  public int getId() {
    return identificador;
  }

  public int getIdCliente() {
    return idCliente;
  }

  public int getIdPortoOrigem() {
    return idPortoOrigem;
  }

  public int getIdPortoDestino() {
    return idPortoDestino;
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

  public int getIdTipoCarga() {
    return idTipoCarga;
  }

  public String getPrioridade() {
    return prioridade;
  }

  public String getSituacao() {
    return situacao;
  }

  public boolean setSituacao(String sit) {
    if (situacao.equals(Situacao.CANCELADO.name())) {
      return false;
    }
    for (Situacao s : Situacao.values()) {
      if (sit.equals(s.name())) {
        prioridade = sit;
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return ("Id: " + identificador + "\nPorto de Origem: " + idPortoOrigem + "\nPorto de Destino: " + idPortoDestino
        + "\nCliente: " + idCliente + "\nTipo de Carga: " + idTipoCarga + "\nSituação: " + situacao
        + ((navio != null) ? ("\nNavio: " + navio.toString() + "\nValor do frete: " + valorFrete)
            : "\nNão há navios designados para esta carga."));
  }

}
