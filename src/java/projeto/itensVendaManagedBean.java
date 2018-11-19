/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;
import dao.itensVendaDAO;
import entidades.itensVenda;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author leonardo
 */
@ManagedBean(name = "itensVendaMB")
@SessionScoped
public class itensVendaManagedBean{
    
    private int idUsuario;
    private int vlTotal;
    private Date dtVenda;
    private List<itensVenda> itensVenda__;
    public itensVenda itensvenda;
    public String mensagem;
     
    public String getMensagem() {
        return mensagem;
    }

    public itensVenda getItensvenda() {
        return itensvenda;
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

    public Date getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    }
    
    public String getItensVenda(int id){
        
        itensvenda = new itensVenda();
        itensvenda.setIdVenda(id);
        itensVendaDAO itensvendaDAO = new itensVendaDAO();
        
        try {
            itensvenda = itensvendaDAO.obter((int)id);
        } catch (Exception ex) {
            setMensagem("Erro:"+ ex);
            Logger.getLogger(itensVendaManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "pfracasso";
        }
        
        return "psucesso";    
    }
    
}

