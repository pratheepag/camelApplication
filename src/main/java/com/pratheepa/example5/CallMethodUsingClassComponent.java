package com.pratheepa.example5;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CallMethodUsingClassComponent {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("direct:start")
				.to("class:com.pratheepa.example5.MyService?method=doSomething");
			}
			
		});
		context.start();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello");
	}

}
