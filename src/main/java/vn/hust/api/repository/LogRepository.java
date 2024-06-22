package vn.hust.api.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.hust.api.repository.helper.ProcedureCallerV3;
import vn.hust.api.repository.helper.ProcedureParameter;
import vn.hust.api.model.ErrorLog;
import vn.hust.api.model.UserActivity;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LogRepository {
    private final ProcedureCallerV3 procedureCallerV3;

    public void logUserActivity(UserActivity userActivity){
        log.info("[userActivity]: {}", userActivity);
        var outputs = procedureCallerV3.callNoRefCursor("log_user_activity", List.of(
                ProcedureParameter.inputParam("prs_customer_id", String.class, userActivity.getCustomerId()),
                ProcedureParameter.inputParam("prs_event_detail", String.class, userActivity.getEventDetail()),
                ProcedureParameter.inputParam("prs_event_type", String.class, userActivity.getEventType()),
                ProcedureParameter.outputParam("out_result", String.class)
        ));

        if(!outputs.get("out_result").equals("success")){
            throw new RuntimeException("call log_user_activity failed");
        }
    }

    public void logError(ErrorLog errorLog){
        log.info("[errorLog]: {}", errorLog);

        var outputs = procedureCallerV3.callNoRefCursor("log_error", List.of(
                ProcedureParameter.inputParam("prs_error_detail", String.class, errorLog.getErrorDetail()),
                ProcedureParameter.inputParam("prs_error_type", String.class, errorLog.getErrorType()),
                ProcedureParameter.outputParam("out_result", String.class)
        ));

        if(!outputs.get("out_result").equals("success")){
            throw new RuntimeException("call log_error failed");
        }
    }
}
