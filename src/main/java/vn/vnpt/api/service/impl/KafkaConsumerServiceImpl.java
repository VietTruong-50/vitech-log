package vn.vnpt.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import vn.vnpt.api.dto.in.notification.NotificationMessage;
import vn.vnpt.api.service.CsvService;
import vn.vnpt.api.service.FirebaseMessageService;
import vn.vnpt.api.service.KafkaConsumerService;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final FirebaseMessageService firebaseMessageService;
    private final CsvService csvService;

    @KafkaListener(topics = {"PurchaseTopic", "Exception", "ClickProductTopic"}, groupId = "vitech-statistic")
    public void receiveOrderMessage(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println(message);
        System.out.println(topic);
        switch (topic) {
            case "ClickProductTopic" -> csvService.logUserProductActivity(message);
            case "Exception" -> csvService.logError(message);
            default -> {}
        }
    }

    private void pushNotification(String message) {
        System.out.println(firebaseMessageService.sendNotification(NotificationMessage.builder().title(message).build()));
    }
}
