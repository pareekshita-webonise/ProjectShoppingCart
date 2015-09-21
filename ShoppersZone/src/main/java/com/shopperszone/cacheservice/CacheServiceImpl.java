package com.shopperszone.cacheservice;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.shopperszone.cache.CachableObject;

@Component
public class CacheServiceImpl implements CacheService {

	private static final Logger LOG = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Autowired
	private RedisTemplate<String, CachableObject> redisTemplate;

	@Override
	public void save(final CachableObject CachableObject){
		try {
			LOG.debug("Saving object for key: {}", CachableObject.getKey());
			redisTemplate.opsForHash().put(CachableObject.getObjectKey(), CachableObject.getKey(), CachableObject);
		} catch (RedisConnectionFailureException e) {
			LOG.info("Redis Server is down");
		}
	}

	@Override
	public void delete(final CachableObject CachableObject){
		try {
			LOG.debug("Deleting object for key: {}", CachableObject.getKey());
			redisTemplate.opsForHash().delete(CachableObject.getObjectKey(), CachableObject.getKey());
		} catch (RedisConnectionFailureException e) {
			LOG.info("Redis Server is down");
		}
	}

	@Override
	public void update(final CachableObject CachableObject){
		try {
			LOG.debug("Updating object for key: {}", CachableObject.getKey());
		} catch (RedisConnectionFailureException e) {
			LOG.info("Redis Server is down");
		}
	}

	@Override
	public CachableObject read(final String hashKey, final String key){
		try {
			LOG.debug("Reading key: {} from hashKey: {}", key, hashKey);
			return (CachableObject) redisTemplate.opsForHash().get(hashKey, key);
		} catch (RedisConnectionFailureException e) {
			LOG.info("Redis Server is down");
		}
		return null;
	}

	@Override
	public Map<Object, Object> readAllHash(final String hashKey){
		Map<Object, Object> mapObject = null;
		try {
			LOG.debug("Reading all object for hashKey: {}", hashKey);
			mapObject = (Map<Object, Object>) redisTemplate.opsForHash().entries(hashKey);
		} catch (RedisConnectionFailureException e) {
			LOG.info("Redis Server is down");
			return null;
		}
		return mapObject;
	}

	@Override
	public void deleteAll(List<CachableObject> CachableObjectList){
		try {
			LOG.debug("Deleting object list {}", CachableObjectList);
			for (CachableObject CachableObject : CachableObjectList) {
				redisTemplate.opsForHash().delete(CachableObject.getObjectKey(), CachableObject.getKey());
			}
		} catch (RedisConnectionFailureException e) {
			LOG.info("Redis Server is down");
		}
	}

	@Override
	public void deleteAll(String hashKey){
		try {
			LOG.debug("Deleting object {}", hashKey);
			redisTemplate.delete(hashKey);
		} catch (RedisConnectionFailureException e) {
			LOG.info("Redis Server is down");
		}
	}

	@Override
	public void flushCache(){
		try {
			Set<String> keys = redisTemplate.keys("*");
			Iterator<String> itr = keys.iterator();
			while (itr.hasNext()) {
				redisTemplate.delete(itr.next().toString());
			}
		} catch (RedisConnectionFailureException e) {
			LOG.info("Redis Server is down");
		}
	}
}
