package com.heystyles.usuarios.cliente.impl;

import com.heystyles.usuarios.cliente.ProveedorClient;
import com.heystyles.usuarios.core.domain.Proveedor;
import com.heystyles.usuarios.core.dto.ProveedorResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ProveedorClientImpl implements ProveedorClient {

    private final RestTemplate client;

    private final String urlBase;

    private interface SegmentPaths {
        String PROVEEDOR = "proveedores";
    }

    public ProveedorClientImpl(String urlBase, RestTemplate client) {
        this.client = client;
        this.urlBase = urlBase;
    }

    @Override
    public Proveedor findProveedorById(Long proveedorId) {
        UriComponentsBuilder urlBuilder = getUriProveedor()
                .pathSegment(String.valueOf(proveedorId));
        return client.getForEntity(urlBuilder.toUriString(), ProveedorResponse.class).getBody().getData();
    }

    private UriComponentsBuilder getUriProveedor() {
        return UriComponentsBuilder.fromHttpUrl(urlBase).pathSegment(SegmentPaths.PROVEEDOR);
    }

}
