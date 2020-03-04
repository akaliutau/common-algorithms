package org.algorithm;

import java.util.Optional;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.ArrayList;
import java.util.List;

interface TimeProvider {
	long getMillis();
}

/**
 *	One possible solution for Vaadin code test challenge 
 */
public class CachingDataStructure {

	public static class Element {

		private String value;
		private long timeToLeaveInMilliseconds;

		public Element(String value, long timeToLeaveInMilliseconds) {
			this.value = value;
			this.timeToLeaveInMilliseconds = timeToLeaveInMilliseconds;
		}

		public boolean isExpired(long currentTimeInMilliseconds) {
			return currentTimeInMilliseconds > timeToLeaveInMilliseconds;
		}

		public String getValue() {
			return value;
		}

	}

	private int maxSize;
	private TimeProvider timeProvider;
	private ConcurrentMap<String, Element> cache = new ConcurrentHashMap<>();

	CachingDataStructure(TimeProvider timeProvider, int maxSize) {
		this.timeProvider = timeProvider;
		this.maxSize = maxSize;
	}

	public void put(String key, String value, long timeToLeaveInMilliseconds) {
		if (size() < maxSize) {
			cache.put(key, new Element(value, timeToLeaveInMilliseconds));
		}
	}

	public Optional<String> get(String key) {
		Element elem = cache.get(key);
		String value = null;
		if (elem != null && !elem.isExpired(timeProvider.getMillis())) {
			value = elem.getValue();
		}
		return Optional.of(value);
	}

	public int size() {
		updateMap();
		return cache.size();
	}

	private void updateMap() {
		List<String> expired = new ArrayList<>();
		for (String key : cache.keySet()) {
			if (cache.get(key).isExpired(timeProvider.getMillis())) {
				expired.add(key);
			}
		}
		cache.keySet().removeAll(expired);
	}

}
