package week;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

/**
 * @author beyondlov1
 * @date 2018/11/30
 */
public class PlanGenerator {

    private PlanMapper planMapper;

    private PlanConverter converter;

    public PlanGenerator(){
        try {
            setPlanMapper();
        } catch (IOException e) {
            e.printStackTrace();
        }
        converter = new PlanConverter();
    }

    public List<Plan> getPlans() throws ParseException {

        List<Map<String, Object>> planMaps = planMapper.selectAllPlan();

        List<Plan> plans = converter.convert(planMaps);

        int index = 0;
        for (Plan plan : plans) {
            if (index>0){
                plan.setPrePlan(plans.get(index-1));
            }
            if (index<plans.size()-1){
                plan.setPostPlan(plans.get(index+1));
            }
            index++;
        }

        return plans;
    }

    private List<Plan> simulatePlans(){
        Calendar calendar = Calendar.getInstance();
        List<Plan> plans = new ArrayList<Plan>();
        for (int i = 0; i < 10; i++) {
            Plan plan = new Plan();
            calendar.add(Calendar.MONTH,1);
            plan.setContent("content"+i);
            plan.setStart(calendar.getTime());
            calendar.add(Calendar.MONTH,1);
            plan.setEnd(calendar.getTime());
            plans.add(plan);
        }
        return plans;
    }

    public void setPlanMapper() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 然后根据 sqlSessionFactory 得到 session
        SqlSession session = sqlSessionFactory.openSession();
        this.planMapper = session.getMapper(PlanMapper.class);
    }

    public PlanConverter getConverter() {
        return converter;
    }

    public void setConverter(PlanConverter converter) {
        this.converter = converter;
    }
}
