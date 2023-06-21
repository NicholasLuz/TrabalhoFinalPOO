package src.com.acmehandel.dados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import src.com.acmehandel.modelo.Cliente;

public class Clientes {
  private ArrayList<Cliente> clientes;

  public Clientes() {
    this.clientes = new ArrayList<Cliente>();
  }

  public boolean checkCodClienteJaExiste(int cod) {
    for (Cliente c : clientes) {
      if (c.getCod() == cod) {
        return true;
      }
    }
    return false;
  }

  public boolean checkEmailClienteJaExiste(String email) {
    for (Cliente c : clientes) {
      if (c.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }

  public boolean adicionaCliente(Cliente c) {
    if (checkEmailClienteJaExiste(c.getEmail()) || checkCodClienteJaExiste(c.getCod())) {
      return false;
    }
    return clientes.add(c);
  }

  public String mostrarClientes() {
    String printClientes = "";
    for (Cliente c : clientes) {
      printClientes += "Código: " + c.getCod() + ";Nome: " + c.getNome() + ";Email: " + c.getEmail() + "\n";
    }
    return printClientes;
  }

  public void sort() {
    clientes.sort((c1, c2) -> c1.getCod() - c2.getCod());
  }

  public List<Cliente> getClientes() {
    return clientes.stream().collect(Collectors.toList());
  }
}
