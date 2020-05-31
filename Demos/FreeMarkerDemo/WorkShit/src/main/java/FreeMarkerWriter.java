import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2018/11/12
 */
public class FreeMarkerWriter implements WordWriter {

    private Configuration configuration;
    private String templatePath;
    private String templateName;
    private String targetPath;
    private String targetName;

    public FreeMarkerWriter(String templatePath,String templateName, String targetPath, String targetName) {
        this.templatePath = templatePath;
        this.templateName = templateName;
        this.targetPath = targetPath;
        this.targetName = targetName;
    }

    public void write(Object data) {
        //创建配置实例
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("UTF-8");
        try {
            //ftl模板文件统一放至 com.lun.template 包下面
            configuration.setClassForTemplateLoading(FreeMarkerWriter.class, templatePath);
            Template template = configuration.getTemplate(templateName);
            File outFile = new File(targetPath + File.separator + targetName);
            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            //生成文件
            template.process(data, out);
            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
