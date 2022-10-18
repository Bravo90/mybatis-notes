package sun.study.note.util;

import org.springframework.util.StringUtils;

/**
 * @author sunzhen03 <sunzhen03@inspur.com>
 * @date 2022/8/26
 */
public class DSUtil {
    public DSUtil() {
    }

    public static String getSubConditon(String organId, String organCol, String netId, String netCol) {
        String result = "";
        if (organId != null) {
            result = result + organCol + "=\"" + organId + "\"";
        }

        if (StringUtils.isEmpty(result)) {
            if (netId != null) {
                result = result + netCol + "=\"" + netId + "\"";
            }
        } else if (netId != null) {
            result = result + " AND " + netCol + "=\"" + netId + "\"";
        }

        return StringUtils.isEmpty(result) ? "" : result + " AND ";
    }

    public static String getSubGroupBy(String organId, String organCol, String netId, String netCol) {
        String result = "";
        if (organId != null) {
            result = result + organCol;
        }

        if (StringUtils.isEmpty(result)) {
            if (netId != null) {
                result = result + netCol;
            }
        } else if (netId != null) {
            result = result + "," + netCol;
        }

        return StringUtils.isEmpty(result) ? "" : "," + result;
    }
}
