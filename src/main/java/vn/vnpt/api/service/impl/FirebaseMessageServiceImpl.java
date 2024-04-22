package vn.vnpt.api.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.vnpt.api.dto.in.notification.NotificationMessage;
import vn.vnpt.api.service.FirebaseMessageService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FirebaseMessageServiceImpl implements FirebaseMessageService {
    private final FirebaseMessaging firebaseMessaging;

    @Override
    public String sendNotification(NotificationMessage notificationMessage) {
        Notification notification = Notification.builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImage())
                .build();

        Message message = Message.builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .putAllData(notificationMessage.getData())
                .build();

        try {
            firebaseMessaging.subscribeToTopic(new ArrayList<>(), "");
            firebaseMessaging.send(message);
            return "Successfully send notification";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Error send notification";
        }
    }
}