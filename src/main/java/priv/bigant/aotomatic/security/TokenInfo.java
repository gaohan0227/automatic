package priv.bigant.aotomatic.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TokenInfo {

    public static final Integer DEFAULT_BUSINESSES_ID = -1;
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInfo.class);
    private static final String ENCRYPT_KEY = "KLWK14CJO5F68DY69EQ5IWBYA3F2DESJ";
    private String token;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 公司id
     */
    private Integer companyId;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 有效时长
     */
    private long expire;


    public TokenInfo() {
    }

    public TokenInfo(Integer userId, Integer companyId, long expire) {
        this.userId = userId;
        this.companyId = companyId;
        this.createTime = System.currentTimeMillis() / 1000;
        this.expire = expire;
        this.token = createToken(userId, companyId, createTime, expire);
    }

    /**
     * 解析出token字符串中包含的内容
     *
     * @param token
     * @return
     */
    public static TokenInfo parseToken(String token) {
        token = StringUtils.replace(token, " ", "+");
        String tokenSource = null;
        try {
            tokenSource = DesUtil.decryptString(ENCRYPT_KEY, token);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | UnsupportedEncodingException e) {
            return null;
        }
        if (StringUtils.isBlank(tokenSource)) {
            return null;
        }
        String[] tokens = tokenSource.split("_");
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(Integer.valueOf(tokens[0]));
        Integer companyId = Integer.valueOf(tokens[1]);
        tokenInfo.setCompanyId(DEFAULT_BUSINESSES_ID == companyId ? null : companyId);
        tokenInfo.setCreateTime(Long.valueOf(tokens[2]));
        tokenInfo.setExpire(Long.valueOf(tokens[3]));
        return tokenInfo;
    }


    /**
     * 生成token
     *
     * @param userId
     * @param companyId
     * @param createTime
     * @param expire
     * @return
     */
    public static String createToken(Integer userId, Integer companyId, Long createTime, Long expire) {
        String message = String.format("%s_%s_%s_%s", userId, companyId, createTime, expire);
        return encryptString(message);
    }

    public static String createToken(Integer userId, Integer companyId) {
        if (companyId == null) {
            companyId = DEFAULT_BUSINESSES_ID;
        }
        String message = String.format("%s_%s_%s_%s", userId, companyId, System.currentTimeMillis() / 1000, 3600L * 8);
        return encryptString(message);
    }

    private static String encryptString(String message) {
        String token = null;
        try {
            token = DesUtil.encryptString(ENCRYPT_KEY, message);
            token = token.trim();
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            LOGGER.error("get token error!", e);
        }
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
