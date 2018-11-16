/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author leonardo
 */

@ManagedBean(name = "produto")
@SessionScoped
public class Produto {
    
    private List<Produto> produtos;
    /**
     * Creates a new instance of Produto
     */
    public Produto() {
    }
    
    
    public List<Produto> getProdutos(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try {
            produtos = produtoDAO.obterTodos();
        } catch (Exception ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
