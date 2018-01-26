package geny.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import geny.common.enumtype.ClientActionTypeEnum;
import geny.common.enumtype.ProductTypeEnum;
import geny.common.enumtype.PromotionTypeEnum;
import geny.persistence.entity.Loyalty;
import geny.resource.dto.LoyaltyRequest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by dat on 1/16/2018.
 */
public class Testing {
    public static void main(String[] args) {
        LoyaltyRequest loyaltyRequest = new LoyaltyRequest();
        loyaltyRequest.setClientId(UUID.randomUUID());
        loyaltyRequest.setProductType(ProductTypeEnum.YO_DEBIT_CARD);
        loyaltyRequest.setClientActionType(ClientActionTypeEnum.PLANNED_TRIP_COMPLETION);
        loyaltyRequest.setPromotion(PromotionTypeEnum.SUMMER_CAMPAIGN);
        loyaltyRequest.setTransactionAmount(BigDecimal.TEN);

        Loyalty loyalty = new Loyalty();
        loyalty.setClientId(UUID.randomUUID());
        loyalty.setPoint(10);

        try {
            System.out.println(JsonMapper.convertObjectToJson(loyaltyRequest));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 3, 1);
        System.out.print(calendar.toString());
    }

}
