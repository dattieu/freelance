package geny.resource.controller;

import geny.common.constant.Labels;
import geny.converter.DtoConverter;
import geny.persistence.entity.Loyalty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import geny.resource.dto.LoyaltyRequest;
import geny.resource.dto.LoyaltyResponse;
import geny.service.serviceintf.LoyaltyService;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Created by dat on 1/15/2018.
 */

@RequestScope
@RestController
public class LoyaltyServiceResource {

    private final LoyaltyService loyaltyService;

    @Autowired
    public LoyaltyServiceResource(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @GetMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION + "/version")
    public String version() {
        return Labels.LOYALTY_SERVICE_RESOURCE_VERSION;
    }

    @PostMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION)
    public LoyaltyResponse updateLoyaltyPoints(@RequestBody @Valid LoyaltyRequest loyaltyRequest) {
        return DtoConverter.toLoyaltyResponse(loyaltyService.updateLoyaltyPoints(loyaltyRequest));
    }

    @GetMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION + "/{clientId}")
    public LoyaltyResponse findLoyaltyByClient(@PathVariable(value = "clientId") UUID clientId) {
        return DtoConverter.toLoyaltyResponse(loyaltyService.findLoyaltyByClient(clientId));
    }

    // FIXME only for testing, will be removed
    @GetMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION)
    public List<Loyalty> findAll() {
        return loyaltyService.findAll();
    }

    // TODO reverse loyalty points??

    // TODO how about redemption???
    @GetMapping(Labels.LOYALTY_SERVICE_RESOURCE_VERSION + Labels.LOYALTY_REDEMPTION + "/{clientId}")
    public void findRedemptionByClient(@PathVariable(value = "clientId") UUID clientId) {

    }
}
