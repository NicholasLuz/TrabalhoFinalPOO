package src.com.acmehandel.modelo;

import java.util.ArrayList;

public class Navio {
  private String nome;
  private double velocidade;
  private double autonomia;
  private double custoPorMilhaBasico;
  private ArrayList<Integer> historicoCargas;
  private boolean isTransporting;

  public Navio(String nome, double velocidade, double autonomia, double custoPorMilhaBasico) {
    this.nome = nome;
    this.velocidade = velocidade;
    this.autonomia = autonomia;
    this.custoPorMilhaBasico = custoPorMilhaBasico;
    this.historicoCargas = new ArrayList<Integer>();
    this.isTransporting = false;
  }

  public String getNome() {
    return nome;
  }

  public double getVelocidade() {
    return velocidade;
  }

  public double getAutonomia() {
    return autonomia;
  }

  public double getCustoPorMilhaBasico() {
    return custoPorMilhaBasico;
  }

  public ArrayList<Integer> getHistoricoCargas() {
    return historicoCargas;
  }

  public boolean isTransporting() {
    return isTransporting;
  }

  public boolean addCarga(Carga carga) {
    return historicoCargas.add(carga.getId());
  }

  public void setIsTransportingTrue() {
    isTransporting = true;
  }

  public void setIsTransportingFalse() {
    isTransporting = false;
  }

  @Override
  public String toString() {
    return (nome);
  }
}
