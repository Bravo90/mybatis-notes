package sun.study.note.module.pro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import sun.study.note.module.pro.processor.AbstractProcessor;

import javax.annotation.PostConstruct;

/**
 * @author sunzhen
 * @data 2022/4/26 17:04
 */
public abstract class AbstractProcessorBuilder<T, R> {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    // 此处为链
    private AbstractProcessor<T, R> instance;

    @PostConstruct
    public void init() {
        initProcessor();
    }

    public abstract void initProcessor();

    public AbstractProcessor<T, R> build() {
        return instance;
    }

    public void addLast(AbstractProcessor<T, R> processor) {
        // 链空时
        if (instance == null) {
            instance = autowired(processor);
            return;
        }
        // 链不为空
        AbstractProcessor<T, R> next = instance;
        while (next.getNext() != null) {
            next = next.getNext();
        }
        // 加到链尾
        next.setNext(autowired(processor));
    }

    protected AbstractProcessor autowired(AbstractProcessor<T, R> processor) {
        autowireCapableBeanFactory.autowireBean(processor);
        return processor;
    }
}
