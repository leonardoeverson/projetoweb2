/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;
import dao.itensVendaDAO;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author leonardo
 */
@ManagedBean(name = "itensVendaMB")
@Entity
public class itensVendaManagedBean{
    
    @Id
    private int idUsuario;
    private int vlTotal;
    private Date dtVenda;
    private List<itensVendaManagedBean> itensVenda__;
    
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
    
}

