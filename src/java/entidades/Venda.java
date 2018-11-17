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

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Timestamp dtVenda) {
        this.dtVenda = Timestamp.from(Instant.now());
    }
    
    public Double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(Double vlTotal) {
        this.vlTotal = vlTotal;
    }
    
}
