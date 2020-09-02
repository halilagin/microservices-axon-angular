package uk.co.peralabs.cqrs.rest;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.peralabs.cqrs.api.commands.AddProductCommand;
import uk.co.peralabs.cqrs.api.data.AddProductData;
import uk.co.peralabs.cqrs.api.data.ProductData;
import uk.co.peralabs.cqrs.query.api.GetProductsQuery;
import uk.co.peralabs.cqrs.reader.ProductSummary;

@RestController
public class ProductController {
	private CommandGateway commandGateway;
	private QueryGateway queryGateway;

	public ProductController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}
	
	@PostMapping("/addProduct")
    public void handle(@RequestBody ProductSummary summary) throws JsonProcessingException{
		String productId = new Random().nextInt(1000000000)+"";  //UUID.randomUUID().toString();
        AddProductData productData = new AddProductData(new ProductData(productId, summary.getPrice(),summary.getStock(), summary.getDescription()));
        System.out.printf("/addProduct:%s",new ObjectMapper().writeValueAsString(productData));
		AddProductCommand cmd = new AddProductCommand(productData );
        commandGateway.sendAndWait(cmd);
    }

    @GetMapping("/products")
    public CompletableFuture<List<ProductSummary>> getProducts(){
        return queryGateway.query(new GetProductsQuery(), 
        		ResponseTypes.multipleInstancesOf(ProductSummary.class));
    }
}
