package Dades;
public class NodeHash<K,V> implements Comparable<NodeHash<K, V>> {
	private K clau;
	private V valor;
	private NodeHash<K,V> seguent;
	
	public NodeHash(K k, V v, NodeHash<K,V> ant) {
		clau=k;
		valor=v;		
		seguent=ant;
	}
	
	public K getClau() {
		return(clau);
	}
	
	public V getValor() {
		return(valor);
	}
	
	public NodeHash<K,V> getSeguent() {
		return(seguent);
	}

	public void setValor(V valor) {
		this.valor = valor;
	}

	public void setSeguent(NodeHash<K, V> seguent) {
		this.seguent = seguent;
	}

	@Override
	public int compareTo(NodeHash<K, V> o) {
		return 0;
	}
}
