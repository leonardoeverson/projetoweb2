/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author leonardo
 */

@Entity
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(nullable = false)
    private String nmProduto;
    
    @Column(nullable = false)
    private String vlProduto;
    
    //private String nmProduto;
    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public String getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(String vlProduto) {
        this.vlProduto = vlProduto;
    }
       
}
