package sun.study.note.domian.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-22
 */
@Getter
@Setter
@ApiModel
public class ProjectDeliveryDTO {

    @ApiModelProperty(value = "项目ID")
    private Integer projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNumber;

    @ApiModelProperty(value = "项目代号")
    private String projectCode;

    @ApiModelProperty(value = "交割时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类别ID")
    private Integer projectTypeId;

    @ApiModelProperty(value = "类别说明")
    private String typeDesc;

    @ApiModelProperty(value = "快手投资主体")
    private String investmentSubject;

    @ApiModelProperty(value = "被投资公司")
    private String investedCompany;

    @ApiModelProperty(value = "行业类别")
    private String industryCategory;

    @ApiModelProperty(value = "被投资公司主营业务")
    private String investedCompanyBusiness;

    @ApiModelProperty(value = "投资金额-币种")
    private String currencyType;

    @ApiModelProperty(value = "投资金额")
    private BigDecimal investmentAmount;

    @ApiModelProperty(value = "投资金额人民币")
    private BigDecimal investmentAmountRmb;

    @ApiModelProperty(value = "交割后持股比例")
    private BigDecimal deliveryShareholdingRatio;

    @ApiModelProperty(value = "供应商")
    private String supplierCompany;

    @ApiModelProperty(value = "交割文件列表")
    private List<StudentDTO> studentDTOList = Collections.EMPTY_LIST;

    @ApiModelProperty(value = "快手委派人员职位")
    private String delegatePost;

    @ApiModelProperty(value = "快手委派人员名称")
    private String delegateName;

    @ApiModelProperty(value = "状态：0-暂存，1-确认完成")
    private Integer status;

}
