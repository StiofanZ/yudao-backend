package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Schema(description = "管理后台 - 标签户籍保存 Request VO")
@Data
@ToString(callSuper = true)
public class BqglHjxxSaveReqVO {

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String djxh;

    @Schema(description = "标签ID列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1, 2]")
    private List<String> bqIds;

    @Schema(description = "标签代码", example = "BQ001")
    private String bqDm;

    @Schema(description = "登记序号列表", example = "[10001, 10002]")
    private List<String> djxhList;

    @Schema(description = "是否删除", example = "true")
    private Boolean deleted;

    public String getDjxh() {
        return djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
    }

    public List<String> getBqIds() {
        return bqIds;
    }

    public void setBqIds(List<String> bqIds) {
        this.bqIds = bqIds;
    }

    public String getBqDm() {
        return bqDm;
    }

    public void setBqDm(String bqDm) {
        this.bqDm = bqDm;
    }

    public List<String> getDjxhList() {
        return djxhList;
    }

    public void setDjxhList(List<String> djxhList) {
        this.djxhList = djxhList;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
