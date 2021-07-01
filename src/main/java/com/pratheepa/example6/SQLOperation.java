package com.pratheepa.example6;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class SQLOperation {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/camel_tutorial");
		dataSource.setUser("root");
		dataSource.setPassword("admin");
		
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("myDataSource", dataSource);
		
		ResultHandler resultHandler = new ResultHandler();
		
		CamelContext context = new DefaultCamelContext(registry);
		
		context.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("direct:start")
				.to("jdbc:myDataSource")
				.bean(new ResultHandler(), "printResult");
			}
			
		});
		context.start();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "select * from customer");
	}

}
