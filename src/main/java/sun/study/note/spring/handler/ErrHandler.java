package sun.study.note.spring.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-16
 */
@RestControllerAdvice
public class ErrHandler {

    @ExceptionHandler
    public String error(Exception e){
        return e.getMessage();
    }
}
