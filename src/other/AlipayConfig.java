package other;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *������AlipayConfig
 *���ܣ�����������
 *��ϸ�������ʻ��й���Ϣ������·��
 *�޸����ڣ�2017-04-05
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���
 */

public class AlipayConfig {
	
//�����������������������������������Ļ�����Ϣ������������������������������

	// Ӧ��ID,����APPID���տ��˺ż�������APPID��Ӧ֧�����˺�
	public static String app_id = "2016102900775243";
	
	// �̻�˽Կ������PKCS8��ʽRSA2˽Կ
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwSGtRm0jL3Zt3XsT7MmudfNNyS1zfDAcqE4sT072kZH4pRb3cbGpR+6O3ooFU0iFGdGN7hyKL/no+bdx4BvGMXpUthqi6veMhkgiUsX3kVksTsYseYrtq8SOwpdGR/eSYDxctigEIriMchlERIvxvhixDkAOYfe9cDSQUQpZtqwDde3mq+oxBZSfqHGlO/OWFkxLgiJYi8PoBcdlzPvKMSqA2OJZkMUH1u0beHKscxcBeKLEdtr8c+oAb8JpDlwPUXbgtcPuRDvZZwRUWZFZFUxutg8q13D4tJI1fUUG+WaGFHKhQKVItTs0b5M1vKu55DlDsf6nEjfEC486KgaZHAgMBAAECggEAPI8EXyqLXY2pxN5HqUvu8FFbil4db3qAU5IXKx6IfZsQRDlzOe/8rmFWItWuvGbbi4qcSmloEtO/RwcSwCEShfhQIy2pdU2aY4P5cqtu5ixG6mMJucyNOfXRHvQ4BIFGMH6upbRNGqBH65qw6sQsGFvthOBKe+53dhYFmPgvGUkcoohKpriRIeDNYCaXMVh0zxzALK/qqxMgoF5FYu5jIl3AM/XTEWflDDbR0vYsnWp52WU799AVI/uv0DxsRlooraHBB7/iNZNjZ1tLzW+0uIP8ac/EaE+BwBeqWTcHNLh/YPnsGgjv4DzKz0Wd8VnagUxw5Zppx0lkq5SJ2SncIQKBgQDvuVBvB5BFuH8j7Z5wrtN+aYAuk4MOUjutzh5dN7XK3QmdB+zw9JB2zq4eU7y9fnbmPeq311HawJQjxJQ/PHLjSAJdEzNbqkzxsPJ5aoe8cwbDoT3MKVHlx75OEk9X/zxcsnOit9TWbphZCEP5BX9gU+SIk6hrF7JSAX1VLMLauQKBgQC8QGzXez1uX2Vr4YVqQ+GPmCIXVsbRKvLLN4u6Qefq7wiVo4bxslz5ObNrm3+/jIjZ2sE4u/1Fd0skPTSZCO7Jbaaq+di4shfmvvjTYSOZBePzSHfF1L7PHFxTtmvE/9Em+Cs0QsmrvRz7hhS0xLqiatrYT52aNnm+Gsnn8DQI/wKBgQC9MNmD5so73BGrPzsN30vWtliNaanCwa+LyeI422DDy5JstfaPa9Qfj5nnIVsRih84MqFtmadmJxpC6D+/cGuTaB9FaYPc0hiLnoSewi6B0Z4DAtwMpoeV+v/8CYNJBYyT89pDDyYQXZQqn9VVULtoQI1aJH6Ell0LT1WkxQC7MQKBgHdHRu+dqcXmcgfSBEvZqPDoDynElQLxeJdkoYcpvT3xqPnPfHBmeFx9v/59r4r6C7gl0GoXSxoZxSW4FjylGU/Nx13sIvKb/WL6PAULNpf90HpUv3c9Ho1yRdnQ6yYOhUJw2kcpzkif8d45DaOgRzUkCXgnsg+FVswqgyYP35iXAoGAflY/RfwMkiltta8NLiv6e8ZdyQeEHQEtzSagXSeiwHbmAlXJ7s3EMSgScZt20C2aK9eapdATtFWuCEiNpTeT5uBtfFRV2d5Nbn2JG0wa7nSRPXI8QLPLdhkXcz1GDtwYrGPCtYL2fWnYma7lsusri3EEK0+Bhu3fEUtHuGZMYv8=";
	
	// ֧������Կ,�鿴��ַ��https://openhome.alipay.com/platform/keyManage.htm ��ӦAPPID�µ�֧������Կ��
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh/kSVHyUMgvVKSxBDoT3I6+JeUYkbiGIiNtb+RdIqmtKRNhAOxr81+iof7AjJFt9yIgTBjD+P1IcgBd+DXSZVnCeXfp8QgexhYwZ/HopXnAUeohlNxKthhCoOxwV9LgwM3Wc9MQTq4fQqvY2FlYzb+Id7Im7oUfdGEUT6vmpjUpq91xH/z8TKrUGN+vSsaDunfhKgQ9MDXsTrXR9w9ikKuPocatAcoVhkrs5RYXry1g4QA+E4uH/7Wirvi9GXXMTVY7NXWBafUE3P5jKNlVgxpQzLNPWAxgv8/gTiJD9Trw9n66iDJYeZUXmUWFDebNEjVATbiVJ4UkEgaeHgOHtZwIDAQAB";

	// �������첽֪ͨҳ��·��  ��http://��ʽ������·�������ܼ�?id=123�����Զ����������������������������
	public static String notify_url = "http://localhost:8080/BookStore/index/paysucceed.jsp";
//	public static String notify_url = "http://47.92.215.142:8080/BookStore/index/succeed.action";

	// ҳ����תͬ��֪ͨҳ��·�� ��http://��ʽ������·�������ܼ�?id=123�����Զ����������������������������
	public static String return_url = "http://localhost:8080/BookStore/index/succeed.action";
//	public static String return_url = "http://47.92.215.142:8080/BookStore/index.jsp";

	// ǩ����ʽ
	public static String sign_type = "RSA2";
	
	// �ַ������ʽ
	public static String charset = "utf-8";
	
	// ֧��������
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// ֧��������
	public static String log_path = "C:\\";


//�����������������������������������Ļ�����Ϣ������������������������������

    /** 
     * д��־��������ԣ�����վ����Ҳ���ԸĳɰѼ�¼�������ݿ⣩
     * @param sWord Ҫд����־����ı�����
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

