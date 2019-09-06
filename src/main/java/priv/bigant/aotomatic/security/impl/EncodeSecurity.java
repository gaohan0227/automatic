package priv.bigant.aotomatic.security.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import priv.bigant.aotomatic.security.Security;
import priv.bigant.aotomatic.security.TokenInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 加密类型用户解析器
 */
@Component
public class EncodeSecurity implements Security {

    /**
     * 获取Request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    @Override
    public void add(String id, Object obj) {
        //TokenInfo.createToken();
    }

    @Override
    public void remove() {

    }

    @Override
    public void updateObj(Object obj) {

    }

    @Override
    public String getId() {
        return null;
    }
}
