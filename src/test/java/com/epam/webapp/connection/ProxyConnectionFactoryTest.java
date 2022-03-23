package com.epam.webapp.connection;

import org.junit.jupiter.api.Test;

public class ProxyConnectionFactoryTest {
    private static final ProxyConnectionFactory factory = new ProxyConnectionFactory();

    @Test
    public void testCreateShouldReturnProxyConnection() {
       ProxyConnection connection = factory.create();

    }
}
