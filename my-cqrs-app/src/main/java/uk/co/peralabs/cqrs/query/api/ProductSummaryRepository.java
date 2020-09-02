package uk.co.peralabs.cqrs.query.api;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.peralabs.cqrs.reader.ProductSummary;

public interface ProductSummaryRepository extends JpaRepository<ProductSummary, String> {

}
