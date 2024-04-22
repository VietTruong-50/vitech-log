package vn.vnpt.api.service;

import vn.vnpt.api.dto.in.notification.NotificationMessage;

public interface FirebaseMessageService {
    String sendNotification(NotificationMessage notificationMessage);
}
