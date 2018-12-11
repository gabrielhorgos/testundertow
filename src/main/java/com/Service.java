package com;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Service {
	
	public String getMessage() {
		return "Message from service";
	}
}
