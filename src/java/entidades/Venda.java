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
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author leonardo
 */
@Entity
public class Venda implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private double vlTotal;
    
    @Column(nullable = false)
    private Timestamp dtVenda;

    @Column(nullable = false)
    private int idUsuario;
    
    @Column(nullable = false)
    private String stEntrega;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
            
    public int getId() {
        return id;
    }
    
    public Timestamp getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Timestamp tempo) {
        this.dtVenda = tempo;
    }
    
    public double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public String getStEntrega() {
        return stEntrega;
    }

    public void setStEntrega(String stEntrega) {
        this.stEntrega = stEntrega;
    }
    
    
}
