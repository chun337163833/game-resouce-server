package org.shovelgame.atlant.web.spring.controller.ws.v1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shovelgame.atlant.json.wrapper.v1.CustomerWrapper;
import org.shovelgame.domain.Customer;
import org.shovelgame.domain.Role;
import org.shovelgame.environment.Environment;
import org.shovelgame.environment.EnvironmentType;
import org.shovelgame.web.spring.ws.datatype.FilteredListJson;
import org.shovelgame.web.spring.ws.exception.MethodNotAllowedException;
import org.shovelgame.web.spring.ws.wrapper.controller.AnnotationMappingWebServiceWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/registration")
public class CustomerControllerWrapper extends AnnotationMappingWebServiceWrapper<Customer, CustomerWrapper> {

	@EnvironmentType(value = { Environment.DEVELOPMENT }, exception = MethodNotAllowedException.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = { "content-type=application/json" })
	@Override
	public CustomerWrapper _update(Customer entity, Errors errors) {
		return super._update(entity, errors);
	}

	@EnvironmentType(value = { Environment.DEVELOPMENT }, exception = MethodNotAllowedException.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Override
	public CustomerWrapper _delete(Customer data, Errors errors) {
		return super._delete(data, errors);
	}

	@EnvironmentType(value = { Environment.DEVELOPMENT }, exception = MethodNotAllowedException.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Override
	public FilteredListJson list(HttpServletRequest request, HttpServletResponse response) {
		return super.list(request, response);
	}

	@EnvironmentType(value = { Environment.DEVELOPMENT }, exception = MethodNotAllowedException.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Override
	public CustomerWrapper detail(Customer entity) {
		return super.detail(entity);
	}

	@Override
	protected Customer createNewEntity() {
		Customer c = super.createNewEntity();
		c.getRoles().add(Role.findRole(2L));
		return c;
	}
}
