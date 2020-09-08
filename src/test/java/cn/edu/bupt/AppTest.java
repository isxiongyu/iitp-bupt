package cn.edu.bupt;

import static org.junit.Assert.assertTrue;

import cn.xiongyu.common.beanUtils.MyBeanUtils;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        System.out.println(MyBeanUtils.getUUID());
        System.out.println(MyBeanUtils.getUUID().length());
    }
}
