package geny.common.enumtype;

import org.apache.commons.lang3.EnumUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dat on 1/21/2018.
 */
public enum ClientActionTypeEnum {
    RETAIL(TransactionTypeEnum.EVERYDAY_USE),
    SALE(TransactionTypeEnum.EVERYDAY_USE),
    TOP_UP(TransactionTypeEnum.EVERYDAY_USE),
    DEPOSIT(TransactionTypeEnum.EVERYDAY_USE),
    DISBURSE(TransactionTypeEnum.EVERYDAY_USE),

    KYC_PROCESS_FULFILLMENT(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    YO_PLUS_APPROVAL(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    FIRST_TRIP_PLAN(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    NEXT_TRIP_PLAN(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    PLANNED_TRIP_COMPLETION(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    ON_TIME_YO_PLUS_PAYMENT(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    ON_TIME_YO_PLUS_CREDIT_CARD_PAYMENT(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    FIRST_YO_DEBIT_TRANSACTION(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    ANNUAL_HUNDRED_MILLION_VND_TRANSACTION(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    CUSTOMER_BIRTHDAY(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    ANNUAL_CARD_ANNIVERSARY(TransactionTypeEnum.BANK_AND_APP_INTERACTION),
    REFERRED_FRIEND_KYC_PROCESS_FULFILLMENT(TransactionTypeEnum.BANK_AND_APP_INTERACTION);

    private enum TransactionTypeEnum {
        EVERYDAY_USE,
        BANK_AND_APP_INTERACTION
    }

    private final TransactionTypeEnum transactionType;

    ClientActionTypeEnum(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public final TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public static final boolean isValidClientActionType(ClientActionTypeEnum clientActionTypeEnum) {
        return EnumUtils.isValidEnum(ClientActionTypeEnum.class, clientActionTypeEnum.name());
    }

    public static final boolean isValidTransactionType(TransactionTypeEnum transactionType) {
        return EnumUtils.isValidEnum(TransactionTypeEnum.class, transactionType.name());
    }

    public static final boolean isEverydayUseClientAction(ClientActionTypeEnum clientActionTypeEnum) {
        return getEverydayUseClientAction().contains(clientActionTypeEnum);
    }

    public static final Set<ClientActionTypeEnum> getEverydayUseClientAction() {
        final Set<ClientActionTypeEnum> clientActionTypeEnumSet = new HashSet<>();

        for (ClientActionTypeEnum clientAction : ClientActionTypeEnum.values()) {
            if (TransactionTypeEnum.EVERYDAY_USE.equals(clientAction.transactionType)) {
                clientActionTypeEnumSet.add(clientAction);
            }
        }

        return clientActionTypeEnumSet;
    }

    public static final Set<ClientActionTypeEnum> getBankAppClientAction() {
        final Set<ClientActionTypeEnum> clientActionTypeEnumSet = new HashSet<>();

        for (ClientActionTypeEnum clientAction : ClientActionTypeEnum.values()) {
            if (TransactionTypeEnum.BANK_AND_APP_INTERACTION.equals(clientAction.transactionType)) {
                clientActionTypeEnumSet.add(clientAction);
            }
        }

        return clientActionTypeEnumSet;
    }
}
