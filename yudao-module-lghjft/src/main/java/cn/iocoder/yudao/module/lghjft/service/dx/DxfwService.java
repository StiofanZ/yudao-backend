package cn.iocoder.yudao.module.lghjft.service.dx;

public interface DxfwService {

    void fsdlyzm(String lxdh, Integer yhlx);

    void jydlyzm(String lxdh, String yzm, Integer yhlx);

    void fsaqyzm(String lxdh);

    void jyaqyzm(String lxdh, String yzm);

    Long fstzdx(String lxdh, Long yhId, Integer yhlx, String dxnr);
}
