import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cargas {
  private ArrayList<Carga> cargas;

  public Cargas() {
    this.cargas = new ArrayList<Carga>();
  }

  private boolean checkCargaIdJaExiste(int id) {
    for (Carga c : cargas) {
      if (c.getId() == id) {
        return true;
      }
    }
    return false;
  }

  public Carga getCargaId(int id){
    if (checkCargaIdJaExiste(id)){
      for (Carga c : cargas) {
        if (c.getId() == id) {
         return c;
        }
      }
    }
    return null;
  }


  public boolean adicionaCarga(Carga c) {
    if (checkCargaIdJaExiste(c.getId()))
      return false;
    return cargas.add(c);
  }

  public void mostrarCargas() {
    for (Carga c : cargas) {
      System.out.println("--------------------------");
      System.out.println(c.toString());
    }
    if (cargas.size() == 0) {
      System.out.println("Não há cargas cadastradas.");
    }
  }

  public void sort() {
    cargas.sort((c1, c2) -> c1.getId() - c2.getId());
  }

  public List<Carga> getPendentes() {
    return cargas.stream()
        .filter(c -> c.getSituacao().equals(Situacao.PENDENTE.name()))
        .collect(Collectors.toList());
  }
}
