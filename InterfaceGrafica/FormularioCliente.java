package InterfaceGrafica;
import javax.swing.*;
import java.awt.*;

public class FormularioCliente extends JFrame {
  private JTextField codCliente;
  private JTextField nomeCliente;
  private JTextField emailCliente;
  private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
  private JLabel mensagem;

  public FormularioCliente() {
    super();
    JPanel janelaPrincipal = new JPanel();
    janelaPrincipal.setLayout(new BorderLayout());
    setTitle("Cadastro de Clientes");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(650, 300);
    GridLayout gridCampos = new GridLayout(4, 2);

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
    nomeCliente = new JTextField();
    painel.add(nomeCliente);

    painel.add(new JLabel("E-mail:"));
    emailCliente = new JTextField();
    painel.add(emailCliente);

    janelaPrincipal.add(painel, BorderLayout.CENTER);

    JPanel botaoPainel = new JPanel();
    botaoPainel.setLayout(new FlowLayout());

    botaoCadastrar = new JButton("Cadastrar");
    mensagem = new JLabel();

    /* Tratamento de evento do botao
    botao.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mensagem.setForeground(Color.RED);
        mensagem.setText("Botão 'Enviar' pressionado: " +
            "Código: " + codCliente.getText() + ", Nome: " + nomeCliente.getText() +
            "Autonomia: " + emailCliente.getText());
      }
    });
    */

    FlowLayout botaoLayout = new FlowLayout();
    botaoPainel = new JPanel(botaoLayout);

    botaoCadastrar = new JButton("Cadastrar");
    botaoCadastrar.setBackground(Color.BLUE);
    botaoCadastrar.setForeground(Color.WHITE);
    //tratar eventos botaoCadastrar.addActionListener
    botaoPainel.add(botaoCadastrar);

    botaoMostrarCadastrados = new JButton("Mostrar Cadastrados");
    botaoMostrarCadastrados.setBackground(Color.BLUE);
    botaoMostrarCadastrados.setForeground(Color.WHITE);
    botaoPainel.add(botaoMostrarCadastrados);

    botaoLimpar = new JButton("Limpar");
    botaoLimpar.setBackground(Color.BLUE);
    botaoLimpar.setForeground(Color.YELLOW);
    botaoPainel.add(botaoLimpar);

    botaoSair = new JButton("Sair");
    botaoSair.setBackground(Color.BLACK);
    botaoSair.setForeground(Color.WHITE);
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
}

