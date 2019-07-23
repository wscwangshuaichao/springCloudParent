package springcloud.learn.wsc.servicehi.enums;

import lombok.Getter;

/**
 * 返回状态码
 * @author ChongLi
 * @date 2019/05/22
 */
@Getter
public enum LcOpEndpointResultCodeEnum {


	// 服务异常
	OK("200","成功"),
	FAILED("500","哎呀，服务开小差了，请稍后重试"),
	USERNAME_NOT_EXISTS("1001", "您当前没有龙币的权限,如需访问,请联系管理员！"),
	NOT_LOGIN("1002", "您未登陆或登陆超时,请重新登陆!"),
	PASSWORD_ERROR("1003", "密码错误!"),
	TRADING_SERACH_ACCNO_ISNULL("1004", "账户编号为空!"),
	USER_ERROR("1012", "登陆异常!"),
	USER_LOGIN_ERROR("1012", "验证失败,请稍后重新尝试!"),
	SSO_LOGIN_TOKEN_ISNULL("1013", "sso登陆token返回空!"),
	TOKEN_GET_USER_ERROR("1014", "用户信息未获取到!"),
	//GET_ACCOUNT_PAGELIST_ERROR("1004", "分页获取账户列表异常"),
	;

	private String code;
	private String msg;

	LcOpEndpointResultCodeEnum(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
}
