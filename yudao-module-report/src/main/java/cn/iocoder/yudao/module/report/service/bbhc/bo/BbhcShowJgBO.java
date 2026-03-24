package cn.iocoder.yudao.module.report.service.bbhc.bo;

import cn.iocoder.yudao.module.report.dal.dataobject.bbhc.GhBbsjHcDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jeecg.modules.jmreport.common.vo.Result;
import org.jeecg.modules.jmreport.desreport.entity.JimuReport;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BbhcShowJgBO {

    private GhBbsjHcDO hc;

    private Result<JimuReport> jg;
}
