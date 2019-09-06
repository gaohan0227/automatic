package priv.bigant.aotomatic.common;

public class ResponseInfo {
    /**
     * 返回数据
     */
    private Object data;

    private Integer code;
    private String message;

    public ResponseInfo(Object data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }


    public ResponseInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseInfo(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public ResponseInfo(Integer code) {
        this.code = code;
    }

    public ResponseInfo() {
    }

    public static ResponseInfo systemError() {
        return new ResponseInfo(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg());
    }

    public static ResponseInfo paramError(String msg) {
        return new ResponseInfo(ResultEnum.PARAM_ERROR.getCode(), msg);
    }

    public static ResponseInfo error(String msg) {
        return new ResponseInfo(ResultEnum.ERROR.getCode(), msg);
    }

    public static ResponseInfo error(ResultEnum resultEnum) {
        return new ResponseInfo(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static ResponseInfo error(ResultEnum resultEnum, String msg) {
        return new ResponseInfo(resultEnum.getCode(), msg);
    }

    public static ResponseInfo success() {
        return new ResponseInfo(ResultEnum.SUCCESS.getCode());
    }

    public static ResponseInfo success(Object data) {
        return new ResponseInfo(ResultEnum.SUCCESS.getCode(), data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum ResultEnum {
        ERROR(500, "请求异常"),
        SYSTEM_ERROR(510, "系统错误"),
        PARAM_ERROR(520, "参数错误"),
        LOGIN_ERROR(530, "请先登陆"),
        COMPANY_EXIST_ERROR(550, "请勿重复添加公司"),
        COMPANY_UPDATE_NAME_ALREADY_AUTH_ERROR(553, "认证用户不允许更改公司名称"),
        COMPANY_UPDATE_NAME_LESS_ONE_MONTH_ERROR(554, "一个月内不允许多次更改公司名称"),
        EXTRACT_AGAIN_ERROR(610, "提现已处理，请勿重复处理"),
        EXTRACT_ERROR(620, "提现失败"),
        SUCCESS(200, "OK");

        private Integer code;
        private String msg;

        ResultEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

/*    public boolean isSuccess() {
        return 200 == this.getCode();
    }*/
}
