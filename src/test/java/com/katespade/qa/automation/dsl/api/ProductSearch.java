package com.katespade.qa.automation.dsl.api;

import com.katespade.qa.automation.dsl.templates.Product;

import com.qait.automation.DSL;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Ramandeep <RamandeepsSingh@QAInfoTech.com>
 */
public class ProductSearch extends Resource{
    
    public ProductSearch(DSL session){
        super(session, "product_search");
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getProductIdsListByCategoryId(String cId) throws UnirestException{
        List<String> productIdsList = new ArrayList();
        Map<String,String> params = new HashMap();
        params.put("refine_1", "cgid="+cId);
        params.put("expand", "prices,variations");
        JSONObject response = get(params);
        System.out.println(response);
        JSONArray hits = response.getJSONArray("hits");
        for(int index = 0; index < hits.length(); index++){
            JSONObject productHit = hits.getJSONObject(index);
            productIdsList.add(productHit.getString("product_id"));
        }
        return productIdsList;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Product> getProductsByCategoryId(String cId) throws UnirestException{
        List<Product> productList = new ArrayList();
        Map<String,String> params = new HashMap();
        params.put("refine_1", "cgid="+cId);
        params.put("expand", "availability,prices,variations");
        JSONObject response = get(params);
        
        JSONArray hits = response.getJSONArray("hits");
        for(int index = 0; index < hits.length(); index++){
            Product product = new Product();
            JSONObject productJson = hits.getJSONObject(index);
            product.name = productJson.getString("product_name");
            product.id = productJson.getString("product_id");
            product.price = productJson.getInt("price");
            product.currency = productJson.getString("currency");
            product.orderable = productJson.getBoolean("orderable");
            productList.add(product);
        }
        return productList;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Product findProductByNotOrderable() throws UnirestException{
        for(int pages = 0; pages < 10; pages++){
            Map<String,String> params = new HashMap();
            params.put("refine_1", "cgid=root");
            params.put("expand", "availability,prices,variations");
            params.put("start", Integer.toString(200*pages));
            params.put("count", "200");
            JSONObject response = get(params);
            if(response.getInt("count") == 0){
                break;
            }
            JSONArray hits = response.getJSONArray("hits");
            for(int index = 0; index < hits.length(); index++){
                JSONObject productJson = hits.getJSONObject(index);
                if(!productJson.getBoolean("orderable")){
                    Product product = new Product();
                    product.name = productJson.getString("product_name");
                    product.id = productJson.getString("product_id");
                    product.price = productJson.getInt("price");
                    product.currency = productJson.getString("currency");
                    product.orderable = productJson.getBoolean("orderable");
                    return product;
                }
            }
        }
        return null;
    }
    
    public List<Product> findProductsByNotOrderable() throws UnirestException{
        return findProductsByNotOrderable(10);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Product> findProductsByNotOrderable(Integer pagesToLookup) throws UnirestException{
        List<Product> productList = new ArrayList();
        for(int pages = 0; pages < pagesToLookup; pages++){
            Map<String,String> params = new HashMap();
            params.put("refine_1", "cgid=root");
            params.put("expand", "availability,prices,variations");
            params.put("start", Integer.toString(200*pages));
            params.put("count", "200");
            JSONObject response = get(params);
            if(response.getInt("count") == 0){
                break;
            }
            JSONArray hits = response.getJSONArray("hits");
            for(int index = 0; index < hits.length(); index++){
                JSONObject productJson = hits.getJSONObject(index);
                if(!productJson.getBoolean("orderable")){
                    Product product = new Product();
                    product.name = productJson.getString("product_name");
                    product.id = productJson.getString("product_id");
                    product.price = productJson.getInt("price");
                    product.currency = productJson.getString("currency");
                    product.orderable = productJson.getBoolean("orderable");
                    productList.add(product);                    
                }
            }
        }
        return productList;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Product findProductByAvailable() throws UnirestException{
        for(int pages = 0; pages < 10; pages++){
            Map<String,String> params = new HashMap();
            params.put("refine_1", "cgid=root");
            params.put("expand", "availability,prices,variations");
            params.put("start", Integer.toString(200*pages));
            params.put("count", "200");
            JSONObject response = get(params);
            if(response.getInt("count") == 0){
                break;
            }
            JSONArray hits = response.getJSONArray("hits");
            for(int index = 0; index < hits.length(); index++){
                JSONObject productJson = hits.getJSONObject(index);
                if(productJson.getBoolean("orderable")){
                    Product product = new Product();
                    product.name = productJson.getString("product_name");
                    product.id = productJson.getString("product_id");
                    product.price = productJson.getInt("price");
                    product.currency = productJson.getString("currency");
                    product.orderable = productJson.getBoolean("orderable");
                    return product;
                }
            }
        }
        return null;
    }
    
    
}
