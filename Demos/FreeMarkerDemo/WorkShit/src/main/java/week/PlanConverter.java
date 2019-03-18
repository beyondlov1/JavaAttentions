package week;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2018/11/30
 */
public class PlanConverter {
    public List<Plan> convert(List<Map<String,Object>> planList) throws ParseException {
        List<Plan> plans = new ArrayList<Plan>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        for (Map planMap : planList) {
            Date start = simpleDateFormat.parse((String) planMap.get("start"));
            Date end = simpleDateFormat.parse((String) planMap.get("end"));
            Plan plan = new Plan();
            plan.setStart(start);
            plan.setEnd(end);
            plan.setContent((String) planMap.get("content"));
            plans.add(plan);
        }
        return plans;
    }
}
