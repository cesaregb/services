import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy
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
def getSystemProperty(String val, String defValue){
	def result = System.getProperty(val)
	if (result != null) return result
	result = (System.getenv(val) != null ) ? System.getenv(val) : defValue;
	return result;
}

@Field String WEBAPP_DIR = getSystemProperty("WEBAPP_DIR", ".")
@Field String baseName = getSystemProperty("baseName", "logFile")

/**
 * Pattern:
	 * %d{yyyy-MM-dd_HH:mm:ss.SSS} - 2017-04-01_14:12:04.757
	 * %-5level - TRACE
	 * %logger{30} - com.oracle.tests.LoggerTest
	 * {} Replace with Log type oriented information
	 * %msg - Logged Message
	 * %n - Breakline
 */
@Field String defaultPattern = "%d{yyyy-MM-dd_HH:mm:ss.SSS} %-5level %logger{90}{} - %msg%n"

/**
 * Add system appenders
 * @return
 */
def addAppenders(){

	// pattern used for the project, this will print between brackets the mdc map used within the app to add context
	def allPattern = defaultPattern.replace("{}", "")
	// pattern used for the project, this will print between brackets the mdc map used within the app to add context
	def appPattern = defaultPattern.replace("{}", " [%mdc]")
	// pattern used for the database info, will add a constant [sql] to the output
	def sqlPattern = defaultPattern.replace("{}", " [sql]")
	// pattern used for the dependencies info, will add a constant [frameworks] to the output
	def dependencyPattern = defaultPattern.replace("{}", " [frameworks]")

	addAppender(1, "STDOUT", allPattern)
	addAppender(1, "APP_STDOUT", appPattern)
	addAppender(1, "SQL_STDOUT", sqlPattern)
	addAppender(1, "FW_STDOUT", dependencyPattern)


	addAppender(2, "SQL_FILE", sqlPattern)
	addAppender(2, "APP_FILE", appPattern)
	addAppender(2, "FW_FILE", dependencyPattern)
}

def addAppender(int type, String name, String _pattern){
	if (type == 1){
		appender(name, ConsoleAppender) {
			encoder(PatternLayoutEncoder) {
				pattern = _pattern
			}
		}
	}else{
		appender(name, RollingFileAppender) {
			file = "${WEBAPP_DIR}/log/${baseName}.log"
			encoder(PatternLayoutEncoder) {
				pattern = _pattern
			}
			rollingPolicy(FixedWindowRollingPolicy) {
				FileNamePattern = "${WEBAPP_DIR}/log/${baseName}.%i.log.zip"
				minIndex = 1
				maxIndex = 9
			}
			triggeringPolicy(SizeBasedTriggeringPolicy) {
				maxFileSize = "20MB"
			}
			append = true
		}
	}
}


/**
 * Logic to set the Logging (Loggin natural) LEVEL to sout
 */
def soutLevelVal = getSystemProperty("soutLevelVal", "ALL")
println "soutLevelVal: " + soutLevelVal
@Field soutLevel = ALL
switch (soutLevelVal) {
	case "ERROR": soutLevel = ERROR; break
	case "WARN": soutLevel = WARN; break
	case "INFO": soutLevel = INFO; break
	case "DEBUG": soutLevel = DEBUG; break
	case "TRACE": soutLevel = TRACE; break
	default: soutLevel = ALL
}

println "soutLevel: " + soutLevel.toString()

/**
 * Logic to set the type of applications logging to sout
 * 1 = all (default)
 * 2 = app + sql (jpa)
 * 3 = app
 */
@Field int stoutType = Integer.valueOf( getSystemProperty("stoutType", "1") )

def addLoggers(){
	logger("com.il.sod", ALL, ["APP_FILE"], true)
	logger("org.hibernate.SQL", ALL, ["SQL_FILE"], true)
	logger("org.hibernate.type.descriptor.sql.BasicBinder", TRACE, ["SQL_FILE"], true)


	logger("com.il.sod", soutLevel, ["APP_STDOUT"], false)

	if ( stoutType < 3 ) {
		logger("org.hibernate.SQL", soutLevel, ["SQL_STDOUT"], false)
		logger("org.hibernate.type.descriptor.sql.BasicBinder", soutLevel, ["SQL_STDOUT"], false)
	}

	if ( stoutType == 1 ){
		String[] frameworks = ["ma.glasnost.orika", "io.swagger", "org.springframework"]
		for (String fw : frameworks ){
			logger(fw, soutLevel, ["FW_STDOUT"], false)
		}
	}


	root(OFF, ["STDOUT"])
}

addAppenders()
addLoggers()




