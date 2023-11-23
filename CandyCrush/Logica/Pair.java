package Logica;

/**
 *	Modela pares K-V genericos
 * @param <K> generico
 * @param <V> generico
 */
public class Pair<K,V>{
    protected K key;
    protected V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Devuelve la clave del par
     */
    public K getKey() {
        return key;
    }

    /**
     * Devuelve el valor de la entrada
     */
    public V getValue() {
        return value;
    }

    /**
     * Establece la clave pasada por parametro
     * @param key clave a establecer
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Establece el valor pasado por parametro
     * @param value valor a establecer
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Devuelve una cadena concatenando la clave y el valor de la entrada
     */
    public String toString() {
        return "{"+getKey()+","+getValue()+"}";
    }
}
