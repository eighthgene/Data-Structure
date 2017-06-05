package Dades;

public class TaulaHashEncadenadaIndirecta<K extends Comparable<K>, V> implements TADEstructura<K, V> {

    private NodeHash<K, V>[] taulaElements;
    private int capacitatTaula, numElements;


    @SuppressWarnings("unchecked")
    public TaulaHashEncadenadaIndirecta(int capacitatTaulaHash) {
        this.capacitatTaula = capacitatTaulaHash;
        this.taulaElements = new NodeHash[capacitatTaulaHash];
        this.numElements = 0;
    }

    @Override
    public void afegir(K k, V v) {
        int clauHash = k.hashCode();
        if (clauHash < 0) clauHash = clauHash * (-1);
        clauHash = clauHash % capacitatTaula;

        if (taulaElements[clauHash] == null) {
            taulaElements[clauHash] = new NodeHash<K, V>(k, v, null);
            this.numElements++;
        } else {
            NodeHash<K, V> nant = taulaElements[clauHash];
            NodeHash<K, V> n = nant.getSeguent();

            while (n != null && !nant.getClau().equals(k)) {
                nant = n;
                n = n.getSeguent();
            }
            if (nant.getClau().equals(k))    //substituir
                nant.setValor(v);
            else {                            //inserir
                NodeHash<K, V> nouNode = new NodeHash<K, V>(k, v, null);
                nant.setSeguent(nouNode);
                this.numElements++;
            }
        }
    }

    @Override
    public V esborrar(K k) {
        int clauHash = k.hashCode();
        if (clauHash < 0) clauHash = clauHash * (-1);
        clauHash = clauHash % capacitatTaula;
        NodeHash<K, V> nant = taulaElements[clauHash];

        if (nant != null) {
            if (nant.getClau().equals(k)) {
                taulaElements[clauHash] = nant.getSeguent();
                numElements--;
                return nant.getValor();
            } else {
                NodeHash<K, V> n = nant.getSeguent();
                while (n != null && !n.getClau().equals(k)) {
                    nant = n;
                    n = n.getSeguent();
                }

                if (n == null)
                    return null;
                else {
                    nant.setSeguent(n.getSeguent());
                    numElements--;
                    return n.getValor();
                }
            }
        }
        return null;
    }

    @Override
    public V consultar(K k) {
        int clauHash = k.hashCode();
        if (clauHash < 0) clauHash = clauHash * (-1);
        clauHash = clauHash % capacitatTaula;

        NodeHash<K, V> n = taulaElements[clauHash];

        while (n != null && !n.getClau().equals(k))
            n = n.getSeguent();

        return (n != null) ? n.getValor() : null;
    }

    @Override
    public LlistaGenerica<K> llistaClaus() {
        NodeHash<K, V> aux;
        LlistaGenerica<K> llistaClaus = new LlistaGenerica<>(taulaElements.length);
        for (NodeHash<K, V> taulaElement : taulaElements) {
            if (taulaElement != null) {
                llistaClaus.afegirElement(taulaElement.getClau());
                aux = taulaElement.getSeguent();
                while (aux != null) {
                    llistaClaus.afegirElement(aux.getClau());
                    aux = aux.getSeguent();
                }
            }
        }
        return llistaClaus;
    }
}
