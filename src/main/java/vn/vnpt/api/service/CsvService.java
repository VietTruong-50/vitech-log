package vn.vnpt.api.service;

public interface CsvService {
    void logUserProductActivity(String message);

    void logPurchaseActivity(String message);

    void logError(String message);
}
