package cn.iocoder.yudao.module.lghjft.service.jfcl.dqdssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqzldssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.*;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.dqdssj.CsFpblDqdssjMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.dqdssj.DqdssjMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.dqdssj.GhJfDqdssjMapper;
import cn.iocoder.yudao.module.lghjft.enums.BackEnums;
import cn.iocoder.yudao.module.lghjft.enums.constant.BackConstant;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class DqdssjServiceImpl implements DqdssjService {

    private static final Logger logger = LoggerFactory.getLogger(DqdssjServiceImpl.class);

    @Resource
    private DqdssjMapper dqdssjMapper;

    @Resource
    private GhJfDqdssjMapper ghJfDqdssjMapper;

    @Resource
    private CsFpblDqdssjMapper csFpblDqdssjMapper;

    @Override
    public PageResult<JfclDqdssjDO> getDqdssjPage(DqdssjPageReqVO pageReqVO) {
        return dqdssjMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<JfzcDqdssjVo> getDqzldssjPage(DqzldssjPageReqVO pageReqVO) {
        IPage<JfzcDqdssjVo> page = MyBatisUtils.buildPage(pageReqVO);
        List<JfzcDqdssjVo> list = dqdssjMapper.selecListzl(pageReqVO);
        // Manual pagination for XML-based queries
        int total = list.size();
        int fromIndex = (int) ((page.getCurrent() - 1) * page.getSize());
        int toIndex = Math.min(fromIndex + (int) page.getSize(), total);
        List<JfzcDqdssjVo> pageList = (fromIndex >= total) ? Collections.emptyList() : list.subList(fromIndex, toIndex);
        return new PageResult<>(pageList, (long) total);
    }

    @Override
    public String updateDqdssjrk(DqdssjSaveReqVO reqVO) {
        try {
            Thread thread = new Thread(new UpdateDqdssjrkRunnable(reqVO));
            thread.start();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "读取代收数据异常";
        }
        return "成功进入读取代收数据，请稍后查看结果";
    }

    @Override
    public String updateDqdssjrkzl(DqdssjSaveReqVO reqVO) {
        try {
            Thread thread = new Thread(new UpdateDqdssjrkRunnable(reqVO));
            thread.start();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "读取代收数据异常";
        }
        return "成功进入读取代收数据，请稍后查看结果";
    }

    // ========== V1: UpdateDqdssjrkRunnable inner class (1:1 translation) ==========

    class UpdateDqdssjrkRunnable implements Runnable {

        private final DqdssjSaveReqVO reqVO;

        public UpdateDqdssjrkRunnable(DqdssjSaveReqVO reqVO) {
            this.reqVO = reqVO;
        }

        @Override
        public void run() {
            logger.info("读取代收数据入库开始：{}", JSONObject.toJSONString(reqVO));
            try {
                Map<String, Object> param = new HashMap<>(10);
                param.put("rkrqStart", reqVO.getRkrqStart());
                param.put("rkrqEnd", reqVO.getRkrqEnd());
                param.put("spuuid", reqVO.getSpuuid());
                param.put("zlbj", reqVO.getZlbj());
                Map<String, String> csFpblMap = new HashMap<>();
                CsFpblDqdssjVo csFpbl = new CsFpblDqdssjVo();
                List<CsFpblDqdssjVo> csFpblJobList = csFpblDqdssjMapper.selectCsFpblJobList(csFpbl);
                if (!CollectionUtils.isEmpty(csFpblJobList)) {
                    csFpblJobList.forEach(csFpblItem -> {
                        try {
                            if (csFpblItem.getTj() != null && !csFpblItem.getTj().isEmpty()) {
                                param.put("yxqq", csFpblItem.getYxqq());
                                param.put("yxqz", csFpblItem.getYxqz());
                                param.put("whereSql", csFpblItem.getTj());
                                handleCsFpblItem(csFpblItem, param, csFpblMap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error("分成异常：", e.getMessage());
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
                handleQsjshkrj(reqVO);
            } catch (Exception e) {
                logger.error("读取代收数据异常：{}", e);
            } finally {
                logger.info("读取代收数据入库结束");
            }
        }

        private void handleQsjshkrj(DqdssjSaveReqVO reqVO) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Map<String, Object> param = new HashMap<>(10);
            param.put("rkrqStart", sdf.format(reqVO.getRkrqStart()));
            param.put("rkrqEnd", sdf.format(reqVO.getRkrqEnd()));
            List<QsjshkrjDO> qsjshkrjList = csFpblDqdssjMapper.selectQsjshkrjList(param);
            List<String> rkrqList = qsjshkrjList.stream().map(QsjshkrjDO::getRkrq).distinct().collect(Collectors.toList());
            String userId = BackConstant.USER_SYSTEM_ID;
            List<JfzcDqdssjVo> jfzcVoList = csFpblDqdssjMapper.selectForQsjshkrj(param);
            List<String> listRkrq = splitDateList(reqVO.getRkrqStart(), reqVO.getRkrqEnd());
            List<QsjshkrjDO> qsjshkrjJobListUpd = new ArrayList<>(1);
            List<QsjshkrjDO> qsjshkrjJobListAdd = new ArrayList<>(1);
            listRkrq.forEach(rkrq -> {
                List<JfzcDqdssjVo> jfzcVoList_ = jfzcVoList.stream()
                        .filter(item -> sdf.format(item.getRkrq()).equals(rkrq))
                        .collect(Collectors.toList());
                BigDecimal je = BigDecimal.ZERO;
                if (!CollectionUtils.isEmpty(jfzcVoList_) && jfzcVoList_.size() > 0) {
                    JfzcDqdssjVo jfzcVo = jfzcVoList_.get(0);
                    je = jfzcVo.getYbtse();
                }
                QsjshkrjDO qsjshkrj = new QsjshkrjDO();
                qsjshkrj.setRkrq(rkrq);
                qsjshkrj.setLx(BackConstant.QSJSHKRJ_LX);
                qsjshkrj.setJe(je);
                qsjshkrj.setCreateBy(userId);
                qsjshkrj.setCreateTime(new Date());
                qsjshkrj.setUpdateBy(userId);
                qsjshkrj.setUpdateTime(new Date());
                List<String> rkrqList_ = rkrqList.stream().filter(item -> item.equals(rkrq)).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(rkrqList_)) {
                    qsjshkrjJobListUpd.add(qsjshkrj);
                } else {
                    qsjshkrjJobListAdd.add(qsjshkrj);
                }
            });
            if (!CollectionUtils.isEmpty(qsjshkrjJobListUpd)) {
                csFpblDqdssjMapper.updateQsjshkrj(qsjshkrjJobListUpd);
            }
            if (!CollectionUtils.isEmpty(qsjshkrjJobListAdd)) {
                csFpblDqdssjMapper.insertQsjshkrj(qsjshkrjJobListAdd);
            }
        }

        /**
         * V1: DateUtils.splitDateList
         */
        private List<String> splitDateList(Date startDate, Date endDate) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<String> listDate = new ArrayList<>();
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
                    listDate.add(dateFormat.format(calendar.getTime()));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                return listDate;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listDate;
        }

        /**
         * V1: handleCsFpblItem - proportion distribution logic
         */
        private void handleCsFpblItem(CsFpblDqdssjVo csFpblItem, Map<String, Object> param, Map<String, String> csFpblMap) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (reqVO.getZlbj().equals("1")) {
                List<JfzcDqdssjVo> jfzcVoList = csFpblDqdssjMapper.selectJFZCJobList(param);
                logger.debug("选择的入库日期入库经费在 {} 至 {}有效期期间，共计{}条经费数据，条件：{}，",
                        sdf.format(csFpblItem.getYxqq()), sdf.format(csFpblItem.getYxqz()),
                        jfzcVoList.size(), csFpblItem.getTj());
                if (!CollectionUtils.isEmpty(jfzcVoList) && jfzcVoList.size() > 0) {
                    List<List<JfzcDqdssjVo>> partitions = partition(jfzcVoList, BackConstant.PARTS_SIZE);
                    for (List<JfzcDqdssjVo> partitionList : partitions) {
                        List<GhjfDqdssjVo> ghjfJobVoList = new ArrayList<>(partitionList.size());
                        partitionList.forEach(jfzcVoItem -> {
                            if (0 <= jfzcVoItem.getSkssqq().compareTo(csFpblItem.getYxqq())
                                    && 0 >= jfzcVoItem.getSkssqz().compareTo(csFpblItem.getYxqz())
                                    && null == csFpblMap.get(jfzcVoItem.getSpuuid())) {
                                processFpbl(jfzcVoItem, csFpblItem, csFpblMap, ghjfJobVoList);
                            }
                        });
                        flushGhjfBatch(ghjfJobVoList);
                    }
                }
            } else {
                List<JfzcDqdssjVo> jfzcVoList = csFpblDqdssjMapper.selectJFZCJobzlList(param);
                logger.debug("选择的入库日期入库经费在 {} 至 {}有效期期间，共计{}条经费数据，条件：{}，",
                        sdf.format(csFpblItem.getYxqq()), sdf.format(csFpblItem.getYxqz()),
                        jfzcVoList.size(), csFpblItem.getTj());
                if (!CollectionUtils.isEmpty(jfzcVoList) && jfzcVoList.size() > 0) {
                    List<List<JfzcDqdssjVo>> partitions = partition(jfzcVoList, BackConstant.PARTS_SIZE);
                    for (List<JfzcDqdssjVo> partitionList : partitions) {
                        List<GhjfDqdssjVo> ghjfJobVoList = new ArrayList<>(partitionList.size());
                        partitionList.forEach(jfzcVoItem -> {
                            if (0 <= jfzcVoItem.getSkssqq().compareTo(csFpblItem.getYxqq())
                                    && 0 >= jfzcVoItem.getSkssqz().compareTo(csFpblItem.getYxqz())
                                    && null == csFpblMap.get(jfzcVoItem.getSpuuid())) {
                                processFpbl(jfzcVoItem, csFpblItem, csFpblMap, ghjfJobVoList);
                            }
                        });
                        if (!CollectionUtils.isEmpty(ghjfJobVoList)) {
                            List<String> spuuidList = ghjfJobVoList.stream().map(GhjfDqdssjVo::getSpuuid).collect(Collectors.toList());
                            List<GhjfDqdssjVo> updateList = getUpdateList(spuuidList, ghjfJobVoList);
                            if (!CollectionUtils.isEmpty(updateList)) {
                                ghJfDqdssjMapper.updateBatch(updateList);
                                ghJfDqdssjMapper.updateBatch(updateList);
                            }
                            List<GhjfDqdssjVo> addList = getAddList(spuuidList, ghjfJobVoList, updateList);
                            if (!CollectionUtils.isEmpty(addList)) {
                                ghJfDqdssjMapper.insertBatch(addList);
                            }
                        }
                    }
                }
            }
        }

        /**
         * Shared proportion distribution logic (extracted from V1 duplicate blocks)
         */
        private void processFpbl(JfzcDqdssjVo jfzcVoItem, CsFpblDqdssjVo csFpblItem,
                                 Map<String, String> csFpblMap, List<GhjfDqdssjVo> ghjfJobVoList) {
            jfzcVoItem.setCbjthbj(BackEnums.CBJTHBJ_N.getCode());
            boolean cbjbj = false;
            if ("399001020".equals(jfzcVoItem.getZspmDm())) {
                cbjbj = true;
            }
            // proportions
            BigDecimal hyghbl = csFpblItem.getHyghbl() != null ? csFpblItem.getHyghbl() : BigDecimal.ZERO;
            BigDecimal jcghbl = csFpblItem.getJcghbl() != null ? csFpblItem.getJcghbl() : BigDecimal.ZERO;
            BigDecimal qgzghbl = csFpblItem.getQgzghbl() != null ? csFpblItem.getQgzghbl() : BigDecimal.ZERO;
            BigDecimal sdghbl = csFpblItem.getSdghbl() != null ? csFpblItem.getSdghbl() : BigDecimal.ZERO;
            BigDecimal sdsjbl = csFpblItem.getSdsjbl() != null ? csFpblItem.getSdsjbl() : BigDecimal.ZERO;
            BigDecimal sjcjbl = csFpblItem.getSjcjbl() != null ? csFpblItem.getSjcjbl() : BigDecimal.ZERO;
            BigDecimal sjghbl = csFpblItem.getSjghbl() != null ? csFpblItem.getSjghbl() : BigDecimal.ZERO;
            BigDecimal swjgbl = csFpblItem.getSwjgbl() != null ? csFpblItem.getSwjgbl() : BigDecimal.ZERO;
            BigDecimal szghbl = csFpblItem.getSzghbl() != null ? csFpblItem.getSzghbl() : BigDecimal.ZERO;
            BigDecimal xjghbl = csFpblItem.getXjghbl() != null ? csFpblItem.getXjghbl() : BigDecimal.ZERO;
            BigDecimal cbjbl = cbjbj ? jcghbl : BigDecimal.ZERO;

            BigDecimal zbl = hyghbl.add(jcghbl).add(qgzghbl).add(sdghbl).add(sdsjbl)
                    .add(sjcjbl).add(sjghbl).add(swjgbl).add(szghbl).add(xjghbl);
            if (zbl.compareTo(BigDecimal.ONE) == 0) {
                jfzcVoItem.setHyghbl(hyghbl);
                if (cbjbj) {
                    jfzcVoItem.setCbjbl(cbjbl);
                    jfzcVoItem.setJcghbl(BigDecimal.ZERO);
                    jfzcVoItem.setJcghhh(null);
                    jfzcVoItem.setJcghhm(null);
                    jfzcVoItem.setJcghzh(null);
                } else {
                    jfzcVoItem.setJcghbl(jcghbl);
                    jfzcVoItem.setCbjbl(BigDecimal.ZERO);
                }

                jfzcVoItem.setQgghbl(qgzghbl);
                jfzcVoItem.setSdghbl(sdghbl);
                jfzcVoItem.setSdsbl(sdsjbl);
                jfzcVoItem.setSjghbl(sjghbl);
                jfzcVoItem.setSwjgbl(swjgbl);
                jfzcVoItem.setSzghbl(szghbl);
                jfzcVoItem.setXjghbl(xjghbl);
                jfzcVoItem.setJcjbl(sjcjbl);

                BigDecimal fcyj = jfzcVoItem.getYbtse().subtract(jfzcVoItem.getJmse());
                if (fcyj == null) {
                    fcyj = BigDecimal.ZERO;
                }
                BigDecimal ljje = BigDecimal.ZERO;
                int SCALE = 2;
                @SuppressWarnings("deprecation")
                int ROUND = BigDecimal.ROUND_HALF_UP;

                BigDecimal hyghje = getBlje(fcyj, ljje, fcyj.multiply(hyghbl).setScale(SCALE, ROUND));
                ljje = ljje.add(hyghje);
                if (cbjbl.add(jcghbl).add(qgzghbl).add(sdghbl).add(sdsjbl).add(sjcjbl).add(sjghbl).add(szghbl).add(xjghbl).add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                    if (ljje.compareTo(fcyj) == 1) {
                        hyghje = fcyj.subtract(ljje);
                    } else if (ljje.compareTo(fcyj) == -1) {
                        hyghje = hyghje.add(fcyj.subtract(ljje));
                    }
                }

                BigDecimal cbjje = BigDecimal.ZERO;
                BigDecimal jcghje = BigDecimal.ZERO;
                if (cbjbj) {
                    cbjje = getBlje(fcyj, ljje, fcyj.multiply(cbjbl).setScale(SCALE, ROUND));
                    ljje = ljje.add(cbjje);
                    if (qgzghbl.add(sdghbl).add(sdsjbl).add(sjcjbl).add(sjghbl).add(szghbl).add(xjghbl).add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                        if (ljje.compareTo(fcyj) == 1) {
                            cbjje = fcyj.subtract(ljje);
                        } else if (ljje.compareTo(fcyj) == -1) {
                            cbjje = cbjje.add(fcyj.subtract(ljje));
                        }
                    }
                } else {
                    jcghje = getBlje(fcyj, ljje, fcyj.multiply(jcghbl).setScale(SCALE, ROUND));
                    ljje = ljje.add(jcghje);
                    if (qgzghbl.add(sdghbl).add(sdsjbl).add(sjcjbl).add(sjghbl).add(szghbl).add(xjghbl).add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                        if (ljje.compareTo(fcyj) == 1) {
                            jcghje = fcyj.subtract(ljje);
                        } else if (ljje.compareTo(fcyj) == -1) {
                            jcghje = jcghje.add(fcyj.subtract(ljje));
                        }
                    }
                }
                BigDecimal qgzghje = getBlje(fcyj, ljje, fcyj.multiply(qgzghbl).setScale(SCALE, ROUND));
                ljje = ljje.add(qgzghje);
                if (sdghbl.add(sdsjbl).add(sjcjbl).add(sjghbl).add(szghbl).add(xjghbl).add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                    if (ljje.compareTo(fcyj) == 1) {
                        qgzghje = fcyj.subtract(ljje);
                    } else if (ljje.compareTo(fcyj) == -1) {
                        qgzghje = qgzghje.add(fcyj.subtract(ljje));
                    }
                }
                BigDecimal sdghje = getBlje(fcyj, ljje, fcyj.multiply(sdghbl).setScale(SCALE, ROUND));
                ljje = ljje.add(sdghje);
                if (sdsjbl.add(sjcjbl).add(sjghbl).add(szghbl).add(xjghbl).add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                    if (ljje.compareTo(fcyj) == 1) {
                        sdghje = fcyj.subtract(ljje);
                    } else if (ljje.compareTo(fcyj) == -1) {
                        sdghje = sdghje.add(fcyj.subtract(ljje));
                    }
                }
                BigDecimal sdsjje = getBlje(fcyj, ljje, fcyj.multiply(sdsjbl).setScale(SCALE, ROUND));
                ljje = ljje.add(sdsjje);
                if (sjcjbl.add(sjghbl).add(szghbl).add(xjghbl).add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                    if (ljje.compareTo(fcyj) == 1) {
                        sdsjje = fcyj.subtract(ljje);
                    } else if (ljje.compareTo(fcyj) == -1) {
                        sdsjje = sdsjje.add(fcyj.subtract(ljje));
                    }
                }
                BigDecimal sjcjje = getBlje(fcyj, ljje, fcyj.multiply(sjcjbl).setScale(SCALE, ROUND));
                ljje = ljje.add(sjcjje);
                if (sjghbl.add(szghbl).add(xjghbl).add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                    if (ljje.compareTo(fcyj) == 1) {
                        sjcjje = fcyj.subtract(ljje);
                    } else if (ljje.compareTo(fcyj) == -1) {
                        sjcjje = sjcjje.add(fcyj.subtract(ljje));
                    }
                }
                BigDecimal sjghje = getBlje(fcyj, ljje, fcyj.multiply(sjghbl).setScale(SCALE, ROUND));
                ljje = ljje.add(sjghje);
                if (szghbl.add(xjghbl).add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                    if (ljje.compareTo(fcyj) == 1) {
                        sjghje = fcyj.subtract(ljje);
                    } else if (ljje.compareTo(fcyj) == -1) {
                        sjghje = sjghje.add(fcyj.subtract(ljje));
                    }
                }
                BigDecimal szghje = getBlje(fcyj, ljje, fcyj.multiply(szghbl).setScale(SCALE, ROUND));
                ljje = ljje.add(szghje);
                if (xjghbl.add(swjgbl).compareTo(BigDecimal.ZERO) == 0) {
                    if (ljje.compareTo(fcyj) == 1) {
                        szghje = fcyj.subtract(ljje);
                    } else if (ljje.compareTo(fcyj) == -1) {
                        szghje = szghje.add(fcyj.subtract(ljje));
                    }
                }
                BigDecimal xjghje = getBlje(fcyj, ljje, fcyj.multiply(xjghbl).setScale(SCALE, ROUND));
                ljje = ljje.add(xjghje);
                if (swjgbl.compareTo(BigDecimal.ZERO) == 0) {
                    if (ljje.compareTo(fcyj) == 1) {
                        xjghje = fcyj.subtract(ljje);
                    } else if (ljje.compareTo(fcyj) == -1) {
                        xjghje = xjghje.add(fcyj.subtract(ljje));
                    }
                }
                BigDecimal swjgje = getBlje(fcyj, ljje, fcyj.multiply(swjgbl).setScale(SCALE, ROUND));
                ljje = ljje.add(swjgje);
                if (ljje.compareTo(fcyj) == 1) {
                    swjgje = fcyj.subtract(ljje);
                } else if (ljje.compareTo(fcyj) == -1) {
                    swjgje = swjgje.add(fcyj.subtract(ljje));
                }

                if ("399001020".equals(jfzcVoItem.getZspmDm())) {
                    jfzcVoItem.setCbjje(cbjje);
                    jfzcVoItem.setJcghje(BigDecimal.ZERO);
                } else {
                    jfzcVoItem.setJcghje(jcghje);
                    jfzcVoItem.setCbjje(BigDecimal.ZERO);
                }
                jfzcVoItem.setHyghje(hyghje);
                jfzcVoItem.setQgghje(qgzghje);
                jfzcVoItem.setSdghje(sdghje);
                jfzcVoItem.setSdsje(sdsjje);
                jfzcVoItem.setJcjje(sjcjje);
                jfzcVoItem.setSjghje(sjghje);
                jfzcVoItem.setSwjgje(swjgje);
                jfzcVoItem.setSzghje(szghje);
                jfzcVoItem.setXjghje(xjghje);
                updateJsbj(jfzcVoItem);
                updateZhxx(jfzcVoItem, hyghje, qgzghje, sdghje, sdsjje, sjcjje, sjghje, swjgje, szghje, xjghje, jcghje, cbjje);
                updateGhjfJobVoList(ghjfJobVoList, jfzcVoItem, csFpblItem, csFpblMap);
            }
        }

        private BigDecimal getBlje(BigDecimal fcyj, BigDecimal ljje, BigDecimal blje) {
            if (ljje.add(blje).compareTo(fcyj) == 1) {
                blje = fcyj.subtract(ljje);
            }
            return blje;
        }

        /**
         * V1: updateJsbj - 错误 E,正常 N,计算 T
         */
        private void updateJsbj(JfzcDqdssjVo jfzcVoItem) {
            if (BackConstant.DEPT_ID_QZ.equals(jfzcVoItem.getDeptId())) {
                jfzcVoItem.setJsbj(BackEnums.JSBJ_E.getCode());
                jfzcVoItem.setBz(BackConstant.GHJF_BZ_WHJXX);
            } else {
                if (jfzcVoItem.getCbjthbj().equals(BackEnums.CBJTHBJ_N.getCode())) {
                    if (jfzcVoItem.getJcghhh() == null || jfzcVoItem.getJcghhh().isEmpty()
                            || jfzcVoItem.getJcghhm() == null || jfzcVoItem.getJcghhm().isEmpty()
                            || jfzcVoItem.getJcghzh() == null || jfzcVoItem.getJcghzh().isEmpty()) {
                        if ("399001020".equals(jfzcVoItem.getZspmDm())
                                || "1000".equals(jfzcVoItem.getXtlbDm())
                                || "1100".equals(jfzcVoItem.getXtlbDm())
                                || "2000".equals(jfzcVoItem.getXtlbDm())
                                || "2100".equals(jfzcVoItem.getXtlbDm())
                                || "2200".equals(jfzcVoItem.getXtlbDm())
                                || "2300".equals(jfzcVoItem.getXtlbDm())) {
                            jfzcVoItem.setJsbj(BackEnums.JSBJ_N.getCode());
                            jfzcVoItem.setBz(null);
                        } else {
                            jfzcVoItem.setJsbj(BackEnums.JSBJ_E.getCode());
                            jfzcVoItem.setBz(BackConstant.GHJF_BZ_WZHXX);
                        }
                    }
                } else {
                    jfzcVoItem.setJsbj(BackEnums.JSBJ_N.getCode());
                    jfzcVoItem.setBz(null);
                }
            }
        }

        private void updateZhxx(JfzcDqdssjVo jfzcVoItem, BigDecimal hyghje, BigDecimal qgzghje,
                                BigDecimal sdghje, BigDecimal sdsjje, BigDecimal sjcjje, BigDecimal sjghje,
                                BigDecimal swjgje, BigDecimal szghje, BigDecimal xjghje, BigDecimal jcghje,
                                BigDecimal cbjje) {
            if (BigDecimal.ZERO.compareTo(hyghje) == 0) {
                jfzcVoItem.setHyghhh(null);
                jfzcVoItem.setHyghhm(null);
                jfzcVoItem.setHyghzh(null);
            }
            if (BigDecimal.ZERO.compareTo(jcghje) == 0) {
                jfzcVoItem.setJcghhh(null);
                jfzcVoItem.setJcghhm(null);
                jfzcVoItem.setJcghzh(null);
            }
            if (BigDecimal.ZERO.compareTo(cbjje) == 0) {
                jfzcVoItem.setCbjhh(null);
                jfzcVoItem.setCbjhm(null);
                jfzcVoItem.setCbjzh(null);
            }
            if (BigDecimal.ZERO.compareTo(qgzghje) == 0) {
                jfzcVoItem.setQgghhh(null);
                jfzcVoItem.setQgghhm(null);
                jfzcVoItem.setQgghzh(null);
            }
            if (BigDecimal.ZERO.compareTo(sdghje) == 0) {
                jfzcVoItem.setSdghhh(null);
                jfzcVoItem.setSdghhm(null);
                jfzcVoItem.setSdghzh(null);
            }
            if (BigDecimal.ZERO.compareTo(sdsjje) == 0) {
                jfzcVoItem.setSdshh(null);
                jfzcVoItem.setSdshm(null);
                jfzcVoItem.setSdszh(null);
            }
            if (BigDecimal.ZERO.compareTo(sjcjje) == 0) {
                jfzcVoItem.setJcjhh(null);
                jfzcVoItem.setJcjhm(null);
                jfzcVoItem.setJcjzh(null);
            }
            if (BigDecimal.ZERO.compareTo(sjghje) == 0) {
                jfzcVoItem.setSjghhh(null);
                jfzcVoItem.setSjghhm(null);
                jfzcVoItem.setSjghzh(null);
            }
            if (BigDecimal.ZERO.compareTo(swjgje) == 0) {
                jfzcVoItem.setSwjghh(null);
                jfzcVoItem.setSwjghm(null);
                jfzcVoItem.setSwjgzh(null);
            }
            if (BigDecimal.ZERO.compareTo(szghje) == 0) {
                jfzcVoItem.setSzghhh(null);
                jfzcVoItem.setSzghhm(null);
                jfzcVoItem.setSzghzh(null);
            }
            if (BigDecimal.ZERO.compareTo(xjghje) == 0) {
                jfzcVoItem.setXjghhh(null);
                jfzcVoItem.setXjghhm(null);
                jfzcVoItem.setXjghzh(null);
            }
        }

        private void updateGhjfJobVoList(List<GhjfDqdssjVo> ghjfJobVoList, JfzcDqdssjVo jfzcVoItem,
                                         CsFpblDqdssjVo csFpblItem, Map<String, String> csFpblMap) {
            String userId = BackConstant.USER_SYSTEM_ID;
            Date nowDate = new Date();
            GhjfDqdssjVo ghjfJobVo = new GhjfDqdssjVo();
            BeanUtils.copyProperties(jfzcVoItem, ghjfJobVo);
            ghjfJobVo.setBluuid(csFpblItem.getBluuid());
            ghjfJobVo.setJym(UUID.randomUUID().toString());
            ghjfJobVo.setCreateBy(userId);
            ghjfJobVo.setCreateTime(nowDate);
            ghjfJobVo.setUpdateBy(userId);
            ghjfJobVo.setUpdateTime(nowDate);
            ghjfJobVoList.add(ghjfJobVo);
            csFpblMap.put(ghjfJobVo.getSpuuid(), ghjfJobVo.getBluuid());
        }

        private void flushGhjfBatch(List<GhjfDqdssjVo> ghjfJobVoList) {
            if (!CollectionUtils.isEmpty(ghjfJobVoList)) {
                List<String> spuuidList = ghjfJobVoList.stream().map(GhjfDqdssjVo::getSpuuid).collect(Collectors.toList());
                List<GhjfDqdssjVo> updateList = getUpdateList(spuuidList, ghjfJobVoList);
                if (!CollectionUtils.isEmpty(updateList)) {
                    ghJfDqdssjMapper.updateBatch(updateList);
                }
                List<GhjfDqdssjVo> addList = getAddList(spuuidList, ghjfJobVoList, updateList);
                if (!CollectionUtils.isEmpty(addList)) {
                    ghJfDqdssjMapper.insertBatch(addList);
                }
            }
        }

        private List<GhjfDqdssjVo> getUpdateList(List<String> spuuidList, List<GhjfDqdssjVo> partitionList) {
            if (CollectionUtils.isEmpty(spuuidList)) {
                return Collections.emptyList();
            }
            List<String> updateSpuuidList = ghJfDqdssjMapper.queryUpdateBySpuuidList(spuuidList);
            return !CollectionUtils.isEmpty(updateSpuuidList)
                    ? partitionList.stream().filter(item -> updateSpuuidList.contains(item.getSpuuid())).collect(Collectors.toList())
                    : Collections.emptyList();
        }

        private List<GhjfDqdssjVo> getAddList(List<String> spuuidList, List<GhjfDqdssjVo> partitionList,
                                               List<GhjfDqdssjVo> updateList) {
            if (CollectionUtils.isEmpty(spuuidList)) {
                return Collections.emptyList();
            }
            List<String> addSpuuidList = new ArrayList<>(spuuidList);
            List<String> updateSpuuidList = updateList.stream().map(GhjfDqdssjVo::getSpuuid).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(updateSpuuidList)) {
                addSpuuidList.removeAll(updateSpuuidList);
            }
            return !CollectionUtils.isEmpty(addSpuuidList)
                    ? partitionList.stream().filter(item -> addSpuuidList.contains(item.getSpuuid())).collect(Collectors.toList())
                    : Collections.emptyList();
        }

        /**
         * V1: com.ruoyi.common.utils.CollectionUtils.partition
         */
        private <T> List<List<T>> partition(List<T> list, int size) {
            List<List<T>> result = new ArrayList<>();
            for (int i = 0; i < list.size(); i += size) {
                result.add(list.subList(i, Math.min(i + size, list.size())));
            }
            return result;
        }
    }
}
