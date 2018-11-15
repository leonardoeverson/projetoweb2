/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author leonardo
 */
@ManagedBean(name = "Vendas")
public class itensVenda{
    
    private int idUsuario;
    private int vlTotal;
    private Date dtVenda;
    private List<itensVenda> itensVenda__;
    
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
    
    public void getVendas(){
        vendasDAO vendas = new vendasDAO();
    }
}

