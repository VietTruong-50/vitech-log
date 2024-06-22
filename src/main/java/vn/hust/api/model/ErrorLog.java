package vn.hust.api.model;

import lombok.Data;

@Data
public class ErrorLog {
    private String id;
    private String errorDetail;
    private String errorType;
    private String timestamp;
}
