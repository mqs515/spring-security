package com.spring.security.core.exception;

import com.spring.security.core.commons.RestStatusProvider;
import lombok.Getter;

/**
 * @author ：miaoqs
 * @date ：2019-06-05 11:22
 * @description：服务处理异常处理
 */
@Getter
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    /**
     * 是否是第三方调用异常
     */
    private boolean thirdParty;
    private int status;
    private String message;
    private boolean catMonitor;

    public ServiceException(int status, String message) {
        this(status, message, false);
    }

    public ServiceException(RestStatusProvider provider) {
        this(provider.value(), provider.getMsg(), false);
    }

    public ServiceException(int status, String message, boolean thirdParty) {
        this.status = status;
        this.message = message;
        this.thirdParty = thirdParty;
    }

    public void openCatMonitor() {
        catMonitor = true;
    }
}
