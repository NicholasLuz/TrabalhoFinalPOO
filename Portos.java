import java.util.ArrayList;

public class Portos {
  private ArrayList<Porto> portos;

  public Portos() {
    this.portos = new ArrayList<Porto>();
  }

  public boolean checkPortoId(int id) {
    for (Porto p : portos) {
      if (p.getId() == id) {
        return true;
      }
    }
    return false;
  }

}
