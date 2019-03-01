package com.katespade.qa.automation.dsl.templates;

/**
 *
 * @author Ramandeep <RamandeepsSingh@QAInfoTech.com>
 */
public class Product {
    
    public String name;
    public String id;
    public Integer price;
    public String currency;
    public Boolean orderable;
    
    public Product() {
        orderable = true;
    }
    
    @Override
    public String toString(){
        String stringify = "[";
        stringify += "name=" + name;
        stringify += ", id=" + id;
        stringify += ", price=" + price;
        stringify += ", currency=" +  currency;
        stringify += ", orderable=" +  orderable;
        stringify += "]";
        return stringify;
    }
}
