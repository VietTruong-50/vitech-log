package vn.vnpt.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import vn.vnpt.api.service.StatisticService;
import vn.vnpt.common.AbstractResponseController;
import vn.vnpt.common.model.SortPageIn;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/v1/statistic", produces = "application/json")
public class StatisticController extends AbstractResponseController {
    private final StatisticService statisticService;

    @GetMapping(value = "/data")
    public DeferredResult<ResponseEntity<?>> showAllCategory() {
        return responseEntityDeferredResult(() -> {
            log.info("[REQUEST]: path: /data");
            var result = statisticService.statisticalData();
            log.info("[RESPONSE]: res: Success!");
            return result;
        });
    }

}
