package com.pratheepa.example5;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CallMethodUsingClassBeanComponent {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		MyService myService = new MyService();
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("myService", myService);
		
		CamelContext context = new DefaultCamelContext(registry);
		
		context.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("direct:start")
				.to("bean:myService?method=doSomething");
			}
			
		});
		context.start();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello");
	}

}
