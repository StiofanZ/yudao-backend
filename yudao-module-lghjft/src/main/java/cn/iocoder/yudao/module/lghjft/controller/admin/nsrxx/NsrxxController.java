package cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo.NsrxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jf.ghjf.GhJfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxKzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jf.ghjf.GhJfMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxKzMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx.GhQxSfxxMapper;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 纳税人信息")
@RestController
@RequestMapping("/lghjft/nsrxx")
@Validated
public class NsrxxController {

    @Resource
    private NsrxxService nsrxxService;
    @Resource
    private NsrxxKzMapper nsrxxKzMapper;
    @Resource
    private GhQxSfxxMapper ghQxSfxxMapper;
    @Resource
    private GhJfMapper ghJfMapper;

    @GetMapping("/query")
    @Operation(summary = "根据纳税人识别号查询纳税人信息")
    @Parameter(name = "nsrsbh", description = "纳税人识别号", required = true, example = "91110108551385082Q")
    public CommonResult<NsrxxResVO> queryNsrxxByNsrsbh(@RequestParam("nsrsbh") String nsrsbh) {
        NsrxxDO nsrxx = nsrxxService.getNsrxxByNsrsbh(nsrsbh);
        NsrxxResVO respVO = BeanUtils.toBean(nsrxx, NsrxxResVO.class);
        
        if (respVO != null) {
            populateExtraInfo(respVO);
        }
        
        return success(respVO);
    }

    @GetMapping("/list")
    @Operation(summary = "根据社会信用代码或纳税人识别号查询纳税人信息列表")
    @Parameter(name = "code", description = "社会信用代码或纳税人识别号", required = true, example = "91110108551385082Q")
    public CommonResult<List<NsrxxResVO>> queryNsrxxList(@RequestParam("code") String code) {
        List<NsrxxDO> list = nsrxxService.getNsrxxList(code);
        List<NsrxxResVO> respList = BeanUtils.toBean(list, NsrxxResVO.class);

        if (respList.isEmpty()) {
            return success(respList);
        }

        // 批量查询扩展信息
        List<String> djxhList = list.stream().map(item -> item.getDjxh().toString()).collect(Collectors.toList());
        if (djxhList.isEmpty()) {
            return success(respList);
        }
        
        List<NsrxxKzDO> kzList = nsrxxKzMapper.selectBatchIds(djxhList);
        Map<String, NsrxxKzDO> kzMap = kzList.stream().collect(Collectors.toMap(NsrxxKzDO::getDjxh, item -> item));

        // 填充扩展信息
        for (NsrxxResVO resp : respList) {
            NsrxxDO nsrxxDO = list.stream().filter(item -> item.getDjxh().equals(resp.getDjxh())).findFirst().orElse(null);
            NsrxxKzDO kzDO = kzMap.get(resp.getDjxh().toString());
            
            // 填充法定代表人信息
            if (nsrxxDO != null) {
                resp.setFddbrxm(nsrxxDO.getFddbrxm());
            }
            if (kzDO != null) {
                resp.setFddbryddh(kzDO.getFddbryddh());
                resp.setCwfzrxm(kzDO.getCwfzrxm());
                resp.setCwfzryddh(kzDO.getCwfzryddh());
            }

            // 填充身份信息和部门ID
            populateExtraInfo(resp);
        }

        return success(respList);
    }

    private void populateExtraInfo(NsrxxResVO resp) {
        if (resp.getDjxh() == null) {
            return;
        }
        String djxhStr = resp.getDjxh().toString();

        // 查询身份信息
        GhQxSfxxDO sfxxDO = ghQxSfxxMapper.selectOne(new LambdaQueryWrapper<GhQxSfxxDO>()
                .eq(GhQxSfxxDO::getDjxh, djxhStr)
                .last("LIMIT 1"));
        if (sfxxDO != null) {
            resp.setSfxx(BeanUtils.toBean(sfxxDO, SfxxResVO.class));
        }

        // 查询部门ID (从gh_jf表获取最新的一条)
        GhJfDO ghJfDO = ghJfMapper.selectOne(new LambdaQueryWrapper<GhJfDO>()
                .eq(GhJfDO::getDjxh, djxhStr)
                .orderByDesc(GhJfDO::getGhjfId)
                .last("LIMIT 1"));
        if (ghJfDO != null && ghJfDO.getDeptId() != null) {
            try {
                resp.setDeptId(Long.valueOf(ghJfDO.getDeptId()));
            } catch (NumberFormatException e) {
                // 忽略解析错误
            }
        }
    }

}
