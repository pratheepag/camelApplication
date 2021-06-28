package com.pratheepa.example3;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerAndConsumerExample {
	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("direct:start")
				.to("seda:end");
			}
			
		});
		context.start();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello World");
		
		ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
		String messages = consumerTemplate.receiveBody("seda:end", String.class);
		System.out.println(messages);
	}
}
