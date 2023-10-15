package com.jpmorgan.gbce.stockmarket.common.repository;

import java.util.List;

/**
 * Base interface for all Repositories
 */
public interface BaseRepository<K, V> {

    V insert(K key, V value);

    V retrieveByKey(K key);

    List<V> fetchAll();

}
