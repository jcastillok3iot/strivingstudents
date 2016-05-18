/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.controller;

import com.striving.controller.exceptions.NonexistentEntityException;
import com.striving.controller.exceptions.RollbackFailureException;
import com.striving.domain.OcAds;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.striving.domain.OcCategories;
import com.striving.domain.OcUsers;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author jcastillo
 */
public class OcAdsJpaController implements Serializable {

    public OcAdsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OcAds ocAds) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OcCategories idCategory = ocAds.getIdCategory();
            if (idCategory != null) {
                idCategory = em.getReference(idCategory.getClass(), idCategory.getIdCategory());
                ocAds.setIdCategory(idCategory);
            }
            OcUsers idUser = ocAds.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                ocAds.setIdUser(idUser);
            }
            em.persist(ocAds);
            if (idCategory != null) {
                idCategory.getOcAdsCollection().add(ocAds);
                idCategory = em.merge(idCategory);
            }
            if (idUser != null) {
                idUser.getOcAdsCollection().add(ocAds);
                idUser = em.merge(idUser);
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

    public void edit(OcAds ocAds) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OcAds persistentOcAds = em.find(OcAds.class, ocAds.getIdAd());
            OcCategories idCategoryOld = persistentOcAds.getIdCategory();
            OcCategories idCategoryNew = ocAds.getIdCategory();
            OcUsers idUserOld = persistentOcAds.getIdUser();
            OcUsers idUserNew = ocAds.getIdUser();
            if (idCategoryNew != null) {
                idCategoryNew = em.getReference(idCategoryNew.getClass(), idCategoryNew.getIdCategory());
                ocAds.setIdCategory(idCategoryNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                ocAds.setIdUser(idUserNew);
            }
            ocAds = em.merge(ocAds);
            if (idCategoryOld != null && !idCategoryOld.equals(idCategoryNew)) {
                idCategoryOld.getOcAdsCollection().remove(ocAds);
                idCategoryOld = em.merge(idCategoryOld);
            }
            if (idCategoryNew != null && !idCategoryNew.equals(idCategoryOld)) {
                idCategoryNew.getOcAdsCollection().add(ocAds);
                idCategoryNew = em.merge(idCategoryNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getOcAdsCollection().remove(ocAds);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getOcAdsCollection().add(ocAds);
                idUserNew = em.merge(idUserNew);
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
                Integer id = ocAds.getIdAd();
                if (findOcAds(id) == null) {
                    throw new NonexistentEntityException("The ocAds with id " + id + " no longer exists.");
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
            OcAds ocAds;
            try {
                ocAds = em.getReference(OcAds.class, id);
                ocAds.getIdAd();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocAds with id " + id + " no longer exists.", enfe);
            }
            OcCategories idCategory = ocAds.getIdCategory();
            if (idCategory != null) {
                idCategory.getOcAdsCollection().remove(ocAds);
                idCategory = em.merge(idCategory);
            }
            OcUsers idUser = ocAds.getIdUser();
            if (idUser != null) {
                idUser.getOcAdsCollection().remove(ocAds);
                idUser = em.merge(idUser);
            }
            em.remove(ocAds);
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

    public List<OcAds>findAdsByTitle(String aTitle){
        EntityManager em = getEntityManager();
        try {
            TypedQuery<OcAds> queryFindAdTitle = em.createNamedQuery("OcAds.findByTitle", OcAds.class);
            queryFindAdTitle.setParameter("title", aTitle);
            return queryFindAdTitle.getResultList();
        } finally {
            em.close();
        } 
    }
    
    public List <OcAds> findAdsByDescription(String aDescription){
        EntityManager em = getEntityManager();
        try {
            return  em.createQuery("SELECT a FROM OcAds a where a.description = :description")
                    .setParameter("description", aDescription)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<OcAds> findOcAdsEntities() {
        return findOcAdsEntities(true, -1, -1);
    }

    public List<OcAds> findOcAdsEntities(int maxResults, int firstResult) {
        return findOcAdsEntities(false, maxResults, firstResult);
    }

    private List<OcAds> findOcAdsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OcAds.class));
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

    public OcAds findOcAds(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OcAds.class, id);
        } finally {
            em.close();
        }
    }

    public int getOcAdsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OcAds> rt = cq.from(OcAds.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
