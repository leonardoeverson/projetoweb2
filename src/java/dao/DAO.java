/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que servirá de base para as ações comuns a todos os DAOs
 */
public class DAO<E> {

    private Class<E> classePersistente;

    public DAO(Class<E> classePersistente) {
        this.classePersistente = classePersistente;
    }

    public void inserir(E entidade) {
        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entidade);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    public void atualizar(E entidade) throws Exception {
        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(entidade);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    public void remover(E entidade) throws Exception {
        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(entidade);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    public void remover(int id) throws Exception {
        E entidade = obter(id);
        remover(entidade);
    }

    public List<E> obterTodos() throws Exception {
        List<E> resultado = null;
        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            resultado = session.createQuery("from "+ classePersistente.getName()).list();
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
        return resultado;
    }

    public E obter(int id) throws Exception {
        E entidade = null;
        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            entidade = session.get(classePersistente, id);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
        return entidade;
    }
    
     public List<E> obter_id_espec(String query) throws Exception {
        E entidade = null;
        List<E> resultado = null;
        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            resultado = session.createQuery("from " + classePersistente.getName() + query).list();
            tx.commit();
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
     
    public void remove_itens(String query){

        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("delete from "+ classePersistente.getName() +" where "+query);
            q.executeUpdate();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
        
    }
}
