package indiv.park.starter.loader;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import indiv.park.starter.annotation.Module;
import indiv.park.starter.inheritance.ModuleBase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ModuleLoader {

	private final Map<String, Object> configurationMap;
	private final Class<?> mainClass;

	public ModuleLoader(Map<String, Object> configurationMap, Class<?> mainClass) {
		this.configurationMap = configurationMap;
		this.mainClass = mainClass;
	}

	@SuppressWarnings("all")
	public void load() throws Exception {
		logger.info("모듈 구성을 시작합니다.");
		
		Reflections reflections = new Reflections("indiv.park");
		
		Set<Class<?>> moduleSet = reflections.getTypesAnnotatedWith(Module.class);
		if (moduleSet.size() == 0) {
			logger.info("구성 가능한 모듈이 존재하지 않습니다.");
			return;
		}
		
		showFoundModule(moduleSet);
		
		final String init = "{} 초기화를 시작합니다.";
		final String done = "{} 초기화가 완료되었습니다.";
		
		for (Class<?> clazz : moduleSet) {
			System.out.println();
			logger.info(init, clazz.getSimpleName());
			
			String name = clazz.getAnnotation(Module.class).name();
			
			Field field = clazz.getDeclaredField("INSTANCE");
			field.setAccessible(true);
			
			ModuleBase module = (ModuleBase) field.get(clazz);
			
			if (configurationMap != null) {
				Object configuration = configurationMap.get(name);
				module.setConfiguration(configuration);
			}
			
			module.initialize(mainClass);
			logger.info(done, clazz.getSimpleName());
		}
	}
	
	private void showFoundModule(Set<Class<?>> moduleSet) {
		final String found = "확인된 모듈 : {}";
		
		for (Class<?> clazz : moduleSet) {
			logger.info(found, clazz.getSimpleName());
		}
	}
}