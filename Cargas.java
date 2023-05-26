import java.util.ArrayList;

public class Cargas {
  private ArrayList<Carga> cargas;

  public Cargas() {
    this.cargas = new ArrayList<Carga>();
  }

  public boolean checkCargaIdJaExiste(int id) {
    for (Carga c : cargas) {
      if (c.getId() == id) {
        return true;
      }
    }
    return false;
  }
}
