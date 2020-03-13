package com.glw.ad.index;

/**
 * @author : glw
 * @date : 2020/3/13
 * @time : 23:55
 * @Description : 推广计划索引接口
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
