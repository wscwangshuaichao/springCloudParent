//package com.longfor.gf.lc.account;
//
//import com.alibaba.fastjson.JSON;
//import com.longfor.gf.lc.account.exception.ServiceException;
//import com.longfor.gf.lc.account.req.AccountReq;
//import com.longfor.gf.lc.account.req.JlAccountReq;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.junit.Test;
//
///**
// * @ClassName AccountTest
// * @Author jiangdan
// * @Date 2019/5/31 18:38
// **/
//public class AccountTest {
//
//    @Test
//    public void testCreateAccNo() throws ServiceException {
//
////        addAccount("100001");
////        for(int i= 0;i<10;i++){
////            System.out.println("================"+i);
////            final int count = i;
////            new Thread(()->{
////                System.out.println("线程"+count);
////            }).start();
//
////            Thread thread = new Thread(new Runnable() {
////                @Override
////                public void run() {
//////                    addAccount();
////                    System.out.println("线程");
////                }
////            });
////            thread.start();
////        }
//    }
//
//    private void addAccount(){
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//
//        HttpResponse response = null;
////        String url = "http://10.238.63.164:8806/api/trading/detail";
//        String url = "http://10.231.128.164:8806/api/account";
//        HttpPost post = new HttpPost(url);
//        try {
//            AccountReq req = new AccountReq();
//            req.setAccountType("JL");
//            JlAccountReq account = new JlAccountReq();
//            account.setPersonAd("100005");
//            req.setData(JSON.toJSONString(account));
//
//            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(6000).setConnectionRequestTimeout(6000).setSocketTimeout(60000).build();
//            post.setConfig(requestConfig);
//
//            StringEntity entity = new StringEntity(JSON.toJSONString(req),"utf-8");
//            entity.setContentEncoding("UTF-8");
//            entity.setContentType("application/json");
//            post.setEntity(entity);
//
//            response = httpclient.execute(post);
//            HttpEntity entity2 = response.getEntity();
//            System.out.println("接口请求数据结果：+++++++++" + EntityUtils.toString(entity2, "utf-8"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            //释放连接
//            post.releaseConnection();
//        }
//    }
//
//    private void tranDetail(){
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//
//        HttpResponse response = null;
//        String url = "http://10.238.63.164:8806/api/transfer";
////        String url = "http://10.231.128.164:8806/api/account";
//        HttpPost post = new HttpPost(url);
//        try {
//            AccountReq req = new AccountReq();
//            req.setAccountType("JL");
//            JlAccountReq account = new JlAccountReq();
//            account.setPersonAd("100005");
//            req.setData(JSON.toJSONString(account));
//
//            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(6000).setConnectionRequestTimeout(6000).setSocketTimeout(60000).build();
//            post.setConfig(requestConfig);
//
//            StringEntity entity = new StringEntity(JSON.toJSONString(req),"utf-8");
//            entity.setContentEncoding("UTF-8");
//            entity.setContentType("application/json");
//            post.setEntity(entity);
//
//            response = httpclient.execute(post);
//            HttpEntity entity2 = response.getEntity();
//            System.out.println("接口请求数据结果：+++++++++" + EntityUtils.toString(entity2, "utf-8"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            //释放连接
//            post.releaseConnection();
//        }
//    }
//
//    public static void main(String[] args) {
//        AccountTest test = new AccountTest();
////        test.addAccount();
//        for(int i= 0;i<10;i++){
//
//            int finalI = i;
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    test.addAccount();
//                    System.out.println("线程" + finalI);
//                }
//            });
//            thread.start();
//        }
//
//    }
//}
