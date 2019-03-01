package com.katespade.qa.automation.dsl.templates;

import java.util.List;

/**
 *
 * @author Ramandeep <RamandeepsSingh@QAInfoTech.com>
 */
public class Category {

    public Boolean isLabel;
    public String name;
    public String id;
    public Boolean hasSubCategories;
    public List<Category> subCategories;
    
    public Category(){
    	
    }
    
    @Override
    public String toString(){
        String stringify = "[";
        stringify += "name=" + name;
        stringify += ", id=" + id;
        stringify += ", isLabel=" + isLabel;
        stringify += ", hasSubCategories=" +  hasSubCategories;
        stringify += ", subCategories=" + subCategories;
        stringify += "]";
        return stringify;
    }
    
}
