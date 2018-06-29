package snoob.gdd.util;

import snoob.gdd.enums.ResultEnum;
import snoob.gdd.model.Result;

public class ResultUtil {

    public static Result success(Object obj){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(obj);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
