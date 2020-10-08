import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ezio
 * @date 2020/10/2 18:03
 */
//public class TestMBG {
//    @Test
//    public void testGenerator() throws Exception {
//        List<String> warnings=new ArrayList<String>();
//        boolean overWriter=true;//指向配置文件　　
//        File configFile=new File("src/main/resources/mbg.xml");
//        ConfigurationParser cp=new ConfigurationParser(warnings);
//        Configuration config=cp.parseConfiguration(configFile);
//        DefaultShellCallback callback=new DefaultShellCallback(overWriter);
//        MyBatisGenerator myBatisGenerator=new MyBatisGenerator(config,callback,warnings);
//        myBatisGenerator.generate(null);
//    }
//}
