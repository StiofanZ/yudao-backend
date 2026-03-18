package cn.iocoder.yudao.module.lghjft.service.jfcl.hbfhz;
import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.file.utils.DateUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo.YhbfhzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfmx.YhbfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfmx.YhbfmxMapper;
import cn.iocoder.yudao.module.lghjft.service.jfcl.ZsYh.ZsYhService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import java.math.BigDecimal;
import java.util.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz.YhbfhzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.hbfhz.YhbfhzMapper;


/**
 * 银行拨付汇总 Service 实现类
 */
@Service
@Validated
public class YhbfhzServiceImpl implements YhbfhzService {

    @Resource
    private YhbfhzMapper yhbfhzMapper;

    @Resource
    private YhbfmxMapper yhbfmxMapper;

    @Resource
    private ZsYhService zsYhService;

    // 固定常量
    private static final String BODY_OBJ = "bodyObj";
    private static final String HEAD_OBJ = "headObj";
    private static final String USER_SYSTEM_ID = "system";

    /**
     * 查询银行拨付汇总
     */
    @Override
    public YhbfhzDO selectGhHkxxYhbfhzByBfhzid(String bfhzid) {
        return yhbfhzMapper.selectGhHkxxYhbfhzByBfhzid(bfhzid);
    }

    /**
     * 查询银行拨付汇总列表
     */
    @Override
    public PageResult<YhbfhzDO> selectGhHkxxYhbfhzList(YhbfhzPageReqVO reqVO) {
        // 完全和你范例一样的写法
        Page<YhbfhzDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<YhbfhzDO> ipage = yhbfhzMapper.selectGhHkxxYhbfhzList(page, reqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    /**
     * 新增银行拨付汇总
     */
    @Override
    public int insertGhHkxxYhbfhz(YhbfhzDO ghHkxxYhbfhz) {
        ghHkxxYhbfhz.setCreateTime(DateUtils.getNowDate());
        return yhbfhzMapper.insertGhHkxxYhbfhz(ghHkxxYhbfhz);
    }

    /**
     * 修改银行拨付汇总
     */
    @Override
    public int updateGhHkxxYhbfhz(YhbfhzDO ghHkxxYhbfhz) {
        ghHkxxYhbfhz.setUpdateTime(DateUtils.getNowDate());
        return yhbfhzMapper.updateGhHkxxYhbfhz(ghHkxxYhbfhz);
    }

    /**
     * 批量删除银行拨付汇总
     */
    @Override
    public int deleteGhHkxxYhbfhzByBfhzids(String[] bfhzids) {
        return yhbfhzMapper.deleteGhHkxxYhbfhzByBfhzids(bfhzids);
    }

    /**
     * 删除银行拨付汇总信息
     */
    @Override
    public int deleteGhHkxxYhbfhzByBfhzid(String bfhzid) {
        return yhbfhzMapper.deleteGhHkxxYhbfhzByBfhzid(bfhzid);
    }

    /**
     * 提交银行审核
     */
    @Override
    public void updateYhbfhztj(YhbfhzDO ghHkxxYhbfhz) {
        if (Objects.isNull(ghHkxxYhbfhz)) {
            throw new ServiceException("拨付汇总行数据不能为空！");
        }
        String bfhzid = String.valueOf(ghHkxxYhbfhz.getBfhzid());
        if (StringUtils.isBlank(bfhzid)) {
            throw new ServiceException("拨付汇总ID不能为空！");
        }
        YhbfhzDO ghHkxxYhbfhz_ = yhbfhzMapper.selectGhHkxxYhbfhzByBfhzid(bfhzid);
        if (StringUtils.isNotBlank(bfhzid) && (!"N".equals(ghHkxxYhbfhz_.getBfzt()))) {
            throw new ServiceException("非待提交审批状态，不能提交！");
        }
        if (StringUtils.isBlank(ghHkxxYhbfhz_.getBfidStr())) {
            throw new ServiceException("拨付汇总bfidStr不能为空！");
        }
        List<YhbfmxDO> ghHkxxYhbfmxs = yhbfmxMapper.selectListByBfidStr(ghHkxxYhbfhz_.getBfidStr().split(","));
        Map<String, Object> bodyParam = new HashMap<>(2);
        bodyParam.put("ghHkxxYhbfhz", ghHkxxYhbfhz_);
        bodyParam.put("ghHkxxYhbfmxs", ghHkxxYhbfmxs);
        Map<String, JSONObject> resMap = zsYhService.qyzfpljbFc(bodyParam, String.valueOf(ghHkxxYhbfhz_.getBfhzid()));
        JSONObject bodyObj = resMap.get(BODY_OBJ);
        JSONObject headObj = resMap.get(HEAD_OBJ);

        if ("SUC0000".equals(headObj.getString("resultcode"))) {
            String reqid = headObj.getString("reqid");
            ghHkxxYhbfhz_.setBfzt("NTE");
            ghHkxxYhbfhz_.setThrq(reqid);
            ghHkxxYhbfhz_.setBfjg("S");
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
                            ghHkxxYhbfhz_.setBfjg("F");
                            ghHkxxYhbfhz_.setSbbs("1");
                            ghHkxxYhbfhz_.setSbje(ghHkxxYhbfhz_.getHzje());
                            ghHkxxYhbfhz_.setThbs("0");
                            ghHkxxYhbfhz_.setThje(BigDecimal.ZERO);
                            ghHkxxYhbfhz_.setCgbs("0");
                            ghHkxxYhbfhz_.setCgje(BigDecimal.ZERO);
                        } else if ("B".equals(jsonObj.getString("rtnFlg"))) {
                            ghHkxxYhbfhz_.setBfjg("B");
                            ghHkxxYhbfhz_.setSbbs("0");
                            ghHkxxYhbfhz_.setSbje(BigDecimal.ZERO);
                            ghHkxxYhbfhz_.setThbs("1");
                            ghHkxxYhbfhz_.setThje(ghHkxxYhbfhz_.getHzje());
                            ghHkxxYhbfhz_.setCgbs("1");
                            ghHkxxYhbfhz_.setCgje(BigDecimal.ZERO);
                        }
                    }
                }
            }
        }
        ghHkxxYhbfhz_.setUpdateBy(USER_SYSTEM_ID);
        ghHkxxYhbfhz_.setUpdateTime(DateUtils.getNowDate());
        yhbfhzMapper.updateGhHkxxYhbfhz(ghHkxxYhbfhz_);
    }

    /**
     * 支付明细查询
     */
    @Override
    public void updateZfmxcx(YhbfhzDO ghHkxxYhbfhz) {
        if (Objects.isNull(ghHkxxYhbfhz)) {
            throw new ServiceException("拨付汇总行数据不能为空！");
        }
        String bfhzid = String.valueOf(ghHkxxYhbfhz.getBfhzid());
        if (StringUtils.isBlank(bfhzid)) {
            throw new ServiceException("拨付汇总ID不能为空！");
        }
        YhbfhzDO ghHkxxYhbfhz_ = yhbfhzMapper.selectGhHkxxYhbfhzByBfhzid(bfhzid);
        if (StringUtils.isNotBlank(bfhzid) && "N".equals(ghHkxxYhbfhz_.getBfzt())) {
            throw new ServiceException("未提交银行审核，不能查询！");
        }
        if (StringUtils.isBlank(ghHkxxYhbfhz_.getBfidStr())) {
            throw new ServiceException("拨付汇总bfidStr不能为空！");
        }
        List<YhbfmxDO> ghHkxxYhbfmxs = Lists.newArrayList();
        Map<String, Object> bodyParam = new HashMap<>(2);
        bodyParam.put("ghHkxxYhbfhz", ghHkxxYhbfhz_);
        bodyParam.put("ghHkxxYhbfmxs", ghHkxxYhbfmxs);
        Map<String, JSONObject> resMap = zsYhService.qyplzfmxcxFc(bodyParam, String.valueOf(ghHkxxYhbfhz_.getBfhzid()));
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

                    YhbfmxDO ghHkxxYhbfmx = new YhbfmxDO();
                    ghHkxxYhbfmx.setBfid(Integer.parseInt(yurRef));
                    ghHkxxYhbfmx.setBfzt(reqSts);
                    ghHkxxYhbfmx.setBfjg(rtnFlg);
                    ghHkxxYhbfmx.setBz(errTxt);
                    ghHkxxYhbfmx.setQwr(eptDat);
                    ghHkxxYhbfmx.setUpdateBy(USER_SYSTEM_ID);
                    ghHkxxYhbfmx.setUpdateTime(DateUtils.getNowDate());
                    ghHkxxYhbfmxs.add(ghHkxxYhbfmx);
                    yhbfmxMapper.updateById(ghHkxxYhbfmx);
                }
            }

            if (bb1qrybdy1Arrye.size() > 0) {
                JSONObject bb1qrybdy1Obj = bb1qrybdy1Arrye.getJSONObject(0);
                String bthNbr = bb1qrybdy1Obj.getString("bthNbr");
            }
        }
    }

}