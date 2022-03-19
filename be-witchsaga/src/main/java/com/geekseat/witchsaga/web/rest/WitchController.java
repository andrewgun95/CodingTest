package com.geekseat.witchsaga.web.rest;

import com.geekseat.witchsaga.service.common.exceptions.DataNotFoundException;
import com.geekseat.witchsaga.service.witch.WitchService;
import com.geekseat.witchsaga.service.witch.dto.KillingDTO;
import com.geekseat.witchsaga.service.witch.dto.WitchDTO;
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
@RequestMapping("v1/witch")
public class WitchController {

    private final WitchService witchService;

    public WitchController(WitchService witchService) {
        this.witchService = witchService;
    }

    @PostMapping
    @Operation(summary = "Create or update a witch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return created witch.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WitchDTO.class))}
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public WitchDTO createOrUpdate(
            @RequestBody @Validated WitchDTO witchDTO
    ) {
        return witchService.createOrUpdate(witchDTO);
    }

    @Operation(summary = "Get witches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list of witches. Empty if not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WitchDTO.class))})
    })
    @GetMapping(path = "/")
    @ResponseBody
    public ResponseEntity<BaseResponse> getAllWitches() {
        return ResponseEntity.ok(BaseResponse.of(witchService.findByAll()));
    }

    @Operation(summary = "Get witch details from id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a witch details.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WitchDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Witch is not found.")
    }
    )
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<BaseResponse> getWitchDetail(@PathVariable(name = "id") Long id) throws DataNotFoundException {
        return ResponseEntity.ok(
                BaseResponse.of(witchService.findByOne(id))
        );
    }

    @PostMapping(path = "/start-kill")
    @Operation(summary = "Trigger witch to start killing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Triggered!.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WitchDTO.class))}
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void startKilling(@RequestBody @Validated KillingDTO killingDTO) throws DataNotFoundException {
        witchService.startKilling(killingDTO);
    }

}
