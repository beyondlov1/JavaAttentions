import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author beyondlov1
 * @date 2018/11/12
 */
public class WordWriterTest {
    @Test
    public void writeToFile(){
        WordWriter wordWriter = new FreeMarkerWriter(
                "/tpl/",
                "week.ftl",
                "F:/work_repository/generation",
                "test1.doc");
        MetadataGenerator metadataGenerator  = new ReflectGenerator();

        List<ClassMetadata> list = metadataGenerator.getMetadatas("ClassMetadata");
        Map<String, List<ClassMetadata>> map = new HashMap<String, List<ClassMetadata>>();
        map.put("metadataList",list);

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat fileNameFormate = new SimpleDateFormat("yyyy.MM.dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,Calendar.AUGUST,21);

        for (int i = 0; i < 55; i++) {

            Map map1 = new HashMap();
            Map map2 = new HashMap();
            map2.put("recordDate",format.format(calendar.getTime()));
            map1.put("data",map2);

            calendar.add(Calendar.DATE,-6);
            String fileName = "工作周报（";
            fileName+=fileNameFormate.format(calendar.getTime());
            fileName+="-";
            calendar.add(Calendar.DATE,6);
            fileName+=fileNameFormate.format(calendar.getTime());
            fileName+="）.doc";

            calendar.add(Calendar.DATE,6);

            WordWriter wordWriter1 = new FreeMarkerWriter(
                    "/tpl/",
                    "week.ftl",
                    "F:/work_repository/generation",
                    fileName);
            wordWriter1.write(map1);
            calendar.add(Calendar.DATE,1);
        }

    }
}
