import ch.qos.logback.classic.Level
import com.il.sod.logging.CustomLayoutWrappingEncoder
import groovy.transform.Field

import static ch.qos.logback.classic.Level.*

@Field String defaultPattern = "[%-5level] %d{yyyy-MM-dd_HH:mm:ss.SSS} %logger{16} {} [%X] - %msg%n"

def addAppenders() {
  Map<String, String> map = new HashMap<>();
  map.put("com.il.sod", "[APP]")
  map.put("ma.glasnost.orika", "[FW]")
  map.put("io.swagger", "[FW]")
  map.put("org.hibernate", "[DB]")

  appender("STDOUT", ConsoleAppender) {
    encoder(CustomLayoutWrappingEncoder) {
      pattern = defaultPattern
      componentTag = map
    }
  }
}

@Field soutLevel = ALL

def addLoggers() {
  logger("org.hibernate.SQL", DEBUG, ["STDOUT"], false)
  logger("org.hibernate.type.descriptor.sql.BasicBinder", TRACE, ["STDOUT"], false)
  logger("com.il.sod", soutLevel as Level, ["STDOUT"], false)
  String[] frameworks = ["ma.glasnost.orika", "io.swagger"]
  for (String fw : frameworks) {
    logger(fw, soutLevel as Level, [], false)
  }

  root(ERROR, ["STDOUT"])
}

addAppenders()
addLoggers()




