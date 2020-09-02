package uk.co.peralabs.cqrs.query.api;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import uk.co.peralabs.cqrs.api.data.OrderData;
import uk.co.peralabs.cqrs.api.events.OrderCreatedEvent;
import uk.co.peralabs.cqrs.reader.OrderSummary;

@Component
public class OrderProjector {

	private final OrderSummaryRepository repository;
	private EventGateway eventGateway;
	
	public OrderProjector(OrderSummaryRepository repository,EventGateway eventGateway) {
		this.repository = repository;
		this.eventGateway = eventGateway;
	}
	
	@EventHandler
	public void on (OrderCreatedEvent event) {
		OrderData orderData = event.getCreateOrderData().getOrderData();
		
		OrderSummary order = new OrderSummary(orderData.getId(),orderData.getPrice(), orderData.getNumber(), orderData.getProductId());
		repository.save(order);
	}
	
//	@EventHandler
//	public void on (StockUpdatedEvent event) {
//		UpdateStockData updateStockData = event.getData();
//		ProductSummary productSummary = repository.findById(updateStockData.getId()).orElse(null);
//		productSummary.setStock(productSummary.getStock() -  updateStockData.getStock());
//		repository.save(productSummary);
//	}
	
	
	@QueryHandler
	public List<OrderSummary> handle(GetOrdersQuery getOrdersQuery ){
		return repository.findAll();
		
	}
}
