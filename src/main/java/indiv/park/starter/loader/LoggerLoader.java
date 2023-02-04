package indiv.park.starter.loader;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;

public final class LoggerLoader {

	private LoggerContext loggerContext;

	public void load(String path) throws JoranException, IOException {
		loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		
		JoranConfigurator logbackConfig = new JoranConfigurator();
		logbackConfig.setContext(loggerContext);
		loggerContext.reset();
		
		try {
			logbackConfig.doConfigure(path);
			
		} catch (JoranException e) {
			InputStream defaultFile = null;
			
			try {
				defaultFile = getClass().getClassLoader().getResourceAsStream("logback.xml");
				
				logbackConfig.doConfigure(defaultFile);
				
			} finally {
				if (defaultFile != null) {
					defaultFile.close();
				}
			}
		}
	}
}