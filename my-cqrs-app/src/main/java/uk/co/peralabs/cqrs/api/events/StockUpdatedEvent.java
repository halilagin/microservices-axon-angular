package uk.co.peralabs.cqrs.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.co.peralabs.cqrs.api.data.UpdateStockData;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdatedEvent   {
	UpdateStockData data;
}
