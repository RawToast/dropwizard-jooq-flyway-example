/**
 * This class is generated by jOOQ
 */
package coop.poc;

/**
 * This class is generated by jOOQ.
 *
 * Convenience access to all sequences in poc
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

	/**
	 * The sequence <code>poc.stores_store_id_seq</code>
	 */
	public static final org.jooq.Sequence<java.lang.Long> STORES_STORE_ID_SEQ = new org.jooq.impl.SequenceImpl<java.lang.Long>("stores_store_id_seq", coop.poc.Poc.POC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));
}