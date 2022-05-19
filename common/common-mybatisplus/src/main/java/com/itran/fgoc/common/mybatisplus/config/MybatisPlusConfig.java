package com.itran.fgoc.common.mybatisplus.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * MybatisPlus配置
 * @author
 */
@Configuration
@MapperScan(basePackages = {"com.itran.fgoc.**.mapper"})
public class MybatisPlusConfig {


	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

}
