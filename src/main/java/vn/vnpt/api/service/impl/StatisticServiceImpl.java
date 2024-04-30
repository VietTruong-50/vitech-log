package vn.vnpt.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.vnpt.api.dto.out.statistic.StatisticalData;
import vn.vnpt.api.repository.StatisticRepository;
import vn.vnpt.api.service.StatisticService;
import vn.vnpt.common.model.SortPageIn;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    @Override
    public StatisticalData statisticalData() {
        return statisticRepository.statisticalData();
    }

    @Override
    public Object statisticSuccessOrder(LocalDate startDate, LocalDate endDate, SortPageIn sortPageIn) {
        return null;
    }

    @Override
    public Object top5seller(LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
