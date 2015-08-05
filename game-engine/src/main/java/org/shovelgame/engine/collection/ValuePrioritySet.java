package org.shovelgame.engine.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This set replace same object if value is higher or lower
 * @author TremlL
 *
 * @param <T>
 */
public class ValuePrioritySet<T extends Valuable> extends HashSet<T> {

	private ValuePriority priority = ValuePriority.Higher;
	
	public ValuePrioritySet() {
	}
	
	public ValuePrioritySet(ValuePriority priority) {
		super();
		this.priority = priority;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean addAll(Collection<? extends T> c) {
		Set<T> set = new ValuePrioritySet<>();
		this.forEach((T t) -> set.add(t));
		c.forEach((T t) -> set.add(t));
		this.clear();
		return super.addAll(set);
	}
	
	@Override
	public boolean add(T e) {
		T remove = null;
		T contains = null;
		for(T t: this) {
			if(e.equals(t)) {
				contains = t;
				if(ValuePriority.Higher.equals(this.priority) && e.getValue().doubleValue() > t.getValue().doubleValue()) {
					remove = t;
				} else if(ValuePriority.Lower.equals(this.priority) && e.getValue().doubleValue() < t.getValue().doubleValue()) {
					remove = t;
				}
				break;
			}
		}
		if(remove != null) {
			this.remove(remove);
			return super.add(e);
		} else if(contains == null) {
			return super.add(e);
		}
		return false;
	}
	
	public enum ValuePriority {
		Higher, Lower
	}
}
