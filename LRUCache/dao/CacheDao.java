package LLDProblems.LRUCache.dao;

import LLDProblems.LRUCache.contants.EvictionPolicy;
import LLDProblems.LRUCache.model.Cache;

public class CacheDao {
    private static CacheDao cacheDao = null;
    private Cache cache = null;
    private CacheDao(){

    }

    public static CacheDao getInstance(){
        if (cacheDao == null){
            cacheDao = new CacheDao();
        }
        return cacheDao;
    }

    public Cache createCache(int size, EvictionPolicy evictionPolicy){
        cache = new Cache(size, evictionPolicy);
        return cache;
    }

    public Object get(Object obj){
        Object result = cache.getCacheStore().get(obj);
        if (result == null){
            return null;
        }

        cache.getDataStore().remove(obj);
        cache.getDataStore().addFirst(obj);

        return result;
    }

    public Boolean put(Object key, Object value){
        // checking cache is free or not

        try{
            if (cache.getSize() == cache.getDataStore().size()){

                // if we dont have enough space then delete the last one
                Object last = cache.getDataStore().getLast();
                cache.getCacheStore().remove(last);
            }

            cache.getDataStore().remove(key);
            cache.getDataStore().addFirst(key);
            cache.getCacheStore().put(key, value);

            return true;
        }catch (Exception e){
            System.out.println("Something went wrong while adding the element ");
        }
        return false;
    }


    public Boolean delete(Object obj) {
        if (cache.getDataStore().size() == 0)
            return true;

        try{
            cache.getDataStore().remove(obj);
            cache.getCacheStore().remove(obj);
            return true;
        }catch(Exception e){
            System.out.println("Something went wrong while removing the element");
        }

        return false;
    }

    public Boolean clear() {
        return cache.clear();
    }
}
