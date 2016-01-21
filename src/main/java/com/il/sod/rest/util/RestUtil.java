package com.il.sod.rest.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RestUtil {

	public static void addLikeFilter(Map<String, Object> filterMap, String filterName, String filterValue) {
		if (filterValue == null) {
			return;
		}
		
		filterValue = filterValue.replaceAll("\\*", "%");
		filterMap.put("LIKE " + filterName, filterValue);
	}
	
	public static String getContentFromInputStream(InputStream is) {
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	
	public static Map<String, String> getSortMap(String sortAttr) {
		if (sortAttr == null) {
			return null;
		}
		Map<String, String> sortMap = new HashMap<String, String>();
		String sortType = sortAttr.startsWith("-") ? "DESC" : "ASC";
		sortMap.put(sortAttr.substring(1), sortType);
		return sortMap;
	}
	
	public static int getStartIndex(String rangeParam) {
		if (rangeParam == null) {
			return 0;
		}
		return getRangeFromParam(rangeParam)[0];
	}
	
	public static int getPageSize(String rangeParam) {
		if (rangeParam == null) {
			return 0;
		}
		int[] range = getRangeFromParam(rangeParam);
		return (range[1] - range[0]) + 1;
	}
	
	private static int[] getRangeFromParam(String rangeParam) {
		String onlyRange = 
				rangeParam.substring(rangeParam.indexOf("=") + 1, rangeParam.length());
		String[] rangeArr = onlyRange.split("-");
		return new int[] { 
			Integer.parseInt(rangeArr[0]), Integer.parseInt(rangeArr[1]) 
		};
	}
}
