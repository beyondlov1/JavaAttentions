package week;

import org.apache.commons.beanutils.BeanUtils;
import sun.applet.Main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2018/12/01
 */
public class MainService {

    private PlanGenerator planGenerator;

    public MainService(){
        planGenerator = new PlanGenerator();
    }

    public List<WeekWork> getAllWeekWorks(){
        List<WeekWork> list = new ArrayList<WeekWork>();
        try {
            List<Plan> plans = planGenerator.getPlans();
            for (Plan plan : plans) {
                WeekWorksGenerator weekWorksGenerator = new WeekWorksGenerator(plan);
                List<WeekWork> weekWorks = weekWorksGenerator.generate();
                list.addAll(weekWorks);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {

    }
}
