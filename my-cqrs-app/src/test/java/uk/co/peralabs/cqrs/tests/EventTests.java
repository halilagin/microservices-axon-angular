package uk.co.peralabs.cqrs.tests;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.co.peralabs.cqrs.api.commands.AddProductCommand;
import uk.co.peralabs.cqrs.api.data.AddProductData;
import uk.co.peralabs.cqrs.api.data.ProductData;
import uk.co.peralabs.cqrs.api.events.ProductAddedEvent;
import uk.co.peralabs.cqrs.writer.ProductWriter;


public class EventTests {

	private AggregateTestFixture<ProductWriter> testFixture;

	@BeforeEach
	void setUp() {
		testFixture = new AggregateTestFixture<ProductWriter>(ProductWriter.class);
	}

	@Test
	void testCreateChatRoom() {
		ProductData productData = new ProductData("product1",101.0, 10, "prodcut1");
		AddProductData addProductData = new AddProductData(productData);
		AddProductCommand addProductCommand = new AddProductCommand(addProductData);
		ProductAddedEvent productAddedEvent = new ProductAddedEvent(addProductData);
		String product1 = productAddedEvent.getAddProductData().getData().getId();
		String product1_ = addProductCommand.getData().getData().getId();
		System.out.println(String.format("%s=%s", product1, product1_));
		System.out.println(product1.equals(product1_));
		testFixture.givenNoPriorActivity().when(addProductCommand)
				.expectEvents(productAddedEvent);
				
	}

}