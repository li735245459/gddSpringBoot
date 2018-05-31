package snoob.gdd.util;

import org.springframework.stereotype.Component;
import snoob.gdd.model.Result;

@Component
public class ResultUtil {
    private static Result result = new Result();

    public static Result success(Object obj){
        result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(obj);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
