package com.taotao.portal.util;

/**
 * @author: tushengtao
 * @date: 2019/12/10
 * @Description:  支付宝支付配置类
 * @param:
 * @return:
 */
public class AlipayConfig {
//
//	public static String app_id = "";//在后台获取（必须配置）
//
//	public static String merchant_private_key = "";//教程查看获取方式（必须配置）
//
//	public static String alipay_public_key = "";//教程查看获取方式（必须配置）


//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号

	public static String app_id ="2016101500696059";

	// 商户私钥，您的PKCS8格式RSA2私钥

	public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCppo+yXkUsQMJVxKBSAtKkq1+cjmMY+ifZGRyzpetnis8ybiIGJaaBkl2pwrNmqnuPeKkzJc2sou51Z5mffyUrvbQISwwN3IbIyS796Qzg4pPVakO7mB2u+dQubb0ARyKt9qAqbrW+J6uBcpSqwoKRqcUkZMMHbBKIvQoyI1JURCBHLAytZjO7+PApMR+zAjfunDqoiHv1ETFpgjS3kPwOQwjbJIx62Wr6KAqH7Nd2/nIGRQ2W2LgZcT2/Ys10MT4sxSd2182DLsgVuZR5+3aeDiNTT66YFo/My7cUq2cIrLN5XG0IUR9I1MiZpgikVgRTRFScLLoHQ27ndLmpIVTlAgMBAAECggEAai9yqbYPnc860+wqXKqfpaoOQjNtG2kYH+5z5I3pdfmQDoSYjzhpq5gtP+A54fy7MeXYIcd7oJ6qmpmpsgcnAOXQFZDDjrmFM4+Y9hw0j13CIkZM9tITYj3E7PqK3e2oazJ+tZBt+QNjebZ6DxeIGhwk5M6cs7yVLTkFdLRfB5ZHJGHzljZc/GX51QdK1YDBbt39y+doYFVKJJTVi6XtVb9hGGYbg0TRGcMRJc+H1buK7gugBucyn5pzOgX+XuA7h781ViViaHz2cT+klwVEUaKamaLIRzd+aLH2vKCQjI1KGr2SStnWcZGX+bjw064TcA7F0iqykVsiloK2sf1bUQKBgQDeHXdvJCh97R4BhWGNxX5tCvxZzGjBYEv9jXZDSXxvCvdJ2VUqKcnYK9lrGlVQR9L6YDI8O4OeJBG7aneWA7JjUSe6dSWpqL0wzJ7dQKgBdG01S32DSQjchjMgUk3eo/yXa3X56RpN2vCnA5sf52AfvE1pUlbxvOTXQ4d2QkrhgwKBgQDDiCKnhTwXsoc3Q8T8hEWD8ggyR1v9IqCKci7g7Rj2ldOnfi7R3qBb65Au/MDB4+8ToqM35Al6Rl9qo3I3YlM0LVpkaSb9jDZVnjESTILkbnWkSX27HFRJZ5hq7qekUgdde7djHiPQFb658TQO1BVgmD+zY7Gn3Ngjv/Pe/pCrdwKBgDUAxRpxf3QlXE6ynIfvQ+8TewqWu7zBlQCpfDosVBRPgplN0oPgOvlp5VUokIk6yn7S68nxzcO0CcM2cdkLgssud5OIFve4e4UknxhfMHhAZzsbi5UDnai6IYMqAMkLSuB7VUjQtmUzxM+mDaN8XDTWg4aJjpPbJqSzyaSPxzyvAoGAFhJ6BvsNS4eFqAFobTDpP/nEXxibE7oi8T3ueMmK9dr354Jg+qPs6jNvNT6m67w1Mrw7PIXrY/gpsTn+HK5mwg8Ukx0jn5+CSqvu0if85phpg0Agh0ytXxPoqrmCBxSTbEQY8YFnfYfW3dqSi9H5D8xf9HoehdpyM6bpepS19AUCgYEAyWqnDX/Ep6/yoGTF9i4Y63sjoNglZ80/G8j5ll0NzttcXjsJUuhEoyjlxovLXM/mIxboJ3dQERvu+m+8Wzb5qRwx+R7lXWGw6p5XJcDLAnVcxqxCVvcTbi4F320HnXW4Q0UQGz8flxmFtvnwmFLC+tvBtTB6Cd70bHp0jwWsdLw=";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。

	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArq/i6rxm/P4wS5wuMFFpFgE3lCYDTBghnozG/Z6FOayh4MLo+FyDnY9ab2/qET3KT/UFjE6coW7RojD9UIJ5kRMsQFuwkT2S+FqqC6vrqS2EOi6GlmqRWE42jPrhu8j11zrJSRkD6LswVgEeHA4HDdy8f2j4W8E+72V5VovRSHV09KzzU3z7q0AD2WBvK0OPHOa3F7R7Jmn7z1RveCIDVEcIiIaJZyOwHOSEMIsw2qRok4y3yidTRCZh5+LZHBXhjkkBf8wEQi3GcHwoBZ+CnPVKTSm2tw5qGQMldgY0/uvzo2DX455kZnAzXWKGX5It+jhZUoYiUireuN5VNmbxvQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问

	public static String notify_url = "http://localhost:8082/alipay/alipayNotifyNotice.html";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8082/alipay/alipayReturnNotice.html";
	
	public static String sign_type = "RSA2";
	
	public static String charset = "utf-8";
	// 注意：沙箱测试环境，正式环境为：https://openapi.alipay.com/gateway.do

	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
