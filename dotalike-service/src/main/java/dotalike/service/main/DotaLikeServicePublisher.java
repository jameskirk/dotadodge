package dotalike.service.main;

import javax.xml.ws.Endpoint;

import dotalike.service.dao.impl.CustomStatisticDaoImpl;
import dotalike.service.db.ApplicationInitializer;
import dotalike.service.misc.GuiceFactory;

public class DotaLikeServicePublisher {

	public static void main(String[] args) {
		
		ApplicationInitializer applicationInitializer = GuiceFactory.getInjector().getInstance(ApplicationInitializer.class);
		applicationInitializer.start();
		Endpoint.publish("http://localhost:9999/ws/customstatisticdao", new CustomStatisticDaoImpl());
	}

}
