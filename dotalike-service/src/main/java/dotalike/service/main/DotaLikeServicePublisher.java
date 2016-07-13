package dotalike.service.main;

import javax.xml.ws.Endpoint;

import dotalike.service.dao.impl.CustomStatisticDaoImpl;

public class DotaLikeServicePublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/customstatisticdao", new CustomStatisticDaoImpl());
	}

}
