package com.spring.security.core.enums;

/**
 * @Author Mqs
 * @Date 2019/3/31 16:46
 * @Desc
 */
public enum ResultErrorEnum {

    NOT_BLANK_ERROR(1, "must not be blank");

    private Integer index;
    private String errorType;

    ResultErrorEnum(Integer index, String errorType) {
        this.index = index;
        this.errorType = errorType;
    }

    public Integer getIndex() {
        return index;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorTypeByIndex(Integer index){
        for (ResultErrorEnum value : ResultErrorEnum.values()) {
            if (value.getIndex() .equals(index)){
                return value.getErrorType();
            }
        }
        return null;
    }
}
