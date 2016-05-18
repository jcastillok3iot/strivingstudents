/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.controller;

import com.striving.controller.exceptions.NonexistentEntityException;
import com.striving.controller.exceptions.RollbackFailureException;
import com.striving.domain.SsUser;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.striving.domain.SsUserType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jcastillo
 */
public class SsUserJpaController implements Serializable {

    public SsUserJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsUser ssUser) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SsUserType ssUtypeId = ssUser.getSsUtypeId();
            if (ssUtypeId != null) {
                ssUtypeId = em.getReference(ssUtypeId.getClass(), ssUtypeId.getUserTypeId());
                ssUser.setSsUtypeId(ssUtypeId);
            }
            em.persist(ssUser);
            if (ssUtypeId != null) {
                ssUtypeId.getSsUserCollection().add(ssUser);
                ssUtypeId = em.merge(ssUtypeId);
            }
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

    public void edit(SsUser ssUser) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SsUser persistentSsUser = em.find(SsUser.class, ssUser.getUserId());
            SsUserType ssUtypeIdOld = persistentSsUser.getSsUtypeId();
            SsUserType ssUtypeIdNew = ssUser.getSsUtypeId();
            if (ssUtypeIdNew != null) {
                ssUtypeIdNew = em.getReference(ssUtypeIdNew.getClass(), ssUtypeIdNew.getUserTypeId());
                ssUser.setSsUtypeId(ssUtypeIdNew);
            }
            ssUser = em.merge(ssUser);
            if (ssUtypeIdOld != null && !ssUtypeIdOld.equals(ssUtypeIdNew)) {
                ssUtypeIdOld.getSsUserCollection().remove(ssUser);
                ssUtypeIdOld = em.merge(ssUtypeIdOld);
            }
            if (ssUtypeIdNew != null && !ssUtypeIdNew.equals(ssUtypeIdOld)) {
                ssUtypeIdNew.getSsUserCollection().add(ssUser);
                ssUtypeIdNew = em.merge(ssUtypeIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ssUser.getUserId();
                if (findSsUser(id) == null) {
                    throw new NonexistentEntityException("The ssUser with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SsUser ssUser;
            try {
                ssUser = em.getReference(SsUser.class, id);
                ssUser.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssUser with id " + id + " no longer exists.", enfe);
            }
            SsUserType ssUtypeId = ssUser.getSsUtypeId();
            if (ssUtypeId != null) {
                ssUtypeId.getSsUserCollection().remove(ssUser);
                ssUtypeId = em.merge(ssUtypeId);
            }
            em.remove(ssUser);
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

    public List<SsUser> findSsUserEntities() {
        return findSsUserEntities(true, -1, -1);
    }

    public List<SsUser> findSsUserEntities(int maxResults, int firstResult) {
        return findSsUserEntities(false, maxResults, firstResult);
    }

    private List<SsUser> findSsUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsUser.class));
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

    public SsUser findSsUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsUser> rt = cq.from(SsUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
