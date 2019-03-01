package com.katespade.qa.automation.dsl.api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Map;
import java.util.HashMap;

import com.qait.automation.DSL;

import org.json.JSONObject;


/**
 *
 * @author Ramandeep <RamandeepsSingh@QAInfoTech.com>
 */

public class Resource {
    
    String path;
    DSL session;
    
    public Resource(DSL session, String resourceName){
        this.session = session;
        path = session.config.get("api_url")
                + "/dw/shop/"
                + session.config.get("api_version")
                + "/" + resourceName;
    }
    
    private String getQueryString(Map<String, String> params){
        String queryString = "";
        for(String key:params.keySet()){
            queryString += "&" + key + "=" + params.get(key);
        }
        queryString += "&client_id=" + session.config.get("api_client_id");
        return queryString;
    }
    
    public JSONObject get(String type, Map<String, String> params) throws UnirestException{
        String queryString = getQueryString(params);
        String url = path + "/" + type + "?" + queryString;
        return new JSONObject(getUrl(url).getBody());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject get(String type) throws UnirestException{
        Map<String, String> query = new HashMap();
        return get(type, query);
    }
    
    public JSONObject get(Map<String, String> params) throws UnirestException{
        String url = path + "?" + getQueryString(params);
        return new JSONObject(getUrl(url).getBody());
    }
    
    public HttpResponse<String> getUrl(String url) throws UnirestException{
        return Unirest.get(url).asString();
    }
}
