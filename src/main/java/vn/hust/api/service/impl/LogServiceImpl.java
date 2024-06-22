package vn.hust.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.api.repository.LogRepository;
import vn.hust.api.service.LogService;
import vn.hust.api.model.ErrorLog;
import vn.hust.api.model.UserActivity;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void logUserProductActivity(String message) {
        try {
            UserActivity userActivity = objectMapper.readValue(message, UserActivity.class);
            logRepository.logUserActivity(userActivity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logError(String message) {
        try {
            ErrorLog errorLog = objectMapper.readValue(message, ErrorLog.class);
            logRepository.logError(errorLog);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
