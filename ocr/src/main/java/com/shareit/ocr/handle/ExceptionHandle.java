package com.shareit.ocr.handle;

import com.shareit.ocr.domain.APIResult;
import com.shareit.ocr.domain.Simulator;
import com.shareit.ocr.enums.APIResultEnum;
import com.shareit.ocr.exception.SimulatorException;
import com.shareit.ocr.util.APIResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindException;

@ControllerAdvice
public class ExceptionHandle {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public APIResult handle(Exception e){
        e.printStackTrace();
        if(e instanceof SimulatorException){
            SimulatorException simulatorException = (SimulatorException) e;
            return APIResultUtil.error(simulatorException.getResultEnum());
        }else if(e instanceof BindException){
            return APIResultUtil.error(-2,((BindException)e).getFieldError().getDefaultMessage());
        }
        else{
            return APIResultUtil.error(APIResultEnum.UNKOWN_ERROR);
        }
    }
}
