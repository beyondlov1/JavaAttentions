package week;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2018/11/30
 */
public class WeekWorksGenerator {

    private Plan plan;

    public WeekWorksGenerator(Plan plan){
        this.plan = plan;
    }

    public List<WeekWork> generate(){

        List<WeekWork> list = new LinkedList<WeekWork>();

        Date start = plan.getStart();
        Date end = plan.getEnd();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        //获取第一个填写时间
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        while (dayOfWeek != Calendar.SUNDAY){
            calendar.add(Calendar.DATE,1);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        }

        //获取填写时间列表
        while (calendar.getTime().before(end)){
            WeekWork weekWork = new WeekWork();
            weekWork.setRecordDate(calendar.getTime());
            weekWork.setFileName(getFileName(calendar));
            list.add(weekWork);
            calendar.add(Calendar.DATE,7);
        }

        int index = 1;
        for (WeekWork work : list) {
            System.out.println(work.getFileName());
            System.out.println(work.getRecordDate());
            getWork(work,list,index);
            getPlan(work,list,index);
            index++;
        }

        return list;
    }

    private void getWork(WeekWork work, List<WeekWork> list, int index) {

        if (index==1){
            if (plan.getPrePlan()!=null&&plan.getPrePlan().getContent()!=null) {
                work.setWork(plan.getPrePlan().getContent());
            }
        }

        String suffix ="("+index+"/"+list.size()+")";
        Date recordDate = work.getRecordDate();
        long lastWeek = recordDate.getTime();
        Date lastWeekDate = new Date(lastWeek);
        if (lastWeekDate.after(plan.getEnd())){
            if (plan.getPostPlan()!=null){
                work.setWork(work.getWork()+"\r\n开始"+plan.getPostPlan().getContent()+suffix);
            }
        }else {
            work.setWork(work.getWork()+"\r\n"+plan.getContent()+suffix);
        }
    }

    private void getPlan(WeekWork work, List<WeekWork> list, int index) {
        String suffix = "("+index+"/"+list.size()+")";
        if (work.getRecordDate().after(plan.getEnd())){
            if (plan.getPostPlan()!=null){
                work.setPlan(plan.getPostPlan().getContent()+suffix);
            }
        }else {
            work.setPlan(plan.getContent()+suffix);
        }

        if (index == list.size()){
            if (plan.getPostPlan()!=null&&plan.getPostPlan().getContent()!=null) {
                work.setPlan(work.getPlan() + "\r\n开始"+plan.getPostPlan().getContent());
            }
        }
    }

    private String getFileName(Calendar calendar){
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyy.MM.dd");
        calendar.add(Calendar.DATE,-6);
        String fileName = "工作周报（";
        fileName+=fileNameFormat.format(calendar.getTime());
        fileName+="-";
        calendar.add(Calendar.DATE,6);
        fileName+=fileNameFormat.format(calendar.getTime());
        fileName+="）.doc";
        return fileName;
    }

    public static void main(String[] args) throws ParseException {
        Plan plan = new Plan();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,Calendar.JANUARY,11);
        plan.setStart(calendar.getTime());
        calendar.set(2018,Calendar.JANUARY,30);
        plan.setEnd(calendar.getTime());
        plan.setContent("plan content");

        PlanGenerator planGenerator = new PlanGenerator();
        List<Plan> plans = planGenerator.getPlans();

        for (Plan plan1 : plans) {
            WeekWorksGenerator weekWorksGenerator = new WeekWorksGenerator(plan1);
            List<WeekWork> WeekWorksOfOneWork = weekWorksGenerator.generate();
            for (WeekWork weekWork : WeekWorksOfOneWork) {
                System.out.println(weekWork.getRecordDate());
                System.out.println(weekWork.getWork());
                System.out.println(weekWork.getPlan());
            }
        }

    }
}
