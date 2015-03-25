package org.shovelgame.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.shovelgame.domain.annotation.EntityInterface;
import org.shovelgame.domain.common.JsonObject;
import org.shovelgame.domain.modification.Modifiable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(persistenceUnit = "puMainDB", table="tx_order")
@Modifiable
@EntityInterface
public class Order implements JsonObject {
	private static final long serialVersionUID = 8806818437728674845L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar pickupTime;
	
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private CapacityType capacityType;
	
	@Size(max=255)
	private String boardingPlace;
	
	private GCoordinate boardingCoordinate;

	@Size(max=255)
	private String exitPlace;
	
	@AttributeOverrides({
		@AttributeOverride(name="latitude", column=@Column(name="ex_latitude")),
		@AttributeOverride(name="longitude", column=@Column(name="ex_longitude"))
		})
	private GCoordinate exitCoordinate;
	
	/**
	 * Fixed price for fare set by dispatcher
	 */	
	private BigDecimal fixedPrice;
	
	/**
	 * Charge for delivery (poplatek za pristaveni)
	 */
	private BigDecimal deliveryFee;
	
	@Lob
	private String note;
	
	private Boolean displayCustomer;
	
	/**
	 * send offer for registration to customer via SMS 
	 */
	private Boolean noRegistrationOffer;
	
	private PaymentType paymentType;
	
	@Enumerated(EnumType.ORDINAL)
	private OrderState orderState;
	
	private Integer routeLength;
	
	private BigDecimal fare;
	
	@Lob
	private String driverNote;
	
	private Double approxRouteLength;
	
	private Integer approxRouteTimeInMin;
	
	private BigDecimal approxPrice;
	
	private Boolean arrivalSmsSent;
	
	private Boolean basicDiscountPaid;
	
	private Boolean streetJob;
	
	@ManyToOne
	private Driver driver;
	
	@ManyToOne
	@NotNull
	private Customer customer;
}
