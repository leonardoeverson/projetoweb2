/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 *
 * @author leonardo
 */
@Entity
public class Venda implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(nullable = false)
    private Double vlTotal;
    
    @Column(nullable = false)
    private Timestamp dtVenda;

    public int getId() {
        return id;
    }
    
    public Timestamp getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Timestamp tempo) {
        this.dtVenda = tempo;
    }
    
    public Double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }
    
}
