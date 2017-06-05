package Dades;


import Excepcions.FormatClauException;

import java.io.IOException;

public interface TADEstructura<K extends Comparable<K>,V> {
	/**
	 * Afegeix un element a la taula de hash
	 * @param k - clau de l'element a afegir
	 * @param v - element a afegir
	 */
	public void afegir(K k,V v) throws FormatClauException;
	
	/**
	 * Esborra un element a la taula de hash
	 * @param k - clau de l'element a esborrar
	 */
	public V esborrar(K k) throws IOException, FormatClauException;
	
	/**
	 * Consulta un element a la taula de hash
	 * @param k - clau de l'element a consultar
	 */	
	public V consultar(K k) throws IOException, FormatClauException;

	public LlistaGenerica<K> llistaClaus();
}
