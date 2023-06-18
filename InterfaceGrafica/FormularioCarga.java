package InterfaceGrafica;
import javax.swing.*;
import java.awt.*;

public class FormularioCarga extends JFrame {
    private JTextField id;
    private JTextField peso;
    private JTextField valorDeclarado;
    private JTextField tempoMaximo;
    private JTextField tipoCarga;
    private JRadioButton barato,rapido;
    private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
    private JLabel mensagem;

    public FormularioCarga() {
        super();
        JPanel janelaPrincipal = new JPanel();
        janelaPrincipal.setLayout(new BorderLayout());
        setTitle("Cadastro de Cargas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 300);
        GridLayout gridCampos = new GridLayout(7, 2);

        JPanel painel = new JPanel();
        painel.setLayout(gridCampos);

        JLabel stringCadastroDeClientes = new JLabel("Cadastro de Cargas: ");
        stringCadastroDeClientes.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(stringCadastroDeClientes);
        painel.add(new JLabel());

        painel.add(new JLabel("Codigo identificador da carga:"));
        id = new JTextField();
        painel.add(id);

        painel.add(new JLabel("Peso da carga:"));
        peso = new JTextField();
        painel.add(peso);

        painel.add(new JLabel("Valor declarado:"));
        valorDeclarado = new JTextField();
        painel.add(valorDeclarado);

        painel.add(new JLabel("Tempo Maximo:"));
        tempoMaximo = new JTextField();
        painel.add(tempoMaximo);

        painel.add(new JLabel("Numero do tipo de carga:"));
        tipoCarga = new JTextField();
        painel.add(tipoCarga);



        painel.add(new JLabel("Selecione o tipo de entrega (prioridade):"));

        JPanel painelCheck = new JPanel(new GridLayout(1,2));
        barato = new JRadioButton("Barato");
        rapido = new JRadioButton("Rapido");

        ButtonGroup botoesCheck = new ButtonGroup();
        botoesCheck.add(barato);
        botoesCheck.add(rapido);

        painelCheck.add(barato);
        painelCheck.add(rapido);
        painel.add(painelCheck);

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
