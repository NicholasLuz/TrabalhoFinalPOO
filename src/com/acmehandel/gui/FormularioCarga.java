package src.com.acmehandel.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class FormularioCarga extends JFrame {
    private JTextField id, peso, valorDeclarado, tempoMaximo, tipoCarga;
    private JRadioButton barato, rapido;
    private ButtonGroup botoesCheck;
    private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
    private JLabel mensagem;

    public FormularioCarga() {
        super();
        JPanel janelaPrincipal = new JPanel();
        janelaPrincipal.setLayout(new BorderLayout());
        setTitle("Cadastro de Cargas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 300);
        GridLayout gridCampos = new GridLayout(7, 2);
        setLocationRelativeTo(null);
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

        JPanel painelCheck = new JPanel(new GridLayout(1, 2));
        barato = new JRadioButton("Barato");
        rapido = new JRadioButton("Rapido");

        botoesCheck = new ButtonGroup();
        botoesCheck.add(barato);
        botoesCheck.add(rapido);

        painelCheck.add(barato);
        painelCheck.add(rapido);
        painel.add(painelCheck);

        janelaPrincipal.add(painel, BorderLayout.CENTER);

        JPanel botaoPainel = new JPanel();
        botaoPainel.setLayout(new FlowLayout());

        FlowLayout botaoLayout = new FlowLayout();
        botaoPainel = new JPanel(botaoLayout);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBackground(Color.BLUE);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCarga();
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

    private void limparCampos() {
        id.setText("");
        valorDeclarado.setText("");
        tipoCarga.setText("");
        tempoMaximo.setText("");
        peso.setText("");
        botoesCheck.clearSelection();
    }

    private void cadastrarCarga() {
        String msg = "";
        try {
            if (!rapido.isSelected() && !barato.isSelected()) {
                throw new InputMismatchException("Preencha todos os campos!");
            }
            if (id.getText().isEmpty() || peso.getText().isEmpty() || valorDeclarado.getText().isEmpty()
                    || tempoMaximo.getText().isEmpty() || tipoCarga.getText().isEmpty()) {
                throw new InputMismatchException("Preencha todos os campos!");
            }
            /*
             * if (numero ja existe) {
             * throw new IllegalArgumentException("Já existe uma carga com este codigo.");
             * }
             * if (!tipocarga){
             * throw new
             * IllegalArgumentException("Não existe tipo de carga com este codigo.");
             * }
             */

            try {
                Integer.parseInt(id.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "O campo 'Codigo' só pode conter numeros", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Integer.parseInt(tipoCarga.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "O campo 'Tipo de Carga' só pode conter numeros", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if (Integer.parseInt(peso.getText()) < 0) {
                    throw new NumberFormatException("O campo 'Peso' precisa ser um numero maior que zero.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "O campo 'Peso' só pode conter numeros", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if (Double.parseDouble(valorDeclarado.getText()) < 0) {
                    throw new NumberFormatException("O campo 'Valor declarado' precisa ser um numero maior que zero.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "O campo 'Valor declarado' só pode conter numeros (use PONTO ao inves de VIRGULA)", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if (Integer.parseInt(tempoMaximo.getText()) < 0) {
                    throw new NumberFormatException("O campo 'Tempo Maximo' precisa ser um numero maior que zero.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "O campo 'Tempo máximo' só pode conter numeros", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            msg = ("Botão 'Enviar' pressionado: " +
                    "Código: " + id.getText() + ", Peso: " + peso.getText() +
                    "Valor Declarado: " + valorDeclarado.getText() + ", Tempo maximo: " + tempoMaximo.getText()
                    + ", Tipo de carga: " + tipoCarga.getText()
                    + "Prioridade: ");

            msg += (rapido.isSelected() ? rapido.getText() : barato.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this, "Deseja confirmar o cadastro?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            mensagem.setText(msg);
            mensagem.setVisible(true);

            limparCampos();
        }
    }
}
