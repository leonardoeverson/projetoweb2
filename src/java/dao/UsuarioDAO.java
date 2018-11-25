/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.DAO;
import entidades.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author leonardo
 */
public class UsuarioDAO extends DAO<Usuario> {
    public UsuarioDAO(){
        super(Usuario.class);
    }
    
    public List<Usuario> getUsuarioByNomeSenha(String nmUsuario, String senhaUsuario) throws Exception {
        Usuario usuario = null;
        List<Usuario> resultado = null;
        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            session.beginTransaction().commit();
            String hql = "from " + Usuario.class.getName() + " where nmUsuario = '" + nmUsuario + "' and senhaUsuario = '" + senhaUsuario + "'";
            resultado = session.createQuery(hql).list();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
        
        return resultado;
    }
}
