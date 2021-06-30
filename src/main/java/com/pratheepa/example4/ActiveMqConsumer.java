package com.pratheepa.example4;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ActiveMqConsumer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CamelContext context = new DefaultCamelContext();
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		context.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("activemq:queue:my_queue")
				.to("seda:end");
			}
			
		});
		
		context.start();
		ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
		String messages = consumerTemplate.receiveBody("seda:end", String.class);
		System.out.println(messages);
	}

}
