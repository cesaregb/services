import ch.qos.logback.classic.Level
import com.il.sod.logging.CustomLayoutWrappingEncoder
import groovy.transform.Field

import static ch.qos.logback.classic.Level.*

/**
 * Get configuration property. Selection order:
 *  1 Java Property,
 *  2 Env config prop
 *  3 Default val
 * @param val
 * @param defValue
 * @return
 */
def getSystemProperty(String val, String defValue) {
  def result = System.getProperty(val)
  if (result != null) return result
  result = (System.getenv(val) != null) ? System.getenv(val) : defValue;
  return result;
}

@Field String WEBAPP_DIR = getSystemProperty("WEBAPP_DIR", ".")
@Field String baseName = getSystemProperty("baseName", "logFile")

/**
 * Pattern:
 * %d{yyyy-MM-dd_HH:mm:ss.SSS} - 2017-04-01_14:12:04.757
 * %-5level - TRACE
 * %logger{30} - com.oracle.tests.LoggerTest
 *{} Replace with Log type oriented information
 * %msg - Logged Message
 * %n - Breakline
 */
@Field String defaultPattern = "[%-5level] %d{yyyy-MM-dd_HH:mm:ss.SSS} %logger{16} {} [%X] - %msg%n"

/**
 * Add system appenders
 * @return
 */
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
  appender("ROLLING_FILE", RollingFileAppender) {
    file = "${WEBAPP_DIR}/log/${baseName}.log"
    encoder(CustomLayoutWrappingEncoder) {
      pattern = defaultPattern
      componentTag = map
    }
    rollingPolicy(FixedWindowRollingPolicy) {
      FileNamePattern = "${WEBAPP_DIR}/log/${baseName}.%i.log.zip"
      minIndex = 1
      maxIndex = 100
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
      maxFileSize = "30MB"
    }
    append = true
  }
}

/**
 * Logic to set the Logging (Loggin natural) LEVEL to sout
 */
def soutLevelVal = getSystemProperty("soutLevelVal", "ALL")
@Field soutLevel = ALL
switch (soutLevelVal) {
  case "ERROR": soutLevel = ERROR; break
  case "WARN": soutLevel = WARN; break
  case "INFO": soutLevel = INFO; break
  case "DEBUG": soutLevel = DEBUG; break
  case "TRACE": soutLevel = TRACE; break
  default: soutLevel = ALL
}

/**
 * Logic to set the type of applications logging to sout
 * 1 = all (default)
 * 2 = app + sql (jpa)
 * 3 = app
 */
@Field int stoutType = Integer.valueOf(getSystemProperty("stoutType", "3"))

def addLoggers() {
  def sqlAppenders = ["ROLLING_FILE"];
  if (stoutType < 3) {
    sqlAppenders.add("STDOUT")
  }
  logger("org.hibernate.SQL", DEBUG, sqlAppenders, false)
  logger("org.hibernate.type.descriptor.sql.BasicBinder", TRACE, sqlAppenders, false)

  logger("com.il.sod", soutLevel as Level, ["STDOUT", "ROLLING_FILE"], false)

  def fwAppenders = ["ROLLING_FILE"]
  if (stoutType == 1) {
    fwAppenders.add("STDOUT")
  }

  String[] frameworks = ["ma.glasnost.orika", "io.swagger"]
  for (String fw : frameworks) {
    logger(fw, soutLevel as Level, fwAppenders, false)
  }

  root(ERROR, ["STDOUT"])
}

addAppenders()
addLoggers()




