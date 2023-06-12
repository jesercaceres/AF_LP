package dao;

import dto.ComprasDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComprasDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;

    public boolean cadastrarCompra(ComprasDTO novaCompra) {
        String sql = "INSERT INTO compra (id_fornecimento, cpf_funcionario, codigo_identificador_produto, quantidade, cnpj_fornecedor) VALUES (?, ?, ?, ?, ?)";

        conn = new ConexaoDAO().getconectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, novaCompra.getIDfornecimento());
            pstm.setString(2, novaCompra.getCPFfuncionario());
            pstm.setString(3, novaCompra.getCodigoIdentificadorProduto());
            pstm.setString(4, novaCompra.getQuantidade());
            pstm.setString(5, novaCompra.getCNPJfornecedor());

            pstm.execute();
            pstm.close();

            return true; // Indica que o cadastro foi bem-sucedido

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar compra: " + erro);
            return false; // Indica que ocorreu um erro ao cadastrar
        }
    }

    public ComprasDTO buscarCompraPorID(String IDfornecimento) {
        String sql = "SELECT * FROM compra WHERE id_fornecimento = ?";
        ComprasDTO compra = null;

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, IDfornecimento);

            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {

                    compra = new ComprasDTO();

                    compra.setIDfornecimento(resultSet.getString("id_fornecimento"));
                    compra.setCPFfuncionario(resultSet.getString("cpf_funcionario"));
                    compra.setCodigoIdentificadorProduto(resultSet.getString("codigo_identificador_produto"));
                    compra.setQuantidade(resultSet.getString("quantidade"));
                    compra.setCNPJfornecedor(resultSet.getString("cnpj_fornecedor"));
                    // Aqui todos os dados encontrados no banco de dados, vao ser setados nas respectivas variaveis.
                    // Retorne o objeto compra encontrado
                    return compra;
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ComprasDAO Buscar: " + erro);
        }

        // Se nenhuma compra foi encontrada, retorne null
        return null;
    }

    public boolean excluirCompraPorIDfornecimento(String IDfornecimento) {
        String sql = "DELETE FROM compra WHERE id_fornecimento = ?";

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, IDfornecimento);
            int rowsDeleted = pstm.executeUpdate();

            return rowsDeleted > 0; // Retorna true se pelo menos uma linha for afetada (exclusão bem-sucedida)
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Compra: " + erro);
            return false; // Indica que ocorreu um erro ao excluir
        }
    }

    public boolean atualizarCompra(ComprasDTO compra) {
        String sql = "UPDATE compra SET quantidade = ?, cnpj_fornecedor = ?, codigo_identificador_produto = ?, cpf_funcionario = ? WHERE id_fornecimento = ?";

        try (Connection conn = new ConexaoDAO().getconectaBD(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, compra.getQuantidade());
            pstm.setString(2, compra.getCNPJfornecedor());
            pstm.setString(3, compra.getCodigoIdentificadorProduto());
            pstm.setString(4, compra.getCPFfuncionario());
            pstm.setString(5, compra.getIDfornecimento());

            int rowsUpdated = pstm.executeUpdate();

            return rowsUpdated > 0; // Retorna true se pelo menos uma linha for atualizada (atualização bem-sucedida)

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar compra: " + erro.getMessage());
            return false; // Indica que ocorreu um erro ao atualizar
        }
    }

}
