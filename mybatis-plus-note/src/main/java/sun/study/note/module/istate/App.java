package sun.study.note.module.istate;

/**
 * @author sunzhen
 * @data 2022/4/26 18:39
 */
public class App {
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.setState(AppContext.STATE_LOGIN);
        context.favorite();
        context.comment("点赞！");
    }
}
