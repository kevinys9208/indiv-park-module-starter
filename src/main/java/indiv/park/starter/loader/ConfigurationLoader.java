package indiv.park.starter.loader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import indiv.park.starter.module.PropertyPool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ConfigurationLoader {

	public Map<String, Object> load() throws IOException {
		logger.info("설정 정보를 로드합니다.");
		
		String configPath = System.getProperty("user.dir") + File.separator + "config";
		
		initialize(configPath);
		PropertyPool.loadProperties(configPath);

		return loadConfiguration(configPath);
	}
	
	private void initialize(String configPath) throws IOException {
		File config = new File(configPath);
		if (!config.exists()) {
			logger.info("초기 환경을 구성합니다.");

			if (config.mkdir()) {
				logger.info("modules.yml 파일을 생성합니다.");
				createApplicationYAML(new File(configPath +  File.separator + "module.yml"));
			}

			logger.info("프로세스를 재시작해 주시기 바랍니다.");
			System.exit(0);
		}
	}
	
	private Map<String, Object> loadConfiguration(String configPath) throws IOException {
		FileInputStream fileInputStream = null;
		Map<String, Object> configuration;

		try {
			File yml = new File(configPath + File.separator + "module.yml");
			if (!yml.exists()) {
				logger.info("module.yml 파일을 생성합니다.");
				createApplicationYAML(new File(configPath + File.separator + "module.yml"));
				
				logger.info("프로세스를 재시작해 주시기 바랍니다.");
				System.exit(0);
			}
			
			fileInputStream = new FileInputStream(yml);
			configuration 	= new Yaml().load(fileInputStream);
			
			System.out.println();

			return configuration;
			
		} finally {
			if (fileInputStream != null)	fileInputStream.close();
		}
	}

	private void createApplicationYAML(File file) throws IOException {
		BufferedWriter writer 	= null;
		OutputStreamWriter os 	= null;
		FileOutputStream fos	= null;

		try {
			if (file.createNewFile()) {
				fos = new FileOutputStream(file, false);
				os = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				writer = new BufferedWriter(os);

				writer.write("# 모듈을 구성하기 위한 설정 정보는 리스트의 형태로 작성되어야 합니다.\r\n");
				writer.write("#\r\n");
				writer.write("# server: (type: tcp, http)\r\n");
				writer.write("#  - { group: string, type: string, port: int, bossThread: int,  workerThread: int }...\r\n");
				writer.write("#\r\n");
				writer.write("# client: (type: tcp, http)\r\n");
				writer.write("#  - { group: string, type: string, workerThread: int }...\r\n");
				writer.write("#\r\n");
				writer.write("# database: (type: oracle, tibero)\r\n");
				writer.write("#  - { name: string, type: string, ip: string, port: int, sid: string, user: string, password: string }...");

				writer.flush();
			}

		} finally {
			if (writer 	!= null)	writer.close();
			if (os 		!= null)	os.close();
			if (fos 	!= null) 	fos.close();
		}
	}
}