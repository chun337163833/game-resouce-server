package org.shovelgame.web.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {

	private String title;
	private List<MenuItem> submenu = new ArrayList<MenuItem>();
	private boolean group;
	private String link;
	private int order;
	private String[] roles;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isGroup() {
		return this.group;
	}

	public void setGroup(boolean group) {
		this.group = group;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MenuItem> getSubmenu() {
		return submenu;
	}

	public void setSubmenu(List<MenuItem> submenu) {
		this.submenu = submenu;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
