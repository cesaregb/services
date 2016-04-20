package com.il.sod.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.junit.Assert;

import com.il.sod.rest.util.PropertyHandler;

public class BaseTestServices {
	
	protected String getJsonFileContent(String fileName) throws Exception{
		URL path = BaseTestServices.class.getResource("/json/" +  fileName);
		StringBuilder json = new StringBuilder();
		String sCurrentLine;
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(path.getPath()));
			while ((sCurrentLine = br.readLine()) != null) {
				json.append(sCurrentLine);
			}
		} catch (IOException e) {
			Assert.fail("Error: " + e.getMessage());
		} finally {
			if (br != null)
				br.close();
		}
		return json.toString();
	}
	
	protected String getBasepath(){
		String result = "";
		result = PropertyHandler.getInstance().getValue("rest.api.host") 
				+ ":" + PropertyHandler.getInstance().getValue("rest.api.port") 
				+ PropertyHandler.getInstance().getValue("rest.api.basepath");
		
		return result;
	}
}
