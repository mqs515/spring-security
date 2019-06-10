package com.spring.security.core.commons;

import lombok.Data;

@Data
public class SocialProperties {

	private QQproperties qq = new QQproperties();
	
	private String filterProcessesUrl = "/auth";
}
