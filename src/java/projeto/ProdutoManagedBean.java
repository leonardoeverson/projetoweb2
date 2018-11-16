/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import dao.ProdutoDAO;
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
    private List<ProdutoManagedBean> produtos;

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
    
    private List<ProdutoManagedBean> getProdutos(){
        return produtos;
    }
    
    private void setProdutos(List<ProdutoManagedBean> produtos) {
        this.produtos = produtos;
    }
    
    private String obterTodos(){
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try {
            produtos = produtoDAO.obterTodos();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            setMensagem("Erro ao obter o(s) produtos(s)");
            return "fracasso";
        }
        
       return "sucesso";
    }
    
}
