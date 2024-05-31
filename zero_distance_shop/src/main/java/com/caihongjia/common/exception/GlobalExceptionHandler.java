package com.caihongjia.common.exception;

import com.caihongjia.common.constant.GlobalExceptionConstant;
import com.caihongjia.common.exception.product.*;
import com.caihongjia.common.exception.shoppingcart.AddShoppingCartException;
import com.caihongjia.common.exception.shoppingcart.DeleteShoppingCartException;
import com.caihongjia.common.exception.shoppingcart.UpdateShoppingCartQuantityException;
import com.caihongjia.common.exception.user.LoginException;
import com.caihongjia.common.exception.user.PasswordMismatchException;
import com.caihongjia.common.exception.user.UpdatePasswordException;
import com.caihongjia.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public Result<Object> loginException(LoginException msg){
        return Result.error(msg.getLocalizedMessage());
    }

    @ExceptionHandler(CategoryException.class)
    public Result<Object> categoryException(){
        return Result.error(GlobalExceptionConstant.CATEGORY_EXCEPTION);
    }

    @ExceptionHandler(AddProductException.class)
    public Result<Object> addProductException(){
        return Result.error(GlobalExceptionConstant.ADD_PRODUCT_EXCEPTION);
    }

    @ExceptionHandler(UpdateProductException.class)
    public Result<Object> updateProductException(){
        return Result.error(GlobalExceptionConstant.UPDATE_PRODUCT_EXCEPTION);
    }

    @ExceptionHandler(DeleteProductException.class)
    public Result<Object> deleteProductException(){
        return Result.error(GlobalExceptionConstant.DELETE_PRODUCT_EXCEPTION);
    }

    @ExceptionHandler(GetProductException.class)
    public Result<Object> getProductException(){
        return Result.error(GlobalExceptionConstant.GET_PRODUCT_EXCEPTION);
    }

    @ExceptionHandler(NotProductException.class)
    public Result<Object> notProductException(NotProductException msg){
        return Result.error(msg.getLocalizedMessage());
    }

    @ExceptionHandler(AddShoppingCartException.class)
    public Result<Object> addShoppingCartException(){
        return Result.error(GlobalExceptionConstant.ADD_SHOPPING_CART_EXCEPTION);
    }

    @ExceptionHandler(UpdateShoppingCartQuantityException.class)
    public Result<Object> updateShoppingCartQuantityException(){
        return Result.error(GlobalExceptionConstant.UPDATE_SHOPPING_CART_QUANTITY_EXCEPTION);
    }

    @ExceptionHandler(DeleteShoppingCartException.class)
    public Result<Object> deleteShoppingCartException(){
        return Result.error(GlobalExceptionConstant.DELETE_SHOPPING_CART_QUANTITY_EXCEPTION);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public Result<Object> passwordMismatchException(){
        return Result.error(GlobalExceptionConstant.PASSWORD_MISMATCH_EXCEPTION);
    }

    @ExceptionHandler(UpdatePasswordException.class)
    public Result<Object> updatePasswordException(){
        return Result.error(GlobalExceptionConstant.UPDATE_PASSWORD_EXCEPTION);
    }


}
