package dao;

import dto.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FornecedorDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;

    public boolean cadastrarFornecedor(Fornecedor novoFornecedor) {
        String sql = "INSERT INTO fornecedor ( cnpj_fornecedor , nome_fornecedor,  telefone,  email, endereco_fornecedor, cidade_fornecedor, estado_fornecedor ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        conn = new ConexaoDAO().getconectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, novoFornecedor.getCnpjFornecedor());
            pstm.setString(2, novoFornecedor.getNomeFornecedor());
            pstm.setString(3, novoFornecedor.getTelefoneFornecedor());
            pstm.setString(4, novoFornecedor.getEmailFornecedor());
            pstm.setString(5, novoFornecedor.getEnderecoFornecedor());
            pstm.setString(6, novoFornecedor.getCidadeFornecedor());
            pstm.setString(7, novoFornecedor.getEstadoFornecedor());

            pstm.execute();
            pstm.close();

            return true; // Indica que o cadastro foi bem-sucedido

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + erro);
            return false; // Indica que ocorreu um erro ao cadastrar
        }
    }

    public Fornecedor buscarFornecedorPorCnpj(String cnpjFornecedor) {
        String sql = "SELECT * FROM fornecedor WHERE cnpj_fornecedor = ?";
        Fornecedor fornecedor = null;

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, cnpjFornecedor);

            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    fornecedor = new Fornecedor();

                    fornecedor.setCnpjFornecedor(resultSet.getString("cnpj_fornecedor"));
                    fornecedor.setNomeFornecedor(resultSet.getString("nome_fornecedor"));
                    fornecedor.setTelefoneFornecedor(resultSet.getString("telefone"));
                    fornecedor.setEmailFornecedor(resultSet.getString("email"));
                    fornecedor.setEnderecoFornecedor(resultSet.getString("endereco_fornecedor"));
                    fornecedor.setCidadeFornecedor(resultSet.getString("cidade_fornecedor"));
                    fornecedor.setEstadoFornecedor(resultSet.getString("estado_fornecedor"));
                    // Aqui todos os dados encontrados no banco de dados, vao ser setados nas respectivas variaveis.

                    // Retorne o objeto Funcionario encontrado
                    return fornecedor;
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Buscar: " + erro);
        }

        // Se nenhum fornecedor foi encontrado, retorne null
        return null;
    }

    public boolean excluirFornecedorPorCNPJ(String cnpjFornecedor) {
        String sql = "DELETE FROM fornecedor WHERE cnpj_fornecedor = ?";

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, cnpjFornecedor);
            int rowsDeleted = pstm.executeUpdate();
            return rowsDeleted > 0; // Retorna true se pelo menos uma linha for afetada (exclusão bem-sucedida)
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor: " + erro);
            return false; // Indica que ocorreu um erro ao excluir
        }
    }

    public boolean atualizarFornecedor(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET nome_fornecedor = ?,"
                + "telefone = ?,  email = ?, endereco_fornecedor = ?, cidade_fornecedor = ?, "
                + "estado_fornecedor = ?  WHERE cnpj_fornecedor = ?";

        try (Connection conn = new ConexaoDAO().getconectaBD(); PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, fornecedor.getNomeFornecedor());
            pstm.setString(2, fornecedor.getTelefoneFornecedor());
            pstm.setString(3, fornecedor.getEmailFornecedor());
            pstm.setString(4, fornecedor.getEnderecoFornecedor());
            pstm.setString(5, fornecedor.getCidadeFornecedor());
            pstm.setString(6, fornecedor.getEstadoFornecedor());
            pstm.setString(7, fornecedor.getCnpjFornecedor());

            int rowsUpdated = pstm.executeUpdate();

            return rowsUpdated > 0; // Retorna true se pelo menos uma linha for atualizada (atualização bem-sucedida)

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Fornecedor: " + erro.getMessage());
            return false; // Indica que ocorreu um erro ao atualizar
        }
    }

}
