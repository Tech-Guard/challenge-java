package br.com.fiap.techguard.view;

import br.com.fiap.techguard.model.Cliente;
import br.com.fiap.techguard.model.SistemaUsuarios;
import br.com.fiap.techguard.model.Diagnostico;
import br.com.fiap.techguard.model.Endereco;
import br.com.fiap.techguard.model.Oficina;
import br.com.fiap.techguard.model.Orcamento;
import br.com.fiap.techguard.model.Veiculo;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        // Instanciando Sistema Usuário
        SistemaUsuarios sistemaUsuarios = new SistemaUsuarios();
        Cliente usuarioAtual = null;
        boolean loggedIn = false;

        // Instanciando Endereço
        Endereco endereco = new Endereco("823543-71", "Rua das Flores", 123, "São Paulo", "SP");
        Endereco endereco2 = new Endereco("912345-67", "Avenida Paulista", 1000, "São Paulo", "SP");

        // Instanciando Cliente
        Cliente cliente = new Cliente();
        Cliente cliente2 = new Cliente("1", "João Silva", "(11) 98765-4321", "12345678909", "joao@gmail.com", "111");
        sistemaUsuarios.cadastrarUsuario(cliente2.getNome(), cliente2.getCpf(), cliente2.getTelefone(), cliente2.getEmail(), cliente2.getSenha());

        // Instanciando Veículo
        Veiculo veiculo = new Veiculo("1", "XYZ-5678", 30000, true, 2018, "Branco", "Corolla", "Toyota");
        Veiculo veiculo2 = new Veiculo("2", "ABC-1234", 15000, true, 2020, "Preto", "Civic", "Honda");

        // Instanciando Diagnóstico
        Diagnostico diagnostico = new Diagnostico("1", "Troca de óleo", "Troca de óleo recomendada", "Baixa", "2024-05-17 10:00");
        Diagnostico diagnostico2 = new Diagnostico("2", "Revisão geral", "Revisão completa do veículo", "Alta", "2024-05-17 15:00");

        // Instanciando Orçamento
        Orcamento orcamento = new Orcamento("1", "Troca de óleo", "Troca de óleo do motor", 250.00);
        Orcamento orcamento2 = new Orcamento("2", "Revisão geral", "Revisão completa com troca de filtros", 1200.00);

        // Instanciando Oficina
        Oficina oficina = new Oficina("1", "Oficina SCCP", endereco, "1234-5678", "oficina.sccp@gmail.com", "Mecânica Geral, Troca de Óleo");
        Oficina oficina2 = new Oficina("2", "Oficina do Marção", endereco2, "9876-5432", "oficina.marcao@gmail.com", "Pintura, Funilaria");

        // Redimensionando a imagem
        ImageIcon originalIcon = new ImageIcon("src/main/java/br/com/fiap/techguard/img/logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Menu de cadastro/login
        do {
            int opcaoCadastro = Integer.parseInt(JOptionPane.showInputDialog("Digite qual opção deseja acessar: " +
                    "\n1 - Cadastrar " +
                    "\n2 - Login " +
                    "\n3 - Sair"));

            switch (opcaoCadastro) {
                case 1:
                    // Painel principal cadastro
                    JPanel panelCadastro = new JPanel(new BorderLayout());
                    panelCadastro.setBackground(Color.black);
                    JLabel labelImagemCadastro = new JLabel(resizedIcon);
                    panelCadastro.add(labelImagemCadastro, BorderLayout.NORTH);

                    // Painel campos cadastro
                    JTextField nomeField = new JTextField();
                    JTextField telefoneField = new JTextField();
                    JTextField cpfField = new JTextField();
                    JTextField emailField = new JTextField();
                    JPasswordField passwordField1 = new JPasswordField();

                    JPanel panelCamposCadastro = new JPanel(new GridLayout(8, 2));
                    panelCamposCadastro.add(new JLabel("Digite seu nome:"));
                    panelCamposCadastro.add(nomeField);
                    panelCamposCadastro.add(new JLabel("Digite seu telefone:"));
                    panelCamposCadastro.add(telefoneField);
                    panelCamposCadastro.add(new JLabel("Digite seu cpf:"));
                    panelCamposCadastro.add(cpfField);
                    panelCamposCadastro.add(new JLabel("Digite seu email:"));
                    panelCamposCadastro.add(emailField);
                    panelCamposCadastro.add(new JLabel("Digite sua senha:"));
                    panelCamposCadastro.add(passwordField1);

                    panelCadastro.add(panelCamposCadastro, BorderLayout.CENTER);

                    // Diálogo cadastro
                    int optionCadastro = JOptionPane.showConfirmDialog(null, panelCadastro, "Cadastro", JOptionPane.OK_CANCEL_OPTION);
                    if (optionCadastro == JOptionPane.OK_OPTION) {
                        String nome = nomeField.getText();
                        String telefone = telefoneField.getText();
                        String cpf = cpfField.getText();
                        String email = emailField.getText();
                        String senha = new String(passwordField1.getPassword());

                        if (nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                        } else {
                            if (sistemaUsuarios.cadastrarUsuario(nome, telefone, cpf, email, senha)) {
                                JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Informações já cadastradas antes!");
                            }
                        }
                    }
                    break;

                case 2:
                    // Painel principal login
                    JPanel panelLogin = new JPanel(new BorderLayout());
                    panelLogin.setBackground(Color.black);
                    JLabel labelImagemLogin = new JLabel(resizedIcon);
                    panelLogin.add(labelImagemLogin, BorderLayout.NORTH);

                    // Painel campos login
                    JTextField emailLoginField = new JTextField();
                    JPasswordField passwordLoginField = new JPasswordField();

                    JPanel panelCamposLogin = new JPanel(new GridLayout(5, 2));
                    panelCamposLogin.add(new JLabel("Digite seu email:"));
                    panelCamposLogin.add(emailLoginField);
                    panelCamposLogin.add(new JLabel("Digite sua senha:"));
                    panelCamposLogin.add(passwordLoginField);

                    panelLogin.add(panelCamposLogin, BorderLayout.CENTER);

                    // Diálogo login
                    int optionLogin = JOptionPane.showConfirmDialog(null, panelLogin, "Login", JOptionPane.OK_CANCEL_OPTION);
                    if (optionLogin == JOptionPane.OK_OPTION) {
                        String email = emailLoginField.getText();
                        String senha = new String(passwordLoginField.getPassword());

                        usuarioAtual = sistemaUsuarios.loginUsuario(email, senha);

                        if (usuarioAtual != null) {
                            loggedIn = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Email ou senha incorretos!");
                        }
                    }
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Obrigado por utilizar o TechGuard!");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Por favor, escolha uma opção válida.");
                    break;
            }
        } while (!loggedIn);

        // Apresentação do sistema
        JOptionPane.showMessageDialog(null, "Bem-vindo ao TechGuard, " + usuarioAtual.getNome() + "!");

        // Menu de serviços
        do {
            int opcaoServico = Integer.parseInt(JOptionPane.showInputDialog("Digite qual serviço deseja acessar: " +
                    "\n1 - Diagnóstico " +
                    "\n2 - Orçamento " +
                    "\n3 - Sair"));

            switch (opcaoServico) {

                // Área diagnóstico
                case 1:
                    String[] perguntas = {
                            "O veículo está emitindo ruídos incomuns?",
                            "Houve perda de potência no motor?",
                            "Há vazamento de fluídos (óleo, água, etc.)?",
                            "As luzes de advertência no painel estão acesas?",
                            "O veículo apresentou falhas ao ligar?",
                            "O veículo sofreu algum impacto recentemente?",
                            "A última revisão foi feita há mais de 6 meses?",
                            "A quilometragem do veículo é superior a 100.000 km?",
                            "O veículo apresentou superaquecimento?",
                            "Você observou fumaça saindo do escapamento?"
                    };

                    boolean[] respostas = new boolean[perguntas.length];

                    for (int i = 0; i < perguntas.length; i++) {
                        int resposta = JOptionPane.showConfirmDialog(null, perguntas[i], "Pergunta " + (i + 1), JOptionPane.YES_NO_OPTION);
                        respostas[i] = (resposta == JOptionPane.YES_OPTION);
                    }

                    diagnostico.processarRespostas(respostas);
                    JOptionPane.showMessageDialog(null, "A gravidade do seu veículo é: " + diagnostico.getGravidade());
                    break;

                // Área diagnóstico
                case 2:
                    // Verificar se o diagnóstico foi realizado
                    if (!diagnostico.isDiagnosticado()) {
                        JOptionPane.showMessageDialog(null, "Diagnóstico de gravidade não identificado. Por favor, realize o diagnóstico primeiro.");
                        break;
                    }

                    // Verificar a gravidade diagnosticada
                    String gravidade = diagnostico.getGravidade().trim().toLowerCase();


                    double valorEstimado;
                    switch (gravidade) {
                        case "baixa":
                            valorEstimado = 300.00;
                            break;
                        case "média":
                            valorEstimado = 600.00;
                            break;
                        case "alta":
                            valorEstimado = 1200.00;
                            break;
                        default:
                            valorEstimado = 0.00;
                            JOptionPane.showMessageDialog(null, "Gravidade não identificada ou inválida.");
                            break;
                    }

                    // Exibir o resultado do orçamento
                    JOptionPane.showMessageDialog(null, "A gravidade diagnosticada foi: " + gravidade +
                            "\nO valor estimado para o serviço é: R$ " + String.format("%.2f", valorEstimado));
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Obrigado por utilizar o TechGuard!");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Por favor, escolha uma opção válida.");
                    break;
            }
        } while (true);
    }
}
