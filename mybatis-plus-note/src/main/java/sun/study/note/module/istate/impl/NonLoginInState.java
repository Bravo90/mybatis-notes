package sun.study.note.module.istate.impl;

import sun.study.note.module.istate.AppContext;

/**
 * @author sunzhen
 * @data 2022/4/26 18:21
 */
public class NonLoginInState extends UserState {

    @Override
    public void favorite() {
        login();
        System.out.println("收藏成功！");
    }

    @Override
    public void comment(String comment) {
        login();
        System.out.println(comment);
    }


    private void login() {
        System.out.println("跳转登录...");
        System.out.println("登录成功...");
         context.setState(AppContext.STATE_LOGIN);
    }
}
