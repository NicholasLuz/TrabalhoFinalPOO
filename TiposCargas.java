import java.util.ArrayList;

public class TiposCargas {
  private ArrayList<TipoCarga> tiposCargas;

  public TiposCargas() {
    this.tiposCargas = new ArrayList<TipoCarga>();
  }

  private boolean checkTipoCargaJaExiste(int numero) {
    for (TipoCarga tc : tiposCargas) {
      if (tc.getNumero() == numero) {
        return true;
      }
    }
    return false;
  }

  public boolean adicionaTipoCarga(TipoCarga tc) {
    if (checkTipoCargaJaExiste(tc.getNumero()))
      return false;
    return tiposCargas.add(tc);
  }

  public void mostrarTiposCargas() {
    for (TipoCarga tc : tiposCargas) {
      System.out.println(
          "Numero: " + tc.getNumero() + ";Descricao: " + tc.getDescricao());
    }
    if (tiposCargas.size() == 0) {
      System.out.println("Não há tipos de cargas cadastrados.");
    }
  }
}