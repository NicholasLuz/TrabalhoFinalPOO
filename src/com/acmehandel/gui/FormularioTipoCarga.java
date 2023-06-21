package src.com.acmehandel.gui;
import src.com.acmehandel.dados.*;
import src.com.acmehandel.modelo.CargaDuravel;
import src.com.acmehandel.modelo.CargaPerecivel;
import src.com.acmehandel.modelo.TipoCarga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class FormularioTipoCarga extends JFrame {
    private JTextField numero, descricao, origem, tempoMaximo, setor, materialPrincipal, percentualIpi;
    private JLabel numeroLabel, descricaoLabel, origemLabel, tempoMaximoLabel, setorLabel, materialPrincipalLabel,
            prioridadeLabel, percentualIpiLabel, mensagem;
    private JRadioButton duravel, perecivel;
    private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
    private ButtonGroup botoesCheck;
    private TiposCargas tiposCargas;

    public FormularioTipoCarga(TiposCargas tiposCargas) {
        super();
        this.tiposCargas=tiposCargas;
        JPanel janelaPrincipal = new JPanel();
        janelaPrincipal.setLayout(new BorderLayout());
        setTitle("Cadastro de Tipo de Carga");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 400);
        GridBagLayout gridCampos = new GridBagLayout();
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(gridCampos);

        JLabel stringCadastroDeClientes = new JLabel("Cadastro de Tipo de Cargas: ");
        stringCadastroDeClientes.setFont(new Font("Arial", Font.BOLD, 14));
        numeroLabel = new JLabel("Numero tipo de carga:");
        numero = new JTextField();

        descricaoLabel = new JLabel("Descricao:");
        descricao = new JTextField();

        prioridadeLabel = new JLabel("Selecione o tipo de entrega (prioridade):");

        JPanel painelCheck = new JPanel(new GridLayout(1, 2));
        duravel = new JRadioButton("Duravel");
        perecivel = new JRadioButton("Perecivel");

        botoesCheck = new ButtonGroup();
        botoesCheck.add(duravel);
        botoesCheck.add(perecivel);
        painelCheck.add(duravel);
        painelCheck.add(perecivel);

        origemLabel = new JLabel("Origem:");
        origem = new JTextField();
        origemLabel.setVisible(false);
        origem.setVisible(false);

        tempoMaximoLabel = new JLabel("Tempo Maximo:");
        tempoMaximo = new JTextField();
        tempoMaximoLabel.setVisible(false);
        tempoMaximo.setVisible(false);

        setorLabel = new JLabel("Setor:");
        setor = new JTextField();
        setorLabel.setVisible(false);
        setor.setVisible(false);

        materialPrincipalLabel = new JLabel("Material Principal");
        materialPrincipal = new JTextField();
        materialPrincipalLabel.setVisible(false);
        materialPrincipal.setVisible(false);

        percentualIpiLabel = new JLabel("IPI:");
        percentualIpi = new JTextField();
        percentualIpiLabel.setVisible(false);
        percentualIpi.setVisible(false);

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
                revalidate();
                repaint();
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
                revalidate();
                repaint();
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        Insets insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        painel.add(stringCadastroDeClientes, constraints);

        mensagem = new JLabel();
        mensagem.setFont(new Font("Arial", Font.BOLD, 13));
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(mensagem, constraints);
        mensagem.setVisible(false);

        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(numeroLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(numero, constraints);
        numero.setPreferredSize(new Dimension(200, 25));
        numero.setMinimumSize(new Dimension(200, 25));
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(descricaoLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(descricao, constraints);
        descricao.setPreferredSize(new Dimension(200, 25));
        descricao.setMinimumSize(new Dimension(200, 25));
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(prioridadeLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(painelCheck, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(origemLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(origem, constraints);
        origem.setPreferredSize(new Dimension(200, 25));
        origem.setMinimumSize(new Dimension(200, 25));
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(tempoMaximoLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(tempoMaximo, constraints);
        tempoMaximo.setPreferredSize(new Dimension(200, 25));
        tempoMaximo.setMinimumSize(new Dimension(200, 25));
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(setorLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(setor, constraints);
        setor.setPreferredSize(new Dimension(200, 25));
        setor.setMinimumSize(new Dimension(200, 25));
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(materialPrincipalLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(materialPrincipal, constraints);
        materialPrincipal.setPreferredSize(new Dimension(200, 25));
        materialPrincipal.setMinimumSize(new Dimension(200, 25));
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(percentualIpiLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        painel.add(percentualIpi, constraints);
        percentualIpi.setPreferredSize(new Dimension(200, 25));
        percentualIpi.setMinimumSize(new Dimension(200, 25));

        escondeCampos();

        janelaPrincipal.add(painel, BorderLayout.CENTER);

        mensagem.setHorizontalAlignment(JLabel.CENTER);
        mensagem.setForeground(Color.BLUE);
        mensagem.setFont(new Font("Arial", Font.BOLD, 13));
        add(mensagem, BorderLayout.NORTH);
        setVisible(true);

        JPanel botaoPainel = new JPanel();
        botaoPainel.setLayout(new FlowLayout());

        botaoCadastrar = new JButton("Cadastrar");

        FlowLayout botaoLayout = new FlowLayout();
        botaoPainel = new JPanel(botaoLayout);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBackground(Color.BLUE);
        botaoCadastrar.setForeground(Color.BLACK);
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarTipoCarga();
            }
        });

        botaoPainel.add(botaoCadastrar);

        botaoMostrarCadastrados = new JButton("Mostrar Cadastrados");
        botaoMostrarCadastrados.setBackground(Color.BLUE);
        botaoMostrarCadastrados.setForeground(Color.BLACK);
        botaoMostrarCadastrados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String todosCadastrados = tiposCargas.mostrarTiposCargas();
                JOptionPane.showMessageDialog(null, todosCadastrados);
            }
        });
        botaoPainel.add(botaoMostrarCadastrados);

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBackground(Color.BLUE);
        botaoLimpar.setForeground(Color.YELLOW);
        botaoPainel.add(botaoLimpar);

        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mensagem.setVisible(false);
                limparCampos();
                escondeCampos();
            }
        });
        botaoPainel.add(botaoLimpar);

        botaoSair = new JButton("Sair");
        botaoSair.setBackground(Color.WHITE);
        botaoSair.setForeground(Color.BLACK);
        botaoPainel.add(botaoSair);

        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        janelaPrincipal.add(botaoPainel, BorderLayout.SOUTH);

        add(janelaPrincipal);
        setVisible(true);
    }

    private void limparCampos() {
        numero.setText("");
        descricao.setText("");
        origem.setText("");
        tempoMaximo.setText("");
        setor.setText("");
        materialPrincipal.setText("");
        percentualIpi.setText("");
        botoesCheck.clearSelection();
    }

    private void escondeCampos() {
        origemLabel.setVisible(false);
        tempoMaximoLabel.setVisible(false);
        origem.setVisible(false);
        tempoMaximo.setVisible(false);
        setorLabel.setVisible(false);
        materialPrincipalLabel.setVisible(false);
        percentualIpiLabel.setVisible(false);
        setor.setVisible(false);
        materialPrincipal.setVisible(false);
        percentualIpi.setVisible(false);

    }

    private void cadastrarTipoCarga() {
        String msg = "";
        try {
            if (!duravel.isSelected() && !perecivel.isSelected()) {
                throw new InputMismatchException("Preencha todos os campos!");
            }
            if (duravel.isSelected()) {
                if (numero.getText().isEmpty() || descricao.getText().isEmpty() || setor.getText().isEmpty()
                        || materialPrincipal.getText().isEmpty() || percentualIpi.getText().isEmpty()) {
                    throw new InputMismatchException("Preencha todos os campos!");
                }

                try {
                    Integer.parseInt(numero.getText());
                    if (tiposCargas.checkTipoCargaJaExiste(Integer.parseInt(numero.getText()))) {
                        throw new IllegalArgumentException("Já existe um tipo de carga com este codigo.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "O campo 'Codigo' só pode conter numeros", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IllegalArgumentException f){JOptionPane.showMessageDialog(this, f.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
                    return;}
                try {
                    if (Double.parseDouble(percentualIpi.getText()) < 0) {
                        throw new NumberFormatException("O campo 'IPI' precisa ser um numero maior que zero.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "O campo 'IPI' só pode conter numeros", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                msg = ("Botão 'Enviar' pressionado: " +
                        "Código: " + numero.getText() + ", Descricao: " + descricao.getText() +
                        "Categoria: " + duravel.getText() + "Setor: " + setor.getText() + "Material Principal: "
                        + materialPrincipal.getText()
                        + "IPI: " + percentualIpi.getText());

            }

            if (perecivel.isSelected()) {
                if (numero.getText().isEmpty() || descricao.getText().isEmpty() || origem.getText().isEmpty()
                        || tempoMaximo.getText().isEmpty()) {
                    throw new InputMismatchException("Preencha todos os campos!");
                }
                try {
                    if (Integer.parseInt(tempoMaximo.getText()) < 0) {
                        throw new NumberFormatException("O campo 'Tempo Maximo' precisa ser um numero maior que zero.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "O campo 'Tempo Maximo' só pode conter numeros inteiros",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                msg = ("Botão 'Enviar' pressionado: " +
                        "Código: " + numero.getText() + ", Descricao: " + descricao.getText() +
                        "Categoria: " + perecivel.getText() + "Origem: " + origem.getText() + "Tempo Maximo: "
                        + tempoMaximo.getText());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this, "Deseja confirmar o cadastro?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            if(duravel.isSelected()){
                tiposCargas.adicionaTipoCarga(new CargaDuravel(Integer.parseInt(numero.getText()),descricao.getText(),setor.getText(),materialPrincipal.getText(),
                Double.parseDouble(percentualIpi.getText())));
            }
            else{
                tiposCargas.adicionaTipoCarga(new CargaPerecivel(Integer.parseInt(numero.getText()),descricao.getText(),origem.getText(),Integer.parseInt(tempoMaximo.getText())));
            }
            tiposCargas.sort();
            mensagem.setText(msg);
            mensagem.setVisible(true);

            limparCampos();
            escondeCampos();
        }
    }
}
