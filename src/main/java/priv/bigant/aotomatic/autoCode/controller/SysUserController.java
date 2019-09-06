package priv.bigant.aotomatic.autoCode.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import priv.bigant.aotomatic.autoCode.bean.SysUserRole;
import priv.bigant.aotomatic.common.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.bigant.aotomatic.autoCode.service.SysUserService;
import priv.bigant.aotomatic.autoCode.bean.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.bigant.aotomatic.common.jsp.DynamicJspListConfigVO;
import priv.bigant.aotomatic.common.jsp.child.JspElementAtagButton;

import java.lang.annotation.Annotation;
import java.util.*;


/**
 * @author gaolei
 * @date 2019-08-15
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    public SysUserService SysUserService;

    /**
     * 分页查询数据
     *
     * @param sysUser 查询条件
     * @return
     */
    /*@ResponseBody
    @RequestMapping("/getSysUserPageList")
    public String getSysUserList(SysUser sysUser, QueryParam queryParam, Model model) {
        model.addAttribute("page", SysUserService.selectPage(sysUser, queryParam));
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("dynamicJspListConfigVO", getDynamicJspListConfigVO(carTotal));
        return "modules/dynamic/dynamicList";
    }*/

    /**
     * 动态列表配置信息
     */
    private DynamicJspListConfigVO getDynamicJspListConfigVO(SysUser sysUser) {
        DynamicJspListConfigVO dvo = DynamicJspListConfigVO.newInstance(sysUser, "", null);
        dvo.toolButtonAddRowTag("添加");//添加按钮
        dvo.toolButtonDelRowTag("删除");//批量删除按钮
        dvo.tableDataSmart(SysUser::getName, SysUser::getCompanyId);
        //dvo.tableDataAddTDByJoinTable(SysUser::getStr, SysUserRole.class,SysUserRole::getRoleId);
        //dvo.tableDataAddTDByJoinTable(CarTotal._fuelId, FuelSaver.class, FuelSaver._fuelSaver);
        dvo.tableButtonAddAtagButtonBasic(JspElementAtagButton.ButtonType.OPERATION_TYPE_EDIT, JspElementAtagButton.ButtonType.OPERATION_TYPE_DELETE);//行记录的修改、删除按钮
        return dvo;
    }


    @RequiresPermissions("carTotal:carTotal:list")
    @RequestMapping(value = {"list", ""})
    public String list(SysUser sysUser, QueryParam queryParam, Model model) {
        IPage<SysUser> page = SysUserService.selectPage(sysUser, queryParam);
        model.addAttribute("page", page);
        model.addAttribute("carTotal", sysUser);
        model.addAttribute("dynamicJspListConfigVO", getDynamicJspListConfigVO(sysUser));
        return "modules/dynamic/dynamicList";
        //return "modules/cartotal/carTotalList";
    }


    /**
     * 保存和修改公用的
     *
     * @param sysUser 传递的实体
     * @return 0 失败  1 成功
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/sysUserSave")
    public ResponseInfo sysUserSave(SysUser sysUser) {
        try {
            return SysUserService.saveOrUpdate(sysUser) ? ResponseInfo.success() : ResponseInfo.systemError();
        } catch (Exception e) {
            logger.error("sysUserSave -=- {}", e.toString());
            return ResponseInfo.systemError();
        }
    }

    /**
     * 根据id删除对象
     *
     * @param id 实体ID
     * @return 0 失败  1 成功
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/sysUserDelete")
    public ResponseInfo sysUserDelete(String id) {
        try {
            return SysUserService.removeById(id) ? ResponseInfo.success() : ResponseInfo.systemError();
        } catch (Exception e) {
            logger.error("sysUserdelete -=- {}", e.toString());
            return ResponseInfo.systemError();
        }
    }

    /**
     * 批量删除对象
     *
     * @param ids 实体集合ID
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/sysUserBatchDelete")
    public ResponseInfo deleteBatchIds(String ids) {
        try {
            List idList = Arrays.asList(ids.split(","));
            return SysUserService.removeByIds(idList) ? ResponseInfo.success() : ResponseInfo.systemError();
        } catch (Exception e) {
            logger.error("sysUserBatchDelete -=- {}", e.toString());
            return ResponseInfo.systemError();
        }
    }


}
