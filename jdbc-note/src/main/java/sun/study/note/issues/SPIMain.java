package sun.study.note.issues;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-07-03
 */
public class SPIMain {

    public static void main(String[] args) {

        ServiceLoader<SPIInterface> load = ServiceLoader.load(SPIInterface.class);

        Iterator<SPIInterface> iterator = load.iterator();
        while (iterator.hasNext()) {
            System.out.println("---");
            SPIInterface next = iterator.next();
            next.method();
        }


    }
}
