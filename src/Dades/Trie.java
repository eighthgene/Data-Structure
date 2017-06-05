package Dades;

import Excepcions.FormatClauException;
import java.io.IOException;

public class Trie<K extends Comparable<K>, V> implements TADEstructura<K, V> {

    public class TrieNode<T> {
        private TrieNode[] arr;
        private T objecte;
        boolean esFi;

        private TrieNode() {
            this.arr = new TrieNode[26];
        }
    }

    private TrieNode root;
    private LlistaGenerica<K> llistaClaus;

    public Trie() {
        root = new TrieNode();
        llistaClaus = new LlistaGenerica<>(2);
    }

    @Override
    public void afegir(K k, V v) throws FormatClauException {
        if (k instanceof String) {
            if (consultar(k) == null) {
                String aux = (String) k;
                TrieNode p = root;
                for (int i = 0; i < aux.length(); i++) {
                    char c = aux.charAt(i);
                    int index = c - 'a';

                    if (p.arr[index] == null) {
                        TrieNode temp = new TrieNode();
                        p.arr[index] = temp;
                        p = temp;
                    } else {
                        p = p.arr[index];
                    }
                }
                p.esFi = true;
                p.objecte = v;
                llistaClaus.afegirElement(k);
            }
        }

    }

    @Override
    public V esborrar(K k) throws FormatClauException, IOException {
        if (k instanceof String) {
            if (consultar(k) != null) {
                V valor = null;
                String aux = (String) k;
                TrieNode p = root;
                TrieNode nodeAux;
                for (int i = 0; i < aux.length(); i++) {
                    char c = aux.charAt(i);
                    int index = c - 'a';
                        if (i != (aux.length() - 1)) {
                            nodeAux = p.arr[index];
                            p.arr[index] = null;
                            p = nodeAux;
                        } else {
                            valor = (V) p.arr[index].objecte;
                            p.esFi = false;
                            p.objecte = null;
                        }

                }
                llistaClaus.esborrar(k);
                return valor;
            } else return null;
        } else throw new FormatClauException();
    }

    @Override
    public V consultar(K k) throws FormatClauException {
        if (k instanceof String) {
            V v;
            String clau = (String) k;
            TrieNode p = root;
            for (int i = 0; i < clau.length(); i++) {
                char c = clau.charAt(i);
                int index = c - 'a';
                if (p.arr[index] != null) {
                    p = p.arr[index];
                } else {
                    v = null;
                    return v;
                }
            }
            if (p == root)
                return null;
            v = (V) p.objecte;
            return (v != null) ? v : null;
        } else
            throw new FormatClauException();
    }

    @Override
    public LlistaGenerica<K> llistaClaus() {
        return llistaClaus;
    }
}