package com.aspromedic.response;

public class ContactoResponseRest extends ResponseRest{
	
	private ContactoResponse contactoResponse = new ContactoResponse();

	public ContactoResponse getContacto() {
		return contactoResponse;
	}

	public void setContacto(ContactoResponse contactoResponse) {
		this.contactoResponse = contactoResponse;
	}

}
