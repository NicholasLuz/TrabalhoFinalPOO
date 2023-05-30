import java.util.ArrayList;

public class Frota {
  private ArrayList<Navio> frota;

  public Frota() {
    this.frota = new ArrayList<Navio>();
  }

  private boolean checkNomeNavioJaExiste(String nome) {
    for (Navio n : frota) {
      if (n.getNome().equals(nome)) {
        return true;
      }
    }
    return false;
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
}
