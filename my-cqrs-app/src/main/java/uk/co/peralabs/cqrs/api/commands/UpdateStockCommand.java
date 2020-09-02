package uk.co.peralabs.cqrs.api.commands;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.peralabs.cqrs.api.data.UpdateStockData;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UpdateStockCommand   {
	
	UpdateStockData data;
	
	public String getId() {
		return data.getId();
	}
}
