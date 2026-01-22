package cn.iocoder.yudao.module.lghjft.dal.mysql.auth;

import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DwQxSfMapper {

    @Select("select ifnull(a.dwmc, b.nsrmc) as dwmc, ifnull(a.zggh_id, b.dept_id) as zggh_id, null as zggh_mc, a.djxh, a.ryxm, a.lxdh, a.sflx, a.qxlx " +
            "from (select djxh, null as dwmc, null as zggh_id, null as zggh_mc, cwfzrxm as ryxm, cwfzryddh as lxdh, '02' as sflx, '1' as qxlx " +
            "      from dj_nsrxx_kz kz " +
            "      where kz.cwfzryddh = #{dlzh} " +
            "      union all " +
            "      select djxh, null as dwmc, null as zggh_id, null as zggh_mc, ryxm, lxdh, sflx, qxlx " +
            "      from gh_dj_nsrxx_glry " +
            "      where lxdh = #{dlzh} " +
            "        and status = 1 " +
            "        and deleted = 0 " +
            "      union all " +
            "      select djxh, ghmc as dwmc, dept_id as zggh_id, null as zggh_mc, ghjflxr as ryxm, ghjflxdh lxdh, '03' as sflx, '1' as qxlx " +
            "      from jhdwyds " +
            "      where ghjflxdh = #{dlzh}) a " +
            "         left join gh_hj b on a.djxh = b.djxh " +
            "where b.djxh is not null " +
            "  and nsrzt_dm < '07'")
    List<AuthorizeResVO.DwQxSf> selectDwQxSfListByDlzh(@Param("dlzh") String dlzh);

}
