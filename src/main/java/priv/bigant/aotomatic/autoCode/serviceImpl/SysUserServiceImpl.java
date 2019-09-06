package priv.bigant.aotomatic.autoCode.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import priv.bigant.aotomatic.autoCode.bean.SysUser;
import priv.bigant.aotomatic.autoCode.dao.SysUserMapper;
import priv.bigant.aotomatic.autoCode.service.SysUserService;
import priv.bigant.aotomatic.common.BaseServiceImpl;
import priv.bigant.aotomatic.common.QueryParam;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gaolei
 * @since 2019-08-15
 */
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IPage<SysUser> selectPage(SysUser sysUser, QueryParam queryParam) {
        LambdaQueryWrapper<SysUser> entityWrapper = new QueryWrapper<SysUser>().lambda();
        getEntityWrapper(entityWrapper, sysUser);
        getEntityWrapperTimeBetween(entityWrapper, queryParam);
        getEntityWrapperTimeOrderBy(entityWrapper);
        return sysUserMapper.selectPage(queryParam, entityWrapper);
    }


    /**
     * 公共查询条件
     *
     * @param entityWrapper
     * @return
     */
    private LambdaQueryWrapper<SysUser> getEntityWrapper(LambdaQueryWrapper<SysUser> entityWrapper, SysUser sysUser) {
        //条件拼接
        if (ObjectUtils.isNotNull(sysUser.getId())) {
            entityWrapper.eq(SysUser::getId, sysUser.getId());
        }
        if (ObjectUtils.isNotNull(sysUser.getCompanyId())) {
            entityWrapper.eq(SysUser::getCompanyId, sysUser.getCompanyId());
        }
        if (ObjectUtils.isNotNull(sysUser.getOfficeId())) {
            entityWrapper.eq(SysUser::getOfficeId, sysUser.getOfficeId());
        }
        if (ObjectUtils.isNotNull(sysUser.getLoginName())) {
            entityWrapper.eq(SysUser::getLoginName, sysUser.getLoginName());
        }
        if (ObjectUtils.isNotNull(sysUser.getPassword())) {
            entityWrapper.eq(SysUser::getPassword, sysUser.getPassword());
        }
        if (ObjectUtils.isNotNull(sysUser.getNo())) {
            entityWrapper.eq(SysUser::getNo, sysUser.getNo());
        }
        if (ObjectUtils.isNotNull(sysUser.getName())) {
            entityWrapper.eq(SysUser::getName, sysUser.getName());
        }
        if (ObjectUtils.isNotNull(sysUser.getEmail())) {
            entityWrapper.eq(SysUser::getEmail, sysUser.getEmail());
        }
        if (ObjectUtils.isNotNull(sysUser.getPhone())) {
            entityWrapper.eq(SysUser::getPhone, sysUser.getPhone());
        }
        if (ObjectUtils.isNotNull(sysUser.getMobile())) {
            entityWrapper.eq(SysUser::getMobile, sysUser.getMobile());
        }
        if (ObjectUtils.isNotNull(sysUser.getUserType())) {
            entityWrapper.eq(SysUser::getUserType, sysUser.getUserType());
        }
        if (ObjectUtils.isNotNull(sysUser.getPhoto())) {
            entityWrapper.eq(SysUser::getPhoto, sysUser.getPhoto());
        }
        if (ObjectUtils.isNotNull(sysUser.getLoginIp())) {
            entityWrapper.eq(SysUser::getLoginIp, sysUser.getLoginIp());
        }
        if (ObjectUtils.isNotNull(sysUser.getLoginDate())) {
            entityWrapper.eq(SysUser::getLoginDate, sysUser.getLoginDate());
        }
        if (ObjectUtils.isNotNull(sysUser.getLoginFlag())) {
            entityWrapper.eq(SysUser::getLoginFlag, sysUser.getLoginFlag());
        }
        if (ObjectUtils.isNotNull(sysUser.getRemarks())) {
            entityWrapper.eq(SysUser::getRemarks, sysUser.getRemarks());
        }
        if (ObjectUtils.isNotNull(sysUser.getDelFlag())) {
            entityWrapper.eq(SysUser::getDelFlag, sysUser.getDelFlag());
        }
        if (ObjectUtils.isNotNull(sysUser.getQrcode())) {
            entityWrapper.eq(SysUser::getQrcode, sysUser.getQrcode());
        }
        if (ObjectUtils.isNotNull(sysUser.getSign())) {
            entityWrapper.eq(SysUser::getSign, sysUser.getSign());
        }
        if (ObjectUtils.isNotNull(sysUser.getCreatedId())) {
            entityWrapper.eq(SysUser::getCreatedId, sysUser.getCreatedId());
        }
        if (ObjectUtils.isNotNull(sysUser.getCreatedTime())) {
            entityWrapper.eq(SysUser::getCreatedTime, sysUser.getCreatedTime());
        }
        if (ObjectUtils.isNotNull(sysUser.getUpdatedId())) {
            entityWrapper.eq(SysUser::getUpdatedId, sysUser.getUpdatedId());
        }
        if (ObjectUtils.isNotNull(sysUser.getUpdatedTime())) {
            entityWrapper.eq(SysUser::getUpdatedTime, sysUser.getUpdatedTime());
        }
        return entityWrapper;
    }

    /**
     * 时间查询条件
     *
     * @param entityWrapper
     * @return
     */
    private LambdaQueryWrapper<SysUser> getEntityWrapperTimeBetween(LambdaQueryWrapper<SysUser> entityWrapper, QueryParam queryParam) {
        //条件拼接
        if (ObjectUtils.isNotNull(queryParam.getBeginTime())) {
            entityWrapper.ge(SysUser::getCreatedTime, queryParam.getBeginTime());
        }
        if (ObjectUtils.isNotNull(queryParam.getEndTime())) {
            entityWrapper.le(SysUser::getCreatedTime, queryParam.getEndTime());
        }
        return entityWrapper;
    }

    /**
     * 排序查询条件
     *
     * @param entityWrapper
     * @return
     */
    private LambdaQueryWrapper<SysUser> getEntityWrapperTimeOrderBy(LambdaQueryWrapper<SysUser> entityWrapper) {

        entityWrapper.orderBy(true, false, SysUser::getCreatedTime);

        return entityWrapper;
    }
}
