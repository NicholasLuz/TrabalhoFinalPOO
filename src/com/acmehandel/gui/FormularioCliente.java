package src.com.acmehandel.gui;

import src.com.acmehandel.dados.Clientes;
import src.com.acmehandel.modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class FormularioCliente extends JFrame {
  private JTextField codCliente, nome, email;
  private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
  private JLabel mensagem;
  private Clientes clientes;

  public FormularioCliente(Clientes clientes) {
    super();
    this.clientes = clientes;
    JPanel janelaPrincipal = new JPanel();
    janelaPrincipal.setLayout(new BorderLayout());
    setTitle("Cadastro de Clientes");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(650, 300);
    GridLayout gridCampos = new GridLayout(4, 2);
    setLocationRelativeTo(null);
    JPanel painel = new JPanel();
    painel.setLayout(gridCampos);

    JLabel stringCadastroDeClientes = new JLabel("Cadastro de Clientes: ");
    stringCadastroDeClientes.setFont(new Font("Arial", Font.BOLD, 14));
    painel.add(stringCadastroDeClientes);
    painel.add(new JLabel());

    painel.add(new JLabel("Codigo do cliente:"));
    codCliente = new JTextField();
    painel.add(codCliente);

    painel.add(new JLabel("Nome do cliente:"));
    nome = new JTextField();
    painel.add(nome);

    painel.add(new JLabel("E-mail:"));
    email = new JTextField();
    painel.add(email);

    janelaPrincipal.add(painel, BorderLayout.CENTER);

    JPanel botaoPainel = new JPanel();
    botaoPainel.setLayout(new FlowLayout());

    botaoCadastrar = new JButton("Cadastrar");
    mensagem = new JLabel();

    botaoCadastrar = new JButton("Cadastrar");
    botaoCadastrar.setBackground(Color.BLUE);
    botaoCadastrar.setForeground(Color.WHITE);
    botaoCadastrar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cadastrarCliente();
      }
    });
    botaoPainel.add(botaoCadastrar);

    botaoMostrarCadastrados = new JButton("Mostrar Cadastrados");
    botaoMostrarCadastrados.setBackground(Color.BLUE);
    botaoMostrarCadastrados.setForeground(Color.WHITE);
    botaoMostrarCadastrados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String todosCadastrados = clientes.mostrarClientes();
        JOptionPane.showMessageDialog(null, todosCadastrados);
      }
    });
    botaoPainel.add(botaoMostrarCadastrados);

    botaoLimpar = new JButton("Limpar");
    botaoLimpar.setBackground(Color.BLUE);
    botaoLimpar.setForeground(Color.YELLOW);
    botaoLimpar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        limparCampos();
      }
    });
    botaoPainel.add(botaoLimpar);

    botaoSair = new JButton("Sair");
    botaoSair.setBackground(Color.BLACK);
    botaoSair.setForeground(Color.WHITE);
    botaoSair.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    botaoPainel.add(botaoSair);

    janelaPrincipal.add(botaoPainel, BorderLayout.SOUTH);

    add(janelaPrincipal);

    mensagem = new JLabel();
    mensagem.setHorizontalAlignment(JLabel.CENTER);
    mensagem.setForeground(Color.BLUE);
    mensagem.setFont(new Font("Arial", Font.BOLD, 13));
    mensagem.setVisible(false);
    add(mensagem, BorderLayout.NORTH);
    setVisible(true);

  }

  public void limparCampos() {
    mensagem.setVisible(false);
    nome.setText("");
    email.setText("");
    codCliente.setText("");
  }

  private void cadastrarCliente() {
    String nome1 = nome.getText();
    String codigo1 = codCliente.getText();
    String email1 = email.getText();

    try {
      if (nome1.isEmpty() || codigo1.isEmpty() || email1.isEmpty()) {
        throw new InputMismatchException("Preencha todos os campos!");
      }
      if (clientes.checkCodClienteJaExiste(Integer.parseInt(codigo1))) {
        throw new IllegalArgumentException("Já existe um cliente com este codigo.");
      }
      if (clientes.checkEmailClienteJaExiste(email1)) {
        throw new IllegalArgumentException("Já existe um cliente com este codigo.");
      }
      if (!email1.contains("@")) {
        throw new IllegalArgumentException("O e-mail precisa possuir o caractere '@' .");
      }
      try {
        if (Integer.parseInt(codigo1) <= 0) {
          throw new NumberFormatException("O campo 'Codigo' precisa ser um numero inteiro positivo");
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "O campo 'Codigo' só pode conter numeros", "Erro",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int opcao = JOptionPane.showConfirmDialog(this, "Deseja confirmar o cadastro?", "Confirmação",
        JOptionPane.YES_NO_OPTION);
    if (opcao == JOptionPane.YES_OPTION) {

      Cliente cliente = new Cliente(Integer.parseInt(codigo1), nome1, email1);
      clientes.adicionaCliente(cliente);
      clientes.sort();

      mensagem
          .setText("Botão 'Enviar' pressionado: " + "Código: " + codCliente.getText() + ", Nome: " + nome.getText() +
              "E-mail: " + email.getText());
      mensagem.setForeground(Color.RED);
      mensagem.setVisible(true);

      nome.setText("");
      codCliente.setText("");
      email.setText("");
    }
  }
}
