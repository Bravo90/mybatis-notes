package sun.study.note.issues;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-07-03
 */
public class DemoDTO {
    static {
        System.out.println("acb");
    }

    public static void main(String[] args) {
        ServiceLoader<Driver> load = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = load.iterator();
        while (iterator.hasNext()){
            Driver next = iterator.next();
            System.out.println(next.getClass());
        }
    }
}
