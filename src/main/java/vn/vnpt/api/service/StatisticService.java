package vn.vnpt.api.service;

import vn.vnpt.api.dto.out.statistic.StatisticalData;
import vn.vnpt.common.model.SortPageIn;

import java.time.LocalDate;

public interface StatisticService {
     StatisticalData statisticalData();

     Object statisticSuccessOrder(LocalDate startDate, LocalDate endDate, SortPageIn sortPageIn);

     Object top5seller(LocalDate startDate, LocalDate endDate);
}
