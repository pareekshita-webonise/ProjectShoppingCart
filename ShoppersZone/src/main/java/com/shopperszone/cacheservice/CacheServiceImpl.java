package com.shopperszone.cacheservice;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.shopperszone.cache.CachableObject;

@Component
public class CacheServiceImpl implements CacheService {
	private static final Logger LOG = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Autowired
	private RedisTemplate<String, CachableObject> redisTemplate;

	@Override
	public void save(final CachableObject CachableObject) {
		LOG.debug("Saving object for key: {}", CachableObject.getKey());
		redisTemplate.opsForHash().put(CachableObject.getObjectKey(), CachableObject.getKey(), CachableObject);
	}

	@Override
	public void delete(final CachableObject CachableObject) {
		LOG.debug("Deleting object for key: {}", CachableObject.getKey());
		redisTemplate.opsForHash().delete(CachableObject.getObjectKey(), CachableObject.getKey());
	}

	@Override
	public void update(final CachableObject CachableObject) {
		LOG.debug("Updating object for key: {}", CachableObject.getKey());
	}

	@Override
	public CachableObject read(final String hashKey, final String key) {
		LOG.debug("Reading key: {} from hashKey: {}", key, hashKey);
		return (CachableObject) redisTemplate.opsForHash().get(hashKey, key);
	}

	@Override
	public Map<Object, Object> readAllHash(final String hashKey) {
		LOG.debug("Reading all object for hashKey: {}", hashKey);
		return (Map<Object, Object>) redisTemplate.opsForHash().entries(hashKey);
	}

	@Override
	public void deleteAll(List<CachableObject> CachableObjectList) {
		LOG.debug("Deleting object list {}", CachableObjectList);
		for (CachableObject CachableObject : CachableObjectList) {
			redisTemplate.opsForHash().delete(CachableObject.getObjectKey(), CachableObject.getKey());
		}
	}

	@Override
	public void deleteAll(String hashKey) {
		redisTemplate.delete(hashKey);
	}

	@Override
	public void flushCache() {
		Set<String> keys = redisTemplate.keys("*");
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			redisTemplate.delete(itr.next().toString());
		}
		LOG.debug("All keys deleted");
	}
}
