/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.controller;

import com.striving.controller.exceptions.NonexistentEntityException;
import com.striving.controller.exceptions.PreexistingEntityException;
import com.striving.controller.exceptions.RollbackFailureException;
import com.striving.domain.OcConfig;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author jcastillo
 */
public class OcConfigJpaController implements Serializable {

    public OcConfigJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OcConfig ocConfig) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(ocConfig);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOcConfig(ocConfig.getConfigKey()) != null) {
                throw new PreexistingEntityException("OcConfig " + ocConfig + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OcConfig ocConfig) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ocConfig = em.merge(ocConfig);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ocConfig.getConfigKey();
                if (findOcConfig(id) == null) {
                    throw new NonexistentEntityException("The ocConfig with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OcConfig ocConfig;
            try {
                ocConfig = em.getReference(OcConfig.class, id);
                ocConfig.getConfigKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocConfig with id " + id + " no longer exists.", enfe);
            }
            em.remove(ocConfig);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OcConfig> findOcConfigEntities() {
        return findOcConfigEntities(true, -1, -1);
    }

    public List<OcConfig> findOcConfigEntities(int maxResults, int firstResult) {
        return findOcConfigEntities(false, maxResults, firstResult);
    }

    private List<OcConfig> findOcConfigEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OcConfig.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OcConfig findOcConfig(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OcConfig.class, id);
        } finally {
            em.close();
        }
    }

    public int getOcConfigCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OcConfig> rt = cq.from(OcConfig.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
