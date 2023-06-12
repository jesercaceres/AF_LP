package dao;

import java.math.BigDecimal;
import dto.ProdutoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;

    public boolean cadastrarProduto(ProdutoDTO novoProduto) {
        String sqlVerificarFornecedor = "SELECT COUNT(*) FROM fornecedor WHERE cnpj_fornecedor = ?";
        String sqlCadastrarProduto = "INSERT INTO produto (codigo_identificador_produto, nome_produto, categoria_produto, marca_produto, preco_produto, cnpj_fornecedor, qtd_produto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        conn = new ConexaoDAO().getconectaBD();

        try {
            // Verificar se o fornecedor está cadastrado
            pstm = conn.prepareStatement(sqlVerificarFornecedor);
            pstm.setString(1, novoProduto.getCnpj_fornecedor());
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            pstm.close();

            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado");
                return false;
            }

            // Inserir o produto
            pstm = conn.prepareStatement(sqlCadastrarProduto);
            pstm.setString(1, novoProduto.getCodigo_identificador_produto());
            pstm.setString(2, novoProduto.getNome_produto());
            pstm.setString(3, novoProduto.getCategoria_produto());
            pstm.setString(4, novoProduto.getMarca_produto());
            pstm.setBigDecimal(5, new BigDecimal(novoProduto.getPreco_produto()));
            pstm.setString(6, novoProduto.getCnpj_fornecedor());
            pstm.setString(7, novoProduto.getQtd_Produto());

            pstm.execute();
            pstm.close();

            return true; // Indica que o cadastro foi bem-sucedido

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro);
            return false; // Indica que ocorreu um erro ao cadastrar
        }
    }

    public ProdutoDTO buscarProduto(String codigo_identificador_produto) {
        String sql = "SELECT * FROM produto WHERE codigo_identificador_produto = ?";
        ProdutoDTO produto = null;

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, codigo_identificador_produto);

            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {

                    produto = new ProdutoDTO();

                    produto.setCodigo_identificador_produto(resultSet.getString("codigo_identificador_produto"));
                    produto.setNome_produto(resultSet.getString("nome_produto"));
                    produto.setCategoria_produto(resultSet.getString("categoria_produto"));
                    produto.setMarca_produto(resultSet.getString("marca_produto"));
                    produto.setPreco_produto(resultSet.getString("preco_produto"));
                    produto.setCnpj_fornecedor(resultSet.getString("cnpj_fornecedor"));
                    produto.setQtd_Produto(resultSet.getString("qtd_produto"));

                    // Aqui todos os dados encontrados no banco de dados, vao ser setados nas respectivas variaveis.
                    // Retorne o objeto produto encontrado
                    return produto;
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO Buscar: " + erro);
        }

        // Se nenhum produto foi encontrado, retorne null
        return null;
    }

    public boolean excluirProdutoPorCODidentificador(String codigo_identificador_produto) {
        String sql = "DELETE FROM produto WHERE codigo_identificador_produto = ?";

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, codigo_identificador_produto);
            int rowsDeleted = pstm.executeUpdate();
            return rowsDeleted > 0; // Retorna true se pelo menos uma linha for afetada (exclusão bem-sucedida)
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Produto: " + erro);
            return false; // Indica que ocorreu um erro ao excluir
        }

    }

    public boolean atualizarProduto(ProdutoDTO produtodto) {
        String sql = "UPDATE produto SET nome_produto = ?, categoria_produto = ?, "
                + "marca_produto = ?, preco_produto = ?, qtd_produto = ?, cnpj_fornecedor = ? "
                + "WHERE codigo_identificador_produto = ?";

        try (Connection conn = new ConexaoDAO().getconectaBD(); PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, produtodto.getNome_produto());
            pstm.setString(2, produtodto.getCategoria_produto());
            pstm.setString(3, produtodto.getMarca_produto());
            pstm.setString(4, produtodto.getPreco_produto());
            pstm.setString(5, produtodto.getQtd_Produto());
            pstm.setString(6, produtodto.getCnpj_fornecedor());
            pstm.setString(7, produtodto.getCodigo_identificador_produto());

            int rowsUpdated = pstm.executeUpdate();

            return rowsUpdated > 0; // Retorna true se pelo menos uma linha for atualizada (atualização bem-sucedida)

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Produto: " + erro.getMessage());
            return false; // Indica que ocorreu um erro ao atualizar
        }
    }

    public List<ProdutoDTO> listarProdutos() {
        String sql = "SELECT * FROM produto";
        List<ProdutoDTO> produtos = new ArrayList<>();

        conn = new ConexaoDAO().getconectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql); ResultSet resultSet = pstm.executeQuery()) {
            while (resultSet.next()) {
                
                String codigo_identificador_produto = resultSet.getString("codigo_identificador_produto");
                String nome_produto = resultSet.getString("nome_produto");
                String categoria_produto = resultSet.getString("categoria_produto");
                String marca_produto = resultSet.getString("marca_produto");
                String preco_produto = resultSet.getString("preco_produto");
                String qtd_Produto = resultSet.getString("qtd_produto");
                String cnpj_fornecedor = resultSet.getString("cnpj_fornecedor");

                ProdutoDTO produtodto = new ProdutoDTO();
                produtodto.setCodigo_identificador_produto(codigo_identificador_produto);
                produtodto.setNome_produto(nome_produto);
                produtodto.setCategoria_produto(categoria_produto);
                produtodto.setMarca_produto(marca_produto);
                produtodto.setPreco_produto(preco_produto);
                produtodto.setQtd_Produto(qtd_Produto);
                produtodto.setCnpj_fornecedor(cnpj_fornecedor);

                produtos.add(produtodto);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro);
        }

        return produtos;
    }
}
