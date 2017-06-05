package Dades;

import Excepcions.FormatClauException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Oleg Sokolov on 05/06/2017.
 */
public class JavaUtil<K extends Comparable<K>,V> implements TADEstructura<K,V> {
    HashMap<K,V> hashMap = new HashMap<>();

    @Override
    public void afegir(K k, V v) throws FormatClauException {
        if (!hashMap.containsKey(k)) {
            hashMap.put(k, v);
        }
    }

    @Override
    public V esborrar(K k) throws IOException, FormatClauException {
        V objectEsborrar = null;
        if (hashMap.containsKey(k)) {
            objectEsborrar = hashMap.get(k);
            hashMap.remove(k);
        }
        return objectEsborrar;
    }

    @Override
    public V consultar(K k) throws IOException, FormatClauException {
       V objectConsultar = null;
       if (hashMap.containsKey(k)){
           objectConsultar = hashMap.get(k);
        }
        return objectConsultar;
    }

    @Override
    public LlistaGenerica<K> llistaClaus() {
        LlistaGenerica<K> llistaClaus = new LlistaGenerica<>(hashMap.size());
        for (K key : hashMap.keySet()) {
            llistaClaus.afegirElement(key);
        }
        return llistaClaus;
    }
}
