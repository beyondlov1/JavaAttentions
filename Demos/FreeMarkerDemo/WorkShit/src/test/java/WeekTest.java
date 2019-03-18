import org.apache.commons.beanutils.BeanUtils;
import week.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2018/11/30
 */
public class WeekTest {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ParseException, IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        MainService mainService = new MainService();
        List<WeekWork> allWeekWorks = mainService.getAllWeekWorks();
        for (WeekWork weekWork : allWeekWorks) {
            WordWriter wordWriter = new FreeMarkerWriter(
                    "/tpl/",
                    "week.ftl",
                    "F:/work_repository/generation/week",
                    weekWork.getFileName());
            Map<String, String> map = BeanUtils.describe(weekWork);
            map.remove("fileName");
            map.put("recordDate",format.format(weekWork.getRecordDate()));
            wordWriter.write(map);
        }

    }
}
