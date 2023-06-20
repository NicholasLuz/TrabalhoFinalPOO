package src.com.acmehandel.dados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import src.com.acmehandel.modelo.Carga;
import src.com.acmehandel.modelo.Situacao;

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

  public Carga getCargaId(int id) {
      for (Carga c : cargas) {
        if (c.getId() == id) {
          return c;
        }
      }
    return null;
  }

  public boolean adicionaCarga(Carga c) {
    if (checkCargaIdJaExiste(c.getId()))
      return false;
    return cargas.add(c);
  }

  public String mostrarCargas() {
    String todasCargas = "";
    for (Carga c : cargas) {
      todasCargas += "--------------------------\n"+ c.toString();
    }
    if (cargas.size() == 0) {
      todasCargas = "Não há cargas cadastradas.";
    }
    return todasCargas;
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
