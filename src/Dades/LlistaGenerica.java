package Dades;

import java.util.Iterator;

public class LlistaGenerica<T extends Comparable<T>> implements Iterable<T> {
    private T[] llista;
    private int num;

    public LlistaGenerica(int dim) {
        llista = (T[]) new Comparable[dim];
        num = 0;
    }

    public void afegirElement(T p) {
        if (num >= llista.length) {
            // amplio
            T[] nova = (T[]) new Comparable[llista.length * 2];
            for (int i = 0; i < llista.length; i++)
                nova[i] = llista[i];
            llista = nova;
        }
        // segur que tinc espai
        int pos = num - 1;
        while ((pos >= 0) && (p.compareTo(llista[pos]) < 0)) {
            llista[pos + 1] = llista[pos];
            pos--;
        }
        llista[pos + 1] = p;
        num++;
    }

    public T consultarIessim(int i) {
        if (i < num) return (llista[i]);
        else return (null);
    }

    public int getNum() {
        return num;
    }

    public void esborrar(T t){
        for (int i = 0; i < num ; i++) {
            if (llista[i].compareTo(t) == 0){
                llista[i] = null;
                for (int j = i; j < num - 1 ; j++) {
                    llista[j] = llista[j+1];
                }
                num--;
            }
        }
    }

    @Override
    public String toString() {
            String str = "Dades de llista: \n";
        for (int i = 0; i < num ; i++) {
            str += i+1 + ". " + llista[i] + "\n";
        }
        return str;
    }

    @Override
    public Iterator<T> iterator() {
        MeuIterator<T> pI = new MeuIterator<T>(this);
        return pI;
    }

}
