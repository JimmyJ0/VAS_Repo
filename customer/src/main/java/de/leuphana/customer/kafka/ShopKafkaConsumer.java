package de.leuphana.customer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Customer;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@Service
public class ShopKafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopKafkaConsumer.class);
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private Tracer tracer;
	
	
    public ShopKafkaConsumer(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}


	@KafkaListener(topics = "customer_topic_create", groupId = "shop_group")
    public void consumeCustomer(Customer customer) {
		Span span = tracer.spanBuilder("createCustomer-span").startSpan();
        try(Scope scope = span.makeCurrent()){
            customerService.createCustomer(customer);
        }catch (KafkaException e) {
            LOGGER.error("Error creating customer: {}", customer, e);
        }finally {
			span.end();
		}
    }

    @KafkaListener(topics = "customer_topic_delete", groupId = "shop_group")
    public void deleteCustomer(Integer customerId) {
    	Span span = tracer.spanBuilder("deleteCustomer-span").startSpan();
        try(Scope scope = span.makeCurrent()){
            customerService.deleteCustomer(customerId);
        }catch (KafkaException e) {
        	LOGGER.error("Error deleting customer with id: {}", customerId, e);
        }finally {
			span.end();
		}
    }
}