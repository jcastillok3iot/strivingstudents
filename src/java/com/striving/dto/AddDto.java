/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.dto;

import com.striving.domain.OcCategories;
import java.io.File;
import java.util.List;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author jcastillo
 */
public class AddDto {
    private String title;
    private int categoryId;
    private int locationId;
    private String description;
    private File uploadImage;
    private UploadedFile file;
    private String phone;
    private String address;
    private double price;
    private String website;
    private boolean displaySpecCategories;
    private int subCategoryId;
    private List<OcCategories> selectedCategories;
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the uploadImage
     */
    public File getUploadImage() {
        return uploadImage;
    }

    /**
     * @param uploadImage the uploadImage to set
     */
    public void setUploadImage(File uploadImage) {
        this.uploadImage = uploadImage;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the displaySpecCategories
     */
    public boolean isDisplaySpecCategories() {
        return displaySpecCategories;
    }

    /**
     * @param displaySpecCategories the displaySpecCategories to set
     */
    public void setDisplaySpecCategories(boolean displaySpecCategories) {
        this.displaySpecCategories = displaySpecCategories;
    }

    /**
     * @return the subCategoryId
     */
    public int getSubCategoryId() {
        return subCategoryId;
    }

    /**
     * @param subCategoryId the subCategoryId to set
     */
    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    /**
     * @return the selectedCategories
     */
    public List<OcCategories> getSelectedCategories() {
        return selectedCategories;
    }

    /**
     * @param selectedCategories the selectedCategories to set
     */
    public void setSelectedCategories(List<OcCategories> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }
    
}
