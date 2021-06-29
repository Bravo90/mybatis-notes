package sun.study.note.common.result;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-29
 */
public class Result<T> {
    private int code;
    private String message;
    private T result;

    public Result() {
    }

    public Result(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> Result<T> ok() {
        return new Result(0, "ok", (Object) null);
    }

    public static <T> Result<T> ok(T result) {
        return new Result(0, "ok", result);
    }

    public static <T> Result<T> fail(String message) {
        return new Result(-1, message, (Object) null);
    }

    public static <T> Result<T> fail(int code, String message) {
        return new Result(code, message, (Object) null);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

