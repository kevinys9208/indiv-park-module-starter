package indiv.park.starter;

import java.util.Map;

import indiv.park.starter.loader.ConfigurationLoader;
import indiv.park.starter.loader.LoggerLoader;
import indiv.park.starter.loader.LogoLoader;
import indiv.park.starter.loader.ModuleLoader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ModuleStarter {
	
	private ModuleStarter() {}

	public static void start(Class<?> mainClass) {
		try {
			LogoLoader.showLogo();
			
			LoggerLoader loggerLoader = new LoggerLoader();
			loggerLoader.load("config/logback.xml");
			
			ConfigurationLoader configurationLoader = new ConfigurationLoader();
			Map<String, Object> configurationMap = configurationLoader.load();

			ModuleLoader moduleLoader = new ModuleLoader(configurationMap, mainClass);
			moduleLoader.load();
			
			LogoLoader.showStart();

		} catch (Exception e) {
			logger.error("모듈을 구성하던 중 예외가 발생하였습니다. [ {} ]", e.toString());
			System.exit(0);
		}
	}
}
