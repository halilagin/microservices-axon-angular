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
public class ProductData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7646545283553396848L;
	String id;
	Double price;
	Integer stock;
	String description;
}
