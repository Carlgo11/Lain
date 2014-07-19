package com.carlgo11.lain;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;
import java.util.ArrayList;

public class WASearch {
 // PUT YOUR APPID HERE:
    private static String appid = "YQ3UVU-P4X3AER34J";

    public static ArrayList<String> search(String args) {

        ArrayList<String> d = new ArrayList<String>();
        // Use "pi" as the default query, or caller can supply it as the lone command-line argument.
        String input = "pi";

            input = args;

        WAEngine engine = new WAEngine();

        engine.setAppID(appid);

        WAQuery query = engine.createQuery();

        query.setInput(input);
        
        try {

            WAQueryResult queryResult = engine.performQuery(query);
            
            if (queryResult.isError()) {
                d.add("Query error");
                d.add("  error code: " + queryResult.getErrorCode());
                d.add("  error message: " + queryResult.getErrorMessage());
            } else if (!queryResult.isSuccess()) {
                StringBuilder a = new StringBuilder();
                String[] b = queryResult.getDidYouMeans();
                for(int i = 0; i < b.length; i++){
                    a.append(b[i]);
                    a.append(", ");
                }
                d.add("Did you mean: "+a.toString()+"?");
            } else {
                int g = 0;
                for (WAPod pod : queryResult.getPods()) {
                    if(g>2){
                        break;
                    }
                    if (!pod.isError()) {
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText) {
                                    d.add(((WAPlainText) element).getText());
                                    d.add("");
                                }
                            }
                        }
                    }
                    g++;
                }
            }
        } catch (WAException e) {
            e.printStackTrace();
        }
        return d;
    }

}
