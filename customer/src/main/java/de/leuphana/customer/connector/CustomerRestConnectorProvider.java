//package de.leuphana.customer.connector;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import de.leuphana.customer.component.behaviour.CustomerService;
//import de.leuphana.customer.component.structure.Customer;
//
//@RestController
//@RequestMapping("/customers")
//public class CustomerRestConnectorProvider {
//	
//	@Autowired
//    private CustomerService customerService;
//
//    @PostMapping
//    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
//        customerService.saveCustomer(customer);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//}
