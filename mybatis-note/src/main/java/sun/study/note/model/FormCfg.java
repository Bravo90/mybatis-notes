package sun.study.note.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author sunzhen03 <sunzhen03@inspur.com>
 * @since 2022-10-18
 */
@Data
public class FormCfg {

    private Integer formCfgId;

    private Integer formType;

    private String formName;

    private String formCfg;

    private String businessSystem;

    private String office;

}
