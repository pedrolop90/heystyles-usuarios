package com.heystyles.usuarios.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.usuarios.api.service.CuentaBancoService;
import com.heystyles.usuarios.core.domain.CuentaBanco;
import com.heystyles.usuarios.core.dto.CuentaBancoResponse;
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

@RequestMapping(value = "/cuentasBanco")
@RestController
@Api(value = "Cuentas de Banco Controlller",
        description = "Controller para el manejo de cuentas de banco de un proveedor")
public class CuentaBancoController {

    @Autowired
    private CuentaBancoService cuentaBancoService;

    @ApiOperation(value = "Permite Crear una Cuenta de Banco para un Proveedor en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cuenta Banco Creada")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(@NotNull @Valid @RequestBody CuentaBanco request) {
        Long idResponse = cuentaBancoService.insert(request);
        return Responses.responseEntity(new IdResponse(idResponse));
    }

    @ApiOperation(value = "Permite actualizar una Cuenta Banco de una Proveedor en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cuenta Banco Actualizada"),
            @ApiResponse(code = 404, message = "Cuenta Banco no encontrado.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody CuentaBanco request) {
        cuentaBancoService.update(request);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar una Cuenta de Banco de un proveedor de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cuenta de Banco Eliminada"),
            @ApiResponse(code = 404, message = "Cuenta de Banco no encontrada.")
    })
    @DeleteMapping(value = "/{cuentaBancoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "cuentaBancoId") Long cuentaBancoId) {
        cuentaBancoService.delete(cuentaBancoId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar un Cuenta de Banco de un proveedor en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cuenta de Banco Encontrada."),
            @ApiResponse(code = 404, message = "Cuenta de Banco no encontrada.")
    })
    @GetMapping(value = "/{cuentaBancoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaBancoResponse> getCuentaBanco(
            @NotNull @PathVariable(name = "cuentaBancoId") Long cuentaBancoId) {
        CuentaBanco cuentaBanco = cuentaBancoService.getCuentaBanco(cuentaBancoId);
        return Responses.responseEntity(new CuentaBancoResponse(cuentaBanco));
    }
}
