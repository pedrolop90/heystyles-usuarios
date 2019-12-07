package com.heystyles.usuarios.cliente.impl;

import com.heystyles.usuarios.cliente.CargoClient;
import com.heystyles.usuarios.core.domain.Cargo;
import com.heystyles.usuarios.core.dto.CargoResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CargoClienteImpl implements CargoClient {

    private final RestTemplate client;

    private final String urlBase;

    private interface SegmentPaths {
        String CARGO = "cargos";
    }

    public CargoClienteImpl(String urlBase, RestTemplate client) {
        this.client = client;
        this.urlBase = urlBase;
    }

    @Override
    public Cargo getCargo(Long cargoId) {
        UriComponentsBuilder urlBuilder = getUriProveedor()
                .pathSegment(String.valueOf(cargoId))
                .pathSegment("basico");
        return client.getForEntity(urlBuilder.toUriString(), CargoResponse.class).getBody().getData();
    }

    private UriComponentsBuilder getUriProveedor() {
        return UriComponentsBuilder.fromHttpUrl(urlBase).pathSegment(SegmentPaths.CARGO);
    }
}
