package cn.deepcoding.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

	/**
	 * 添加string
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key, String value);
	
	/**
	 * 获取string
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * 是否存在
	 * @param key
	 * @return
	 */
	Boolean exists(String key);
	
	/**
	 * 设置key的有效期
	 * @param key
	 * @param seconds
	 * @return
	 */
	Long expire(String key, int seconds);
	
	/**
	 * 获取kay的剩余过期时间
	 * @param key
	 * @return
	 */
	Long ttl(String key);
	
	/**
	 * 递增
	 * @param key
	 * @return
	 */
	Long incr(String key);
	
	/**
	 * 添加hash
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	Long hset(String key, String field, String value);

	/**
	 * 获取hash
	 * @param key
	 * @param field
	 * @return
	 */
	String hget(String key, String field);
	
	/**
	 * 删除hash
	 * @param key
	 * @param field
	 * @return
	 */
	Long hdel(String key, String... field);
	
	/**
	 * hash的键是否存在
	 * @param key
	 * @param field
	 * @return
	 */
	Boolean hexists(String key, String field);
	
	/**
	 * 返回hash所有域的值
	 * @param key
	 * @return
	 */
	List<String> hvals(String key);
	
	/**
	 * 删除根据key
	 * @param key
	 * @return
	 */
	Long del(String key);
	
	//对链表进行操作 list
	/**
	 * 从链表的头部添加元素
	 * @param key
	 * @param values
	 * @return
	 */
	Long lpush(String key, String...values);
	
	/**
	 * 从链表的尾部添加元素
	 * @param key
	 * @param values
	 * @return
	 */
	Long rpush(String key, String...values);
	
	/**
	 * 从链表中获取元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	List<String> lrange(String key,Long start,Long end);
	
	/**
	 * 保留指定范围的元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	String ltrim(String key, Long start,Long end);
	
	/**
	 * 从链表的头部删除一个元素，返回删除的元素
	 * @param key
	 * @return
	 */
	String lpop(String key);
	
	/**
	 * 从链表的尾部删除一个元素，返回删除的元素
	 * @param key
	 * @return
	 */
	String rpop(String key);
	
	/**
	 * 查询链表的长度
	 * @param key
	 * @return
	 */
	Long llen(String key);
	
	/**
	 * 获取链表指定位置的元素 下标从0开始
	 * @param key
	 * @param index
	 * @return
	 */
	String lindex(String key,Long index);
	
	//无序集合 set
	/**
	 * 向集合中添加元素
	 * @param key
	 * @param members
	 * @return
	 */
	Long sadd(String key,String...members);
	
	/**
	 * 获取集合中的元素(所有)
	 * @param key
	 * @return
	 */
	Set<String> smembers(String key);
	
	/**
	 * 获取多个集合的差集
	 * @param keys
	 * @return
	 */
	Set<String> sdiff(String ... keys);
	
	/**
	 * 获取多个集合交集
	 * @param keys
	 * @return
	 */
	Set<String> sinter(String ... keys);
	
	/**
	 * 获取多个集合并集，去掉重复元素
	 * @param keys
	 * @return
	 */
	Set<String> sunion(String ... keys);
	
	/**
	 * 获取集合中元素的个数
	 * @param key
	 * @return
	 */
	Long scard(String key);
	
	/**
	 * 查看member元素是不是key集合的成员 
	 * @param key
	 * @param member
	 * @return
	 */
	Boolean sismember(String key,String member);
	
	/**
	 * 随机移除key集合的一个元素，并返回 
	 * @param key
	 * @return
	 */
	String spop(String key);
	
	//有序集合zset
	/**
	 * 向有序集合key中添加元素value，score用于排序。
	 * 如果该元素已经存在，则根据score更新该元素的顺序
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	Long zadd(String key,double score,String member);
	
	/**
	 * 向有序集合key中添加元素value，score用于排序。
	 * 如果该元素已经存在，则根据score更新该元素的顺序
	 * @param key
	 * @param scoreMembers
	 * @return
	 */
	Long zadd(String key,Map<String, Double> scoreMembers);
	
	/**
	 * 按序号升序获取有序集合中的内容
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Set<String> zrange(String key, Long start,Long end);
	
	/**
	 *  按序号降序获取有序集合中的内容
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Set<String> zrevrange(String key, Long start,Long end);
	
	/**
	 * 删除有序集合key中的元素value。
	 * @param key
	 * @param members
	 * @return
	 */
	Long zrem(String key,String...members);
	
	/**
	 * 返回有序集合key中的元素个数。
	 * @param key
	 * @return
	 */
	Long zcard(String key);
	
	/**
	 * 修改元素的排序，如果元素不存在则添加该元素，且排序的score值为增加值
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	Double zincrBy(String key, Double score, String member);
}
