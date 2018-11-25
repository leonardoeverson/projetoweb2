/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import dao.ProdutoDAO;
import entidades.Produto;
import java.sql.Timestamp;
import java.time.Instant;
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
    public Produto produtoAlteracao;
    private String mensagem;   
    private boolean selected;
    
    private String nmProduto;
    private double vlProduto;
    private Timestamp dtCadastro;
    
    public ProdutoManagedBean(){
        this.setVlProduto(0);
        obterTodos();
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public double getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(double vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Timestamp getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Timestamp dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public List<Produto> getProdutos(){
        obterTodos();
        return produtos;
    }
    
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProdutoAlteracao() {
        return produtoAlteracao;
    }

    public void setProdutoAlteracao(Produto produtoAlteracao) {
        this.produtoAlteracao = produtoAlteracao;
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
    
    public String cadastrarProduto(){
               
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO(); 
            Produto produto = new Produto();
            produto.setNmProduto(this.getNmProduto());
            produto.setVlProduto(this.getVlProduto());
            produto.setDtCadastro(Timestamp.from(Instant.now()));
            produto.setSelected(false);
            
            produtoDAO.inserir(produto);
        } catch (Exception ex) {
            setMensagem("Erro ao obter o(s) produtos(s)");
            return "fracasso";
        }
        
        setMensagem(this.getNmProduto() + " cadastrado com sucesso!");
        this.setNmProduto("");
        this.setVlProduto(0);
        return "sucesso";
    }
   
    public void excluirProduto(int id){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try {
            produtoDAO.remover(id);           
            setMensagem("Produto excluído com sucesso!");
        } catch (Exception ex) {
            setMensagem("Houve um erro ao exluir o produto:"+ex);
        }
    }
    
    public String getProdutoAlteracao(int id){              
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO(); 
            produtoAlteracao = produtoDAO.obter(id);
            return "altera";
        } catch (Exception ex) {
            produtoAlteracao = null;
            setMensagem("Erro ao obter o produto");
            return "fracasso";
        }   
    }
    
    public String alterarProduto(){
               
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO(); 
            produtoDAO.atualizar(produtoAlteracao);
        } catch (Exception ex) {
            setMensagem("Erro ao atualizar o produto");
            return "fracasso";
        }
        
        setMensagem(produtoAlteracao.getNmProduto() + " atualizado com sucesso!");
        return "sucesso";
    }
}
