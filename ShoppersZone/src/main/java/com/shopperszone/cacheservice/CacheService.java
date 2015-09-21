package com.shopperszone.cacheservice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.shopperszone.cache.CachableObject;
import com.shopperszone.custom.exceptions.ShoppersZoneException;

@Component
public interface CacheService {
	public void save(CachableObject CachableObject) throws ShoppersZoneException;

	public void delete(CachableObject CachableObject) throws ShoppersZoneException;

	public void update(CachableObject CachableObject) throws ShoppersZoneException;

	public CachableObject read(String hashKey, String key) throws ShoppersZoneException;

	public Map<Object, Object> readAllHash(String hashKey) throws ShoppersZoneException;

	public void deleteAll(List<CachableObject> cachableObject) throws ShoppersZoneException;

	public void deleteAll(String hashKey) throws ShoppersZoneException;
	
	public void flushCache() throws ShoppersZoneException;
}
