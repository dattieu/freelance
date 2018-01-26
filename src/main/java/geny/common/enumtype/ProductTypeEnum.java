package geny.common.enumtype;

import com.google.common.collect.ImmutableSet;

/**
 * Created by dat on 1/15/2018.
 */

public enum ProductTypeEnum {
    YO_DEBIT_CARD(ImmutableSet.of(ClientActionTypeEnum.RETAIL, ClientActionTypeEnum.SALE)),
    YO_CREDIT_CARD(ImmutableSet.of(ClientActionTypeEnum.RETAIL, ClientActionTypeEnum.SALE)),
    YO_ACCOUNT(ImmutableSet.of(ClientActionTypeEnum.TOP_UP, ClientActionTypeEnum.DEPOSIT)),
    YO_PLUS(ImmutableSet.of(ClientActionTypeEnum.DISBURSE, ClientActionTypeEnum.DISBURSE));

    private final ImmutableSet<ClientActionTypeEnum> clientActionTypeEnumSet;

    ProductTypeEnum(ImmutableSet<ClientActionTypeEnum> clientActionTypeEnumSet) {
        this.clientActionTypeEnumSet = clientActionTypeEnumSet;
    }

    public final ImmutableSet<ClientActionTypeEnum> getValidClientActionForProductType() {
        return clientActionTypeEnumSet;
    }

    public static final boolean isValidClientActionWithProductType(ProductTypeEnum productType,
                                                                   ClientActionTypeEnum clientActionType) {
        return productType.clientActionTypeEnumSet.contains(clientActionType);
    }
}
