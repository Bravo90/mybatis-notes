package sun.study.note.dao;

import sun.study.note.model.FormCfg;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunzhen03 <sunzhen03@inspur.com>
 * @since 2022-10-18
 */
public interface FormCfgMapper {

   // @Select("select * from form_cfg")
    List<FormCfg> list();
}
