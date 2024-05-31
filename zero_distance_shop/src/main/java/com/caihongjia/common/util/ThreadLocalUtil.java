package com.caihongjia.common.util;

import com.caihongjia.pojo.vo.UserVO;
import io.jsonwebtoken.Claims;

public class ThreadLocalUtil {
    private static final ThreadLocal threadLocal =  new ThreadLocal();
    
    public static void set(Object value) {
        threadLocal.set(value);
    }

    public static Object get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
