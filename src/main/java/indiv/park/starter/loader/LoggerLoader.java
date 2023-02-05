package indiv.park.starter.loader;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public final class LoggerLoader {

	public void load(String path) throws JoranException, IOException {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		
		JoranConfigurator logbackConfig = new JoranConfigurator();
		logbackConfig.setContext(loggerContext);
		loggerContext.reset();
		
		try {
			logbackConfig.doConfigure(path);
			
		} catch (JoranException e) {
			try (InputStream defaultFile = getClass().getClassLoader().getResourceAsStream("logback.xml")) {
				logbackConfig.doConfigure(defaultFile);
			}
		}
	}
}