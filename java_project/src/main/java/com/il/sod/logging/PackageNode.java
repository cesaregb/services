package com.il.sod.logging;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to
 * Created by cesaregb on 4/2/17.
 */
public class PackageNode {
	private String subPackage;
	private String replaceWith;
	private Map<String, PackageNode> children = new HashMap<>();

	public PackageNode(String subPackage, String replaceWith){
		this.subPackage = subPackage;
		this.replaceWith = replaceWith;
	}

	public String getSubPackage() {
		return subPackage;
	}

	public void setSubPackage(String subPackage) {
		this.subPackage = subPackage;
	}

	public String getReplaceWith() {
		return replaceWith;
	}

	public void setReplaceWith(String replaceWith) {
		this.replaceWith = replaceWith;
	}

	public Map<String, PackageNode> getChildren() {
		return children;
	}

	public void setChildren(Map<String, PackageNode> children) {
		this.children = children;
	}
}
