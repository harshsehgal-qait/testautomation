package com.katespade.qa.automation.dsl.api;

import com.katespade.qa.automation.dsl.templates.Category;

import com.qait.automation.DSL;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Ramandeep <RamandeepsSingh@QAInfoTech.com>
 */
public class Categories extends Resource{
    
    public Categories(DSL session){
        super(session, "categories");
    }
    
    public Category getRootCategories() throws UnirestException{
        Category root = new Category();
        root.name = "ROOT";
        root.id = "root";
        root.isLabel = true;
        root.subCategories = getCategories("root");
        return root;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Category> getCategoriesAndSubCategories(String parentId) throws UnirestException{
        List<Category> categories = new ArrayList();
        JSONObject response = get(parentId);
        if(response.has("categories")){
            JSONArray categoryList = response.getJSONArray("categories");
            for(int index = 0; index < categoryList.length(); index++){
                JSONObject categoryObject = categoryList.getJSONObject(index);
                if(categoryObject.has("c_showInMenu")){
                    if(categoryObject.getBoolean("c_showInMenu") && !categoryObject.getBoolean("c_hideInTopNav")){
                        Category category = new Category();
                        category.name = categoryObject.getString("name");
                        category.id = categoryObject.getString("id");
                        category.isLabel = categoryObject.getBoolean("c_isNavLabel");
                        category.subCategories = getCategories(category.id);
                        if(!category.subCategories.isEmpty()){
                            category.hasSubCategories=true;
                        }
                        categories.add(category);
                    }
                }
            }
        }
        return categories;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Category> getCategories(String parentId) throws UnirestException{
        List<Category> categories = new ArrayList();
        JSONObject response = get(parentId);
        if(response.has("categories")){
            JSONArray categoryList = response.getJSONArray("categories");
            for(int index = 0; index < categoryList.length(); index++){
                JSONObject categoryObject = categoryList.getJSONObject(index);
                if(categoryObject.has("c_showInMenu")){
                    if(categoryObject.getBoolean("c_showInMenu") && !categoryObject.getBoolean("c_hideInTopNav")){
                        Category category = new Category();
                        category.name = categoryObject.getString("name");
                        category.id = categoryObject.getString("id");
                        category.isLabel = categoryObject.getBoolean("c_isNavLabel");
                        categories.add(category);
                    }
                }
            }
        }
        return categories;
    }    
    
}
