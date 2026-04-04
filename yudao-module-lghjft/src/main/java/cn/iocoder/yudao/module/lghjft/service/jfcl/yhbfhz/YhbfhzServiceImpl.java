package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfhz;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.file.utils.DateUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo.YhbfhzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhz.YhbfhzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfmx.YhbfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfhz.YhbfhzMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfmx.YhbfmxMapper;
import cn.iocoder.yudao.module.lghjft.service.jfcl.ZsYh.ZsYhService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 银行拨付汇总 Service 实现类
 */
@Service
@Validated
public class YhbfhzServiceImpl implements YhbfhzService {

    // 固定常量
    private static final String BODY_OBJ = "bodyObj";
    private static final String HEAD_OBJ = "headObj";
    private static final String USER_SYSTEM_ID = "system";
    @Resource
    private YhbfhzMapper yhbfhzMapper;
    @Resource
    private YhbfmxMapper yhbfmxMapper;
    @Resource
    private ZsYhService zsYhService;

    /**
     * 查询银行拨付汇总
     */
    @Override
    public YhbfhzDO selectYhbfhzByBfhzid(String bfhzid) {
        return yhbfhzMapper.selectYhbfhzByBfhzid(bfhzid);
    }

    /**
     * 查询银行拨付汇总列表
     */
    @Override
    public PageResult<YhbfhzDO> selectYhbfhzList(YhbfhzPageReqVO reqVO) {
        // 完全和你范例一样的写法
        Page<YhbfhzDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<YhbfhzDO> ipage = yhbfhzMapper.selectYhbfhzList(page, reqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    /**
     * 新增银行拨付汇总
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertYhbfhz(YhbfhzDO yhbfhz) {
        yhbfhz.setCreateTime(DateUtils.getNowDate());
        return yhbfhzMapper.insertYhbfhz(yhbfhz);
    }

    /**
     * 修改银行拨付汇总
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateYhbfhz(YhbfhzDO yhbfhz) {
        yhbfhz.setUpdateTime(DateUtils.getNowDate());
        return yhbfhzMapper.updateYhbfhz(yhbfhz);
    }

    /**
     * 批量删除银行拨付汇总
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteYhbfhzByBfhzids(String[] bfhzids) {
        return yhbfhzMapper.deleteYhbfhzByBfhzids(bfhzids);
    }

    /**
     * 删除银行拨付汇总信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteYhbfhzByBfhzid(String bfhzid) {
        return yhbfhzMapper.deleteYhbfhzByBfhzid(bfhzid);
    }

    /**
     * 提交银行审核
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateYhbfhztj(YhbfhzDO yhbfhz) {
        if (Objects.isNull(yhbfhz)) {
            throw new ServiceException("拨付汇总行数据不能为空！");
        }
        String bfhzid = String.valueOf(yhbfhz.getBfhzid());
        if (StringUtils.isBlank(bfhzid)) {
            throw new ServiceException("拨付汇总ID不能为空！");
        }
        YhbfhzDO yhbfhz_ = yhbfhzMapper.selectYhbfhzByBfhzid(bfhzid);
        if (StringUtils.isNotBlank(bfhzid) && (!"N".equals(yhbfhz_.getBfzt()))) {
            throw new ServiceException("非待提交审批状态，不能提交！");
        }
        if (StringUtils.isBlank(yhbfhz_.getBfidStr())) {
            throw new ServiceException("拨付汇总bfidStr不能为空！");
        }
        List<YhbfmxDO> yhbfmxs = yhbfmxMapper.selectListByBfidStr(yhbfhz_.getBfidStr().split(","));
        Map<String, Object> bodyParam = new HashMap<>(2);
        bodyParam.put("yhbfhz", yhbfhz_);
        bodyParam.put("yhbfmxs", yhbfmxs);
        Map<String, JSONObject> resMap = zsYhService.qyzfpljbFc(bodyParam, String.valueOf(yhbfhz_.getBfhzid()));
        JSONObject bodyObj = resMap.get(BODY_OBJ);
        JSONObject headObj = resMap.get(HEAD_OBJ);

        if ("SUC0000".equals(headObj.getString("resultcode"))) {
            String reqid = headObj.getString("reqid");
            yhbfhz_.setBfzt("NTE");
            yhbfhz_.setThrq(reqid);
            yhbfhz_.setBfjg("S");
        } else {
            Object obj = bodyObj.get("bb1paybhz1");
            JSONArray bb1paybhz1Arry = new JSONArray();
            if (obj instanceof JSONArray) {
                bb1paybhz1Arry = (JSONArray) obj;
            }

            if (bb1paybhz1Arry.size() > 0) {
                for (int i = 0; i < bb1paybhz1Arry.size(); i++) {
                    if (Objects.isNull(bb1paybhz1Arry.get(i))) {
                        continue;
                    }
                    JSONObject jsonObj = bb1paybhz1Arry.getJSONObject(i);
                    if ("FIN".equals(jsonObj.getString("reqSts"))) {
                        if ("F".equals(jsonObj.getString("rtnFlg"))) {
                            yhbfhz_.setBfjg("F");
                            yhbfhz_.setSbbs("1");
                            yhbfhz_.setSbje(yhbfhz_.getHzje());
                            yhbfhz_.setThbs("0");
                            yhbfhz_.setThje(BigDecimal.ZERO);
                            yhbfhz_.setCgbs("0");
                            yhbfhz_.setCgje(BigDecimal.ZERO);
                        } else if ("B".equals(jsonObj.getString("rtnFlg"))) {
                            yhbfhz_.setBfjg("B");
                            yhbfhz_.setSbbs("0");
                            yhbfhz_.setSbje(BigDecimal.ZERO);
                            yhbfhz_.setThbs("1");
                            yhbfhz_.setThje(yhbfhz_.getHzje());
                            yhbfhz_.setCgbs("1");
                            yhbfhz_.setCgje(BigDecimal.ZERO);
                        }
                    }
                }
            }
        }
        yhbfhz_.setUpdateBy(USER_SYSTEM_ID);
        yhbfhz_.setUpdateTime(DateUtils.getNowDate());
        yhbfhzMapper.updateYhbfhz(yhbfhz_);
    }

    /**
     * 支付明细查询
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZfmxcx(YhbfhzDO yhbfhz) {
        if (Objects.isNull(yhbfhz)) {
            throw new ServiceException("拨付汇总行数据不能为空！");
        }
        String bfhzid = String.valueOf(yhbfhz.getBfhzid());
        if (StringUtils.isBlank(bfhzid)) {
            throw new ServiceException("拨付汇总ID不能为空！");
        }
        YhbfhzDO yhbfhz_ = yhbfhzMapper.selectYhbfhzByBfhzid(bfhzid);
        if (StringUtils.isNotBlank(bfhzid) && "N".equals(yhbfhz_.getBfzt())) {
            throw new ServiceException("未提交银行审核，不能查询！");
        }
        if (StringUtils.isBlank(yhbfhz_.getBfidStr())) {
            throw new ServiceException("拨付汇总bfidStr不能为空！");
        }
        List<YhbfmxDO> yhbfmxs = Lists.newArrayList();
        Map<String, Object> bodyParam = new HashMap<>(2);
        bodyParam.put("yhbfhz", yhbfhz_);
        bodyParam.put("yhbfmxs", yhbfmxs);
        Map<String, JSONObject> resMap = zsYhService.qyplzfmxcxFc(bodyParam, String.valueOf(yhbfhz_.getBfhzid()));
        JSONObject bodyObj = resMap.get(BODY_OBJ);
        JSONObject headObj = resMap.get(HEAD_OBJ);

        if ("SUC0000".equals(headObj.getString("resultcode"))) {

            Object obj1 = bodyObj.get("bb1qrybdy1");
            Object obj2 = bodyObj.get("bb1qrybdz1");

            JSONArray bb1qrybdy1Arry = new JSONArray();
            JSONArray bb1qrybdz1Arry = new JSONArray();

            if (obj1 instanceof JSONArray) {
                bb1qrybdy1Arry = (JSONArray) obj1;
            }
            if (obj2 instanceof JSONArray) {
                bb1qrybdz1Arry = (JSONArray) obj2;
            }

            JSONArray bb1qrybdy1Arrye = new JSONArray();
            JSONArray bb1qrybdz1Arrye = new JSONArray();

            if (bb1qrybdy1Arry.size() > 0) {
                bb1qrybdy1Arrye = bb1qrybdy1Arry.getJSONArray(0);
            }
            if (bb1qrybdz1Arry.size() > 0) {
                bb1qrybdz1Arrye = bb1qrybdz1Arry.getJSONArray(0);
            }

            if (bb1qrybdz1Arrye.size() > 0) {
                for (int i = 0; i < bb1qrybdz1Arrye.size(); i++) {
                    if (Objects.isNull(bb1qrybdz1Arrye.get(i))) {
                        continue;
                    }
                    JSONObject obj = bb1qrybdz1Arrye.getJSONObject(i);
                    String yurRef = obj.getString("yurRef");
                    String reqSts = obj.getString("reqSts");
                    String rtnFlg = obj.getString("rtnFlg");
                    String errTxt = obj.getString("errTxt");
                    String eptDat = obj.getString("eptDat");

                    YhbfmxDO yhbfmx = new YhbfmxDO();
                    yhbfmx.setBfid(Integer.parseInt(yurRef));
                    yhbfmx.setBfzt(reqSts);
                    yhbfmx.setBfjg(rtnFlg);
                    yhbfmx.setBz(errTxt);
                    yhbfmx.setQwr(eptDat);
                    yhbfmx.setUpdateBy(USER_SYSTEM_ID);
                    yhbfmx.setUpdateTime(DateUtils.getNowDate());
                    yhbfmxs.add(yhbfmx);
                    yhbfmxMapper.updateById(yhbfmx);
                }
            }

            if (bb1qrybdy1Arrye.size() > 0) {
                JSONObject bb1qrybdy1Obj = bb1qrybdy1Arrye.getJSONObject(0);
                String bthNbr = bb1qrybdy1Obj.getString("bthNbr");
            }
        }
    }

}