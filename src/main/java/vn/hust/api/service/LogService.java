package vn.hust.api.service;

public interface LogService {
    void logUserProductActivity(String message);

    void logError(String message);
}
