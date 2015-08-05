package org.shovelgame.game.domain.enumeration;

public enum PlayerResource {

	Gold(new ResourceGeneratorData(500, 1500, 8.5)), 
	Diamond(new ResourceGeneratorData(1, 2, .5)), 
	ActionPoint(new ResourceGeneratorData(1, 2, 1.5));
	
	private ResourceGeneratorData genData;

	private PlayerResource(ResourceGeneratorData genData) {
		this.genData = genData;
	}

	public ResourceGeneratorData getGenData() {
		return genData;
	}
	
}
