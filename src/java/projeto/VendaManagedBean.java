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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import util.SessionUtils;

/**
 *
 * @author Leonardo
 */

@ManagedBean(name = "vendaMB")
@SessionScoped
public class VendaManagedBean {
  
    private int vlTotal;
    private String dtVenda;
    private String mensagem;
    public double total = 0;
    public List<Produto> carrinho = new ArrayList<Produto>();
    
    
    public VendaManagedBean(){
        carrinho = new ArrayList<Produto>();
    }
    
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
    
    public Integer getIdUsuario() {
        HttpSession session = SessionUtils.getSession();
	return Integer.parseInt(session.getAttribute("idUsuario").toString());
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

    public double getTotal() {
        return total;
    }
    
    public void adiciona_item_carrinho(Produto e){
        for(Produto p : carrinho){
            if(p.getId() == e.getId()){
                setMensagem("O produto já está em sua cesta de compras");
                return;
            }
        }
        
        calcula_total("somar", e.getVlProduto());
        carrinho.add(e);
    }
    
    
    public void remove_item_carrinho(int id){
        for(Produto p : carrinho){
            if(p.getId() == id){
                calcula_total("subtrair",p.getVlProduto());
                carrinho.remove(carrinho.indexOf(p));
                return;
            }
        }
    }
    
    private void calcula_total(String op, double valor){
     
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
        venda.setIdUsuario(getIdUsuario());
        venda.setVlTotal(total);
        
        VendasDAO vendasDAO = new VendasDAO();
        
        itensVendaDAO itensDAO = new itensVendaDAO();
        
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
                itensvenda.setVlVendaProduto((double)p.getVlProduto());
                itensDAO.inserir(itensvenda);
            } catch (Exception ex) {
                carrinho = new ArrayList<Produto>();
                setMensagem("Erro ao cadastrar os itens da venda:"+ex);
                return "fracasso";
            }
            
        }
        
        carrinho = new ArrayList<Produto>();
        setMensagem("Venda feita com sucesso");
        return "sucesso";
    }
    
    public List getVendas(){
        
        VendasDAO vendasDAO = new VendasDAO();       
        List<Venda> vendas = new ArrayList<Venda>();
        
        try {
            vendas = vendasDAO.obter_id_espec(" where idUsuario="+ getIdUsuario());
        } catch (Exception ex) {
            Logger.getLogger(VendaManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return vendas;
    }
    
    
    public void excluiVenda(int id){
        VendasDAO vendasDAO = new VendasDAO();
        itensVendaDAO itensvendasDAO = new itensVendaDAO();
        
        try {
            vendasDAO.remover(id);
            itensvendasDAO.remove_itens("idVenda="+id);
            
            setMensagem("registro excluído com sucesso");
        } catch (Exception ex) {
            setMensagem("Houve um erro ao exluir a venda:"+ex);
            Logger.getLogger(VendaManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
