package sun.study.note.module.istate;

import sun.study.note.module.istate.impl.LoginInState;
import sun.study.note.module.istate.impl.NonLoginInState;
import sun.study.note.module.istate.impl.UserState;

/**
 * @author sunzhen
 * @data 2022/4/26 18:20
 */
public class AppContext {

    public static final UserState STATE_LOGIN = new LoginInState();
    public static final UserState STATE_NON_LOGIN = new NonLoginInState();

    private UserState currentState = STATE_NON_LOGIN;

    {
        STATE_LOGIN.setContext(this);
        STATE_NON_LOGIN.setContext(this);
    }

    public void setState(UserState state) {
         currentState = state;
         currentState.setContext(this);
    }

    public UserState getState() {
        return currentState;
    }

    public void favorite() {
         currentState.favorite();
    }

    public void comment(String comment) {
         currentState.comment(comment);
    }
}
