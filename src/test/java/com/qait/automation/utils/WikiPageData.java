package com.qait.automation.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Ramandeep <RamandeepsSingh@QAInfoTech.com>
 */
public class WikiPageData {

    public String pageName;
    public Map<String, List<Map<String,String>>> data;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public WikiPageData(Map config, String pageName) throws Exception{
        data = new HashMap();
        this.pageName = pageName;
        
        String pageId = (String)((Map)config.get("test_data_pages")).get(pageName);
        //System.out.println("Wiki page: " + pageId);
        
        HttpResponse<String> response = Unirest
                .get(config.get("wiki_url")
                        +"/wiki/plugins/viewsource/viewpagesrc.action?pageId="
                        +pageId
                    )
                .basicAuth((String)config.get("wiki_username")
                        , ((String)config.get("wiki_password")))
                .asString();
        String contentText = response.getBody();
        Document wikiPage = Jsoup.parse(contentText);
        for(Element section:wikiPage.select("div.innerCell")){
            String name = section.select("h2").text();
            List<Element> rows = section.select("tr");
//            System.out.println("Size of Rows: " + rows.size());
//            for (Element r : rows) {
//            	System.out.println("Row Element: " + r.text());
//            }
            
            /* compute headers */
            List<Element> headers = rows.get(0).select("th");
//           System.out.println("Size of headers: " + headers.size());
//            for (Element h : headers) {
//            	System.out.println("Header Element: " + h.text());
//            }
            
            List<Map<String,String>> dataTable = new ArrayList();
            for(int rownumber = 1; rownumber < rows.size(); rownumber++){
                List<Element> cells = rows.get(rownumber).select("td");
//               System.out.println("Size of Cells: " + cells.size());
//                for (Element c : cells) {
//                	System.out.println("Cell Element: " + c.text());
//                }
                
                Map<String, String> dataRow = new HashMap();
                for(int column = 0; column<cells.size(); column++){
                    dataRow.put(headers.get(column).text(), cells.get(column).text());
                    /*try{
                    	//System.out.println("Header Text  " + headers.get(column).text());
                    	//System.out.println("Cell Text  " + cells.get(column).text());
                    	dataRow.put(headers.get(column).text(), cells.get(column).text());
                    }
                    catch(IndexOutOfBoundsException ex){
                    	//System.out.println("Size of column " + column);
                    	
                    }*/
                }
                dataTable.add(dataRow);
            }
            data.put(name, dataTable);
        }
    }
}
