package com.crx.streamdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class MapUtils {
	public static <K, V> Map<K, V> filterMap(Predicate<K> keyCondition, Predicate<V> valueCondition,
			Function<K, K> convert, Map<K, V> map) {
		final Map<K, V> resultMap = new HashMap<K, V>();
		map.keySet().stream().filter(keyCondition).forEach(key -> {
			if (valueCondition.test(map.get(key))) {
				resultMap.put(convert.apply(key), map.get(key));
			}
		});
		return resultMap;
	}

	public static <K, V> Map<K, V> filterMap(Predicate<K> keyCondition, Predicate<V> valueCondition, Map<K, V> map) {
		return filterMap(keyCondition, valueCondition, s -> s, map);
	}

	public static <K, V> Map<K, V> filterMapByKey(Predicate<K> keyCondition, Map<K, V> map) {
		return filterMap(keyCondition, s -> true, map);
	}

	public static <K, V> Map<K, V> filterMapByValue(Predicate<V> valueCondition, Map<K, V> map) {
		return filterMap(s -> true, valueCondition, map);
	}

	public static <K, V> Map<K, V> convertMapKey(Function<K, K> convert, Map<K, V> map) {
		return filterMap(key -> true, value -> true, convert, map);
	}
}
