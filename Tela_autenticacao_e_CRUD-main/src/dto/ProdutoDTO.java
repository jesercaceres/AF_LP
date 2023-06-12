package dto;

public class ProdutoDTO {
    
    private String codigo_identificador_produto;
    private String nome_produto;
    private String categoria_produto;
    private String marca_produto;
    private String preco_produto;
    private String cnpj_fornecedor;
    private String qtd_Produto;

  
    public String getCodigo_identificador_produto() {
        return codigo_identificador_produto;
    }


    public void setCodigo_identificador_produto(String codigo_identificador_produto) {
        this.codigo_identificador_produto = codigo_identificador_produto;
    }


    public String getNome_produto() {
        return nome_produto;
    }

 
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }


    public String getCategoria_produto() {
        return categoria_produto;
    }

  
    public void setCategoria_produto(String categoria_produto) {
        this.categoria_produto = categoria_produto;
    }

  
    public String getMarca_produto() {
        return marca_produto;
    }

  
    public void setMarca_produto(String marca_produto) {
        this.marca_produto = marca_produto;
    }


    public String getPreco_produto() {
        return preco_produto;
    }

    
    public void setPreco_produto(String preco_produto) {
        this.preco_produto = preco_produto;
    }

 
    public String getCnpj_fornecedor() {
        return cnpj_fornecedor;
    }

    
    public void setCnpj_fornecedor(String cnpj_fornecedor) {
        this.cnpj_fornecedor = cnpj_fornecedor;
    }

    /**
     * @return the qtd_Produto
     */
    public String getQtd_Produto() {
        return qtd_Produto;
    }

    /**
     * @param qtd_Produto the qtd_Produto to set
     */
    public void setQtd_Produto(String qtd_Produto) {
        this.qtd_Produto = qtd_Produto;
    }
     @Override
    public String toString() {
        return codigo_identificador_produto;
    }
}
  