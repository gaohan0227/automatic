package priv.bigant.aotomatic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.bigant.aotomatic.common.SpringContextHolder;
import priv.bigant.aotomatic.autoCode.bean.SysUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringContextHolderTest {


    @Test
    public void test(){
        SysUser sysUser = new SysUser();
        String daoNameByEntity = SpringContextHolder.findDaoNameByEntity(sysUser, false);
    }

}
