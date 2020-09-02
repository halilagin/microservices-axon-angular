package uk.co.peralabs.cqrs.reader;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummary {

	@Id
	String id;
	Double price;
	Integer stock;
	String description;
	
	
	
	
}
