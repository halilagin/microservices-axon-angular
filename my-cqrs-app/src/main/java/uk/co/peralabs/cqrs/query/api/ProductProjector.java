package uk.co.peralabs.cqrs.query.api;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import uk.co.peralabs.cqrs.api.data.ProductData;
import uk.co.peralabs.cqrs.api.data.UpdateStockData;
import uk.co.peralabs.cqrs.api.events.ProductAddedEvent;
import uk.co.peralabs.cqrs.api.events.StockUpdatedEvent;
import uk.co.peralabs.cqrs.reader.ProductSummary;

@Component
public class ProductProjector {

	private final ProductSummaryRepository repository;
	
	public ProductProjector(ProductSummaryRepository repository) {
		this.repository = repository;
	}
	
	@EventHandler
	public void on (ProductAddedEvent event) {
		ProductData productData = event.getAddProductData().getData();
		ProductSummary product = new ProductSummary(productData.getId(), 
				productData.getPrice(), 
				productData.getStock(), 
				productData.getDescription());
		repository.save(product);
	}
	
	@EventHandler
	public void on (StockUpdatedEvent event) {
		UpdateStockData updateStockData = event.getData();
		ProductSummary productSummary = repository.findById(updateStockData.getId()).orElse(null);
		productSummary.setStock(productSummary.getStock() -  updateStockData.getStock());
		repository.save(productSummary);
	}
	
	
	@QueryHandler
	public List<ProductSummary> handle(GetProductsQuery getProductsQuery ){
		return repository.findAll();
		
	}
}
