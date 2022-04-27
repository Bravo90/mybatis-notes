package sun.study.note.module.pro.context;

import lombok.Data;
import lombok.ToString;

/**
 * @author sunzhen
 * @data 2022/4/26 17:34
 */
@Data
@ToString
public class ProResult {

    private boolean orderSucceed;
    private boolean paySucceed;
    private boolean postSucceed;
}
