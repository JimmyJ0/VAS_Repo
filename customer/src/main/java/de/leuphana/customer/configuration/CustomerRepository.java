package de.leuphana.customer.configuration;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.leuphana.customer.component.structure.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{


}
