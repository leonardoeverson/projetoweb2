/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(nullable = false)
    private String nmProduto;
    
    @Column(nullable = false)
    private double vlProduto;
    
    @Column(nullable = false)
    private Timestamp dtCadastro;
    
    private boolean selected;
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public String getDtCadastro() {
        return dtCadastro.toString();
    }
    
    public int getId() {
        return id;
    }
    
    //private String nmProduto;
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
       
}
