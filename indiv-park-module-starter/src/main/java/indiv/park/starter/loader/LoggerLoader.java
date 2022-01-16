package indiv.park.starter.loader;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;

public class LoggerLoader {

	private LoggerContext loggerContext;

	public void load(String path) throws JoranException, IOException {
		loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		
		JoranConfigurator log4jconfig = new JoranConfigurator();
		log4jconfig.setContext((Context) loggerContext);
		loggerContext.reset();
		
		try {
			log4jconfig.doConfigure(path);
			
		} catch (JoranException e) {
			InputStream defaultFile = null;
			
			try {
				defaultFile = getClass().getClassLoader().getResourceAsStream("logback.xml");
				log4jconfig.doConfigure(defaultFile);
				
			} finally {
				if (defaultFile != null) {
					defaultFile.close();
				}
			}
		}
	}
	
	public LoggerContext getLoggerContext() {
		return loggerContext;
	}
}
