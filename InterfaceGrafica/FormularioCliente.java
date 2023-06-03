import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioCliente extends JFrame {
  // Componentes principais
  private JTextField codCliente;
  private JTextField nomeCliente;
  private JTextField emailCliente;
  private JButton botao;
  private JLabel mensagem;

  public FormularioCliente() {
    super();

    JLabel formTitle = new JLabel("Digite as informações do cliente.");
    formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

    GridLayout gridCampos = new GridLayout(4, 2);
    JPanel painelCampos = new JPanel(gridCampos);
    JLabel codClienteLabel = new JLabel("código");
    JLabel nomeClienteLabel = new JLabel("nome");
    JLabel emailClienteLabel = new JLabel("e-mail");
    codCliente = new JTextField();
    nomeCliente = new JTextField();
    emailCliente = new JTextField();
    painelCampos.add(codClienteLabel);
    painelCampos.add(codCliente);
    painelCampos.add(nomeClienteLabel);
    painelCampos.add(nomeCliente);
    painelCampos.add(emailClienteLabel);
    painelCampos.add(emailCliente);

    botao = new JButton("Enviar");
    mensagem = new JLabel();

    // Tratamento de evento do botao
    botao.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mensagem.setForeground(Color.RED);
        mensagem.setText("Botão 'Enviar' pressionado: " +
            "Código: " + codCliente.getText() + ", Nome: " + nomeCliente.getText() +
            "Autonomia: " + emailCliente.getText());
      }
    });

    GridLayout grid = new GridLayout(4, 1);
    JPanel painel = new JPanel(grid);
    painel.add(formTitle);
    painel.add(painelCampos);
    FlowLayout botaoLayout = new FlowLayout(FlowLayout.LEFT);
    JPanel botaoPainel = new JPanel(botaoLayout);
    botaoPainel.add(botao);
    painel.add(botaoPainel);
    painel.add(mensagem);

    this.setTitle("Formulario - Swing");
    this.add(painel);
    this.setSize(1200, 600);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    FormularioCliente janela = new FormularioCliente();
  }
}
