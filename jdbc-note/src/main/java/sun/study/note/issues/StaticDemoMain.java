package sun.study.note.issues;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-07-03
 */
public class StaticDemoMain {
    final StaticDemo staticDemo;

    static {
        new StaticDemo();
    }

    public  StaticDemoMain(){
        this.staticDemo = new StaticDemo();
    }

    public static void main(String[] args) throws Exception {

       // StaticDemoMain staticDemoMain = new StaticDemoMain();
    }
}
