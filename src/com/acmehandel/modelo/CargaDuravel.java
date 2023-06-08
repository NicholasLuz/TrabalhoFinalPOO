package src.com.acmehandel.modelo;


public class CargaDuravel extends TipoCarga {
  private String setor;
  private String materialPrincipal;
  private double percentualIpi;

  public CargaDuravel(int numero, String descricao, String setor, String materialPrincipal, double percentualIpi) {
    super(numero, descricao);
    this.setor = setor;
    this.materialPrincipal = materialPrincipal;
    this.percentualIpi = percentualIpi;
  }

  public String getSetor() {
    return setor;
  }

  public String getMaterialPrincipal() {
    return materialPrincipal;
  }

  public double getPercentualIpi() {
    return percentualIpi;
  }
}
