package src.com.acmehandel.gui;
import src.com.acmehandel.dados.*;
import src.com.acmehandel.modelo.Navio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;

public class FormularioNavio extends JFrame {
    private JTextField campoNome, campoVelocidade, campoAutonomia, campoCustoMilhaBasico;
    private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
    private JLabel mensagemUsuario;
    private Frota frota;


    public FormularioNavio(Frota frota) {
        super();
        this.frota = frota;
        JPanel janelaPrincipal = new JPanel();
        janelaPrincipal.setLayout(new BorderLayout());
        setTitle("Cadastro de Navios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2));
        // **;**;**;**;**
        JLabel stringCadastroDeNavios = new JLabel("Cadastro de Navio: ");
        stringCadastroDeNavios.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(stringCadastroDeNavios);
        painel.add(new JLabel());
        // cadastro, null;**;**;**;**
        painel.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painel.add(campoNome);
        // cadastro, null;string nome, textfield nome;**;**;**
        painel.add(new JLabel("Velocidade (em nos):"));
        campoVelocidade = new JTextField();
        painel.add(campoVelocidade);
        // cadastro, null;string nome, textfield;string vel, textfield;**;**
        painel.add(new JLabel("Autonomia (milhas nauticas):"));
        campoAutonomia = new JTextField();
        painel.add(campoAutonomia);
        // cadastro, null;string nome, textfield;string vel, textfield;string auton,
        // textfield;**
        painel.add(new JLabel("Custo por milha Basico (reais):"));
        campoCustoMilhaBasico = new JTextField();
        painel.add(campoCustoMilhaBasico);
        // cadastro, null;string nome, textfield;string vel, textfield;string auton,
        // textfield;string customilha, textfield
        janelaPrincipal.add(painel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarNavio();
            }
        });
        buttonPanel.add(botaoCadastrar);

        botaoMostrarCadastrados = new JButton("Mostrar Cadastrados");
        botaoMostrarCadastrados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String todosCadastrados = frota.mostrarNavios();
                JOptionPane.showMessageDialog(null, todosCadastrados);
            }
        });
        buttonPanel.add(botaoMostrarCadastrados);

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        buttonPanel.add(botaoLimpar);

        botaoSair = new JButton("Sair");
        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(botaoSair);

        janelaPrincipal.add(buttonPanel, BorderLayout.SOUTH);

        add(janelaPrincipal);

        mensagemUsuario = new JLabel();
        mensagemUsuario.setHorizontalAlignment(JLabel.CENTER);
        mensagemUsuario.setForeground(Color.RED);
        mensagemUsuario.setFont(new Font("Arial", Font.BOLD, 13));
        mensagemUsuario.setVisible(false);
        add(mensagemUsuario, BorderLayout.NORTH);
        setVisible(true);
    }

    private void limparCampos() {
        mensagemUsuario.setVisible(false);
        campoNome.setText("");
        campoVelocidade.setText("");
        campoAutonomia.setText("");
        campoCustoMilhaBasico.setText("");
    }

    private void cadastrarNavio() {
        String nome = campoNome.getText();
        String velocidade = campoVelocidade.getText();
        String autonomia = campoAutonomia.getText();
        String custoPorMilhaBasico = campoCustoMilhaBasico.getText();

        try {
            if (nome.isEmpty() || velocidade.isEmpty() || autonomia.isEmpty() || custoPorMilhaBasico.isEmpty()) {
                throw new InputMismatchException("Preencha todos os campos!");
            } if (frota.checkNomeNavioJaExiste(nome))
                throw new IllegalArgumentException("Ja existe um navio com este nome.");

            try {
                if (Double.parseDouble(autonomia) <= 0) {
                    throw new NumberFormatException("O campo 'Autonomia' precisa ser um numero positivo");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "O campo 'Autonomia' só pode conter numeros (lembre-se que como separador de virgula utiliza-se PONTO[.])",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if (Double.parseDouble(velocidade) <= 0) {
                    throw new NumberFormatException(
                            "O campo 'Velocidade' precisa ser um numero positivo maior que zero");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "O campo 'Velocidade' só pode conter numeros (lembre-se que como separador de virgula utiliza-se PONTO[.])",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if (Double.parseDouble(custoPorMilhaBasico) <= 0) {
                    throw new NumberFormatException(
                            "O campo 'Custo por milha basico' precisa ser um numero positivo maior que zero");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "O campo 'Custo por milha basico' só pode conter numeros (lembre-se que como separador de virgula utiliza-se PONTO[.])",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this, "Deseja confirmar o cadastro?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            frota.adicionaNavio(new Navio(nome, Double.parseDouble(velocidade), Double.parseDouble(autonomia), Double.parseDouble(custoPorMilhaBasico)));
            frota.sort();
            String mensagem = ("Navio cadastrado com sucesso!! " + "Nome: " + nome + " ; Velocidade: " + velocidade
                    + " ; Autonomia: " + autonomia + " ; Custo por milha basico: " + custoPorMilhaBasico);
            mensagemUsuario.setText(mensagem);
            mensagemUsuario.setVisible(true);

            campoNome.setText("");
            campoVelocidade.setText("");
            campoAutonomia.setText("");
            campoCustoMilhaBasico.setText("");
        }
    }
}
