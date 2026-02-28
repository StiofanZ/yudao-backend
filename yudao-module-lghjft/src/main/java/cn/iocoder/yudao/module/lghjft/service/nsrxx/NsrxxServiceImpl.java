package cn.iocoder.yudao.module.lghjft.service.nsrxx;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo.NsrxxPayFormResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo.NsrxxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxKzMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.vo.NsrxxQueryParam;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.Collection;
import java.util.List;

/**
 * 纳税人信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class NsrxxServiceImpl implements NsrxxService {

    @Resource
    private NsrxxMapper nsrxxMapper;

    @Resource
    private NsrxxKzMapper nsrxxKzMapper;
    @Override
    public List<NsrxxDO> getNsrxxList(String keyword) {
        return nsrxxMapper.getNsrxxList(new NsrxxQueryParam().setKeyword(keyword));
    }


    @Override
    public NsrxxDO getNsrxx(String id) {
        return nsrxxMapper.getNsrxx(id);
    }

    @Override
    public List<NsrxxDO> getNsrxxList(Collection<String> ids) {
        return nsrxxMapper.getNsrxxList(new NsrxxQueryParam().setDjxhList(ids).setOnlyValid(false));
    }

    public NsrxxPayFormResVO getNsrdwxxByShxydm(String shxydm) {
        if (shxydm == null) {
            return null;
        }

        // 2. 查询原始数据（包含银行代码、税务局代码）
        NsrxxPayFormResVO vo = nsrxxMapper.getNsrdwxxWithAll(shxydm);
        if (vo == null) {
            return null;
        }

        // ========== 替换银行行别代码（核心：dict_type=bank_code） ==========
        String bankCode = vo.getJcghyh(); // 从VO中获取原始银行代码（如103）
        if (StringUtils.isNotBlank(bankCode)) {
            // 关键：字典类型用真实的bank_code（不是之前的yhhb_type）
            String bankName = nsrxxMapper.getDictLabel("bank_code", bankCode);
            vo.setJcghyh(StringUtils.isBlank(bankName) ? bankCode : bankName);
        } else {
            System.out.println("【银行替换】银行代码为空，跳过替换");
        }

        // ========== 替换税务局代码（核心：dict_type=sys_swjg_dict_type） ==========
        String taxCode = vo.getZgswjmc(); // 从VO中获取原始税务局代码（如16204210000）
        if (StringUtils.isNotBlank(taxCode)) {
            // 关键：字典类型用真实的sys_swjg_dict_type（不是之前的ghjg_type）
            String taxName = nsrxxMapper.getDictLabel("sys_swjg_dict_type", taxCode);

            // 兜底：查不到名称则保留原代码
            vo.setZgswjmc(StringUtils.isBlank(taxName) ? taxCode : taxName);
        } else {
            System.out.println("【税务局替换】税务局代码为空，跳过替换");
        }

        // 3. 返回替换后的结果
        return vo;
    }
}
