package cn.iocoder.yudao.module.lghjft.controller.app.markerinfo;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo.MarkerInfoPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.markerinfo.vo.MarkerInfoAppRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.markerinfo.MarkerInfoDO;
import cn.iocoder.yudao.module.lghjft.service.markerinfo.MarkerInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户App  - 高德地图标注点信息")
@RestController
@RequestMapping("/lghjft/bsdt/marker-info")
@Validated
public class MarkerinfoAppController {
    @Resource
    private MarkerInfoService markerInfoService;

    // 定义 grade 映射（注意：现在 grade 是 String 类型）
    private String getGradeText(String grade) {
        // 根据字符串值判断
        switch (grade) {
            case "0":
                return "县区级工会";
            case "1":
                return "市级工会";
            case "3":
                return "省级工会";
            default:
                return "未知级别";
        }
    }

    @GetMapping("/get")
    @Operation(summary = "获得办事地图标注点信息")
    @PreAuthorize("isAuthenticated()")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<MarkerInfoAppRespVO> getMarkerInfo(@RequestParam("id") Long id) {
        // 1. 获取数据
        MarkerInfoDO markerInfo = markerInfoService.getMarkerInfo(id);

        // 2. 转换为 VO
        MarkerInfoAppRespVO vo = BeanUtils.toBean(markerInfo, MarkerInfoAppRespVO.class);

        // 3. 设置 gradeText（注意：markerInfo.getGrade() 返回的是 String）
        String grade = markerInfo.getGrade();
        String gradeText = getGradeText(grade);
        vo.setGradeText(gradeText);  // 设置文字显示
        return success(vo);
    }

    @GetMapping("/list-page")
    @Operation(summary = "获得办事地图标注点信息分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<MarkerInfoAppRespVO>> getMarkerInfoPage(@Valid MarkerInfoPageReqVO pageReqVO) {
        // 1. 获取分页数据
        PageResult<MarkerInfoDO> pageResult = markerInfoService.getMarkerInfoPage(pageReqVO);

        // 2. 创建一个列表来存放转换后的数据
        List<MarkerInfoAppRespVO> voList = new ArrayList<>();

        // 3. 遍历转换
        for (MarkerInfoDO markerInfo : pageResult.getList()) {
            // 3.1 转换为 VO
            MarkerInfoAppRespVO vo = BeanUtils.toBean(markerInfo, MarkerInfoAppRespVO.class);

            // 3.2 设置 gradeText
            String grade = markerInfo.getGrade();
            String gradeText = getGradeText(grade);
            vo.setGradeText(gradeText);

            // 3.3 添加到列表
            voList.add(vo);
        }

        // 4. 创建新的分页结果
        PageResult<MarkerInfoAppRespVO> result = new PageResult<>(voList, pageResult.getTotal());

        return success(result);
    }

//    @GetMapping("/around-page")
//    @Operation(summary = "获得高德地图标注点信息分页")
//    @PreAuthorize("isAuthenticated()")
//    public CommonResult<PageResult<MarkerInfoAppRespVO>> getMarkerAround(@RequestParam("id") Long id) {
//        // 1. 获取分页数据
//        PageResult<MarkerInfoDO> pageResult = markerInfoService.getMarkerInfoPage(id);
//
//        // 2. 创建一个列表来存放转换后的数据
//        List<MarkerInfoAppRespVO> voList = new ArrayList<>();
//
//        // 3. 遍历转换
//        for (MarkerInfoDO markerInfo : pageResult.getList()) {
//            // 3.1 转换为 VO
//            MarkerInfoAppRespVO vo = BeanUtils.toBean(markerInfo, MarkerInfoAppRespVO.class);
//
//            // 3.2 设置 gradeText
//            String grade = markerInfo.getGrade();
//            String gradeText = getGradeText(grade);
//            vo.setGradeText(gradeText);
//
//            // 3.3 添加到列表
//            voList.add(vo);
//        }
//
//        // 4. 创建新的分页结果
//        PageResult<MarkerInfoAppRespVO> result = new PageResult<>(voList, pageResult.getTotal());
//
//        return success(result);
//    }
}
