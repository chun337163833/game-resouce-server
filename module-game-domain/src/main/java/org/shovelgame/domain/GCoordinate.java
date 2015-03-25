package org.shovelgame.domain;

import javax.persistence.Embeddable;

import org.springframework.roo.addon.javabean.RooJavaBean;

@Embeddable
@RooJavaBean
public class GCoordinate {
	private double longitude;
	private double latitude;
}
