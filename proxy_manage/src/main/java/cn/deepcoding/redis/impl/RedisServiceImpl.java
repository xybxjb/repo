package cn.deepcoding.redis.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.deepcoding.redis.RedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * redis的实现类
 * @author root
 *
 */
@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		Boolean result = jedis.exists(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, field, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key, field);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String... field) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(key, field);
		jedis.close();
		return result;
	}

	@Override
	public Boolean hexists(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		Boolean result = jedis.hexists(key, field);
		jedis.close();
		return result;
	}

	@Override
	public List<String> hvals(String key) {
		Jedis jedis = jedisPool.getResource();
		List<String> result = jedis.hvals(key);
		jedis.close();
		return result;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long lpush(String key, String ... values) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.lpush(key,values);
		return result;
	}

	@Override
	public Long rpush(String key, String... values) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.rpush(key, values);
		return result;
	}

	@Override
	public List<String> lrange(String key, Long start, Long end) {
		Jedis jedis = jedisPool.getResource();
		List<String> result = jedis.lrange(key, start, end);
		return result;
	}

	@Override
	public String ltrim(String key, Long start, Long end) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.ltrim(key, start, end);
		return result;
	}

	@Override
	public String lpop(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.lpop(key);
		return result;
	}

	@Override
	public String rpop(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.rpop(key);
		return result;
	}

	@Override
	public Long llen(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.llen(key);
		return result;
	}

	@Override
	public String lindex(String key,Long index) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.lindex(key, index);
		return result;
	}

	@Override
	public Long sadd(String key, String... members) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.sadd(key, members);
		return result;
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = jedisPool.getResource();
		Set<String> result = jedis.smembers(key);
		return result;
	}

	@Override
	public Set<String> sdiff(String ... keys) {
		Jedis jedis = jedisPool.getResource();
		Set<String> result = jedis.sdiff(keys);
		return result;
	}

	@Override
	public Set<String> sinter(String... keys) {
		Jedis jedis = jedisPool.getResource();
		Set<String> result = jedis.sinter(keys);
		return result;
	}

	@Override
	public Set<String> sunion(String... keys) {
		Jedis jedis = jedisPool.getResource();
		Set<String> result = jedis.sunion(keys);
		return result;
	}

	@Override
	public Long scard(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.scard(key);
		return result;
	}

	@Override
	public Boolean sismember(String key, String member) {
		Jedis jedis = jedisPool.getResource();
		Boolean result = jedis.sismember(key, member);
		return result;
	}

	@Override
	public String spop(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.spop(key);
		return result;
	}


	@Override
	public Long zadd(String key,double score,String member) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.zadd(key, score, member);
		return result;
	}

	@Override
	public Long zadd(String key,Map<String, Double> scoreMembers) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.zadd(key, scoreMembers);
		return result;
	}

	@Override
	public Set<String> zrange(String key, Long start, Long end) {
		Jedis jedis = jedisPool.getResource();
		Set<String> result = jedis.zrange(key, start, end);
		return result;
	}

	@Override
	public Set<String> zrevrange(String key, Long start, Long end) {
		Jedis jedis = jedisPool.getResource();
		Set<String> result = jedis.zrevrange(key, start, end);
		return result;
	}

	@Override
	public Long zrem(String key, String... members) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.zrem(key, members);
		return result;
	}

	@Override
	public Long zcard(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.zcard(key);
		return null;
	}

	@Override
	public Double zincrBy(String key, Double score, String member) {
		Jedis jedis = jedisPool.getResource();
		Double result = jedis.zincrby(key, score, member);
		return result;
	}

	

	

}
