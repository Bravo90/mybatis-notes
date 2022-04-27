package sun.study.note.module.istate.impl;


/**
 * @author sunzhen
 * @data 2022/4/26 18:21
 */
public class LoginInState extends UserState {

    @Override
    public void favorite() {
        System.out.println("收藏成功！");
    }

    @Override
    public void comment(String comment) {
        System.out.println(comment);
    }
}
