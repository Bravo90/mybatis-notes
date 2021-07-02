package sun.study.note.domian;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生-课程关系表
 * </p>
 *
 * @author sunzhen03<sunzhen03 @ kuaishou.com>
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ts_student_course")
@ApiModel(value = "StudentCourse对象", description = "学生-课程关系表")
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "student_course_id", type = IdType.AUTO)
    private Integer studentCourseId;

    @ApiModelProperty(value = "课程ID")
    private Integer courseId;

    @ApiModelProperty(value = "学生ID")
    private Integer studentId;


}
