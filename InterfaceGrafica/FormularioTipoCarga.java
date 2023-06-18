package InterfaceGrafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioTipoCarga extends JFrame {
    private JTextField numero;
    private JTextField descricao;
    private JRadioButton duravel,perecivel;
    private JTextField origem;
    private JTextField tempoMaximo;
    private JTextField setor;
    private JTextField materialPrincipal;
    private JTextField percentualIpi;
    private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
    private JLabel mensagem;

    public FormularioTipoCarga() {
        super();
        JPanel janelaPrincipal = new JPanel();
        janelaPrincipal.setLayout(new BorderLayout());
        setTitle("Cadastro de Tipo de Carga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 300);
        GridLayout gridCampos = new GridLayout(9, 2);

        JPanel painel = new JPanel();
        painel.setLayout(gridCampos);

        JLabel stringCadastroDeClientes = new JLabel("Cadastro de Tipo de Cargas: ");
        stringCadastroDeClientes.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(stringCadastroDeClientes);
        painel.add(new JLabel());

        painel.add(new JLabel("Numero tipo de carga:"));
        numero = new JTextField();
        painel.add(numero);

        painel.add(new JLabel("Descricao:"));
        descricao = new JTextField();
        painel.add(descricao);

        painel.add(new JLabel("Selecione o tipo de entrega (prioridade):"));

        JPanel painelCheck = new JPanel(new GridLayout(1,2));
        duravel = new JRadioButton("Duravel");
        perecivel = new JRadioButton("Perecivel");

        ButtonGroup botoesCheck = new ButtonGroup();
        botoesCheck.add(duravel);
        botoesCheck.add(perecivel);

        painelCheck.add(duravel);
        painelCheck.add(perecivel);
        painel.add(painelCheck);

        JLabel origemLabel = new JLabel("Origem:");
        painel.add(origemLabel);
        origem = new JTextField();
        painel.add(origem);
        origemLabel.setVisible(false);
        origem.setVisible(false);

        JLabel tempoMaximoLabel = new JLabel("Tempo Maximo:");
        painel.add(tempoMaximoLabel);
        tempoMaximo = new JTextField();
        painel.add(tempoMaximo);
        tempoMaximoLabel.setVisible(false);
        tempoMaximo.setVisible(false);


        JLabel setorLabel = new JLabel("Setor:");
        painel.add(setorLabel);
        setor = new JTextField();
        painel.add(setor);
        setorLabel.setVisible(false);
        setor.setVisible(false);

        JLabel materialPrincipalLabel = new JLabel("Material Principal");
        painel.add(materialPrincipalLabel);
        materialPrincipal = new JTextField();
        painel.add(materialPrincipal);
        materialPrincipalLabel.setVisible(false);
        materialPrincipal.setVisible(false);

        JLabel percentualIpiLabel = new JLabel("Percentual de imposto sobre produtos industrializados (IPI):");
        painel.add(percentualIpiLabel);
        percentualIpi = new JTextField();
        painel.add(percentualIpi);
        percentualIpiLabel.setVisible(false);
        percentualIpi.setVisible(false);

        janelaPrincipal.add(painel, BorderLayout.CENTER);

        duravel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                origem.setText("");
                tempoMaximo.setText("");
                origemLabel.setVisible(false);
                tempoMaximoLabel.setVisible(false);
                origem.setVisible(false);
                tempoMaximo.setVisible(false);
                setorLabel.setVisible(true);
                materialPrincipalLabel.setVisible(true);
                percentualIpiLabel.setVisible(true);
                setor.setVisible(true);
                materialPrincipal.setVisible(true);
                percentualIpi.setVisible(true);
            }
        });
        perecivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                origemLabel.setVisible(true);
                tempoMaximoLabel.setVisible(true);
                origem.setVisible(true);
                tempoMaximo.setVisible(true);
                setor.setText("");
                materialPrincipal.setText("");
                percentualIpi.setText("");
                setorLabel.setVisible(false);
                materialPrincipalLabel.setVisible(false);
                percentualIpiLabel.setVisible(false);
                setor.setVisible(false);
                materialPrincipal.setVisible(false);
                percentualIpi.setVisible(false);
            }
        });

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
