import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

class Aluno implements Comparable<Aluno> {
    private String nome;
    private long ra;

    public Aluno(String nome, long ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public long getRA() {
        return ra;
    }

    @Override
    public int compareTo(Aluno outroAluno) {
        return this.nome.compareToIgnoreCase(outroAluno.getNome());
    }

    @Override
    public String toString() {
        return "Aluno [Nome=" + nome + ", RA=" + ra + "]";
    }
}

public class TesteAluno extends JFrame {
    private JTextField nomeField;
    private JTextField raField;
    private JTextField removeRaField;
    private JTextArea displayArea;
    private ArrayList<Aluno> alunos;

    public TesteAluno() {
        setTitle("Cadastro de Alunos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        alunos = new ArrayList<>();

        // Cria um JTabbedPane para organizar as abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adiciona a aba de adicionar e mostrar alunos
        tabbedPane.addTab("Gerenciar Alunos", createManageAlunoPanel());
        tabbedPane.addTab("Remover Aluno", createRemoveAlunoPanel());

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createManageAlunoPanel() {
        JPanel manageAlunoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações de Layout
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label e Campo para Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        manageAlunoPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        nomeField = new JTextField(20);
        manageAlunoPanel.add(nomeField, gbc);

        // Label e Campo para RA
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        manageAlunoPanel.add(new JLabel("RA:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        raField = new JTextField(15);
        manageAlunoPanel.add(raField, gbc);

        // Botão para adicionar aluno
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addButton = new JButton("Adicionar Aluno");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAluno();
            }
        });
        manageAlunoPanel.add(addButton, gbc);

        // Área de Exibição
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        manageAlunoPanel.add(new JScrollPane(displayArea), gbc);

        return manageAlunoPanel;
    }

    private JPanel createRemoveAlunoPanel() {
        JPanel removeAlunoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações de Layout
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label e Campo para Remover RA
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        removeAlunoPanel.add(new JLabel("Remover RA:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        removeRaField = new JTextField(15);
        removeAlunoPanel.add(removeRaField, gbc);

        // Botão para remover aluno
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton removeButton = new JButton("Remover Aluno");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAluno();
            }
        });
        removeAlunoPanel.add(removeButton, gbc);

        return removeAlunoPanel;
    }

    private void addAluno() {
        String nome = nomeField.getText();
        long ra;
        try {
            ra = Long.parseLong(raField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "RA deve ser um número.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Aluno aluno : alunos) {
            if (aluno.getRA() == ra) {
                JOptionPane.showMessageDialog(this, "RA já existe. Insira um RA único.", "Erro de Duplicação", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Aluno aluno = new Aluno(nome, ra);
        alunos.add(aluno);
        nomeField.setText("");
        raField.setText("");
        showAlunos();
    }

    private void removeAluno() {
        long ra;
        try {
            ra = Long.parseLong(removeRaField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "RA deve ser um número.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean alunoRemovido = alunos.removeIf(aluno -> aluno.getRA() == ra);
        if (alunoRemovido) {
            JOptionPane.showMessageDialog(this, "Aluno removido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Aluno com RA " + ra + " não encontrado.");
        }
        removeRaField.setText("");
        showAlunos();
    }

    private void showAlunos() {
        Collections.sort(alunos);
        displayArea.setText("Alunos (ordenados por nome):\n");
        for (Aluno aluno : alunos) {
            displayArea.append(aluno.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TesteAluno();
            }
        });
    }
}
