/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.controller;

import com.striving.controller.exceptions.NonexistentEntityException;
import com.striving.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.striving.domain.SsUser;
import com.striving.domain.SsUserType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jcastillo
 */
public class SsUserTypeJpaController implements Serializable {

    public SsUserTypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsUserType ssUserType) throws RollbackFailureException, Exception {
        if (ssUserType.getSsUserCollection() == null) {
            ssUserType.setSsUserCollection(new ArrayList<SsUser>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<SsUser> attachedSsUserCollection = new ArrayList<SsUser>();
            for (SsUser ssUserCollectionSsUserToAttach : ssUserType.getSsUserCollection()) {
                ssUserCollectionSsUserToAttach = em.getReference(ssUserCollectionSsUserToAttach.getClass(), ssUserCollectionSsUserToAttach.getUserId());
                attachedSsUserCollection.add(ssUserCollectionSsUserToAttach);
            }
            ssUserType.setSsUserCollection(attachedSsUserCollection);
            em.persist(ssUserType);
            for (SsUser ssUserCollectionSsUser : ssUserType.getSsUserCollection()) {
                SsUserType oldSsUtypeIdOfSsUserCollectionSsUser = ssUserCollectionSsUser.getSsUtypeId();
                ssUserCollectionSsUser.setSsUtypeId(ssUserType);
                ssUserCollectionSsUser = em.merge(ssUserCollectionSsUser);
                if (oldSsUtypeIdOfSsUserCollectionSsUser != null) {
                    oldSsUtypeIdOfSsUserCollectionSsUser.getSsUserCollection().remove(ssUserCollectionSsUser);
                    oldSsUtypeIdOfSsUserCollectionSsUser = em.merge(oldSsUtypeIdOfSsUserCollectionSsUser);
                }
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

    public void edit(SsUserType ssUserType) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SsUserType persistentSsUserType = em.find(SsUserType.class, ssUserType.getUserTypeId());
            Collection<SsUser> ssUserCollectionOld = persistentSsUserType.getSsUserCollection();
            Collection<SsUser> ssUserCollectionNew = ssUserType.getSsUserCollection();
            Collection<SsUser> attachedSsUserCollectionNew = new ArrayList<SsUser>();
            for (SsUser ssUserCollectionNewSsUserToAttach : ssUserCollectionNew) {
                ssUserCollectionNewSsUserToAttach = em.getReference(ssUserCollectionNewSsUserToAttach.getClass(), ssUserCollectionNewSsUserToAttach.getUserId());
                attachedSsUserCollectionNew.add(ssUserCollectionNewSsUserToAttach);
            }
            ssUserCollectionNew = attachedSsUserCollectionNew;
            ssUserType.setSsUserCollection(ssUserCollectionNew);
            ssUserType = em.merge(ssUserType);
            for (SsUser ssUserCollectionOldSsUser : ssUserCollectionOld) {
                if (!ssUserCollectionNew.contains(ssUserCollectionOldSsUser)) {
                    ssUserCollectionOldSsUser.setSsUtypeId(null);
                    ssUserCollectionOldSsUser = em.merge(ssUserCollectionOldSsUser);
                }
            }
            for (SsUser ssUserCollectionNewSsUser : ssUserCollectionNew) {
                if (!ssUserCollectionOld.contains(ssUserCollectionNewSsUser)) {
                    SsUserType oldSsUtypeIdOfSsUserCollectionNewSsUser = ssUserCollectionNewSsUser.getSsUtypeId();
                    ssUserCollectionNewSsUser.setSsUtypeId(ssUserType);
                    ssUserCollectionNewSsUser = em.merge(ssUserCollectionNewSsUser);
                    if (oldSsUtypeIdOfSsUserCollectionNewSsUser != null && !oldSsUtypeIdOfSsUserCollectionNewSsUser.equals(ssUserType)) {
                        oldSsUtypeIdOfSsUserCollectionNewSsUser.getSsUserCollection().remove(ssUserCollectionNewSsUser);
                        oldSsUtypeIdOfSsUserCollectionNewSsUser = em.merge(oldSsUtypeIdOfSsUserCollectionNewSsUser);
                    }
                }
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
                Integer id = ssUserType.getUserTypeId();
                if (findSsUserType(id) == null) {
                    throw new NonexistentEntityException("The ssUserType with id " + id + " no longer exists.");
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
            SsUserType ssUserType;
            try {
                ssUserType = em.getReference(SsUserType.class, id);
                ssUserType.getUserTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssUserType with id " + id + " no longer exists.", enfe);
            }
            Collection<SsUser> ssUserCollection = ssUserType.getSsUserCollection();
            for (SsUser ssUserCollectionSsUser : ssUserCollection) {
                ssUserCollectionSsUser.setSsUtypeId(null);
                ssUserCollectionSsUser = em.merge(ssUserCollectionSsUser);
            }
            em.remove(ssUserType);
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

    public List<SsUserType> findSsUserTypeEntities() {
        return findSsUserTypeEntities(true, -1, -1);
    }

    public List<SsUserType> findSsUserTypeEntities(int maxResults, int firstResult) {
        return findSsUserTypeEntities(false, maxResults, firstResult);
    }

    private List<SsUserType> findSsUserTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsUserType.class));
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

    public SsUserType findSsUserType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsUserType.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsUserTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsUserType> rt = cq.from(SsUserType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
