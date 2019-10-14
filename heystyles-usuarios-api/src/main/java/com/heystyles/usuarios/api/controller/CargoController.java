package com.heystyles.usuarios.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.usuarios.api.service.CargoService;
import com.heystyles.usuarios.core.domain.Cargo;
import com.heystyles.usuarios.core.dto.CargoListResponse;
import com.heystyles.usuarios.core.dto.CargoResponse;
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

@RequestMapping(value = "/cargos")
@RestController
@Api(value = "Cargo Controller",
        description = "Controller para el manejo de los cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;


    @ApiOperation(value = "Permite Crear un Cargo en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cargo Creado.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody Cargo cargo) {
        Long idCargo = cargoService.insert(cargo);
        return Responses.responseEntity(new IdResponse(idCargo));
    }

    @ApiOperation(value = "Permite actualizar un Cargo en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cargo Actualizado."),
            @ApiResponse(code = 404, message = "Cargo no encontrado.")
    })
    @PutMapping(value = "/{cargoId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(
            @NotNull @PathVariable(name = "cargoId") Long cargoId,
            @NotNull @Valid @RequestBody Cargo cargo) {
        cargoService.update(cargoId, cargo);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar un Cargo de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cargo Eliminado."),
            @ApiResponse(code = 404, message = "Cargo no encontrado.")
    })
    @DeleteMapping(value = "/{cargoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "cargoId") Long cargoId) {
        cargoService.delete(cargoId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar un Cargo en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cargo Encontrado."),
            @ApiResponse(code = 404, message = "Cargo no encontrado.")
    })
    @GetMapping(value = "/{cargoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoResponse> getCargo(
            @NotNull @PathVariable(name = "cargoId") Long cargoId) {
        Cargo cargo = cargoService.getCargo(cargoId);
        return Responses.responseEntity(new CargoResponse(cargo));
    }

    @ApiOperation(value = "Permite Listar todas los Cargos de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cargos Encontrados."),
            @ApiResponse(code = 404, message = "Cargos no encontrados.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoListResponse> getCargos() {
        List<Cargo> cargos = cargoService.findAll();
        return Responses.responseEntity(new CargoListResponse(cargos));
    }


}
