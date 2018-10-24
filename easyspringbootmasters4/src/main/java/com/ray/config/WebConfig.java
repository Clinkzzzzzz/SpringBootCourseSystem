/**
 * 
 */
package com.ray.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ray
 *
 */

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/pics/**","/css/**","/js/**","/security/toLogin","/security/login","/security/signup");
	}
}
