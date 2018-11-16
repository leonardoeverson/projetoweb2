/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author leonardo
 */
@Entity
public class itensVenda {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(nullable = false)
    private int idVenda;
    
    @Column(nullable = false)
    private int idProduto;
    
    @Column(nullable = false)
    private Double vlVendaProduto;

    public int getId() {
        return id;
    }
    
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public Double getVlVendaProduto() {
        return vlVendaProduto;
    }

    public void setVlVendaProduto(Double vlVendaProduto) {
        this.vlVendaProduto = vlVendaProduto;
    }
    
    
}
