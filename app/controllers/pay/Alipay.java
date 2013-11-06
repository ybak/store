package controllers.pay;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import modules.pay.config.AlipayConfig;
import modules.pay.util.AlipaySubmit;
import play.mvc.Controller;

public class Alipay extends Controller {

    public static void rediretToAlipay() throws Exception {
        String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";

        // //////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////

        // 返回格式
        String format = "xml";
        // 必填，不需要修改

        // 返回格式
        String v = "2.0";
        // 必填，不需要修改

        // 请求号
        String req_id = "";
        // 必填，须保证每次请求都是唯一

        // req_data详细信息

        // 服务器异步通知页面路径
        String notify_url = "http://www.xxx.com/WS_WAP_PAYWAP-JAVA-UTF-8/notify_url.jsp";
        // 需http://格式的完整路径，不能加?id=123这类自定义参数

        // 页面跳转同步通知页面路径
        String call_back_url = "http://127.0.0.1:8080/WS_WAP_PAYWAP-JAVA-UTF-8/call_back_url.jsp";
        // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

        // 操作中断返回地址
        String merchant_url = "http://127.0.0.1:8080/WS_WAP_PAYWAP-JAVA-UTF-8/xxxxxx.jsp";
        // 用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数

        // 卖家支付宝帐户
        String seller_email = new String(request.params.get("WIDseller_email").getBytes("ISO-8859-1"), "UTF-8");
        // 必填

        // 商户订单号
        String out_trade_no = new String(request.params.get("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        // 商户网站订单系统中唯一订单号，必填

        // 订单名称
        String subject = new String(request.params.get("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
        // 必填

        // 付款金额
        String total_fee = new String(request.params.get("WIDtotal_fee").getBytes("ISO-8859-1"), "UTF-8");
        // 必填

        // 请求业务参数详细
        String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>"
                + call_back_url + "</call_back_url><seller_account_name>" + seller_email
                + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject
                + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url
                + "</merchant_url></direct_trade_create_req>";
        // 必填

        // ////////////////////////////////////////////////////////////////////////////////

        // 把请求参数打包成数组
        Map<String, String> sParaTempToken = new HashMap<String, String>();
        sParaTempToken.put("service", "alipay.wap.trade.create.direct");
        sParaTempToken.put("partner", AlipayConfig.partner);
        sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
        sParaTempToken.put("sec_id", AlipayConfig.sign_type);
        sParaTempToken.put("format", format);
        sParaTempToken.put("v", v);
        sParaTempToken.put("req_id", req_id);
        sParaTempToken.put("req_data", req_dataToken);

        // 建立请求
        String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, "", "", sParaTempToken);
        // URLDECODE返回的信息
        sHtmlTextToken = URLDecoder.decode(sHtmlTextToken, AlipayConfig.input_charset);
        // 获取token
        String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
        // out.println(request_token);

        // //////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////

        // 业务详细
        String req_data = "<auth_and_execute_req><request_token>" + request_token
                + "</request_token></auth_and_execute_req>";
        // 必填

        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("sec_id", AlipayConfig.sign_type);
        sParaTemp.put("format", format);
        sParaTemp.put("v", v);
        sParaTemp.put("req_data", req_data);

        // 建立请求
        String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
        response.print(sHtmlText);
    }
    
    public void nofity(){
        
    }

}
