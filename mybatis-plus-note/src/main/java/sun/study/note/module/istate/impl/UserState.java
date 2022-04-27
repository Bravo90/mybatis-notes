package sun.study.note.module.istate.impl;

import sun.study.note.module.istate.AppContext;

/**
 * @author sunzhen
 * @data 2022/4/26 18:19
 */
public abstract class UserState {

    protected AppContext context;

    public void setContext(AppContext context) {
        this.context = context;
    }

    public abstract void favorite();

    public abstract void comment(String comment);
}
