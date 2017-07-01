package com.il.sod.logging;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Created by cesaregb on 4/2/17.
 */
public class CustomPatternLayout extends PatternLayout {

  private PackageNode packageTrie;

  public void setPackageTrie(PackageNode packageTrie) {
    this.packageTrie = packageTrie;
  }

  @Override
  public String doLayout(ILoggingEvent event) {
    StringBuilder builder = new StringBuilder(super.doLayout(event));
    replaceMap(event, builder);
    return builder.toString();
  }

  private void replaceMap(ILoggingEvent event, StringBuilder builder) {
    if (packageTrie != null && packageTrie.getChildren() != null && !packageTrie.getChildren().isEmpty()) {
      String[] loggerNameParts = event.getLoggerName().split("\\.");
      String replaceWith = "";

      int index = 0;
      PackageNode helper = packageTrie;
      while (helper.getChildren().containsKey(loggerNameParts[index])) {
        replaceWith = helper.getChildren().get(loggerNameParts[index]).getReplaceWith();
        helper = helper.getChildren().get(loggerNameParts[index++]);
      }

      String from = "{}";
      index = builder.indexOf(from);
      builder.replace(index, index + from.length(), replaceWith);
    }
  }
}
