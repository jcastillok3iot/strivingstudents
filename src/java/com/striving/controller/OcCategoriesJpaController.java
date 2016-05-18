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
import com.striving.domain.OcCategories;
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
public class OcCategoriesJpaController implements Serializable {

    public OcCategoriesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OcCategories ocCategories) throws RollbackFailureException, Exception {
        if (ocCategories.getOcAdsCollection() == null) {
            ocCategories.setOcAdsCollection(new ArrayList<OcAds>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<OcAds> attachedOcAdsCollection = new ArrayList<OcAds>();
            for (OcAds ocAdsCollectionOcAdsToAttach : ocCategories.getOcAdsCollection()) {
                ocAdsCollectionOcAdsToAttach = em.getReference(ocAdsCollectionOcAdsToAttach.getClass(), ocAdsCollectionOcAdsToAttach.getIdAd());
                attachedOcAdsCollection.add(ocAdsCollectionOcAdsToAttach);
            }
            ocCategories.setOcAdsCollection(attachedOcAdsCollection);
            em.persist(ocCategories);
            for (OcAds ocAdsCollectionOcAds : ocCategories.getOcAdsCollection()) {
                OcCategories oldIdCategoryOfOcAdsCollectionOcAds = ocAdsCollectionOcAds.getIdCategory();
                ocAdsCollectionOcAds.setIdCategory(ocCategories);
                ocAdsCollectionOcAds = em.merge(ocAdsCollectionOcAds);
                if (oldIdCategoryOfOcAdsCollectionOcAds != null) {
                    oldIdCategoryOfOcAdsCollectionOcAds.getOcAdsCollection().remove(ocAdsCollectionOcAds);
                    oldIdCategoryOfOcAdsCollectionOcAds = em.merge(oldIdCategoryOfOcAdsCollectionOcAds);
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

    public void edit(OcCategories ocCategories) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OcCategories persistentOcCategories = em.find(OcCategories.class, ocCategories.getIdCategory());
            Collection<OcAds> ocAdsCollectionOld = persistentOcCategories.getOcAdsCollection();
            Collection<OcAds> ocAdsCollectionNew = ocCategories.getOcAdsCollection();
            List<String> illegalOrphanMessages = null;
            for (OcAds ocAdsCollectionOldOcAds : ocAdsCollectionOld) {
                if (!ocAdsCollectionNew.contains(ocAdsCollectionOldOcAds)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OcAds " + ocAdsCollectionOldOcAds + " since its idCategory field is not nullable.");
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
            ocCategories.setOcAdsCollection(ocAdsCollectionNew);
            ocCategories = em.merge(ocCategories);
            for (OcAds ocAdsCollectionNewOcAds : ocAdsCollectionNew) {
                if (!ocAdsCollectionOld.contains(ocAdsCollectionNewOcAds)) {
                    OcCategories oldIdCategoryOfOcAdsCollectionNewOcAds = ocAdsCollectionNewOcAds.getIdCategory();
                    ocAdsCollectionNewOcAds.setIdCategory(ocCategories);
                    ocAdsCollectionNewOcAds = em.merge(ocAdsCollectionNewOcAds);
                    if (oldIdCategoryOfOcAdsCollectionNewOcAds != null && !oldIdCategoryOfOcAdsCollectionNewOcAds.equals(ocCategories)) {
                        oldIdCategoryOfOcAdsCollectionNewOcAds.getOcAdsCollection().remove(ocAdsCollectionNewOcAds);
                        oldIdCategoryOfOcAdsCollectionNewOcAds = em.merge(oldIdCategoryOfOcAdsCollectionNewOcAds);
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
                Integer id = ocCategories.getIdCategory();
                if (findOcCategories(id) == null) {
                    throw new NonexistentEntityException("The ocCategories with id " + id + " no longer exists.");
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
            OcCategories ocCategories;
            try {
                ocCategories = em.getReference(OcCategories.class, id);
                ocCategories.getIdCategory();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocCategories with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<OcAds> ocAdsCollectionOrphanCheck = ocCategories.getOcAdsCollection();
            for (OcAds ocAdsCollectionOrphanCheckOcAds : ocAdsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OcCategories (" + ocCategories + ") cannot be destroyed since the OcAds " + ocAdsCollectionOrphanCheckOcAds + " in its ocAdsCollection field has a non-nullable idCategory field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ocCategories);
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

    public List<OcCategories> findOcCategoriesEntities() {
        return findOcCategoriesEntities(true, -1, -1);
    }

    public List<OcCategories> findOcCategoriesEntities(int maxResults, int firstResult) {
        return findOcCategoriesEntities(false, maxResults, firstResult);
    }

    private List<OcCategories> findOcCategoriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OcCategories.class));
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

    public OcCategories findOcCategories(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OcCategories.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<OcCategories> findParentCategories(){
        EntityManager em = getEntityManager();
        try{
            Query aQuery = em.createNamedQuery("OcCategories.findByIdCategoryParent", OcCategories.class)
                             .setParameter("idCategoryParent",1);
            return aQuery.getResultList();
        }finally{
            em.close();
        }   
    }
    
    public List<OcCategories> findBySpecificCategory(int aParentCatId){
     EntityManager em = getEntityManager();
        try{
            Query aQuery = em.createNamedQuery("OcCategories.findByIdCategoryParent", OcCategories.class)
                             .setParameter("idCategoryParent",aParentCatId);
            return aQuery.getResultList();
        }finally{
            em.close();
        }   
    }

    public int getOcCategoriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OcCategories> rt = cq.from(OcCategories.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
