/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.beans;

import com.striving.controller.OcAdsJpaController;
import com.striving.controller.OcCategoriesJpaController;
import com.striving.controller.OcLocationsJpaController;
import com.striving.controller.OcUsersJpaController;
import com.striving.domain.OcAds;
import com.striving.domain.OcCategories;
import com.striving.domain.OcLocations;
import com.striving.domain.OcUsers;
import com.striving.dto.AddDto;
import com.striving.dto.DashboardDto;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.UserTransaction;

/**
 *
 * @author jcastillo
 */
@Named(value = "strivingBean")
@ViewScoped
public class StrivingBean implements Serializable{

    @PersistenceUnit(unitName="StrivingStudentsPU") //inject from your application server 
    EntityManagerFactory emf; 
    @Resource //inject from your application server 
    UserTransaction  utx; 
    
    private List<OcCategories> mainCategories;
    private List<OcLocations> allLocations;
    private HashMap<OcCategories,List<OcCategories >> allCategoriesMap;    
    private ArrayList<String> keyCategories;
    private AddDto anAddDto;
    private DashboardDto dashboardDto;
    @ManagedProperty("#{aSelectedCategory}")
    private OcCategories selectedCategory;
    private int currentLocation;
    
    /**
     * Creates a new instance of StrivingBean
     */
    public StrivingBean() {
    }
    
    public List<OcCategories> retrieveMainCategories(){        
        OcCategoriesJpaController categoriesCtrler = new OcCategoriesJpaController(utx, emf);
        List <OcCategories> parentCategories =  categoriesCtrler.findParentCategories();
        return  parentCategories;        
    }
    
    
     private void retrieveSpecificCategories(){
        List<OcCategories>parentCategories = getMainCategories();
        OcCategoriesJpaController categoriesCtrler = new OcCategoriesJpaController(utx, emf);
        HashMap<OcCategories,List<OcCategories >> categoryHash = new HashMap<>();
        keyCategories = new ArrayList<>();  
        checkForLoggedUser();        
        parentCategories.stream().forEach((parentCategory) -> {
            List<OcCategories> specCategoryList = categoriesCtrler.findBySpecificCategory(parentCategory.getIdCategory());
            categoryHash.put(parentCategory, specCategoryList);           
            keyCategories.add(parentCategory.getName());           
        });    
        this.setAllCategoriesMap(categoryHash);       
     }
     
     private void checkForLoggedUser(){
         //lets find login bean first , if found we will filter by location otherwise 
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        LoginBean loginBean = (LoginBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "loginBean");
        if (loginBean != null && loginBean.getCurrentLoggedUser() != null ){   
            int aLocationId = loginBean.getCurrentLoggedUser().getIdLocation();
            this.setCurrentLocation(aLocationId);           
         }        
     }
     
     public List<OcCategories> categoryItemsByDescription(String aCategoryName){                  
            for (OcCategories aCategory : getAllCategoriesMap().keySet()) {
                if (aCategory.getName().equalsIgnoreCase(aCategoryName)){
                    return getAllCategoriesMap().get(aCategory);
                }
            }        
         return null;
     }
     
     public void findSpecificCategoryForDisplay(){
         int aParentCatId = this.getAnAddDto().getCategoryId();
         OcCategoriesJpaController categoriesCtrler = new OcCategoriesJpaController(utx, emf);
         this.getAnAddDto().setSelectedCategories(categoriesCtrler.findBySpecificCategory(aParentCatId));      
         this.getAnAddDto().setDisplaySpecCategories(true);
     }
     
     public String displayLink(OcCategories aCategory){
          if (getCurrentLocation() == 0){
            return aCategory.getName() + "(" + aCategory.getOcAdsCollection().size() + ")";
          }else{
            return aCategory.getName() + "(" + adsByLocation(aCategory)  + ")"; 
          }         
     }
     
     private int adsByLocation(OcCategories aCategory){
         int i=0;
         for( OcAds ocAd:aCategory.getOcAdsCollection()){
             if (ocAd.getIdLocation() == this.getCurrentLocation()){
                 i++;
             }
         }
         return i;
     }
     
     public void categoryAction(OcCategories aCategory){
        this.setSelectedCategory(aCategory);
        try {            
            FacesContext.getCurrentInstance().getExternalContext().dispatch("detail.xhtml");            
        } catch (IOException ex) {
            Logger.getLogger(StrivingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     private String calculateClientIPAddress(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            return request.getRemoteAddr();
        }
        return ipAddress.split("\\s*,\\s*", 2)[0];
     }
     
      public BigInteger ipToBigDecimal(String ipAddress) {		
	Long result = 0L;		
        if (ipAddress!= null){
            String[] ipAddressInArray = ipAddress.split("\\.");
            for (int i = 3; i >= 0; i--) {			
                    long ip = Long.parseLong(ipAddressInArray[3 - i]);			
                    //left shifting 24,16,8,0 and bitwise OR			
                    //1. 192 << 24
                    //1. 168 << 16
                    //1. 1   << 8
                    //1. 2   << 0
                    result |= ip << (i * 8);		
            }
        }
        return new BigInteger(result.toString());
     }
     
     private OcAds createAdFromAddDto(){
         OcAds anAd = new OcAds();
         if (getAnAddDto()!= null){
             anAd.setAddress(getAnAddDto().getAddress());
             long aLong = new Timestamp(new Date().getTime()).getTime();
           //  anAd.setIdCategory(retrieveCategoryMatching(getAnAddDto().getCategoryId()));
             anAd.setIdCategory(retrieveCategoryMatching(getAnAddDto().getSubCategoryId()));
             anAd.setCreated(new Date(aLong));             
             anAd.setDescription(getAnAddDto().getDescription());
             anAd.setHasImages(false);
             anAd.setIdLocation(getAnAddDto().getLocationId());
             anAd.setIpAddress(ipToBigDecimal(calculateClientIPAddress()));
             anAd.setPhone(getAnAddDto().getPhone());
             anAd.setPrice(new BigDecimal(getAnAddDto().getPrice()));
             anAd.setPublished(new Date(aLong));
             anAd.setTitle(getAnAddDto().getTitle());
             anAd.setSeotitle("SEO " + getAnAddDto().getTitle());
             anAd.setStatus(false);             
             anAd.setIdUser(retrieveUserMatchingUid(8));
             anAd.setWebsite(getAnAddDto().getWebsite());
             anAd.setCfCollege(retrieveLocationName(getAnAddDto().getCategoryId()));
         }
         return anAd;
     }
     
     public String searchFromMain(){    
        OcAdsJpaController adsController = new OcAdsJpaController(utx, emf);
        adsController.findAdsByTitle("test");    
        adsController.findAdsByDescription("test");
              
        return null;
     }
     
     private OcCategories retrieveCategoryMatching(int aCategoryId){
         OcCategoriesJpaController catController = new OcCategoriesJpaController(utx, emf);
         return catController.findOcCategories(aCategoryId);
     }
     
     private String retrieveLocationName(int aLocationId){
           OcLocationsJpaController locationsCtrler = new OcLocationsJpaController(utx,emf);
           OcLocations aLocation = locationsCtrler.findOcLocations(aLocationId);
           return aLocation != null ? aLocation.getName(): "";
     }
     
     private OcUsers retrieveUserMatchingUid(int aUserId){
         OcUsersJpaController userController = new OcUsersJpaController(utx, emf);
         OcUsers aUser = userController.findOcUsers(aUserId);
         return aUser;
     }
     
     public void saveNewAd(){
        OcAds anAd = createAdFromAddDto();
        OcAdsJpaController adController = new OcAdsJpaController(utx, emf);
        try {
            adController.create(anAd);
        } catch (Exception ex) {
            Logger.getLogger(StrivingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    public void loadKeys(){       
        getAllCategoriesMap().keySet().stream().forEach((keyCategory) -> {
            getKeyCategories().add(keyCategory.getName());
        });
    }
    
    
    private List<OcLocations> retrieveAllLocations(){
        OcLocationsJpaController locationsCtrler = new OcLocationsJpaController(utx,emf);
        return locationsCtrler.findOcLocationsEntities();
    }

    /**
     * @return the mainCategories
     */
    public List<OcCategories> getMainCategories() {
        if (mainCategories == null){
            mainCategories = retrieveMainCategories();
        }
        return mainCategories;
    }

    /**
     * @param mainCategories the mainCategories to set
     */
    public void setMainCategories(List<OcCategories> mainCategories) {
        this.mainCategories = mainCategories;
    }

    /**
     * @return the allLocations
     */
    public List<OcLocations> getAllLocations() {
        if (allLocations == null){
            allLocations = retrieveAllLocations();
        }
        return allLocations;
    }

    /**
     * @param allLocations the allLocations to set
     */
    public void setAllLocations(List<OcLocations> allLocations) {
        this.allLocations = allLocations;
    }

    /**
     * @return the allCategoriesMap
     */
    public HashMap<OcCategories,List<OcCategories >> getAllCategoriesMap() {
        return allCategoriesMap;
    }

    /**
     * @param allCategoriesMap the allCategoriesMap to set
     */
    public void setAllCategoriesMap(HashMap<OcCategories,List<OcCategories >> allCategoriesMap) {
        this.allCategoriesMap = allCategoriesMap;
    }

    /**
     * @return the keyCategories
     */
    public ArrayList<String> getKeyCategories() {
        if (keyCategories == null){
            retrieveSpecificCategories();
        }
        return keyCategories;
    }

    /**
     * @param keyCategories the keyCategories to set
     */
    public void setKeyCategories(ArrayList<String> keyCategories) {
        this.keyCategories = keyCategories;
    }

    /**
     * @return the selectedCategory
     */
    public OcCategories getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * @param selectedCategory the selectedCategory to set
     */
    public void setSelectedCategory(OcCategories selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    /**
     * @return the anAddDto
     */
    public AddDto getAnAddDto() {
        if (anAddDto == null){
            anAddDto = new AddDto();
        }
        return anAddDto;
    }

    /**
     * @param anAddDto the anAddDto to set
     */
    public void setAnAddDto(AddDto anAddDto) {
        this.anAddDto = anAddDto;
    }

    /**
     * @return the currentLocation
     */
    public int getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation the currentLocation to set
     */
    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * @return the dashboardDto
     */
    public DashboardDto getDashboardDto() {
        if (dashboardDto == null){
            dashboardDto = new DashboardDto();
        }
        return dashboardDto;
    }

    /**
     * @param dashboardDto the dashboardDto to set
     */
    public void setDashboardDto(DashboardDto dashboardDto) {
        this.dashboardDto = dashboardDto;
    }

     
}
