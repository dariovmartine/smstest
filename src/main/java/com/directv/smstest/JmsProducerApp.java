
package com.directv.smstest;

import java.io.IOException;
import java.sql.SQLException;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.jms.support.converter.oracle.MappingAdtMessageConverter;
import org.springframework.data.jdbc.jms.support.oracle.StructDatumMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.transaction.PlatformTransactionManager;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jms.AQjmsConnectionFactory;

@SpringBootApplication
@EnableJms
public class JmsProducerApp {
	
	static final Logger logger = LoggerFactory.getLogger(JmsProducerApp.class);
	
	@Bean(name = "dataSourceAQ")
	public OracleDataSource getDataSource() throws SQLException {
		
		OracleDataSource dataSource = new OracleDataSource();
		
		dataSource.setURL("jdbc:oracle:thin:@//rgmau1-scan.dtvdev.net:1521/shitxu1");
		dataSource.setUser("jmsuser");
		dataSource.setPassword("jmsuser_qam");

		return dataSource;
	}	
	
	@Bean(name = "aQjmsConnectionFactory")
	public AQjmsConnectionFactory getAQjmsConnectionFactory(
			@Qualifier("dataSourceAQ") DataSource  dataSource) throws JMSException {
		
		AQjmsConnectionFactory factory = new AQjmsConnectionFactory();
		factory.setDatasource(dataSource);
		return factory;
	} 
	
	@Bean(name = "aQSmsPostMessageRequestStructDatumMapper")
	public StructDatumMapper<AQSmsPostMessageRequest> getAQSmsPostMessageRequestStructDatumMapper() {
		 StructDatumMapper<AQSmsPostMessageRequest> ret = new  StructDatumMapper<AQSmsPostMessageRequest> (
				 "SMSTYPE",
				 AQSmsPostMessageRequest.class); 
		 return ret;
	}
  
	@Bean(name = "aQSmsPostMessageRequestMessageConverter")
	public MappingAdtMessageConverter getAQSmsPostMessageRequestMessageConverter(
			@Qualifier("aQSmsPostMessageRequestStructDatumMapper") StructDatumMapper<AQSmsPostMessageRequest> structDatumMapper) {
		return new MappingAdtMessageConverter(structDatumMapper);
	}
	
 	@Bean(name = "addcustomerJMSTemplate")
 	public JmsTemplate getJmsTemplate(
 			@Qualifier("aQjmsConnectionFactory") ConnectionFactory connectionFactory,
 			@Qualifier("aQSmsPostMessageRequestMessageConverter") MessageConverter messageConverter) {
 		
 		JmsTemplate ret = new JmsTemplate(); 
 		ret.setConnectionFactory(connectionFactory);
        ret.setMessageConverter(messageConverter);
        return ret;
 	}  
 	
 	@Bean
 	PlatformTransactionManager transactionManager(@Qualifier("dataSourceAQ") DataSource  dataSource) {
 		return  new DataSourceTransactionManager(dataSource);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(JmsProducerApp.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        System.out.print("ENTER para enviar mensajes:");
        //System.in.read();
        AQSmsPostMessageRequest req = new AQSmsPostMessageRequest();
        for (int i=0; i < 3; i++) {
        	
        	jmsTemplate.convertAndSend("solveext721_queue", req);
        	logger.info("Enviando mensaje a la cola");
        }
        System.out.print("ENTER para terminar");
        System.in.read();
    }

}
