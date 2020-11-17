package cn.edu.bupt;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        String s = "小叶是猪！";
        String basePath = this.getClass().getResource("upload.properties").getPath();
        System.out.println(basePath);
    }
}
