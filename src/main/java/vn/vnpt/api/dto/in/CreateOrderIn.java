package vn.vnpt.api.dto.in;

import lombok.Data;
import vn.vnpt.api.dto.model.OrderStatusEnum;
import vn.vnpt.api.dto.model.PaymentMethodEnum;

import java.time.LocalDate;

@Data
public class CreateOrderIn {
    private String shippingMethodId;
    private String addressId;
    private PaymentMethodEnum paymentMethodEnum;
    private String cardNumber;
    private String cardOwner;
    private String month;
    private String year;
    private OrderStatusEnum orderStatusEnum;
    private LocalDate deliveryDate;
    private String receiverName;
    private String phone;
    private String email;
    private String city;
    private String district;
    private String subDistrict;
    private String specificAddress;
    private String sessionToken;
}
