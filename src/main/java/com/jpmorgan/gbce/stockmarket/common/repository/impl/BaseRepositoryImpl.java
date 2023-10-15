package com.jpmorgan.gbce.stockmarket.common.repository.impl;

import com.jpmorgan.gbce.stockmarket.common.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Base Repository implementation class
 *
 * @param <K>
 * @param <V>
 */
public abstract class BaseRepositoryImpl<K, V> implements BaseRepository<K, V> {

    protected abstract Map<K, V> getStockMarketData();

    /**
     * Method to insert the Stock or Trade details
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V insert(final K key, final V value) {
        getStockMarketData().put(key, value);
        return getStockMarketData().get(value);
    }

    /**
     * Method to retrieve the Stock or Trade details by key
     *
     * @param key
     * @return
     */
    @Override
    public V retrieveByKey(final K key) {
        return getStockMarketData().get(key);
    }

    /**
     * ethod to retrieve all the Stock or Trade details
     *
     * @return
     */
    @Override
    public List<V> fetchAll() {
        return new ArrayList<>(getStockMarketData().values());
    }

}
