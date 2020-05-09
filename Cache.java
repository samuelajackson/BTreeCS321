import java.util.LinkedList;

/**
 * CS321: Bioinformatics Group Project
 * 
 * 
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 * @param <T>
 */

public class Cache<T extends Comparable<T>> {

    private LinkedList <T> cacheLL;
    private static int cacheSize, nodeCount;

    public Cache(int size){
        this.cacheSize = size;
        cacheLL = new LinkedList<T>();
        nodeCount = 0;
    }

    public void addObject(T obj){
        cacheLL.addFirst(obj);
        if(cacheLL.size()>cacheSize){
            cacheLL.removeLast();
        }
    }

    public T removeObject(T obj){
        T ob = null;
        for(int i=0; i<cacheLL.size(); i++){
            if(cacheLL.get(i).compareTo(obj)==0){
                ob = cacheLL.remove(i);
                
            }
        }
        return ob;
    }

    public boolean containsObject(T obj){
        return cacheLL.contains(obj);
    }

    public void clearCache(){
        cacheLL.clear();
    }
 }