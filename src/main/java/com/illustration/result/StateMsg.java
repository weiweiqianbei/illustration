package com.illustration.result;

public enum StateMsg {
    StatusMsg_100(100,"继续"),
    StatusMsg_101(101,"Switching Protocols"),
    StatusMsg_102(102,"Processing"),
    StatusMsg_103(103,"Early Hints"),

    StatusMsg_200(200,"OK"),
    StatusMsg_201(201,"已创建"),
    StatusMsg_202(202,"已接受请求"),
    StatusMsg_203(203,"非授权信息"),
    StatusMsg_204(204,"No Content"),
    StatusMsg_205(205,"重置内容"),
    StatusMsg_206(206,"Partial Content"),
    StatusMsg_207(207,"Multi-Status"),
    StatusMsg_208(208,"Already Reported"),

    StatusMsg_226(226,"IM Used"),

    StatusMsg_300(300,"请求拥有多个可能的响应"),
    StatusMsg_301(301,"请求资源的 URL 已永久更改"),
    StatusMsg_302(302,"所请求资源的 URI 已暂时更改"),
    StatusMsg_303(303,"查看其它地址"),
    StatusMsg_304(304,"Not Modified"),
    StatusMsg_305(305,"Use Proxy"),
    StatusMsg_306(306,"Unused"),
    StatusMsg_307(307,"临时重定向"),

    StatusMsg_400(400,"无法请求"),
    StatusMsg_401(401, "没有登录"),
    StatusMsg_402(402, "Payment Required"),
    StatusMsg_403(403, "没有权限"),
    StatusMsg_404(404, "Not Found"),
    StatusMsg_405(405, "Method Not Allowed"),
    StatusMsg_406(406, "Not Acceptable"),
    StatusMsg_407(407, "Proxy Authentication Required"),
    StatusMsg_408(408, "请求时间过长"),
    StatusMsg_409(409, "Conflict"),
    StatusMsg_410(410, "资源已经不存在"),
    StatusMsg_411(411, "Length Required"),
    StatusMsg_412(412, "Precondition Failed"),
    StatusMsg_413(413, "Payload Too Large"),
    StatusMsg_414(414, "请求的URI过长"),
    StatusMsg_415(415, "Unsupported Media Type"),
    StatusMsg_416(416, "请求的范围无效"),
    StatusMsg_417(417, "I'm a teapot"),
    StatusMsg_418(418, "服务端拒绝用茶壶煮咖啡"),

    StatusMsg_421(421, "请求被定向到无法生成响应的服务器"),
    StatusMsg_422(422, "Unprocessable Entity"),
    StatusMsg_423(423, "正在访问的资源已锁定"),
    StatusMsg_424(424, "请求失败"),
    StatusMsg_425(425, "Too Early"),
    StatusMsg_426(426, "Upgrade Required"),
    StatusMsg_428(428, "Precondition Required"),
    StatusMsg_429(429, "Too Many Requests"),

    StatusMsg_431(431, "Request Header Fields Too Large"),
    StatusMsg_432(432, "用户名或密码错误"),
    StatusMsg_451(451, "Unavailable For Legal Reasons"),

    StatusMsg_500(500, "Internal Server Error"),
    ;

    StateMsg(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    private String msg;
    private int state;

    public static String getMsg(int state) {
        for (StateMsg stateMsg : StateMsg.values()) {
            if (stateMsg.state == state) {
                return stateMsg.msg;
            }
        }
        return StatusMsg_400.msg;
    }

    public static StateMsg of(int state) {
        for (StateMsg stateMsg : StateMsg.values()) {
            if (stateMsg.state == state) {
                return stateMsg;
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
