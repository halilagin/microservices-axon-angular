package uk.co.peralabs.cqrs.reader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class OrderSummary {
	@Id
	String id;
	
	Double price;
	Integer number;
	Integer productId;
	
	@ManyToOne
	@JoinColumn(name="productId", updatable = false, insertable=false)
	
	ProductSummary productSummary;
	
	
	public OrderSummary(String id,Double price, Integer number, Integer productId) {
		this.id = id;
		this.price = price;
		this.number = number;
		this.productId = productId;
	}
}
