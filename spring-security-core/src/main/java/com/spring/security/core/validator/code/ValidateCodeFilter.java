package com.spring.security.core.validator.code;

import com.spring.security.core.commons.Conts;
import com.spring.security.core.commons.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 21:08
 * @description：短信验证码校验过滤器
 */
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    @Getter
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    
    private Set<String> urls = new HashSet<>();
    
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    
    @Autowired
    private SecurityProperties securityProperties;
    
    @Override
    public void afterPropertiesSet() throws ServletException {
    	super.afterPropertiesSet();
    	String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
    	for(String configUrl : configUrls){
    		urls.add(configUrl);
    	}
    	urls.add("/api/authentication/form");
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
    	urls.add("/user/1");
    	urls.add("/user");
    	urls.add("/api/authentication/form");
    	
    	boolean action = false;
    	logger.info("================================过滤器请求路径：{}");
/*    	for(String url : urls){
    		if(pathMatcher.match(url, request.getRequestURI())){
    			action = true;
    		}
    	}*/
    	
    	if(pathMatcher.match("/api/authentication/form", request.getRequestURI())){
			action = true;
		}
    
	    if(action){
			try{
                validate(new ServletWebRequest(request));
            }
            catch(ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;//当失败时则不执行后面的过滤器
            }
	        
	    }
	    
	    filterChain.doFilter(request, response);
    }
    
    private void validate(ServletWebRequest request) throws ServletRequestBindingException{
        
    	//从session中获取生成的图形验证码
    	ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(request, Conts.SESSION_KEY);
        
        //请求中的参数imageCode为登录页面中手填的验证码的值
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        
        if("".equals(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空");
        }
        
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        
        if(codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request, Conts.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        
        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }
        
        sessionStrategy.removeAttribute(request, Conts.SESSION_KEY);
    }
}
