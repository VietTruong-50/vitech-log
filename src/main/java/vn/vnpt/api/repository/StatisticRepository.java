package vn.vnpt.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vn.vnpt.api.dto.out.statistic.StatisticalData;
import vn.vnpt.api.repository.helper.ProcedureCallerV3;
import vn.vnpt.api.repository.helper.ProcedureParameter;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticRepository {
    private final ProcedureCallerV3 procedureCallerV3;

    public StatisticalData statisticalData(){
        var outputs = procedureCallerV3.callNoRefCursor("statistical_data", List.of(
                ProcedureParameter.outputParam("out_product_numbers", Integer.class),
                ProcedureParameter.outputParam("out_order_numbers", Integer.class),
                ProcedureParameter.outputParam("out_customer_numbers", Integer.class),
                ProcedureParameter.outputParam("out_revenue", Long.class),
                ProcedureParameter.outputParam("out_result", String.class)
        ));

        if(!outputs.get("out_result").equals("success")){
            throw new RuntimeException("call statistical_data failed");
        }

        return StatisticalData.builder().build();
    }
}
