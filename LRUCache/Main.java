package LLDProblems.LRUCache;

import LLDProblems.LRUCache.contants.EvictionPolicy;
import LLDProblems.LRUCache.model.Cache;
import LLDProblems.LRUCache.service.CacheService;

public class Main {
    public static void main(String[] args) {
        CacheService service = CacheService.getInstance();
        Cache cache = service.init(10, EvictionPolicy.LRU);
        service.put("hello", " First assignment is done");
        System.out.println(service.get("hello"));
    }
}
