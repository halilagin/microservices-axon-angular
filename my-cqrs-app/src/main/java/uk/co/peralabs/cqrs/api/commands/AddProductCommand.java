package uk.co.peralabs.cqrs.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.co.peralabs.cqrs.api.data.AddProductData;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AddProductCommand {
	AddProductData data;
	
	@TargetAggregateIdentifier
	public String getId() {
		return data.getData().getId();
	}
	
}
