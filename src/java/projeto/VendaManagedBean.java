/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import entidades.Produto;
import entidades.Venda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Leonardo
 */

@ManagedBean(name = "vendaMB")
@SessionScoped
public class VendaManagedBean {
  
    private int idUsuario;
    private int vlTotal;
    private String dtVenda;
    public String mensagem;
    public List<Produto> carrinho = new ArrayList<Produto>();
    //private static final long serialVersionUID = 1L;
    
    
    public String getMensagem() {
        return mensagem;
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
     
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(int vlTotal) {
        this.vlTotal = vlTotal;
    }

    public String getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(String dtVenda) {
        this.dtVenda = dtVenda;
    }
    
    public void adiciona_item_carrinho(Produto e){
        for(Produto p : carrinho){
            if(p.getId() == e.getId()){
                setMensagem("O produto já está em sua cesta de compras");
                return;
            }
        }
        
        carrinho.add(e);
    }
    
    
    public void remove_item_carrinho(int id){
        for(Produto p : carrinho){
            if(p.getId() == id){
                carrinho.remove(carrinho.indexOf(p));
                return;
            }
        }
    }
}
