package com.dacky;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.dacky.filter.Post;
import com.dacky.filter.Pre;
import com.dacky.filter.Route;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}

	@Bean
	public Pre preFilter() {
		return new Pre();
	}

	@Bean
	public Post postFilter() {
		return new Post();
	}

	@Bean
	public com.dacky.filter.Error errorFilter() {
		return new com.dacky.filter.Error();
	}

	@Bean
	public Route routeFilter() {
		return new Route();
	}
}
