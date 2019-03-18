import java.util.List;

/**
 * @author beyondlov1
 * @date 2018/11/12
 */
public interface MetadataGenerator {
    List<ClassMetadata> getMetadatas(String fullClassName);
}
