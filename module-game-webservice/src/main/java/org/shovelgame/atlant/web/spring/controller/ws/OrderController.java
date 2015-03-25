package org.shovelgame.atlant.web.spring.controller.ws;


import org.shovelgame.domain.Customer;
import org.shovelgame.domain.Order;
import org.shovelgame.web.spring.ws.direct.controller.RestWebService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("/order")
//@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class OrderController extends RestWebService<Order> {
	
	@Override
	protected Order createNewEntity() {
		Order order = super.createNewEntity();
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		try {
			order.setCustomer((Customer) Customer.findTxUsersByUserName(a.getName()).getSingleResult());
		} catch (Exception e) {
			throw new HttpStatusCodeException(HttpStatus.FORBIDDEN, "Logged user must be type of customer."){};
		}
		
		return order;
	}
}
