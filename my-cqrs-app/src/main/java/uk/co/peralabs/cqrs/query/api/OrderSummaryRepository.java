package uk.co.peralabs.cqrs.query.api;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.peralabs.cqrs.reader.OrderSummary;

public interface OrderSummaryRepository extends JpaRepository<OrderSummary, String> {

}
