import java.util.ArrayList;

public class Clientes {
  private ArrayList<Cliente> clientes;

  public Clientes() {
    this.clientes = new ArrayList<Cliente>();
  }

  public boolean checkNomeCliente(int cod) {
    for (Cliente c : clientes) {
      if (c.getCod() == cod) {
        return true;
      }
    }
    return false;
  }
}
