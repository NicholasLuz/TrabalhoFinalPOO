import java.util.ArrayList;

public class Frota {
  private ArrayList<Navio> frota;

  public Frota() {
    this.frota = new ArrayList<Navio>();
  }

  public boolean checkNomeNavioJaExiste(String nome) {
    for (Navio n : frota) {
      if (n.getNome().equals(nome)) {
        return true;
      }
    }
    return false;
  }

  public Navio getMelhorNavio(String prioridade, double distancia) {
    ArrayList<Navio> naviosAutonomiaDisponivel = new ArrayList<Navio>();
    for (Navio n : frota) {
      if (n.getAutonomia() <= distancia && !n.isTransporting()) {
        naviosAutonomiaDisponivel.add(n);
      }
    }
    if (naviosAutonomiaDisponivel.size() == 0) {
      return null;
    }
    if (prioridade.equals(Prioridade.BARATO.name())) {
      Navio melhorNavio = naviosAutonomiaDisponivel.get(0);
      for (Navio n : naviosAutonomiaDisponivel) {
        if (n.getCustoPorMilhaBasico() < melhorNavio.getCustoPorMilhaBasico()) {
          melhorNavio = n;
        }
      }
      return melhorNavio;
    } else {
      Navio melhorNavio = naviosAutonomiaDisponivel.get(0);
      for (Navio n : naviosAutonomiaDisponivel) {
        if (n.getVelocidade() > melhorNavio.getVelocidade()) {
          melhorNavio = n;
        }
      }
      return melhorNavio;
    }
  }

  public boolean adicionaNavio(Navio n) {
    if (checkNomeNavioJaExiste(n.getNome()))
      return false;
    return frota.add(n);
  }

  public void mostrarNavios() {
    for (Navio n : frota) {
      System.out.println(
          "Nome: " + n.getNome() + ";Velocidade: " + n.getVelocidade() + ";Autonomia: " + n.getAutonomia()
              + ";CustoMilhaBasico: " + n.getCustoPorMilhaBasico());
    }
    if (frota.size() == 0) {
      System.out.println("Não há navios cadastrados.");
    }
  }

  public void sort() {
    frota.sort((n1, n2) -> n1.getNome().compareTo(n2.getNome()));
  }

  public void alteraSituacaoNavio(String nomeNav) {
    for (Navio n : frota) {
      if (n.getNome().equals(nomeNav)) {
        n.setIsTransporting();
      }
    }
  }

  public boolean haNavioAutonomia(double distancia) {
    for (Navio n : frota) {
      if (n.getAutonomia() <= distancia) {
        return true;
      }
    }
    return false;
  }
}
