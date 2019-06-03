package com.yy.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.renderable.RenderContext;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {

        //登录校检，肯定是在前置拦截
        return "pre";
    }

    @Override
    public int filterOrder() {

        //设置优先顺序级 1-10
        return 1;
    }

    @Override
    public boolean shouldFilter() {

        //返回true，代表过滤器生效
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //登录校检编辑
        // 1）获取Zuul提供的请求上下文对象
        RequestContext  ctx = RequestContext.getCurrentContext();

        //2)从上下文中获取request对象
        HttpServletRequest req = ctx.getRequest();

        //3)从请求中获取token
        String token = req.getParameter("access-token");

        //4) 判断
        if(token == null || "".equals(token.trim())){

            //返回401状态吗。也可以考虑重定向到登录页
            ctx.setResponseStatusCode(401);

            //没有token，登录校检失败，拦截
            ctx.setSendZuulResponse(false);
        }
        //校检通过，可以考虑把用户信息放入上下文，继续向后执行
        return null;
    }
}
