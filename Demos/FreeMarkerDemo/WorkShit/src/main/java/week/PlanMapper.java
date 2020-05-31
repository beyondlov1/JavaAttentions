package week;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2018/11/30
 */
public interface PlanMapper {
    List<Map<String,Object>> selectAllPlan();
}
