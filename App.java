import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {
  private Frota frota = new Frota();
  private Cargas cargas = new Cargas();
  private Clientes clientes = new Clientes();
  private Portos portos = new Portos();
  private Scanner entrada = null; // Atributo para entrada de dados
  private PrintStream standard = System.out; // variavel para trocar scanner para terminal
  private PrintStream streamSaida;

  public App() {
    // ver maneira de manter clientes e portos em ordme crescente
  }

  public void executar() {
    System.out.println("Rodando");
  }

  public boolean cadastraPorto(Porto p) {
    return portos.adicionaPorto(p);
  }

  public boolean cadastraNavio(Navio n) {
    return frota.adicionaNavio(n);
  }

  public boolean cadastraCliente(Cliente c) {
    return clientes.adicionaCliente(c);
  }

  public void mostrarCargas() {
    // cargas
  }
}
