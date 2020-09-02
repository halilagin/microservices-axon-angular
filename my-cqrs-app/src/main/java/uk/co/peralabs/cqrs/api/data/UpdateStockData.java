package uk.co.peralabs.cqrs.api.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockData  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5657503702171388685L;
	String id;
	Integer stock;
}
