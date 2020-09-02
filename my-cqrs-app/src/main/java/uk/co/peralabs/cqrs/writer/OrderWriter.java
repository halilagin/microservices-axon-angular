package uk.co.peralabs.cqrs.writer;

import org.axonframework.commandhandling.CommandHandler;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import uk.co.peralabs.cqrs.api.commands.CreateOrderCommand;
import uk.co.peralabs.cqrs.api.data.OrderData;
import uk.co.peralabs.cqrs.api.events.OrderCreatedEvent;

@Aggregate
public class OrderWriter {

	OrderData orderData;
	
	
	@AggregateIdentifier
	String orderId;
	
	
	@CommandHandler
	public OrderWriter(CreateOrderCommand cmd ) {
		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(cmd.getCreateOrderData() );
		AggregateLifecycle.apply(orderCreatedEvent);
	}
	
	@EventSourcingHandler
	public void on(OrderCreatedEvent event) {
		
		OrderData newOrderData = event.getCreateOrderData().getOrderData();
		this.orderData = OrderData.builder()
				.id(newOrderData.getId())
				.number(newOrderData.getNumber())
				.price(newOrderData.getPrice())
				.productId(newOrderData.getProductId())
				.build();
		this.orderId = this.orderData.getId();
	}
}
