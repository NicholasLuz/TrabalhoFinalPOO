package src.com.acmehandel.gui;
import src.com.acmehandel.dados.*;
import src.com.acmehandel.modelo.Carga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class FormularioCarga extends JFrame {
    private JTextField id,idCliente,portoOrigem,portoDestino, peso, valorDeclarado, tempoMaximo, tipoCarga;
    private JRadioButton barato, rapido;
    private ButtonGroup botoesCheck;
    private JButton botaoCadastrar, botaoMostrarCadastrados, botaoSair, botaoLimpar;
    private JLabel mensagem;
    private Cargas cargas;

    public FormularioCarga(Cargas cargas) {
        super();
        this.cargas = cargas;
        JPanel janelaPrincipal = new JPanel();
        janelaPrincipal.setLayout(new BorderLayout());
        setTitle("Cadastro de Cargas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 300);
        GridLayout gridCampos = new GridLayout(10, 2);
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

        painel.add(new JLabel("Codigo identificador do cliente:"));
        idCliente = new JTextField();
        painel.add(idCliente);

        painel.add(new JLabel("Codigo identificador do porto de origem:"));
        portoOrigem = new JTextField();
        painel.add(portoOrigem);

        painel.add(new JLabel("Codigo identificador do porto de destino:"));
        portoDestino = new JTextField();
        painel.add(portoDestino);

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
        botaoMostrarCadastrados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String todosCadastrados = cargas.mostrarCargas2();
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
            if (id.getText().isEmpty() || idCliente.getText().isEmpty() || portoDestino.getText().isEmpty() || portoOrigem.getText().isEmpty()
                    || peso.getText().isEmpty() || valorDeclarado.getText().isEmpty()
                    || tempoMaximo.getText().isEmpty() || tipoCarga.getText().isEmpty()) {
                throw new InputMismatchException("Preencha todos os campos!");
            }

            try {
                if (cargas.checkCargaIdJaExiste(Integer.parseInt(id.getText()))){
                    throw new IllegalArgumentException("Já existe uma carga com este codigo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "O campo 'Codigo' só pode conter numeros", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }catch (IllegalArgumentException f){
                JOptionPane.showMessageDialog(this, f.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
            try {
                Integer.parseInt(portoOrigem.getText());
                Integer.parseInt(portoDestino.getText());
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Os campos 'Porto' só podem conter numeros", "Erro",
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
                    "Código: " + id.getText()+" Cliente: "+idCliente.getText()+"Porto de origem: "+portoOrigem.getText()+
                    " Porto de destino: "+portoDestino.getText()+ ", Peso: " + peso.getText() +
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
            if (rapido.isSelected()){
                cargas.adicionaCarga(new Carga(Integer.parseInt(id.getText()), Integer.parseInt(idCliente.getText()),Integer.parseInt(portoOrigem.getText()),
                        Integer.parseInt(portoDestino.getText()),Integer.parseInt(peso.getText()), Double.parseDouble(valorDeclarado.getText()),
                        Integer.parseInt(tempoMaximo.getText()),Integer.parseInt(tipoCarga.getText()), rapido.getText(), "PENDENTE"));
            }
            else{
                cargas.adicionaCarga(new Carga(Integer.parseInt(id.getText()), Integer.parseInt(idCliente.getText()),Integer.parseInt(portoOrigem.getText()),
                        Integer.parseInt(portoDestino.getText()),Integer.parseInt(peso.getText()), Double.parseDouble(valorDeclarado.getText()),
                        Integer.parseInt(tempoMaximo.getText()),Integer.parseInt(tipoCarga.getText()), barato.getText(), "PENDENTE"));
            }
            cargas.sort();
            mensagem.setText(msg);
            mensagem.setVisible(true);

            limparCampos();
        }
    }
}
