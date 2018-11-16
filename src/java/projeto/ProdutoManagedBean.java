/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import dao.ProdutoDAO;
import entidades.Produto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author leonardo
 */

@ManagedBean(name = "produtoMB")
@SessionScoped
public class ProdutoManagedBean {
    private List<Produto> produtos;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    private String mensagem;   
    /**
     * Creates a new instance of Produto
     */
    
    private List<Produto> getProdutos(){
        return produtos;
    }
    
    private void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public String obterTodos(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try {
            produtos = produtoDAO.obterTodos();
        } catch (Exception ex) {
            setMensagem("Erro ao obter o(s) usuário(s)");
            return "fracasso";
        }
        return "sucesso";
    }
   
}
