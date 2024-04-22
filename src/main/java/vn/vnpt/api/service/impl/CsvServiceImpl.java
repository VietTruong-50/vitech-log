package vn.vnpt.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.vnpt.api.service.CsvService;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CsvServiceImpl implements CsvService {
    private static final String USER_ACTIVITY_PATH = "/files/user_activity_log.csv";
    private static final String EXCEPTION_PATH = "files/exception.csv";
    private static final String PURCHASE_ORDER_PATH = "/files/purchase_order.csv";

    @Override
    public void logUserProductActivity(String userId) {
        // Generate a timestamp for the current activity
        LocalDateTime timestamp = LocalDateTime.now();

        // Format the data as a CSV row
        String csvRow = String.format("%s,%s,%s,%s,%s\n", timestamp, userId, "click_product", "productId", "productName");

        // Write the CSV row to the file
        try (FileWriter csvWriter = new FileWriter(USER_ACTIVITY_PATH, true)) {
            csvWriter.append(csvRow);
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it, retry, etc.)
        }
    }

    @Override
    public void logPurchaseActivity(String message) {
        // Generate a timestamp for the current activity
        LocalDateTime timestamp = LocalDateTime.now();

        // Format the data as a CSV row
        String csvRow = String.format("%s,%s,%s,%s,%s,%s\n", timestamp, message, "purchase", "productId", "productName", "totalPrice");

        // Write the CSV row to the file
        try (FileWriter csvWriter = new FileWriter(PURCHASE_ORDER_PATH, true)) {
            csvWriter.append(csvRow);
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it, retry, etc.)
        }
    }

    @Override
    public void logError(String message) {
        LocalDateTime timestamp = LocalDateTime.now();

        // Format the data as a CSV row
        String csvRow = String.format("%s,%s\n", message, timestamp);

        try (FileWriter csvWriter = new FileWriter(EXCEPTION_PATH, true)) {
            csvWriter.append(csvRow);

            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
