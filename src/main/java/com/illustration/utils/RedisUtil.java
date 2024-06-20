package com.illustration.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /** --------------- key相关操作 -------------- */

    /**
     * 删除key
     * @param keys
     */
    public void delete(String... keys) {
        for (String key : keys) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 批量删除key
     * @param keys
     */
    public void delete(Collection<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    /**
     * 序列化key
     * @param key
     * @return
     */
    public byte[] dump(String key) {
        return stringRedisTemplate.dump(key);
    }

    /**
     * 检查是否存在key
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    /**
     * 设置过期时间
     * @param key
     * @param date
     * @return
     */
    public Boolean expireAt(String key, Date date) {
        return stringRedisTemplate.expireAt(key, date);
    }

    /**
     * 查找匹配的key
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    /**
     * 将当前数据的key移动到给定的数据库db当中
     * @param key
     * @param dbIndex
     * @return
     */
    public Boolean move(String key, int dbIndex) {
        return stringRedisTemplate.move(key, dbIndex);
    }

    /**
     * 移除key的过期时间
     * @param key
     * @return
     */
    public Boolean persist(String key) {
        return stringRedisTemplate.persist(key);
    }

    /**
     * 返回key的剩余过期时间
     * @param key
     * @param unit
     * @return
     */
    public Long getExpire(String key, TimeUnit unit) {
        return stringRedisTemplate.getExpire(key, unit);
    }

    /**
     * 返回key的剩余过期时间
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    /**
     * 从当前数据库中随机返回一个key
     * @return
     */
    public String randomKey() {
        return stringRedisTemplate.randomKey();
    }

    /**
     * 修改key的名称
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey) {
        stringRedisTemplate.rename(oldKey, newKey);
    }

    /**
     * 仅当newkey不存在时，将oldKey改名为newKey
     * @param oldKey
     * @param newKey
     * @return
     */
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return stringRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 返回key所储存的值的类型
     * @param key
     * @return
     */
    public DataType type(String key) {
        return stringRedisTemplate.type(key);
    }

    /** --------------- string相关操作 -------------- */

    /**
     * 设置指定key的值
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取指定key的值
     * @param key
     * @return
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 返回key中字符串值的子字符
     * @param key
     * @param start
     * @param end
     * @return
     */
    public String get(String key, long start, long end) {
        return stringRedisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 将key的值设为value，并返回key的旧值
     * @param key
     * @param value
     * @return
     */
    public String getAndSet(String key, String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 对key所储存的字符串值，获取指定偏移量上的位(bit)
     * @param key
     * @param offset
     * @return
     */
    public Boolean getBit(String key, long offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 批量获取
     * @param keys
     * @return
     */
    public List<String> multiGet(Collection<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 对key所储存的字符串值，设置或清除指定偏移量上的位(bit)
     * @param key
     * @param offset
     * @param value
     * @return
     */
    public Boolean setBit(String key, long offset, boolean value) {
        return stringRedisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 设置指定key的值，并设置key的过期时间
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 只有key不存在时设置key的值
     * @param key
     * @param value
     * @return
     */
    public boolean setIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 用value参数覆写给定key所储存的字符串值，从偏移量offset开始
     * @param key
     * @param value
     * @param offset
     */
    public void set(String key, String value, long offset) {
        stringRedisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * 获取字符串的长度
     * @param key
     * @return
     */
    public Long size(String key) {
        return stringRedisTemplate.opsForValue().size(key);
    }

    /**
     * 批量添加
     * @param map
     */
    public void multiSet(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 同时设置一个或多个key-value对，当且仅当所有给定key都不存在
     * @param map
     * @return
     */
    public boolean multiSetIfAbsent(Map<String, String> map) {
        return stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    /**
     * 将key所储存的值加上给定的增量值
     * @param key
     * @param delta
     * @return
     */
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * key所储存的值加上给定的增量值
     * @param key
     * @param delta
     * @return
     */
    public Double increment(String key, double delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 如果key已经存在并且是一个字符串，将指定的value追加到该key原来值的末尾
     * @param key
     * @param value
     * @return
     */
    public Integer append(String key, String value) {
        return stringRedisTemplate.opsForValue().append(key, value);
    }

    /** --------------- hash相关操作 -------------- */

    /**
     * 获取存储在哈希表中指定字段的值
     * @param key
     * @param field
     * @return
     */
    public Object hashGet(String key, String field) {
        return stringRedisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取所有字段的值
     * @param key
     * @return
     */
    public Map<Object, Object> hashGetAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取所有给定字段的值
     * @param key
     * @param fields
     * @return
     */
    public List<Object> hashMultiGet(String key, Collection<Object> fields) {
        return stringRedisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * 存储hash值
     * @param key
     * @param field
     * @param value
     */
    public void hashPut(String key, String field, String value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 批量存储hash值
     * @param key
     * @param map
     */
    public void hashPutAll(String key, Map<String, String> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 仅当hashKey不存在时存入
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Boolean hashPutIfAbsent(String key, String field, String value) {
        return stringRedisTemplate.opsForHash().putIfAbsent(key, field, value);
    }

    /**
     * 删除一个或多个哈希表字段
     * @param key
     * @param fields
     * @return
     */
    public Long hashDelete(String key, Object... fields) {
        return stringRedisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 查看哈希表key中，指定的字段是否存在
     * @param key
     * @param field
     * @return
     */
    public Boolean hashHasKey(String key, String field) {
        return stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 为哈希表key中的指定字段的整数值加上增量
     * @param key
     * @param field
     * @param delta
     * @return
     */
    public Long hashIncrement(String key, String field, long delta) {
        return stringRedisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 为哈希表key中的指定字段的整数值加上增量increment
     * @param key
     * @param field
     * @param delta
     * @return
     */
    public Double hashIncrement(String key, String field, double delta) {
        return stringRedisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 获取所有哈希表中的字段
     * @param key
     * @return
     */
    public Set<Object> hashKeys(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取哈希表中字段的数量
     * @param key
     * @return
     */
    public Long hashSize(String key) {
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * 获取哈希表中所有值
     * @param key
     * @return
     */
    public List<Object> hashValues(String key) {
        return stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * 迭代哈希表中的键值对
     * @param key
     * @param options
     * @return
     */
    public Cursor<Map.Entry<Object, Object>> hashScan(String key, ScanOptions options) {
        return stringRedisTemplate.opsForHash().scan(key, options);
    }

    /** --------------- list相关操作 -------------- */

    /**
     * 通过索引获取列表中的元素
     * @param key
     * @param index
     * @return
     */
    public Object listIndex(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取列表指定范围内的元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> listRange(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 存储在list头部
     * @param key
     * @param value
     * @return
     */
    public Long listLeftPush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 批量存储在头部
     * @param key
     * @param value
     * @return
     */
    public Long listLeftPushAll(String key, String... value) {
        return stringRedisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 批量存储在头部
     * @param key
     * @param value
     * @return
     */
    public Long listLeftPushAll(String key, Collection<String> value) {
        return stringRedisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 当list存在的时候才加入
     * @param key
     * @param value
     * @return
     */
    public Long listLeftPushIfPresent(String key, String value) {
        return stringRedisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 如果pivot存在，在pivot前面添加
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    public Long listLeftPush(String key, String pivot, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 存储在list末尾
     * @param key
     * @param value
     * @return
     */
    public Long listRightPush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 批量存储在list末尾
     * @param key
     * @param value
     * @return
     */
    public Long listRightAll(String key, String... value) {
        return stringRedisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 批量存储在list末尾
     * @param key
     * @param value
     * @return
     */
    public Long listRightAll(String key, Collection<String> value) {
        return stringRedisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 为已存在的列表添加值
     * @param key
     * @param value
     * @return
     */
    public Long listRightPushIfPresent(String key, String value) {
        return stringRedisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 在pivot元素的右边添加值
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    public Long listRightPush(String key, String pivot, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, pivot, value);
    }

    /**
     * 通过索引设置列表元素的值
     * @param key
     * @param index
     * @param value
     */
    public void listSet(String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移出并获取列表的第一个元素
     * @param key
     * @return
     */
    public String listLeftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移出并获取列表最后一个元素
     * @param key
     * @return
     */
    public String listRightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移出并获取列表第一个元素，如果没有元素会阻塞列表知道等待超时或发现可弹出元素为止
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public String listLeftPop(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移出并获取列表最后一个元素，如果没有元素会阻塞列表知道等待超时或发现可弹出元素为止
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public String listRightPop(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    /**
     * 移除列表最后一个元素，并将该元素添加到另一个列表并返回
     * @param sourceKey
     * @param destinationKey
     * @return
     */
    public String listRightPopAndLeftPop(String sourceKey, String destinationKey) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
    }

    /**
     * 从列表中弹出一个值，将弹出的元素插入到另一个列表中并返回它
     * @param sourceKey
     * @param destinationKey
     * @param timeout
     * @param unit
     * @return
     */
    public String listRightPopAndLeftPop(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
    }

    /**
     * 删除从index开始值等于value的元素
     * @param key
     * @param count
     * @param value
     * @return
     */
    public Long listRemove(String key, long count, String value) {
        return stringRedisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 裁剪list
     * @param key
     * @param start
     * @param end
     */
    public void listTrim(String key, long start, long end) {
        stringRedisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 获取列表长度
     * @param key
     * @return
     */
    public Long listSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /** --------------- set相关操作 -------------- */

    /**
     * set添加元素
     * @param key
     * @param values
     * @return
     */
    public Long setAdd(String key, String... values) {
        return stringRedisTemplate.opsForSet().add(key, values);
    }

    /**
     * set移除元素
     * @param key
     * @param values
     * @return
     */
    public Long setRemove(String key, Object... values) {
        return stringRedisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 移除并返回集合的一个随机元素
     * @param key
     * @return
     */
    public String setPop(String key) {
        return stringRedisTemplate.opsForSet().pop(key);
    }

    /**
     * 将元素value从一个集合移动到另一个集合
     * @param key
     * @param value
     * @param destKey
     * @return
     */
    public Boolean setMove(String key, String value, String destKey) {
        return stringRedisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 获取集合的大小
     * @param key
     * @return
     */
    public Long setSize(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * 判断集合是否包含member
     * @param key
     * @param member
     * @return
     */
    public Boolean setIsMember(String key, String member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * 获取两个集合的交集
     * @param key
     * @param values
     * @return
     */
    public Set<String> setIntersect(String key, String values) {
        return stringRedisTemplate.opsForSet().intersect(key, values);
    }

    /**
     * 获取key集合与多个集合的交集
     * @param key
     * @param values
     * @return
     */
    public Set<String> intersect(String key, Collection<String> values) {
        return stringRedisTemplate.opsForSet().intersect(key, values);
    }

    /**
     * key集合与otherKey的集合的交集存储到destKey集合中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long setIntersectAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的交集存储到destKey集合中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long setIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取两个集合的并集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<String> setUnion(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().union(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的并集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<String> setUnion(String key, Collection<String> otherKeys) {
        return stringRedisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的并集存储到destKey中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long setUnionAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的并集存储到destKey中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long setUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取两个集合的差集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<String> setDifference(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的差集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<String> setDifference(String key, Collection<String> otherKeys) {
        return stringRedisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的差集存储到destKey中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long setDifferenceAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的差集存储到destKey中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long setDifferenceAndStore(String key, Collection<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取集合所有元素
     * @param key
     * @return
     */
    public Set<String> setMembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取集合中的一个元素
     * @param key
     * @return
     */
    public String setRandomMember(String key) {
        return stringRedisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取集合中count个元素
     * @param key
     * @param count
     * @return
     */
    public List<String> setRandomMembers(String key, long count) {
        return stringRedisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取集合中count个不重复的元素
     * @param key
     * @param count
     * @return
     */
    public Set<String> setDistinctRandomMembers(String key, long count) {
        return stringRedisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * 迭代集合中的元素
     * @param key
     * @param options
     * @return
     */
    public Cursor<String> setScan(String key, ScanOptions options) {
        return stringRedisTemplate.opsForSet().scan(key, options);
    }

    /** --------------- sorted set相关操作 -------------- */

    /**
     * 添加元素
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean zAdd(String key, String value, double score) {
        return stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 添加元素
     * @param key
     * @param tuples
     * @return
     */
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<String>> tuples) {
        return stringRedisTemplate.opsForZSet().add(key, tuples);
    }

    /**
     * 删除元素
     * @param key
     * @param value
     * @return
     */
    public Long zRemove(String key, Object... value) {
        return stringRedisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 增加元素score值，并返回增加后的值
     * @param key
     * @param value
     * @param delta
     * @return
     */
    public Double zIncrementScore(String key, String value, double delta) {
        return stringRedisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    /**
     * 返回元素在集合的排名，有序集合时按照元素的score值由小到大排列
     * @param key
     * @param value
     * @return
     */
    public Long zRank(String key, Object value) {
        return stringRedisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 返回元素在集合的排名，有序集合时按照元素的score值由小到大排列
     * @param key
     * @param value
     * @return
     */
    public Long zReverseRank(String key, Object value) {
        return stringRedisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取集合的元素，从小到大排序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zRange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取集合元素，并且把score值也获取
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 根据score值查询集合元素
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> zRangeByScore(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 根据score值查询集合元素，从小到大排序
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * 根据score值和序号查询集合元素，从小到大排序
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end) {
        return stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
    }

    /**
     * 获取集合的元素，从大到小排序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zReverseRange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 获取集合的元素，从大到小排序，并返回score值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 根据Score值查询集合元素，从大到小排序
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> zReverseRangeByScore(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 根据score值查询集合元素，从大到小排序
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * 根据score值和序号查询集合元素，从大到小排序
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     */
    public Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
    }

    /**
     * 根据score值获取集合元素数量
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zCount(String key, Double min, Double max) {
        return stringRedisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 获取集合大小
     * @param key
     * @return
     */
    public Long zSize(String key) {
        return stringRedisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取有序集合的成员数
     * @param key
     * @return
     */
    public Long zCard(String key) {
        return stringRedisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 获取集合中value元素的score值
     * @param key
     * @param value
     * @return
     */
    public Double zScore(String key, Object value) {
        return stringRedisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 移除指定索引位置的成员
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zRemoveRange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 根据指定的score值的范围来移除成员
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 获取key和otherKey的并集存储在destKey中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * 获取key和多个集合的并集存储在destKey中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取key和otherKey的交集存储在destKey中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long zIntersectAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    /**
     * 获取key和多个集合的交集存储在destKey中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * 迭代有序集合中的元素
     * @param key
     * @param options
     * @return
     */
    public Cursor<ZSetOperations.TypedTuple<String>> zScan(String key, ScanOptions options) {
        return stringRedisTemplate.opsForZSet().scan(key, options);
    }
}
