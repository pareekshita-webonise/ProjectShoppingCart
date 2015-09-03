package com.shopperszone.cacheservice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.shopperszone.cache.CachableObject;

@Component
public interface CacheService {
	public void save(CachableObject CachableObject);

	public void delete(CachableObject CachableObject);

	public void update(CachableObject CachableObject);

	public CachableObject read(String hashKey, String key);

	public Map<Object, Object> readAllHash(String hashKey);

	public void deleteAll(List<CachableObject> cachableObject);

	public void deleteAll(String hashKey);
	
	public void flushCache();
}
