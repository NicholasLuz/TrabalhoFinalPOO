import java.util.ArrayList;

public class Portos {
  private ArrayList<Porto> portos;

  public Portos() {
    this.portos = new ArrayList<Porto>();
  }

  private boolean checkPortoIdJaExiste(int id) {
    for (Porto p : portos) {
      if (p.getId() == id) {
        return true;
      }
    }
    return false;
  }

  public boolean adicionaPorto(Porto p) {
    if (checkPortoIdJaExiste(p.getId()))
      return false;
    return portos.add(p);
  }

}
