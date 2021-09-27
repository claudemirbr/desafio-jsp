package com.br.atomicweb.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CriaEntityManagerFactory {

	private static EntityManagerFactory emf;
	private static String PERSISTENCE_UNIT_NAME = "default";
	private static String AMBIENTE = "local";

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			if (AMBIENTE.equals("heroku")) {
				String databaseUrl = System.getenv("DATABASE_URL");
				StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
				String dbVendor = st.nextToken(); // if DATABASE_URL is set
				String userName = st.nextToken();
				String password = st.nextToken();
				String host = st.nextToken();
				String port = st.nextToken();
				String databaseName = st.nextToken();
				String jdbcUrl = String.format(
						"jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host,
						port, databaseName);
				Map<String, String> properties = new HashMap<String, String>();
				properties.put("javax.persistence.jdbc.url", databaseUrl);
				properties.put("javax.persistence.jdbc.user", userName);
				properties.put("javax.persistence.jdbc.password", password);
				properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
				properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
				emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
			} else {
				emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			}
		}
		return emf;
	}
}
