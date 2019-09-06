package priv.bigant.aotomatic.autoCode.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import priv.bigant.aotomatic.autoCode.bean.SysUser;
import priv.bigant.aotomatic.common.BaseService;
import priv.bigant.aotomatic.common.QueryParam;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gaolei
 * @since 2019-08-15
 */
public interface SysUserService extends BaseService<SysUser> {

        /**
         *  分页查询
         * @param queryParam
         * @param sysUser
         * @return
         */
        IPage<SysUser> selectPage(SysUser sysUser,QueryParam queryParam);

}
