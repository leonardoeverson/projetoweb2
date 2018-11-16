/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Leonardo
 */

@Entity
public class Venda {
  
    @Id
    private int idUsuario;
    private int vlTotal;
    private String dtVenda;
    
}
