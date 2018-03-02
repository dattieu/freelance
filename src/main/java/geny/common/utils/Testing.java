package geny.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import geny.common.enumtype.ClientActionTypeEnum;
import geny.common.enumtype.ProductTypeEnum;
import geny.common.enumtype.PromotionTypeEnum;
import geny.persistence.entity.Loyalty;
import geny.persistence.entity.LoyaltyTransaction;
import geny.resource.dto.LoyaltyRequest;
import geny.service.serviceimpl.BaseLoyaltyBean;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by dat on 1/16/2018.
 */
public class Testing {
    public static void main(String[] args) {
        LoyaltyRequest loyaltyRequest = new LoyaltyRequest();
        loyaltyRequest.setPhoneNumber("0946471974");
        loyaltyRequest.setClientId(UUID.randomUUID());
        loyaltyRequest.setTransactionId(UUID.randomUUID());
        loyaltyRequest.setProductType(ProductTypeEnum.YO_DEBIT_CARD);
        loyaltyRequest.setClientActionType(ClientActionTypeEnum.RETAIL);
        loyaltyRequest.setPromotion(PromotionTypeEnum.OTHER_CAMPAIGN);
        loyaltyRequest.setTransactionAmount(new BigDecimal(20));
        loyaltyRequest.setCrossBorderTransaction(false);
        loyaltyRequest.setReversalLoyalty(false);

        try {
            System.out.println(JsonMapper.convertObjectToJson(loyaltyRequest));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
