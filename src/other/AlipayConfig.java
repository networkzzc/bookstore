package other;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102900775243";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwSGtRm0jL3Zt3XsT7MmudfNNyS1zfDAcqE4sT072kZH4pRb3cbGpR+6O3ooFU0iFGdGN7hyKL/no+bdx4BvGMXpUthqi6veMhkgiUsX3kVksTsYseYrtq8SOwpdGR/eSYDxctigEIriMchlERIvxvhixDkAOYfe9cDSQUQpZtqwDde3mq+oxBZSfqHGlO/OWFkxLgiJYi8PoBcdlzPvKMSqA2OJZkMUH1u0beHKscxcBeKLEdtr8c+oAb8JpDlwPUXbgtcPuRDvZZwRUWZFZFUxutg8q13D4tJI1fUUG+WaGFHKhQKVItTs0b5M1vKu55DlDsf6nEjfEC486KgaZHAgMBAAECggEAPI8EXyqLXY2pxN5HqUvu8FFbil4db3qAU5IXKx6IfZsQRDlzOe/8rmFWItWuvGbbi4qcSmloEtO/RwcSwCEShfhQIy2pdU2aY4P5cqtu5ixG6mMJucyNOfXRHvQ4BIFGMH6upbRNGqBH65qw6sQsGFvthOBKe+53dhYFmPgvGUkcoohKpriRIeDNYCaXMVh0zxzALK/qqxMgoF5FYu5jIl3AM/XTEWflDDbR0vYsnWp52WU799AVI/uv0DxsRlooraHBB7/iNZNjZ1tLzW+0uIP8ac/EaE+BwBeqWTcHNLh/YPnsGgjv4DzKz0Wd8VnagUxw5Zppx0lkq5SJ2SncIQKBgQDvuVBvB5BFuH8j7Z5wrtN+aYAuk4MOUjutzh5dN7XK3QmdB+zw9JB2zq4eU7y9fnbmPeq311HawJQjxJQ/PHLjSAJdEzNbqkzxsPJ5aoe8cwbDoT3MKVHlx75OEk9X/zxcsnOit9TWbphZCEP5BX9gU+SIk6hrF7JSAX1VLMLauQKBgQC8QGzXez1uX2Vr4YVqQ+GPmCIXVsbRKvLLN4u6Qefq7wiVo4bxslz5ObNrm3+/jIjZ2sE4u/1Fd0skPTSZCO7Jbaaq+di4shfmvvjTYSOZBePzSHfF1L7PHFxTtmvE/9Em+Cs0QsmrvRz7hhS0xLqiatrYT52aNnm+Gsnn8DQI/wKBgQC9MNmD5so73BGrPzsN30vWtliNaanCwa+LyeI422DDy5JstfaPa9Qfj5nnIVsRih84MqFtmadmJxpC6D+/cGuTaB9FaYPc0hiLnoSewi6B0Z4DAtwMpoeV+v/8CYNJBYyT89pDDyYQXZQqn9VVULtoQI1aJH6Ell0LT1WkxQC7MQKBgHdHRu+dqcXmcgfSBEvZqPDoDynElQLxeJdkoYcpvT3xqPnPfHBmeFx9v/59r4r6C7gl0GoXSxoZxSW4FjylGU/Nx13sIvKb/WL6PAULNpf90HpUv3c9Ho1yRdnQ6yYOhUJw2kcpzkif8d45DaOgRzUkCXgnsg+FVswqgyYP35iXAoGAflY/RfwMkiltta8NLiv6e8ZdyQeEHQEtzSagXSeiwHbmAlXJ7s3EMSgScZt20C2aK9eapdATtFWuCEiNpTeT5uBtfFRV2d5Nbn2JG0wa7nSRPXI8QLPLdhkXcz1GDtwYrGPCtYL2fWnYma7lsusri3EEK0+Bhu3fEUtHuGZMYv8=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh/kSVHyUMgvVKSxBDoT3I6+JeUYkbiGIiNtb+RdIqmtKRNhAOxr81+iof7AjJFt9yIgTBjD+P1IcgBd+DXSZVnCeXfp8QgexhYwZ/HopXnAUeohlNxKthhCoOxwV9LgwM3Wc9MQTq4fQqvY2FlYzb+Id7Im7oUfdGEUT6vmpjUpq91xH/z8TKrUGN+vSsaDunfhKgQ9MDXsTrXR9w9ikKuPocatAcoVhkrs5RYXry1g4QA+E4uH/7Wirvi9GXXMTVY7NXWBafUE3P5jKNlVgxpQzLNPWAxgv8/gTiJD9Trw9n66iDJYeZUXmUWFDebNEjVATbiVJ4UkEgaeHgOHtZwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/BookStore/index/paysucceed.jsp";
//	public static String notify_url = "http://47.92.215.142:8080/BookStore/index/succeed.action";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/BookStore/index/succeed.action";
//	public static String return_url = "http://47.92.215.142:8080/BookStore/index.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

