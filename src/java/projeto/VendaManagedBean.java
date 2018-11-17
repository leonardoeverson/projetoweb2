/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import dao.VendasDAO;
import dao.itensVendaDAO;
import entidades.Produto;
import entidades.Venda;
import entidades.itensVenda;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    public Double total = (double)0;
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

    public Double getTotal() {
        return total;
    }
    
    public void adiciona_item_carrinho(Produto e){
        for(Produto p : carrinho){
            if(p.getId() == e.getId()){
                setMensagem("O produto já está em sua cesta de compras");
                return;
            }
        }
        
        calcula_total("somar", Integer.parseInt(e.getVlProduto()));
        carrinho.add(e);
    }
    
    
    public void remove_item_carrinho(int id){
        for(Produto p : carrinho){
            if(p.getId() == id){
                calcula_total("subtrair",Integer.parseInt(p.getVlProduto()));
                carrinho.remove(carrinho.indexOf(p));
                return;
            }
        }
    }
    
    private void calcula_total(String op, int valor){
     
        if(op.equals("somar")){
            total += valor;
        }
        
        else if(op.equals("subtrair")){
            total -= valor;
        }
        
    }
    
    
    public String finaliza_compra(){
        
        Venda venda = new Venda();
        venda.setDtVenda(Timestamp.from(Instant.now()));
        venda.setVlTotal(total);
        VendasDAO vendasDAO = new VendasDAO();
        
        itensVendaDAO itensvendaDAO = new itensVendaDAO();
        
        try {
            vendasDAO.inserir(venda);
        } catch (Exception ex) {
            setMensagem("Erro ao cadastrar a venda: "+ex);
            return "fracasso";
        }
        
        for(Produto p: carrinho){
            try {
                itensVenda itensvenda = new itensVenda();
                itensvenda.setIdProduto(p.getId());
                itensvenda.setIdVenda(venda.getId());
                vendasDAO.inserir(venda);
            } catch (Exception ex) {
                setMensagem("Erro ao cadastrar os itens da venda:"+ex);
                return "fracasso";
            }
            setMensagem("Venda feita com sucesso");
        }
        
        return "sucesso";
    }
}
