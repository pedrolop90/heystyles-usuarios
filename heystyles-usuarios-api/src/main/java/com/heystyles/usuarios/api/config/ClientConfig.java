package com.heystyles.usuarios.api.config;

import com.heystyles.common.response.ClientResponseErrorHandler;
import com.heystyles.file.cliente.FileClient;
import com.heystyles.file.cliente.impl.FileClientImpl;
import com.heystyles.seguridad.cliente.RolClient;
import com.heystyles.seguridad.cliente.UserCliente;
import com.heystyles.seguridad.cliente.impl.RolClientImpl;
import com.heystyles.seguridad.cliente.impl.UserClienteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    @Autowired
    private ClientProperties clientProperties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(clientProperties.getConnectionTimeout())
                .setReadTimeout(clientProperties.getReadTimeout())
                .errorHandler(new ClientResponseErrorHandler())
                .build();
    }

    @Bean
        public UserCliente userCliente(RestTemplate restTemplate) {
        return new UserClienteImpl(clientProperties.getSeguridadUrlBase(), restTemplate);
    }

    @Bean
    public RolClient rolClient(RestTemplate restTemplate) {
        return new RolClientImpl(clientProperties.getSeguridadUrlBase(), restTemplate);
    }

    @Bean
    public FileClient fileClient(RestTemplate restTemplate) {
        return new FileClientImpl(clientProperties.getFileUrlBase(), restTemplate);
    }
}
