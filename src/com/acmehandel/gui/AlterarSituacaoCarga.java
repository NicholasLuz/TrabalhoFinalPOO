package src.com.acmehandel.gui;

import src.app.App;
import src.com.acmehandel.dados.Cargas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class AlterarSituacaoCarga extends JFrame{
    private JTextField codigo;
    private JButton alterar,voltar, mostrar;
    private ButtonGroup botoesCheck;
    private JRadioButton cancelado, locado, finalizado, pendente;
    private App app;
    private JLabel mensagem;

    public AlterarSituacaoCarga(App app) {
        super();
        this.app = app;
        JPanel janelaPrincipal = new JPanel();
        janelaPrincipal.setLayout(new BorderLayout());
        setTitle("Alterar situacao de Cargas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 300);
        GridLayout gridCampos = new GridLayout(4, 2);
        setLocationRelativeTo(null);
        JPanel painel = new JPanel();
        painel.setLayout(gridCampos);

        JLabel stringCadastroDeClientes = new JLabel("Alterar situacao de Cargas: ");
        stringCadastroDeClientes.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(stringCadastroDeClientes);
        painel.add(new JLabel());

        painel.add(new JLabel("Codigo identificador da carga:"));
        codigo = new JTextField();
        painel.add(codigo);


        painel.add(new JLabel("Selecione a nova situacao: "));

        JPanel botaoPainelMostra = new JPanel();
        botaoPainelMostra.setLayout(new FlowLayout());

        mostrar = new JButton("Mostrar carga digitada");
        mostrar.setBackground(Color.BLUE);
        mostrar.setForeground(Color.WHITE);
        mostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarga();
            }
        });
        botaoPainelMostra.add(mostrar);
        botaoPainelMostra.setAlignmentY(Component.CENTER_ALIGNMENT);
        painel.add(botaoPainelMostra);

        JPanel painelCheck = new JPanel(new GridLayout(1, 2));
        JPanel painelCheck2 = new JPanel(new GridLayout(1, 2));
        cancelado = new JRadioButton("Cancelado");
        locado = new JRadioButton("Locado");
        pendente = new JRadioButton("Pendente");
        finalizado = new JRadioButton("Finalizado");
        botoesCheck = new ButtonGroup();
        botoesCheck.add(locado);
        botoesCheck.add(pendente);
        botoesCheck.add(cancelado);
        botoesCheck.add(finalizado);

        painelCheck.add(locado);
        painelCheck.add(pendente);
        painelCheck2.add(cancelado);
        painelCheck2.add(finalizado);
        painel.add(painelCheck);
        painel.add(painelCheck2);

        janelaPrincipal.add(painel, BorderLayout.CENTER);

        JPanel botaoPainel = new JPanel();
        botaoPainel.setLayout(new FlowLayout());

        alterar = new JButton("Alterar");
        alterar.setBackground(Color.BLUE);
        alterar.setForeground(Color.WHITE);
        alterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alteraCargas();
            }
        });
        botaoPainel.add(alterar);


        voltar = new JButton("Voltar");
        voltar.setBackground(Color.BLACK);
        voltar.setForeground(Color.WHITE);
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        botaoPainel.add(voltar);

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
        codigo.setText("");
        botoesCheck.clearSelection();
    }
    private void mostrarCarga(){
        int id = 0;
        try {
            if (codigo.getText().isEmpty()) {
                throw new InputMismatchException("É necessario um codigo para consultar a carga!");
            }
            id = Integer.parseInt(codigo.getText());
            if (!app.getCargas().checkCargaIdJaExiste(id)) {
                throw new IllegalArgumentException("Não existe carga com este codigo.");
            }
            mensagem.setText(app.getCargas().getCargaId(id).toString());
            mensagem.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo 'Codigo' só pode conter numeros", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }catch (Exception x){
            JOptionPane.showMessageDialog(this, x.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void alteraCargas() {
        String msg = "";
        String situ = "";
        int id = 0;
        try {
            if (!cancelado.isSelected() && !finalizado.isSelected() && !locado.isSelected() && !pendente.isSelected()) {
                throw new InputMismatchException("Preencha todos os campos!");
            }
            if (codigo.getText().isEmpty()) {
                throw new InputMismatchException("Preencha todos os campos!");
            }
            try {
                id = Integer.parseInt(codigo.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "O campo 'Codigo' só pode conter numeros", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!app.getCargas().checkCargaIdJaExiste(id)) {
                throw new IllegalArgumentException("Não existe carga com este codigo.");
            }
            else{
                if (cancelado.isSelected()) {
                    situ = "CANCELADO";
                } else if (locado.isSelected()) {
                    situ = "LOCADO";
                } else if (finalizado.isSelected()) {
                    situ = "FINALIZADO";
                } else if (pendente.isSelected()) {
                    situ = "PENDENTE";
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this, "Deseja confirmar o cadastro?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            situ.toUpperCase();
            msg = app.alterarCarga(id,situ);
            mensagem.setText(msg);
            mensagem.setVisible(true);

            limparCampos();
        }
    }
}
