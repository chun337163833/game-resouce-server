package org.shovelgame.web.menu;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.shovelgame.annotation.Logger;
import org.shovelgame.utils.ReflectionUtils;
import org.shovelgame.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Logger
public class MenuFactory {
	
	public static final String ROOT_MENU = "ROOT_MENU";
	
	private String scanPackage;
	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}
	
	private Map<String, String> groupAliases = new HashMap<>();
	public void setGroupAliases(Map<String, String> groupAliases) {
		this.groupAliases = groupAliases;
	}
	
	private MenuItem menu;
	private Map<String, MenuItem> tempGroups;
	
	@PostConstruct
	public void scan() throws Exception {		
		if(StringUtils.isEmpty(this.scanPackage)) {
			throw new MenuException("Scan package cannot be null.");
		}
		this.tempGroups = new HashMap<>();
		List<Class<?>> classesForScan = ReflectionUtils.findClasses(Menu.class, this.scanPackage);
		this.sort(classesForScan);
		for(Class<?> cls: classesForScan) {
			MenuItem item = this.createItem(cls);
			Menu annotation = cls.getDeclaredAnnotation(Menu.class);
			this.groupItem(item, annotation.group(), annotation.groupOf());
		}
		this.menu = this.tempGroups.get(ROOT_MENU);
		this.tempGroups = null;
		if(this.menu == null) {
			log.info(String.format("No menu declarations found of package %s", this.scanPackage));
		} else {
			this.printHierarchy();
		}
	}
	
	private void sort(List<Class<?>> classes) {
		Collections.sort(classes, new Comparator<Class<?>>() {
			@Override
			public int compare(Class<?> o1, Class<?> o2) {
				Menu a1 = o1.getDeclaredAnnotation(Menu.class);
				Menu a2 = o2.getDeclaredAnnotation(Menu.class);
				if(a1.order() == 0 && a2.order() == 0) {
					return o1.getSimpleName().compareTo(o2.getSimpleName());
				}
				if(a1.order() == a2.order()) {
					return 0;
				}
				return a1.order() > a1.order()?-1:1;
			}
		});
		
	}
	
	private void groupItem(MenuItem item, String groupId, String groupOf) {		
		if(item.isGroup()) {
			this.tempGroups.put(item.getTitle(), item);
		}
		MenuItem group = this.tempGroups.get(groupId);
		if(group == null) {
			group = new MenuItem();
			group.setGroup(true);
			group.setTitle(this.getGroupTitle(groupId));
			this.tempGroups.put(groupId, group);
			if(!StringUtils.isEmpty(groupOf) && !groupId.equals(groupOf)) {
				this.groupItem(group, groupOf, null);
			}
		}
		
		group.getSubmenu().add(item);
	}
	
	private String getGroupTitle(String group) {
		String alias = this.groupAliases.get(group);
		if(StringUtils.isEmpty(alias)) {
			return group;
		}
		return alias;
	}
	
	private MenuItem createItem(Class<?> cls) throws Exception {
		Menu menuAnnotation = cls.getDeclaredAnnotation(Menu.class);
		RequestMapping rmAnnotation = cls.getDeclaredAnnotation(RequestMapping.class);
		if(menuAnnotation != null && rmAnnotation == null) {
			throw new MenuException("When @Menu annotation is declared then @RequestMapping annotation must be declared too in class " + cls.getCanonicalName());
		}
		MenuItem item = new MenuItem();
		item.setLink(rmAnnotation.value()[0]);
		item.setTitle(menuAnnotation.name());
		item.setGroup(menuAnnotation.isGroup());
		item.setOrder(menuAnnotation.order());
		log.info(String.format("Creating menu item %s for link %s", item.getTitle(), item.getLink()));
		return item;
	}
	
	public MenuItem getMenu() {
		return menu;
	}
	
	private void printHierarchy() {
		StringBuffer hierarchy = new StringBuffer();
		hierarchy.append("\n");
		hierarchy.append(String.format("Menu hierarchy for package %s", this.scanPackage));
		this.makePrintableResult(this.menu.getSubmenu(), 0, hierarchy);
		log.info(hierarchy.toString());
	}
	
	private void makePrintableResult(List<MenuItem> items, int stage, StringBuffer hierarchy) {
		for(MenuItem item: items) {
			hierarchy.append("\n");
			if(StringUtils.isEmpty(item.getLink())) {
				hierarchy.append(String.format(">> %s %s", getTabSpace(stage), item.getTitle()));
			} else {
				hierarchy.append(String.format(">> %s %s -> %s", getTabSpace(stage), item.getTitle(), item.getLink()));
			}
			if(item.isGroup()) {
				makePrintableResult(((MenuItem) item).getSubmenu(), stage+1, hierarchy);
			}
		}
	}
	
	private String getTabSpace(int count) {
		StringBuilder buff = new StringBuilder();
		for(int i = 0; i < count; i++) {
			buff.append("	");
		}
		return buff.toString();
	}
	
}
