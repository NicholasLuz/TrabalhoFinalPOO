import java.util.ArrayList;

public class Distancias {
  private ArrayList<Distancia> distancias;

  public Distancias() {
    this.distancias = new ArrayList<Distancia>();
  }

  private boolean checkDistanciaJaExiste(int idOrigem, int idDestino) {
    for (Distancia d : distancias) {
      if ((d.getIdOrigem() == idOrigem) && (d.getIdDestino() == idDestino)) {
        return true;
      }
    }
    return false;
  }

  public boolean adicionaDistancias(Distancia d) {
    if (checkDistanciaJaExiste(d.getIdOrigem(), d.getIdDestino()))
      return false;
    return distancias.add(d);
  }

  public double getDistanciaPortos(int idOrigem, int idDestino) {
    for (Distancia d : distancias) {
      if ((d.getIdOrigem() == idOrigem) && (d.getIdDestino() == idDestino)) {
        return d.getDistancia();
      }
    }
    return -1;
  }

  public void mostrarDistancias() {
    for (Distancia d : distancias) {
      System.out.println(
          "IdOrigem: " + d.getIdOrigem() + ";IdDestino: " + d.getIdDestino() + ";Distancia: " + d.getDistancia());
    }
    if (distancias.size() == 0) {
      System.out.println("Não há distâncias cadastradas.");
    }
  }
}
