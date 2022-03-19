package com.geekseat.witchsaga.web.rest;

import com.geekseat.witchsaga.service.common.exceptions.DataNotFoundException;
import com.geekseat.witchsaga.service.person.PersonService;
import com.geekseat.witchsaga.service.person.dto.PersonDTO;
import com.geekseat.witchsaga.web.rest.error.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @Operation(summary = "Create or update a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return created person.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))}
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PersonDTO createOrUpdate(
            @RequestBody @Validated PersonDTO personDTO
    ) {
        return personService.createOrUpdate(personDTO);
    }

    @Operation(summary = "Get persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list of persons. Empty if not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))})
    })
    @GetMapping(path = "/")
    @ResponseBody
    public ResponseEntity<BaseResponse> getAllUsers() {
        return ResponseEntity.ok(BaseResponse.of(personService.findByAll()));
    }

    @Operation(summary = "Get person details from id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a person details.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Person is not found.")
    }
    )
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<BaseResponse> getPersonDetail(@PathVariable(name = "id") Long id) throws DataNotFoundException {
        return ResponseEntity.ok(
                BaseResponse.of(personService.findByOne(id))
        );
    }

    @Operation(summary = "Find the average number of people the witch killed on year of birth of those people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a person details.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Person is not found.")
    })
    @GetMapping(path = "/find-average")
    public ResponseEntity<BaseResponse> findAverage() throws DataNotFoundException {
        return ResponseEntity.ok(
                BaseResponse.of(personService.findAverage())
        );
    }

}
