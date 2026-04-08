package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.dqdssj;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.CsFpblDqdssjVo;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfzcDqdssjVo;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.QsjshkrjDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CsFpblDqdssjMapper {

    List<CsFpblDqdssjVo> selectCsFpblJobList(@Param("csFpbl") CsFpblDqdssjVo csFpbl);

    List<JfzcDqdssjVo> selectJFZCJobList(Map<String, Object> param);

    List<JfzcDqdssjVo> selectJFZCJobzlList(Map<String, Object> param);

    List<QsjshkrjDO> selectQsjshkrjList(Map<String, Object> param);

    List<JfzcDqdssjVo> selectForQsjshkrj(Map<String, Object> param);

    int updateQsjshkrj(@Param("list") List<QsjshkrjDO> qsjshkrjJob);

    int insertQsjshkrj(@Param("list") List<QsjshkrjDO> qsjshkrjJob);
}
