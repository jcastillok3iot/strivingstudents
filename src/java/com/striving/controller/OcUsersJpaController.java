/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.controller;

import com.striving.controller.exceptions.IllegalOrphanException;
import com.striving.controller.exceptions.NonexistentEntityException;
import com.striving.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.striving.domain.OcAds;
import com.striving.domain.OcUsers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author jcastillo
 */
public class OcUsersJpaController implements Serializable {

    public OcUsersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OcUsers ocUsers) throws RollbackFailureException, Exception {
        if (ocUsers.getOcAdsCollection() == null) {
            ocUsers.setOcAdsCollection(new ArrayList<OcAds>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<OcAds> attachedOcAdsCollection = new ArrayList<OcAds>();
            for (OcAds ocAdsCollectionOcAdsToAttach : ocUsers.getOcAdsCollection()) {
                ocAdsCollectionOcAdsToAttach = em.getReference(ocAdsCollectionOcAdsToAttach.getClass(), ocAdsCollectionOcAdsToAttach.getIdAd());
                attachedOcAdsCollection.add(ocAdsCollectionOcAdsToAttach);
            }
            ocUsers.setOcAdsCollection(attachedOcAdsCollection);
            em.persist(ocUsers);
            for (OcAds ocAdsCollectionOcAds : ocUsers.getOcAdsCollection()) {
                OcUsers oldIdUserOfOcAdsCollectionOcAds = ocAdsCollectionOcAds.getIdUser();
                ocAdsCollectionOcAds.setIdUser(ocUsers);
                ocAdsCollectionOcAds = em.merge(ocAdsCollectionOcAds);
                if (oldIdUserOfOcAdsCollectionOcAds != null) {
                    oldIdUserOfOcAdsCollectionOcAds.getOcAdsCollection().remove(ocAdsCollectionOcAds);
                    oldIdUserOfOcAdsCollectionOcAds = em.merge(oldIdUserOfOcAdsCollectionOcAds);
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

    public void edit(OcUsers ocUsers) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OcUsers persistentOcUsers = em.find(OcUsers.class, ocUsers.getIdUser());
            Collection<OcAds> ocAdsCollectionOld = persistentOcUsers.getOcAdsCollection();
            Collection<OcAds> ocAdsCollectionNew = ocUsers.getOcAdsCollection();
            List<String> illegalOrphanMessages = null;
            for (OcAds ocAdsCollectionOldOcAds : ocAdsCollectionOld) {
                if (!ocAdsCollectionNew.contains(ocAdsCollectionOldOcAds)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OcAds " + ocAdsCollectionOldOcAds + " since its idUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<OcAds> attachedOcAdsCollectionNew = new ArrayList<OcAds>();
            for (OcAds ocAdsCollectionNewOcAdsToAttach : ocAdsCollectionNew) {
                ocAdsCollectionNewOcAdsToAttach = em.getReference(ocAdsCollectionNewOcAdsToAttach.getClass(), ocAdsCollectionNewOcAdsToAttach.getIdAd());
                attachedOcAdsCollectionNew.add(ocAdsCollectionNewOcAdsToAttach);
            }
            ocAdsCollectionNew = attachedOcAdsCollectionNew;
            ocUsers.setOcAdsCollection(ocAdsCollectionNew);
            ocUsers = em.merge(ocUsers);
            for (OcAds ocAdsCollectionNewOcAds : ocAdsCollectionNew) {
                if (!ocAdsCollectionOld.contains(ocAdsCollectionNewOcAds)) {
                    OcUsers oldIdUserOfOcAdsCollectionNewOcAds = ocAdsCollectionNewOcAds.getIdUser();
                    ocAdsCollectionNewOcAds.setIdUser(ocUsers);
                    ocAdsCollectionNewOcAds = em.merge(ocAdsCollectionNewOcAds);
                    if (oldIdUserOfOcAdsCollectionNewOcAds != null && !oldIdUserOfOcAdsCollectionNewOcAds.equals(ocUsers)) {
                        oldIdUserOfOcAdsCollectionNewOcAds.getOcAdsCollection().remove(ocAdsCollectionNewOcAds);
                        oldIdUserOfOcAdsCollectionNewOcAds = em.merge(oldIdUserOfOcAdsCollectionNewOcAds);
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
                Integer id = ocUsers.getIdUser();
                if (findOcUsers(id) == null) {
                    throw new NonexistentEntityException("The ocUsers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OcUsers ocUsers;
            try {
                ocUsers = em.getReference(OcUsers.class, id);
                ocUsers.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocUsers with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<OcAds> ocAdsCollectionOrphanCheck = ocUsers.getOcAdsCollection();
            for (OcAds ocAdsCollectionOrphanCheckOcAds : ocAdsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OcUsers (" + ocUsers + ") cannot be destroyed since the OcAds " + ocAdsCollectionOrphanCheckOcAds + " in its ocAdsCollection field has a non-nullable idUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ocUsers);
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

    public List<OcUsers> findOcUsersEntities() {
        return findOcUsersEntities(true, -1, -1);
    }

    public List<OcUsers> findOcUsersEntities(int maxResults, int firstResult) {
        return findOcUsersEntities(false, maxResults, firstResult);
    }

    private List<OcUsers> findOcUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OcUsers.class));
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

    public OcUsers findOcUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OcUsers.class, id);
        } finally {
            em.close();
        }
    }
    
    /* TypedQuery<CdrFile> queryCdrFileByFileName =
     cdrList = em.createQuery("SELECT c FROM Cdr c  where c.cdrPK.ctlDate = :aCtlDate and c.ani = :anAni")
                    .setParameter("aCtlDate", getCurrentCtlDate())
                    .setParameter("anAni",ani)                
                    .getResultList();       
            return cdrList;
    */
    public OcUsers findOcUsersBy(String userId, String password){
        EntityManager em = getEntityManager();
        try {
           return (OcUsers)em.createQuery("SELECT o FROM OcUsers o WHERE o.name = :name and  o.password = :password")
              .setParameter("name", userId)
              .setParameter("password", password)
              .getSingleResult();            
        } finally {
            em.close();
        }
    }
    
    public int userIdExists(String anEmail){
          EntityManager em = getEntityManager();
          try {
              TypedQuery<OcUsers> queryFindUIdExisting = em.createNamedQuery("OcUsers.findByEmail",OcUsers.class);
              queryFindUIdExisting.setParameter("email", anEmail );            
              return  queryFindUIdExisting.getResultList().size();              
        } finally {
            em.close();
        }          
    }

    public int getOcUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OcUsers> rt = cq.from(OcUsers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
