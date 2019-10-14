package com.heystyles.usuarios.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.usuarios.api.service.ProveedorService;
import com.heystyles.usuarios.core.domain.Proveedor;
import com.heystyles.usuarios.core.dto.ProveedorListResponse;
import com.heystyles.usuarios.core.dto.ProveedorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(value = "/proveedores")
@RestController
@Api(value = "Proveedor Controller",
        description = "Controller para el manejo de proveedores.")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @ApiOperation(value = "Permite Crear un Proveedor en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Proveedor Creado")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody Proveedor proveedor) {
        Long idPersona = proveedorService.insert(proveedor);
        return Responses.responseEntity(new IdResponse(idPersona));
    }

    @ApiOperation(value = "Permite actualizar un Proveedor en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Proveedor Actualizado"),
            @ApiResponse(code = 404, message = "Proveedor no encontrado.")
    })
    @PutMapping(value = "/{proveedorId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(
            @NotNull @PathVariable(name = "proveedorId") Long proveedorId,
            @NotNull @Valid @RequestBody Proveedor proveedor) {
        proveedorService.update(proveedorId, proveedor);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar un Proveedor de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Proveedor Eliminado"),
            @ApiResponse(code = 404, message = "Proveedor no encontrada.")
    })
    @DeleteMapping(value = "/{proveedorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "proveedorId") Long proveedorId) {
        proveedorService.delete(proveedorId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar un Proveedor en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Proveedor Encontrado."),
            @ApiResponse(code = 404, message = "Proveedor no encontrado.")
    })
    @GetMapping(value = "/{proveedorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorResponse> getProveedor(
            @NotNull @PathVariable(name = "proveedorId") Long proveedorId) {
        Proveedor proveedor = proveedorService.getProveedor(proveedorId);
        return Responses.responseEntity(new ProveedorResponse(proveedor));
    }

    @ApiOperation(value = "Permite Listar todos los Proveedores de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Proveedores Encontrados."),
            @ApiResponse(code = 404, message = "Proveedor no encontradas.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorListResponse> getProveedores() {
        List<Proveedor> proveedores = proveedorService.findAll();
        return Responses.responseEntity(new ProveedorListResponse(proveedores));
    }


}
