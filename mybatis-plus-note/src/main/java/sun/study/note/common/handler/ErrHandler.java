package sun.study.note.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sun.study.note.common.result.Result;

import java.util.logging.Level;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-16
 */
@RestControllerAdvice
@Slf4j
public class ErrHandler {

    @ExceptionHandler(Throwable.class)
    public Result<Object> errorHandlerOverJson(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            log.error(e.getMessage(), e);
            return Result.fail(((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage());
        }

        return Result.fail(0, e.getMessage());
    }
}
