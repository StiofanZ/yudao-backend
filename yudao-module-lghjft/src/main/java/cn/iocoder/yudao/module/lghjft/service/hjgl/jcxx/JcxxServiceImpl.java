package cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx;

import cn.hutool.core.lang.Assert;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxBaseVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.ghhjyhxx.GhHjYhxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhjyhxx.GhHjYhxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.jcxx.GhHjJcxxMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GH_HJ_JCXX_DJXH_EXISTS;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GH_HJ_JCXX_NOT_EXISTS;

/**
 * 户籍管理/基础信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class JcxxServiceImpl implements JcxxService {

    @Resource
    private GhHjJcxxMapper jcxxMapper;

    @Resource
    private GhHjYhxxMapper ghHjYhxxMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createJcxx(JcxxCreateReqVO createReqVO) {
        // 校验登记序号是否唯一
        validateJcxxForCreateOrUpdate(createReqVO.getDjxh(), null);

        // 插入
        GhHjJcxxDO jcxx = BeanUtils.toBean(createReqVO, GhHjJcxxDO.class);
        jcxxMapper.insert(jcxx);

        // 保存银行信息
        saveGhHjYhxx(jcxx);

        // 返回
        return jcxx.getDjxh();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJcxx(JcxxUpdateReqVO updateReqVO) {
        // 校验存在
        GhHjJcxxDO oldJcxx = validateJcxxExists(updateReqVO.getDjxh());

        // 更新
        GhHjJcxxDO updateObj = BeanUtils.toBean(updateReqVO, GhHjJcxxDO.class);
        jcxxMapper.updateById(updateObj);

        // 检查银行信息是否变更
        if (isBankInfoChanged(oldJcxx, updateObj)) {
            // 检查今天是否已经有生效的记录
            GhHjYhxxDO todayRecord = ghHjYhxxMapper.selectOne(new LambdaQueryWrapperX<GhHjYhxxDO>()
                    .eq(GhHjYhxxDO::getDjxh, updateObj.getDjxh())
                    .eq(GhHjYhxxDO::getYxqq, LocalDate.now())
                    .isNull(GhHjYhxxDO::getYxqz));

            if (todayRecord != null) {
                // 如果今天已经有记录，直接更新这条记录
                todayRecord.setJcghzh(updateObj.getJcghzh());
                todayRecord.setJcghhm(updateObj.getJcghhm());
                todayRecord.setJcghhh(updateObj.getJcghhh());
                todayRecord.setJcghyh(updateObj.getJcghyh());
                ghHjYhxxMapper.updateById(todayRecord);
            } else {
                // 作废旧记录
                expireOldBankInfo(updateObj.getDjxh());
                // 保存银行信息
                saveGhHjYhxx(updateObj);
            }
        }
    }

    @Override
    public void deleteJcxx(String id) {
        // 校验存在
        validateJcxxExists(id);
        // 删除
        jcxxMapper.deleteById(id);
    }

    private void saveGhHjYhxx(GhHjJcxxDO jcxx) {
        GhHjYhxxDO yhxx = GhHjYhxxDO.builder()
                .djxh(jcxx.getDjxh())
                .jcghzh(jcxx.getJcghzh())
                .jcghhm(jcxx.getJcghhm())
                .jcghhh(jcxx.getJcghhh())
                .jcghyh(jcxx.getJcghyh())
                .yxqq(LocalDate.now()) // 有效起期为当前日期
                .yxqz(null) // 有效止期为空，表示当前有效
                .build();
        ghHjYhxxMapper.insert(yhxx);
    }

    private void expireOldBankInfo(String djxh) {
        // 将旧的有效记录作废（设置有效止期为当前日期）
        ghHjYhxxMapper.update(null, new LambdaUpdateWrapper<GhHjYhxxDO>()
                .eq(GhHjYhxxDO::getDjxh, djxh)
                .isNull(GhHjYhxxDO::getYxqz) // 只处理当前有效的
                .set(GhHjYhxxDO::getYxqz, LocalDate.now()));
    }

    private boolean isBankInfoChanged(GhHjJcxxDO oldObj, GhHjJcxxDO newObj) {
        if (oldObj == null) {
            return true;
        }
        return !Objects.equals(oldObj.getJcghzh(), newObj.getJcghzh()) ||
                !Objects.equals(oldObj.getJcghhm(), newObj.getJcghhm()) ||
                !Objects.equals(oldObj.getJcghhh(), newObj.getJcghhh()) ||
                !Objects.equals(oldObj.getJcghyh(), newObj.getJcghyh());
    }

    private void validateJcxxForCreateOrUpdate(String djxh, String id) {
        // 如果 ID 不一致，说明是修改了登记序号（虽然 PK 通常不修改），或者新增
        if (id == null) {
            GhHjJcxxDO exists = jcxxMapper.selectById(djxh);
            if (exists != null) {
                throw exception(GH_HJ_JCXX_DJXH_EXISTS);
            }
        }
    }

    private GhHjJcxxDO validateJcxxExists(String id) {
        GhHjJcxxDO jcxxDO = jcxxMapper.selectById(id);
        if (jcxxDO == null) {
            throw exception(GH_HJ_JCXX_NOT_EXISTS);
        }
        return jcxxDO;
    }

    @Override
    public GhHjJcxxDO getJcxx(String id) {
        return jcxxMapper.selectById(id);
    }

    @Override
    public PageResult<GhHjJcxxDO> getJcxxPage(JcxxPageReqVO pageReqVO) {
        return jcxxMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GhHjJcxxDO> getJcxxList(String lxdh) {
        return jcxxMapper.selectList(new LambdaQueryWrapperX<GhHjJcxxDO>()
                .eqIfPresent(GhHjJcxxDO::getLxdh, lxdh));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean allocationJcxx(JcxxBaseVO baseVO) {
        // 参数校验
        if (baseVO == null || StringUtils.isBlank(baseVO.getDeptId())
                || CollectionUtils.isEmpty(baseVO.getDjxhs())) {
            throw new IllegalArgumentException("参数为空，无法调拨");
        }

        // 执行调拨
        int update = jcxxMapper.updateAllocationJcxx(baseVO);
        if (update == 0) {
            throw new IllegalArgumentException("户籍调拨失败");
        }
        return true;
    }
}


