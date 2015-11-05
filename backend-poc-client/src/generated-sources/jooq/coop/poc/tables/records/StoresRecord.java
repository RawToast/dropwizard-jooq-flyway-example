/**
 * This class is generated by jOOQ
 */
package coop.poc.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StoresRecord extends org.jooq.impl.UpdatableRecordImpl<coop.poc.tables.records.StoresRecord> implements org.jooq.Record3<java.lang.Integer, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -1543817305;

	/**
	 * Setter for <code>poc.stores.store_id</code>.
	 */
	public void setStoreId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>poc.stores.store_id</code>.
	 */
	public java.lang.Integer getStoreId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>poc.stores.name</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>poc.stores.name</code>.
	 */
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>poc.stores.postcode</code>.
	 */
	public void setPostcode(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>poc.stores.postcode</code>.
	 */
	public java.lang.String getPostcode() {
		return (java.lang.String) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.Integer, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.Integer, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return coop.poc.tables.Stores.STORES.STORE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return coop.poc.tables.Stores.STORES.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return coop.poc.tables.Stores.STORES.POSTCODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getStoreId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getPostcode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StoresRecord value1(java.lang.Integer value) {
		setStoreId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StoresRecord value2(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StoresRecord value3(java.lang.String value) {
		setPostcode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StoresRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.String value3) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached StoresRecord
	 */
	public StoresRecord() {
		super(coop.poc.tables.Stores.STORES);
	}

	/**
	 * Create a detached, initialised StoresRecord
	 */
	public StoresRecord(java.lang.Integer storeId, java.lang.String name, java.lang.String postcode) {
		super(coop.poc.tables.Stores.STORES);

		setValue(0, storeId);
		setValue(1, name);
		setValue(2, postcode);
	}
}
