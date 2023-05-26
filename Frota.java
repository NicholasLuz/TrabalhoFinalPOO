import java.util.ArrayList;

public class Frota {
  private ArrayList<Navio> frota;

  public Frota() {
    this.frota = new ArrayList<Navio>();
  }

  public boolean checkNomeNavio(String nome) {
    for (Navio n : frota) {
      if (n.getNome().equals(nome)) {
        return true;
      }
    }
    return false;
  }
}
