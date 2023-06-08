package src.com.acmehandel.modelo;

public class Distancia {
  private int idOrigem;
  private int idDestino;
  private double distancia;

  public Distancia(int idOrigem, int idDestino, double distancia) {
    this.idOrigem = idOrigem;
    this.idDestino = idDestino;
    this.distancia = distancia;
  }

  public int getIdOrigem() {
    return idOrigem;
  }

  public int getIdDestino() {
    return idDestino;
  }

  public double getDistancia() {
    return distancia;
  }
}
