package dao;


import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidade.Jogo;
import util.JPAUtil;

public class JogoDAO {
    public static void salvar(Jogo jogo) {
    	
    	EntityManager entityManager = JPAUtil.criarEntityManager();
    	
    	try { 
    		entityManager.getTransaction().begin();
    		entityManager.persist(jogo);
    		entityManager.getTransaction().commit();
        
        } finally {
        	entityManager.close();
        }
    }
    
    public static void editar(Jogo jogo) {
    	EntityManager em = JPAUtil.criarEntityManager();
    	try{ em.getTransaction().begin();
        em.merge(jogo);
        em.getTransaction().commit();
    }finally {
    	em.close();
    }
  }
    public static void excluir(Integer id) {
    	EntityManager em = JPAUtil.criarEntityManager();
    	
    	try{ em.getTransaction().begin();
    		Jogo jogo = em.find(Jogo.class, id);
        if (jogo != null) {
        	jogo = em.merge(jogo);
            em.remove(jogo);
        }
        em.getTransaction().commit();
    } finally {
    	em.close();
    }
  }
    public static List<Jogo> listar() {
    	EntityManager em = JPAUtil.criarEntityManager(); 
    	try {
             Query query = em.createQuery("select j from Jogo j");
             List<Jogo> resultado = query.getResultList();
             return resultado;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         } finally {
             if (em != null) {
                 em.close();
             }
         }
    }

    public Jogo buscarPorId(Integer id) {
    	EntityManager em = JPAUtil.criarEntityManager();
    	return em.find(Jogo.class, id);
    }
}

