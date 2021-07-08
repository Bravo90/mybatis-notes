package sun.study.note.domian.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-07-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @ApiModelProperty(value = "姓名")
    @NotNull(message = "姓名不可为NULL")
    @NotBlank(message = "姓名不可为空")
    private String name;

    @ApiModelProperty(value = "年龄")
    @Max(value = 19, message = "年龄不能超过19岁")
    // @Size(min = 16,max = 18,message = "年龄区间在16-18之间")
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    @Email
    private String email = "123@qw.com";
}
