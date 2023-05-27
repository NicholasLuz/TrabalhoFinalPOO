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

  public void mostrarCargas() {
    for (Carga c : cargas) {
      System.out.println(c.toString());
    }
    if (cargas.size() == 0) {
      System.out.println("Não há cargas cadastradas.");
    }
  }
}
