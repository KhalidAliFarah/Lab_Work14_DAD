package model;

import java.io.Serializable;

/**
 * 
 * @Solution  Exercise 2
 * 
 * This class represents a Customer model with an ID and name.
 * It implements the Serializable interface to allow objects of this class to be serialized and deserialized.
 * 
 * @author Khalid
 */
public class Customer implements Serializable {
	
	private int id;
	private String name;
	
	/**
	 * Returns the ID of the customer.
	 *
	 * @return The ID of the customer.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the ID of the customer.
	 *
	 * @param id The ID to set for the customer.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the name of the customer.
	 *
	 * @return The name of the customer.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the customer.
	 *
	 * @param name The name to set for the customer.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
