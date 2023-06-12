package dao;

import dto.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jedle
 */
public class FuncionarioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;

    public boolean cadastrarFuncionario(Funcionario novoFuncionario) {
        String sql = "INSERT INTO funcionario (cpf_funcionario, nome_funcionario, sexo_funcionario, endereco_funcionario, cidade_funcionario, estado_funcionario) VALUES (?, ?, ?, ?, ?, ?)";

        conn = new ConexaoDAO().getconectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, novoFuncionario.getCpf());
            pstm.setString(2, novoFuncionario.getNome());
            pstm.setString(3, novoFuncionario.getSexo());
            pstm.setString(4, novoFuncionario.getEndereco());
            pstm.setString(5, novoFuncionario.getCidade());
            pstm.setString(6, novoFuncionario.getEstado());

            pstm.execute();
            pstm.close();

            return true; // Indica que o cadastro foi bem-sucedido

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + erro);
            return false; // Indica que ocorreu um erro ao cadastrar
        }
    }

    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf_funcionario = ?";
        Funcionario funcionario = null;

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, cpf);

            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    funcionario = new Funcionario();
                    funcionario.setCpf(resultSet.getString("cpf_funcionario"));
                    funcionario.setNome(resultSet.getString("nome_funcionario"));
                    funcionario.setSexo(resultSet.getString("sexo_funcionario"));
                    funcionario.setEndereco(resultSet.getString("endereco_funcionario"));
                    funcionario.setCidade(resultSet.getString("cidade_funcionario"));
                    funcionario.setEstado(resultSet.getString("estado_funcionario"));
                    // Aqui todos os dados encontrados no banco de dados, vao ser setados nas respectivas variaveis.

                    // Retorne o objeto Funcionario encontrado
                    return funcionario;
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Buscar: " + erro);
        }

        // Se nenhum funcionário foi encontrado, retorne null
        return null;
    }

    public boolean excluirFuncionarioPorCpf(String cpf) {
        String sql = "DELETE FROM funcionario WHERE cpf_funcionario = ?";

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, cpf);
            int rowsDeleted = pstm.executeUpdate();

            return rowsDeleted > 0; // Retorna true se pelo menos uma linha for afetada (exclusão bem-sucedida)
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário: " + erro);
            return false; // Indica que ocorreu um erro ao excluir
        }
    }

    public boolean atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome_funcionario = ?, sexo_funcionario = ?, endereco_funcionario = ?, cidade_funcionario = ?,"
                + " estado_funcionario = ? WHERE cpf_funcionario = ?";

        try (Connection conn = new ConexaoDAO().getconectaBD(); PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getSexo());
            pstm.setString(3, funcionario.getEndereco());
            pstm.setString(4, funcionario.getCidade());
            pstm.setString(5, funcionario.getEstado());
            pstm.setString(6, funcionario.getCpf());

            int rowsUpdated = pstm.executeUpdate();

            return rowsUpdated > 0; // Retorna true se pelo menos uma linha for atualizada (atualização bem-sucedida)

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário: " + erro.getMessage());
            return false; // Indica que ocorreu um erro ao atualizar
        }

    }

    public List<Funcionario> listarFuncionarios() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql); ResultSet resultSet = pstm.executeQuery()) {
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf_funcionario");
                String nome = resultSet.getString("nome_funcionario");
                String sexo = resultSet.getString("sexo_funcionario");
                String endereco = resultSet.getString("endereco_funcionario");
                String cidade = resultSet.getString("cidade_funcionario");
                String estado = resultSet.getString("estado_funcionario");

                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(cpf);
                funcionario.setNome(nome);
                funcionario.setSexo(sexo);
                funcionario.setEndereco(endereco);
                funcionario.setCidade(cidade);
                funcionario.setEstado(estado);

                funcionarios.add(funcionario);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionários: " + erro);
        }

        return funcionarios;
    }

}
