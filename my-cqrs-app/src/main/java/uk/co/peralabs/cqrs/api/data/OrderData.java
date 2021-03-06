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
public class OrderData  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -570336108336149928L;
	String id;
	Double price;
	Integer number;
	Integer productId;
	
}
