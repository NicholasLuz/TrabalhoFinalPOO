package src.com.acmehandel.gui;
import src.app.App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface extends JFrame {
    private JButton cadastrarNovoDado, consultarCargas, alterarCargas, fretarCargas, salvarDados, carregarDados, sair;
    private JPanel painel;

    public MainInterface(App app) {
        super("Menu inicial");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel = new JPanel();
        painel.setLayout(new GridLayout(7, 1));

        cadastrarNovoDado = new JButton("Cadastrar novo dado");
        consultarCargas = new JButton("Consultar cargas");
        alterarCargas = new JButton("Alterar cargas");
        fretarCargas = new JButton("Fretar cargas");
        salvarDados = new JButton("Salvar dados");
        carregarDados = new JButton("Carregar dados");
        sair = new JButton("Sair");

        painel.add(cadastrarNovoDado);
        cadastrarNovoDado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastrarDado(app);
            }
        });
        painel.add(consultarCargas);
        consultarCargas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarCarga(app.getCargas().mostrarCargas());
            }
        });
        painel.add(alterarCargas);
        alterarCargas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AlterarSituacaoCarga(app);
            }
        });
        painel.add(fretarCargas);
        fretarCargas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FretarCarga(app);
            }
        });
        painel.add(salvarDados);
        salvarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SalvarDados(app);
            }
        });
        painel.add(carregarDados);
        carregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CarregarDado(app.getClientes(), app.getCargas(), app.getDistancias(), app.getFrota(), app.getPortos(), app.getTiposCargas());
            }
        });
        painel.add(sair);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(painel);
        setVisible(true);
    }
}
