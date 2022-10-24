package com.gsww.qyws.gzbd.config;

import com.gsww.qyws.gzbd.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurationSupport  {
	@Value("${server.servlet.context-path}")
	private String contextPath;
	private static final String CONTENTTYPE = "text/html; charset=UTF-8";
	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(swaggerEnabled).groupName(contextPath + "/").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.gsww.qyws.gzbd.rest")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs")
				.termsOfServiceUrl("http://127.0.0.1:8888/gzbd-jkzx/").contact("冠状病毒监测中心v1.0").version("1.0").build();
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(true).parameterName("mediaType").defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML).mediaType("html", MediaType.TEXT_HTML)
				.mediaType("json", MediaType.APPLICATION_JSON);
	}


	public static String LOGIN_USER = "loginUser";
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		System.out.println("验证");
		//registry.addViewController("/").setViewName("redirect:/login/jumpLogin");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

	/**
	 * 配置拦截器
	 * @author lance
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器
		InterceptorRegistration ir = registry.addInterceptor(new UserLoginInterceptor());
		// 配置拦截的路径
		ir.addPathPatterns("/**");
		// 配置不拦截的路径
		ir.excludePathPatterns("/");
		ir.excludePathPatterns("/swagger-ui.html");
	//	ir.excludePathPatterns("/yqjk/**");
		ir.excludePathPatterns("/login").excludePathPatterns("/login/*");
		ir.excludePathPatterns("/sso").excludePathPatterns("/loginToSystem");
		// springboot2.x 会拦截静态资源
		ir.excludePathPatterns(Arrays.asList("/dist/**", "/static/dist/business/**"));
	}
	/**
	 * 静态资源加载配置
	 *不拦截
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
