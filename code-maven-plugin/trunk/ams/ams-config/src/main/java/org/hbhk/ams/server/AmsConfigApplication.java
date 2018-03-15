package org.hbhk.ams.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableConfigServer
@SpringBootApplication
@EnableEurekaClient
public class AmsConfigApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(AmsConfigApplication.class).web(true).run(args);
	}
}