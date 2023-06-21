package src.com.acmehandel.dados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import src.com.acmehandel.modelo.Distancia;

public class Distancias {
  private ArrayList<Distancia> distancias;

  public Distancias() {
    this.distancias = new ArrayList<Distancia>();
  }

  public boolean checkDistanciaJaExiste(int idOrigem, int idDestino) {
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

  public String mostrarDistancias() {
    String dist = "";
    for (Distancia d : distancias) {
      dist +=(
          "IdOrigem: " + d.getIdOrigem() + ";IdDestino: " + d.getIdDestino() + ";Distancia: " + d.getDistancia()+"\n");
    }
    if (distancias.size() == 0) {
      dist = ("Não há distâncias cadastradas.");
    }
    return dist;
  }

  public List<Distancia> getDistancias() {
    return distancias.stream().collect(Collectors.toList());
  }
}
