package controllers.pay;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import modules.pay.config.AlipayConfig;
import modules.pay.util.AlipayNotify;
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
        // response.print(request_token);

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

    public static void callback() throws Exception {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.params.data;
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        // 商户订单号

        String out_trade_no = new String(request.params.get("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 支付宝交易号

        String trade_no = new String(request.params.get("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 交易状态
        String result = new String(request.params.get("result").getBytes("ISO-8859-1"), "UTF-8");

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verifyReturn(params);

        if (verify_result) {// 验证成功
            // ////////////////////////////////////////////////////////////////////////////////////////
            // 请在这里加上商户的业务逻辑程序代码

            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            // 判断该笔订单是否在商户网站中已经做过处理
            // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            // 如果有做过处理，不执行商户的业务程序

            // 该页面可做页面美工编辑
            response.print("验证成功<br />");
            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            // ////////////////////////////////////////////////////////////////////////////////////////
        } else {
            // 该页面可做页面美工编辑
            response.print("验证失败");
        }
    }

    public static void nofity() throws Exception {
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.params.data;
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

        // 解密（如果是RSA签名需要解密，如果是MD5签名则下面一行清注释掉）
        Map<String, String> decrypt_params = AlipayNotify.decrypt(params);
        // XML解析notify_data数据
        Document doc_notify_data = DocumentHelper.parseText(decrypt_params.get("notify_data"));

        // 商户订单号
        String out_trade_no = doc_notify_data.selectSingleNode("//notify/out_trade_no").getText();

        // 支付宝交易号
        String trade_no = doc_notify_data.selectSingleNode("//notify/trade_no").getText();

        // 交易状态
        String trade_status = doc_notify_data.selectSingleNode("//notify/trade_status").getText();

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        if (AlipayNotify.verifyNotify(params)) {// 验证成功
            // ////////////////////////////////////////////////////////////////////////////////////////
            // 请在这里加上商户的业务逻辑程序代码

            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if (trade_status.equals("TRADE_FINISHED")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序

                // 注意：
                // 该种交易状态只在两种情况下出现
                // 1、开通了普通即时到账，买家付款成功后。
                // 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。

                response.print("success"); // 请不要修改或删除
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序

                // 注意：
                // 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。

                response.print("success"); // 请不要修改或删除
            }

            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            // ////////////////////////////////////////////////////////////////////////////////////////
        } else {// 验证失败
            response.print("fail");
        }
    }

}
