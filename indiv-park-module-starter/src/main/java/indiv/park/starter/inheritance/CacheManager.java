package indiv.park.starter.inheritance;

import java.util.Optional;

public interface CacheManager<K, V> {

	public V createCache(K id);
	
	public boolean addCache(V cache);
	
	public boolean removeCache(K id);
	
	public Optional<V> getCache(K id);
	
	public int size();
	
}
