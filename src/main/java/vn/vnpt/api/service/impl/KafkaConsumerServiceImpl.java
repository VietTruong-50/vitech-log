package vn.vnpt.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import vn.vnpt.api.model.UserActivity;
import vn.vnpt.api.service.KafkaConsumerService;
import vn.vnpt.api.service.LogService;
import vn.vnpt.common.exception.NotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final LogService logService;

    final String USER_ACTIVITY = "user-activity";
    final String EXCEPTION = "exception";

    @KafkaListener(topics = {"exception", "user-activity"}, groupId = "vitech-statistic")
    public void receiveMessage(@Payload String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("[topic]: {}, [message]: {}", topic, message);
        switch (topic) {
            case USER_ACTIVITY -> logService.logUserProductActivity(message);
            case EXCEPTION -> logService.logError(message);
            default -> throw new NotFoundException("Topic not found!");
        }
    }

//    private void pushNotification(String message) {
//        System.out.println(firebaseMessageService.sendNotification(NotificationMessage.builder().title(message).build()));
//    }
//}
}