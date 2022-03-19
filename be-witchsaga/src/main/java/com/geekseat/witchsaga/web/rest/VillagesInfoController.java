package com.geekseat.witchsaga.web.rest;


import com.geekseat.witchsaga.service.village.VillageInfoService;
import com.geekseat.witchsaga.service.witch.dto.WitchDTO;
import com.geekseat.witchsaga.web.rest.error.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/villages")
public class VillagesInfoController {

    private final VillageInfoService villageInfoService;

    public VillagesInfoController(VillageInfoService villageInfoService) {
        this.villageInfoService = villageInfoService;
    }

    @Operation(summary = "Get village infos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list of villages. Empty if not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WitchDTO.class))})
    })
    @GetMapping(path = "/")
    @ResponseBody
    public ResponseEntity<BaseResponse> getAllVillageInfos() {
        return ResponseEntity.ok(BaseResponse.of(villageInfoService.findByAll()));
    }

}
