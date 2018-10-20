/**
 *
 */
package com.trummer.business;

/**
 * @author chris
 *
 */
public class Tickt {

	private long ticketId;
	private String ticketName;
	private String shortDescription;
	private String description;

	public String getDescription() {
		return description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public long getTicketId() {
		return ticketId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

}
