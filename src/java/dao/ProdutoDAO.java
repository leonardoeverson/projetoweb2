/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.DAO;
import entidades.Produto;

/**
 *
 * @author leonardo
 */
public class ProdutoDAO extends DAO<Produto> {
    ProdutoDAO(){
        super(Produto.class);
    }
}
