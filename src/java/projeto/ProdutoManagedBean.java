/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import dao.ProdutoDAO;
import entidades.Produto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author leonardo
 */

@ManagedBean(name = "produtoMB")
@SessionScoped
public class ProdutoManagedBean {
    public List<Produto> produtos;
    private String mensagem;   
    private boolean selected;
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
   
    public List<Produto> getProdutos(){
        return produtos;
    }
    
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public String obterTodos(){
        ProdutoDAO produtoDAO = new ProdutoDAO();        
        try {
            produtos = produtoDAO.obterTodos();
        } catch (Exception ex) {
            setMensagem("Erro ao obter o(s) produtos(s)");
            return "fracasso";
        }
        
        return "sucesso";
    }
   
}
