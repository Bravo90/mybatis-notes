package sun.study.note.issues;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-07-03
 */
public class SPIImpl implements SPIInterface{
    static {
        System.out.println("SPIImpl 调用静态代码块！");
    }

    @Override
    public void method() {
        System.out.println("SPIImpl 调用method()方法");
    }
}
