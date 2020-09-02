package uk.co.peralabs.cqrs.rest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.peralabs.cqrs.api.commands.CreateOrderCommand;
import uk.co.peralabs.cqrs.api.data.CreateOrderData;
import uk.co.peralabs.cqrs.api.data.OrderData;
import uk.co.peralabs.cqrs.query.api.GetOrdersQuery;
import uk.co.peralabs.cqrs.reader.OrderSummary;

@RestController
public class OrderController {
	//CommandGateway used CommandBus underneath
    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/createOrder")
    public void handle(@RequestBody OrderSummary summary){
        CreateOrderData createOrderData = new CreateOrderData(new OrderData(summary.getId(), summary.getPrice(), summary.getNumber(), summary.getProductId()));
		CreateOrderCommand cmd = new CreateOrderCommand(createOrderData );
        commandGateway.send(cmd);
    }
    
    @GetMapping("/orders")
    public CompletableFuture<List<OrderSummary>> getOrders(){
        return  queryGateway.query(new GetOrdersQuery(), 
                ResponseTypes.multipleInstancesOf(OrderSummary.class));
    }
}
