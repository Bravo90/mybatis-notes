package sun.study.note.module.pro.processor;

import sun.study.note.module.pro.Processor;

/**
 * @author sunzhen
 * @data 2022/4/26 16:54
 */
public abstract class AbstractProcessor<T, R> implements Processor<T, R> {

    private AbstractProcessor<T, R> next;

    @Override
    public void fireNext(T context, R result) {
        if (next != null) {
            next.invoke(context, result);
            if (next.getNext() != null) {
                next.getNext().invoke(context, result);
            }
        }
    }


    public void invoke(T context, R result) {
        try {
            if (preCheck(context, result)) {
                process(context, result);
                fireNext(context, result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public boolean preCheck(T context, R result) {
        return true;
    }

    public AbstractProcessor<T, R> getNext() {
        return next;
    }

    public void setNext(AbstractProcessor<T, R> next) {
         next = next;
    }
}
