package com.zzarit.oreum;

import com.zzarit.oreum.scheduler.service.SynchronizeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OreumApplication {
	public static void main(String[] args) {
		SpringApplication.run(OreumApplication.class, args);

	}
}
