package org.shovelgame.atlant.web.spring.controller.ws.v1;

import org.shovelgame.atlant.json.wrapper.v1.OrderWrapper;
import org.shovelgame.domain.Customer;
import org.shovelgame.domain.Order;
import org.shovelgame.web.spring.ws.wrapper.controller.AnnotationMappingWebServiceWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("/v1/order")
public class OrderControllerWrapper extends AnnotationMappingWebServiceWrapper<Order, OrderWrapper> {

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
