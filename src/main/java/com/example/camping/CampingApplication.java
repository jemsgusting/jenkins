package com.example.camping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 설치형 tomcat 설정시 추가되는 부분 
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CampingApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CampingApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CampingApplication.class, args);
	}
}

/*
//내장 톰캣 사용할때 사용하는 버전 
@SpringBootApplication
public class CampingApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampingApplication.class, args);
    }
}
*/



