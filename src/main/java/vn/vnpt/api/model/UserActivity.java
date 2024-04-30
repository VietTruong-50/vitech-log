package vn.vnpt.api.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class UserActivity {
    private String customerId;
    private String eventType;
    private String eventDetail;
}
