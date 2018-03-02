package geny.resource.controller;

import geny.common.constant.Labels;
import geny.converter.DtoConverter;
import geny.exception.BusinessException;
import geny.service.serviceintf.LoyaltyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import geny.resource.dto.LoyaltyRequest;
import geny.resource.dto.LoyaltyResponse;
import geny.service.serviceintf.LoyaltyService;

import javax.validation.Valid;

/**
 * Created by dat on 1/15/2018.
 */

@RestController
public class LoyaltyServiceResource {

    private final LoyaltyService loyaltyService;
    private final LoyaltyTransactionService loyaltyTransactionService;

    @Autowired
    public LoyaltyServiceResource(LoyaltyService loyaltyService, LoyaltyTransactionService loyaltyTransactionService) {
        this.loyaltyService = loyaltyService;
        this.loyaltyTransactionService = loyaltyTransactionService;
    }

    @GetMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION + "/version")
    public String version() {
        return Labels.LOYALTY_SERVICE_RESOURCE_VERSION;
    }

    @PostMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION)
    public LoyaltyResponse updateLoyaltyPoints(@Valid @RequestBody LoyaltyRequest loyaltyRequest, BindingResult result) {
        if(result.hasErrors()) {
            throw BusinessException.invalidLoyaltyRequest();
        }
        return DtoConverter.toLoyaltyResponse(loyaltyService.updateLoyaltyPoints(loyaltyRequest));
    }

    @DeleteMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION)
    public LoyaltyResponse reverseLoyaltyPoints(@Valid @RequestBody LoyaltyRequest loyaltyRequest) {
        // TODO
        return new LoyaltyResponse();
    }

    @GetMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION + "/{phoneNumber}")
    public LoyaltyResponse findLoyaltyByClient(@PathVariable(value = "phoneNumber") String phoneNumber) {
        return DtoConverter.toLoyaltyResponse(loyaltyService.findLoyaltyByClient(phoneNumber));
    }
}
