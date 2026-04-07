package cn.iocoder.yudao.module.lghjft.framework.deptfilter;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.stereotype.Component;

/**
 * 部门过滤工具类 —— 还原 V1 的部门自动填充与根节点放行逻辑。
 *
 * <p>V1 中每个 Service 查询方法都包含如下逻辑：
 * <ol>
 *   <li>如果前端未传入 deptId，则自动取当前登录用户的部门 ID；</li>
 *   <li>如果 deptId 等于根部门（如 "100000" 或 "620000"），则置为 null，表示不做部门过滤（根用户可查看全部数据）。</li>
 * </ol>
 *
 * <p>V2 迁移时该逻辑丢失，本工具类将其集中提供，供各 Service 统一调用。
 */
@Component
public class DeptFilterHelper {

    /** 主根部门编码，大多数功能使用 */
    public static final String ROOT_DEPT_PRIMARY = "100000";

    /** 次根部门编码，仅 top 功能使用 */
    public static final String ROOT_DEPT_SECONDARY = "620000";

    /**
     * 过滤部门 ID（使用主根部门 {@value #ROOT_DEPT_PRIMARY}）。
     *
     * @param deptId 前端传入的部门 ID，可为 null 或空字符串
     * @return 过滤后的部门 ID；若为根部门则返回 null（表示不做部门过滤）
     */
    public String filterDeptId(String deptId) {
        return filterDeptId(deptId, ROOT_DEPT_PRIMARY);
    }

    /**
     * 过滤部门 ID，还原 V1 的自动填充与根部门放行逻辑。
     *
     * <ul>
     *   <li>若 deptId 为空，自动获取当前登录用户的部门 ID；</li>
     *   <li>若 deptId 等于指定的根部门编码，返回 null（不做部门过滤）；</li>
     *   <li>否则原样返回 deptId。</li>
     * </ul>
     *
     * @param deptId     前端传入的部门 ID，可为 null 或空字符串
     * @param rootDeptId 根部门编码（匹配时返回 null 以跳过部门过滤）
     * @return 过滤后的部门 ID；若为根部门则返回 null
     */
    public String filterDeptId(String deptId, String rootDeptId) {
        // 1. 未传入部门 ID 时，自动取当前登录用户的部门
        if (StrUtil.isEmpty(deptId)) {
            Long loginUserDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (loginUserDeptId != null) {
                deptId = loginUserDeptId.toString();
            }
        }

        // 2. 根部门用户可查看全部数据，不做部门过滤
        if (deptId != null && deptId.equals(rootDeptId)) {
            return null;
        }

        return deptId;
    }
}
