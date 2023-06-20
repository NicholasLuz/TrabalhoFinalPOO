package src.com.acmehandel.dados;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import src.com.acmehandel.modelo.Porto;

public class Portos {
  private ArrayList<Porto> portos;

  public Portos() {
    this.portos = new ArrayList<Porto>();
  }

  public boolean isNacional(int idOrigem, int idDestino) throws NoSuchElementException {
    String origem = getPaisPorto(idOrigem);
    String destino = getPaisPorto(idDestino);
    if (origem.equals("Brasil") && destino.equals("Brasil")) {
      return true;
    }
    return false;
  }

  private String getPaisPorto(int id) {
    for (Porto p : portos) {
      if (p.getId() == id) {
        return p.getPais();
      }
    }
    throw new NoSuchElementException();
  }

  public boolean checkPortoIdJaExiste(int id) {
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

  public Porto getPortoId(int index) {
    return portos.get(index);
  }

  public void mostrarPortos() {
    for (Porto p : portos) {
      System.out.println("Id: " + p.getId() + ";Nome: " + p.getNome() + ";País: " + p.getPais());
    }
    if (portos.size() == 0) {
      System.out.println("Não há portos cadastrados.");
    }
  }

  public int size() {
    return portos.size();
  }

  public void sort() {
    portos.sort((p1, p2) -> p1.getId() - p2.getId());
  }
}
