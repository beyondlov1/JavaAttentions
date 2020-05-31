import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2018/11/12
 */
public class ReflectGenerator implements MetadataGenerator {

    public List<ClassMetadata> getMetadatas(String fullClassName) {
        try {
            List<ClassMetadata> list = new ArrayList<ClassMetadata>();
            Class<?> clazz = Class.forName(fullClassName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                ClassMetadata metadata = new ClassMetadata();
                metadata.setMethodName(method.getName());
                Class<?>[] parameterTypes = method.getParameterTypes();
                metadata.setParamType(splice(parameterTypes));
                metadata.setReturnType(method.getReturnType()==null?"":method.getReturnType().getSimpleName());
                metadata.setDescription("");
                metadata.setName(clazz.getSimpleName());
                list.add(metadata);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String splice(Class<?>[] clazz){
        StringBuilder result = new StringBuilder();
        for (Class c : clazz) {
            result.append(",");
            result.append(c.getSimpleName());
        }
        if (result.toString().length()>1){
            return result.substring(1);
        }
        return "";
    }
}
