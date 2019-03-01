package com.katespade.qa.automation.dsl.api;

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
public class Products extends Resource{
    
    public Products(DSL session){
        super(session, "products");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getProductsByCategory(String cId) throws UnirestException {
        List<String> productIdsList = new ArrayList();
        Map<String,String> params = new HashMap();
        params.put("refine_1", "cgid="+cId);
        JSONObject response = get(params);
        JSONArray hits = response.getJSONArray("hits");
        for(int index = 0; index < hits.length(); index++){
            JSONObject productHit = hits.getJSONObject(index);
            productIdsList.add(productHit.getString("product_id"));
        }
        return productIdsList;
    }
    
}
