package com.borun.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class RuangongApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuangongApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
		factory.setMaxFileSize("18MB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("24MB");
		// 设置文件上传路径.
		//factory.setLocation("C:/Users/Qing/Downloads/");
		return factory.createMultipartConfig();
	}
}
