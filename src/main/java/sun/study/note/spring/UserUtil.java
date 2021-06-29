package sun.study.note.spring;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-25
 */
public class UserUtil {

    private static InheritableThreadLocal<String> userCache = new InheritableThreadLocal<>();

    public static void putUser(String userId){
        userCache.set(userId);
    }

    public static String getUser(){
        return userCache.get();
    }
}
