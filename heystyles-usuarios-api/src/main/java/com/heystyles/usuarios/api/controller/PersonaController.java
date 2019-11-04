package com.heystyles.usuarios.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.usuarios.api.service.PersonaService;
import com.heystyles.usuarios.core.domain.Persona;
import com.heystyles.usuarios.core.dto.PersonaListResponse;
import com.heystyles.usuarios.core.dto.PersonaResponse;
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

@RequestMapping(value = "/personas")
@RestController
@Api(value = "Persona Controller",
        description = "Controller para el manejo de las personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @ApiOperation(value = "Permite Crear una Persona en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Persona Creada")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody Persona persona) {
        Long idPersona = personaService.insert(persona);
        return Responses.responseEntity(new IdResponse(idPersona));
    }

    @ApiOperation(value = "Permite actualizar una Persona en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Persona Actualizada"),
            @ApiResponse(code = 404, message = "Persona no encontrada.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody Persona persona) {
        personaService.update(persona);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar una Persona de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Persona Eliminada."),
            @ApiResponse(code = 404, message = "Persona no encontrada.")
    })
    @DeleteMapping(value = "/{personaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "personaId") Long personaId) {
        personaService.delete(personaId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar una Persona en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Persona Encontrada."),
            @ApiResponse(code = 404, message = "Persona no encontrada.")
    })
    @GetMapping(value = "/{personaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonaResponse> getPersona(
            @NotNull @PathVariable(name = "personaId") Long personaId) {
        Persona persona = personaService.getPersona(personaId);
        return Responses.responseEntity(new PersonaResponse(persona));
    }

    @ApiOperation(value = "Permite Buscar una Persona por el numero de documento en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Persona Encontrada."),
            @ApiResponse(code = 404, message = "Persona no encontrada.")
    })
    @GetMapping(value = "/numeroDocumento/{numeroDocumento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonaResponse> getPersonaByNumeroDocumento(
            @NotNull @PathVariable(name = "numeroDocumento") String numeroDocumento) {
        Persona persona = personaService.getPersonaByNumeroDocumento(numeroDocumento);
        return Responses.responseEntity(new PersonaResponse(persona));
    }

    @ApiOperation(value = "Permite Listar todas las Persona de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Persoans Encontradas."),
            @ApiResponse(code = 404, message = "Personas no encontradas.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonaListResponse> getPersonas() {
        List<Persona> personas = personaService.findAll();
        return Responses.responseEntity(new PersonaListResponse(personas));
    }

}
