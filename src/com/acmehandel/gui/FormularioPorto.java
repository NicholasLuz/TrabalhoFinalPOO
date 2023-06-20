package src.com.acmehandel.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class FormularioPorto extends JFrame {
    private JTextField codPorto, nome, pais;
    private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
    private JLabel mensagem;

    public FormularioPorto() {
        super();
        JPanel janelaPrincipal = new JPanel();
        janelaPrincipal.setLayout(new BorderLayout());
        setTitle("Cadastro de Portos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 300);
        GridLayout gridCampos = new GridLayout(4, 2);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(gridCampos);

        JLabel stringCadastroDeClientes = new JLabel("Cadastro de Porto: ");
        stringCadastroDeClientes.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(stringCadastroDeClientes);
        painel.add(new JLabel());

        painel.add(new JLabel("Codigo do porto:"));
        codPorto = new JTextField();
        painel.add(codPorto);

        painel.add(new JLabel("Nome do porto:"));
        nome = new JTextField();
        painel.add(nome);

        painel.add(new JLabel("Pais:"));
        pais = new JTextField();
        painel.add(pais);

        janelaPrincipal.add(painel, BorderLayout.CENTER);

        JPanel botaoPainel = new JPanel();
        botaoPainel.setLayout(new FlowLayout());

        FlowLayout botaoLayout = new FlowLayout();
        botaoPainel = new JPanel(botaoLayout);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBackground(Color.BLUE);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPorto();
            }
        });
        botaoPainel.add(botaoCadastrar);

        botaoMostrarCadastrados = new JButton("Mostrar Cadastrados");
        botaoMostrarCadastrados.setBackground(Color.BLUE);
        botaoMostrarCadastrados.setForeground(Color.WHITE);
        botaoPainel.add(botaoMostrarCadastrados);

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBackground(Color.BLUE);
        botaoLimpar.setForeground(Color.YELLOW);
        botaoLimpar.addActionListener(new ActionListener() {
            @Override
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
        pais.setText("");
        codPorto.setText("");
    }

    private void cadastrarPorto() {
        String nome1 = nome.getText();
        String codigo1 = codPorto.getText();
        String pais1 = pais.getText();

        try {
            if (nome1.isEmpty() || codigo1.isEmpty() || pais1.isEmpty()) {
                throw new InputMismatchException("Preencha todos os campos!");
            }
            /*
             * if (codigo ja existe) {
             * throw new IllegalArgumentException("Já existe um porto com este codigo.");
             * }
             */
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
            /*
             * cadastrados.novoNavio(nome,Double.parseDouble(velocidade),Double.parseDouble(
             * autonomia), Double.parseDouble(custoPorMilhaBasico));
             */
            mensagem.setText(
                    "Botão 'Enviar' pressionado: " + "Código: " + codPorto.getText() + ", Nome: " + nome.getText() +
                            "Pais: " + pais.getText());
            mensagem.setForeground(Color.RED);
            mensagem.setVisible(true);

            nome.setText("");
            pais.setText("");
            codPorto.setText("");
        }
    }
}
