package com.qait.automation.utils;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import org.yaml.snakeyaml.Yaml;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Ramandeep <RamandeepsSingh@QAInfoTech.com>
 */
public class TestDataHandler {
    
    Map<String, Object> config;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public TestDataHandler(Map config) throws Exception{
        this.config = config;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, WikiPageData> testDataMap() throws Exception{
        Map<String, WikiPageData> testData = new HashMap();
        Map<String, String> pages = (Map)config.get("test_data_pages");
        for(String pageName: pages.keySet()){
            testData.put(pageName, new WikiPageData(config, pageName));
        }
        return testData;
    }
    
    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	private Map<String, List<Map<String,String>>> initWikiPageData() throws Exception{
		Map<String, List<Map<String,String>>> testData = new HashMap();
        HttpResponse<String> response = Unirest
                .get(config.get("wiki_url")
                        +"/plugins/viewsource/viewpagesrc.action?pageId="
                        +config.get("test_data_file")
                    )
                .basicAuth((String)config.get("wiki_username")
                        ,(String)config.get("wiki_password"))
                .asString();
        String contentText = response.getBody();
        Document wikiPage = Jsoup.parse(contentText);
        for(Element section:wikiPage.select("div.innerCell")){
            String name = section.select("h2").text();
            List<Element> rows = section.select("tr");
            
            /* compute headers */
            List<Element> headers = rows.get(0).select("th");
            List<Map<String,String>> dataTable = new ArrayList();
            for(int rownumber = 1; rownumber < rows.size(); rownumber++){
                List<Element> cells = rows.get(rownumber).select("td");
                Map<String, String> dataRow = new HashMap();
                for(int column = 0; column<cells.size(); column++){
                    dataRow.put(headers.get(column).text(), cells.get(column).text());
                }
                dataTable.add(dataRow);
            }
            testData.put(name, dataTable);
        }
        return testData;
    }
    
    /**
     * Method to read attachment in confluence wiki page as YAML files
     * 
     * @return
     * @throws Exception 
     */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	private Map<String, Object> initWikiData() throws Exception {
		HttpResponse<String> response = Unirest
				.get(config.get("wiki_url") + "/download/attachments/" + config.get("test_data_file") + "?api=v2")
				.basicAuth((String) config.get("wiki_username"), ((String) config.get("wiki_password"))).asString();
		String contentText = response.getBody();
		Yaml yaml = new Yaml();
		return (Map) yaml.load(contentText);
	}
}
