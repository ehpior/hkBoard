package com.hk.test.util;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil extends CacheManager {
	
	private static CacheManager manager = null;
	
	public static void createCacheManager() {
		URL url = CacheUtil.class.getResource("/ehcache.xml");
		manager = CacheManager.create(url);
	}
	
	public static void closeCacheManager() {
		manager.shutdown();
	}

	public static Object getCache(String cacheName, Object key) {
		if( manager == null ) {
			createCacheManager();
		}
		Cache cache = manager.getCache(cacheName);
		if( cache != null && cache.get(key) != null ) {
			return cache.get(key).getObjectValue();
		}
		return null;
	}
	public static void setCache(String cacheName, Object key, Object data) {
		if( manager == null ) {
			createCacheManager();
		}
		Cache cache = manager.getCache(cacheName);
		if( cache != null ) {
			cache.put(new Element(key, data));
		}
	}
	
	public static Object getCache(String cacheName, int key) {
		return getCache(cacheName, String.valueOf(key));
	}
	public static void setCache(String cacheName, int key, Object data) {
		setCache(cacheName, String.valueOf(key), data);
	}
	
	public static void deleteCache(String cacheName) {
		if( manager == null ) {
			createCacheManager();
		}
		Cache cache = manager.getCache(cacheName);
		cache.removeAll();
	}
	public static void deleteCacheAll() {
		if( manager == null ) {
			createCacheManager();
		}
		manager.clearAll();
	}
}
