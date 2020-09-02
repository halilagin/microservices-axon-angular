package uk.co.peralabs.cqrs.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.co.peralabs.cqrs.api.data.CreateOrderData;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand   {
	CreateOrderData createOrderData;
	
	@TargetAggregateIdentifier
	public String getId() {
		return createOrderData.getOrderData().getId();
	}
	
	
}
