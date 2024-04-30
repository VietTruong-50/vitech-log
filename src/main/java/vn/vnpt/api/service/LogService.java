package vn.vnpt.api.service;

public interface LogService {
    void logUserProductActivity(String message);

    void logError(String message);
}
