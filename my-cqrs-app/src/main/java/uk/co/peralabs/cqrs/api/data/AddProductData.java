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
public class AddProductData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6671987984707587135L;
	ProductData data;
}
