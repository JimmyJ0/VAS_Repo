package de.leuphana.customer.configuration;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.customer.component.structure.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
