package com.huawei.controller;

import com.huawei.Utils.CommonUtils;
import com.huawei.projo.Goods;
import com.huawei.projo.Orders;
import com.huawei.projo.User;
import com.huawei.service.DataSourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ViewController {
    @Autowired
    DataSourcesService dataSourcesService;

    @RequestMapping(value="sign", method = RequestMethod.GET)
    public String sign(HttpServletRequest request){
        return "com/sign";
    }

    @RequestMapping(value="signIn", method = RequestMethod.POST)
    @ResponseBody
    public String signIn(HttpServletRequest request){
        return dataSourcesService.signInAndUp(request,CommonUtils.SIGN_IN);
    }

    @RequestMapping(value="signUp", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(HttpServletRequest request){
        return dataSourcesService.signInAndUp(request,CommonUtils.SIGN_UP);
    }

    @RequestMapping(value="signOut", method = RequestMethod.GET)
    @ResponseBody
    public String signOut(HttpServletRequest request){
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("userName");
        return "SignOutSuccess";
    }



    @RequestMapping(value="mall", method = RequestMethod.GET)
    public String mall(HttpServletRequest request){
        List<Goods> goodsList = dataSourcesService.getNorMalGoodsList();
        Goods rushToBuyGoods = dataSourcesService.getRushToBuyGoodsList();
        request.setAttribute("goodsList",goodsList);
        request.setAttribute("rushToBuyGoods",rushToBuyGoods);
        request.getSession().setAttribute("test","sunpeng");
        return "com/mall";
    }


    @RequestMapping(value="normalGoodsDetail", method = RequestMethod.GET)
    public String normalGoodsDetail(HttpServletRequest request){
        String goodsId = request.getParameter("goodsId");
        String goodsType = CommonUtils.GOODS_TYPE_NORMAL;
        Goods goods = dataSourcesService.getGoodsDetail(goodsId,goodsType);
        request.setAttribute("goods",goods);
        return "com/goodsDetail";
    }
    @RequestMapping(value="rushToBuyGoodsDetail", method = RequestMethod.GET)
    public String rushToBuyGoodsDetail(HttpServletRequest request){
        String goodsId = request.getParameter("goodsId");
        String goodsType = CommonUtils.GOODS_TYPE_RUSH_TO_BUY;
        Goods goods = dataSourcesService.getGoodsDetail(goodsId,goodsType);
        request.setAttribute("goods",goods);
        return "com/goodsDetail";
    }

    @RequestMapping(value="payNormalGoods", method = RequestMethod.GET)
    public String payNormalGoods(HttpServletRequest request){
        String goodsId = request.getParameter("goodsId");
        String goodsType = CommonUtils.GOODS_TYPE_NORMAL;
        Goods goods = dataSourcesService.getGoodsDetail(goodsId,goodsType);
        request.setAttribute("goods",goods);
        return "com/pay";
    }
    @RequestMapping(value="payRushToBuyGoods", method = RequestMethod.GET)
    public String payRushToBuyGoods(HttpServletRequest request){
        String goodsId = request.getParameter("goodsId");
        String goodsType = CommonUtils.GOODS_TYPE_RUSH_TO_BUY;
        Goods goods = dataSourcesService.getGoodsDetail(goodsId,goodsType);
        request.setAttribute("goods",goods);
        return "com/pay";
    }

    @RequestMapping(value="normalPay", method = RequestMethod.POST)
    @ResponseBody
    public String normalPay(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String goodsId = request.getParameter("goodsId");
        String goodsPrice = request.getParameter("goodsPrice");
        return dataSourcesService.pay(userId,goodsId,goodsPrice,CommonUtils.GOODS_TYPE_NORMAL);
    }
    @RequestMapping(value="rushToBuyPay", method = RequestMethod.POST)
    @ResponseBody
    public String rushToBuyPay(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String goodsId = request.getParameter("goodsId");
        String goodsPrice = request.getParameter("goodsPrice");
        return dataSourcesService.pay(userId,goodsId,goodsPrice,CommonUtils.GOODS_TYPE_RUSH_TO_BUY);
    }

    @RequestMapping(value="orders", method = RequestMethod.GET)
    public String orders(HttpServletRequest request){
        String userId = request.getParameter("userId");
        List<Orders> ordersList = dataSourcesService.ordersList(userId);
        request.setAttribute("ordersList",ordersList);
        return "com/orders";
    }

    @RequestMapping(value="userDetail", method = RequestMethod.GET)
    public String userDetail(HttpServletRequest request){
        String userId = request.getParameter("userId");
        User user =dataSourcesService.userDetail(userId);
        request.setAttribute("user",user);
        return "com/userDetail";
    }

}
