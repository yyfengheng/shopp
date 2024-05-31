package com.caihongjia.common.exception.user;

public class PasswordMismatchException extends Exception{
    public PasswordMismatchException(){
        super("密码不匹配异常");
    }
}
