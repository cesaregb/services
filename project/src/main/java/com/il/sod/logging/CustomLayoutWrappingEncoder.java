package com.il.sod.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;

import java.util.Map;

/**
 * Created by cesaregb on 4/2/17.
 * "clone" class of ch.qos.logback.classic.encoder.PatternLayoutEncoder, utilize logic for layout, add tag oriented information.
 */
public class CustomLayoutWrappingEncoder extends PatternLayoutEncoderBase<ILoggingEvent> {
	private Map<String, String> componentTag;
	public void setComponentTag(Map<String, String> componentTag) {
		this.componentTag = componentTag;
	}
	private PackageNode root;

	public CustomLayoutWrappingEncoder() { }

	@Override
	public void start() {
		createMap();

		CustomPatternLayout patternLayout = new CustomPatternLayout();
		patternLayout.setContext(this.context);
		patternLayout.setPackageTrie(root);
		patternLayout.setPattern(this.getPattern());
		patternLayout.setOutputPatternAsHeader(this.outputPatternAsHeader);
		patternLayout.start();
		this.layout = patternLayout;
		super.start();
	}

	private void createMap(){
		if (componentTag == null || componentTag.isEmpty()){
			return;
		}
		root = new PackageNode("0","");
		for (Map.Entry<String, String> entry : componentTag.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			String[] parts = key.split("\\.");
			createTrie(root, parts, 0, value);
		}
	}

	private void createTrie(PackageNode root, String[] parts, int index, String replace){
		if (index == parts.length){
			return;
		}
		String val = parts[index];
		if (!root.getChildren().containsKey(val)){
			PackageNode n = new PackageNode(val, replace);
			root.getChildren().put(val, n);
		}
		createTrie(root.getChildren().get(val), parts, index + 1, replace);
	}

}
