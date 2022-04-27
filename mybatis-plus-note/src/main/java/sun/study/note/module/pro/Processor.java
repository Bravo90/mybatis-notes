package sun.study.note.module.pro;

/**
 * @author sunzhen
 * @data 2022/4/26 16:52
 */
public interface Processor<T, R> {

    void process(T context, R result);

    void fireNext(T context, R result);

    boolean preCheck(T context, R result);
    
}
