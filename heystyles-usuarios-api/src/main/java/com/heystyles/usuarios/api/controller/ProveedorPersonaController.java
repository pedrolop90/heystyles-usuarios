package com.heystyles.usuarios.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.usuarios.api.service.ProveedorPersonaService;
import com.heystyles.usuarios.core.domain.ProveedorPersona;
import com.heystyles.usuarios.core.dto.ProveedorPersonaResponse;
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

@RequestMapping(value = "/proveedorPersonas")
@RestController
@Api(value = "Controller Proveedor Personas",
        description = "Controller para el manejo de contactos de los Proveedor")
public class ProveedorPersonaController {

    @Autowired
    private ProveedorPersonaService proveedorPersonaService;


    @ApiOperation(value = "Permite Crear un Contacto para un Proveedor en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Contacto Creado.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(@NotNull @Valid @RequestBody ProveedorPersona request) {
        Long idResponse = proveedorPersonaService.insert(request);
        return Responses.responseEntity(new IdResponse(idResponse));
    }

    @ApiOperation(value = "Permite actualizar un Contacto para un Proveedor en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Contacto Actualizado."),
            @ApiResponse(code = 404, message = "Contacto no encontrado.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody ProveedorPersona request) {
        proveedorPersonaService.update(request);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar un Contacto para un proveedor de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Contacto Eliminada"),
            @ApiResponse(code = 404, message = "Contacto no encontrada.")
    })
    @DeleteMapping(value = "/{proveedorPersonaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "proveedorPersonaId") Long proveedorPersonaId) {
        proveedorPersonaService.delete(proveedorPersonaId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar un Contacto para un proveedor en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Contacto Encontrado."),
            @ApiResponse(code = 404, message = "Contacto no encontrado.")
    })
    @GetMapping(value = "/{proveedorPersonaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorPersonaResponse> getContacto(
            @NotNull @PathVariable(name = "proveedorPersonaId") Long proveedorPersonaId) {
        ProveedorPersona cuentaBanco = proveedorPersonaService.getProveedorPersona(proveedorPersonaId);
        return Responses.responseEntity(new ProveedorPersonaResponse(cuentaBanco));
    }


}
