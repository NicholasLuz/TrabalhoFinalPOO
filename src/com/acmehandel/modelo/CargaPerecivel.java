package src.com.acmehandel.modelo;

public class CargaPerecivel extends TipoCarga {
  private String origem;
  private int tempoMaximoValidade;

  public CargaPerecivel(int numero, String descricao, String origem, int tempoMaximoValidade) {
    super(numero, descricao);
    this.origem = origem;
    this.tempoMaximoValidade = tempoMaximoValidade;
  }

  public String getOrigem() {
    return origem;
  }

  public int getTempoMaximoValidade() {
    return tempoMaximoValidade;
  }
}
