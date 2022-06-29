package com.bsep.device;

import com.bsep.device.service.AlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeviceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DeviceApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(DeviceApplication.class, args);
		LOG.info("Hello world :)");

		AlarmService.generateAlarms();
	}

}
