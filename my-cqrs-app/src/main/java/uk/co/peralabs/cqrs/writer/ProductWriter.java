package uk.co.peralabs.cqrs.writer;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import uk.co.peralabs.cqrs.api.commands.AddProductCommand;
import uk.co.peralabs.cqrs.api.commands.UpdateStockCommand;
import uk.co.peralabs.cqrs.api.data.ProductData;
import uk.co.peralabs.cqrs.api.events.ProductAddedEvent;
import uk.co.peralabs.cqrs.api.events.StockUpdatedEvent;

@Aggregate
public class ProductWriter {
	ProductData productData ;
	
	
	@AggregateIdentifier
	public String productId;
	
	
	public ProductWriter() {
		this.productData = new ProductData();
	}
	
	@CommandHandler
	public ProductWriter(AddProductCommand cmd ) {
		ProductAddedEvent productAddedEvent = new ProductAddedEvent(cmd.getData() );
		AggregateLifecycle.apply(productAddedEvent);
	}
	
	
	@CommandHandler
	public void handle(UpdateStockCommand cmd) {
		if (cmd.getData().getStock() > this.productData.getStock()) 
			return;
		StockUpdatedEvent stockUpdatedvent = new StockUpdatedEvent(cmd.getData() );
		AggregateLifecycle.apply(stockUpdatedvent);		
	}
	
	@EventSourcingHandler
	public void on(StockUpdatedEvent event) {
		this.productData = ProductData.builder()
				.id(event.getData().getId())
				.stock(event.getData().getStock())
				.build();
		this.productId = this.productData.getId();
	}
	
	@EventSourcingHandler
	public void on(ProductAddedEvent event) {
		
		ProductData newProductData = event.getAddProductData().getData();
		this.productData = ProductData.builder()
				.id(newProductData.getId())
				.stock(newProductData.getStock())
				.description(newProductData.getDescription())
				.price(newProductData.getPrice())
				.build();
		
		System.out.println(String.format("ProductAddedEvent: %s, %s, %d, %f",newProductData.getId(),newProductData.getDescription(),newProductData.getStock(), newProductData.getPrice() ));
		this.productId = this.productData.getId();

		
	
	}
}
