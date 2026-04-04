package cn.iocoder.yudao.module.lghjft.dal.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * v1 兼容的 String[] 类型处理器，将 String[] 存为逗号分隔字符串。
 * <p>
 * v1 使用 StringArrayTypeHandler，在 DB 中存储为 "a,b,c"。
 * v2 的 JacksonTypeHandler 会存为 JSON 格式 ["a","b","c"]，与 v1 数据不兼容。
 * 本处理器保持与 v1 一致的逗号分隔格式。
 */
@MappedTypes(String[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CommaSeparatedStringArrayTypeHandler extends BaseTypeHandler<String[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.join(",", parameter));
    }

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return parseValue(columnValue);
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return parseValue(columnValue);
    }

    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return parseValue(columnValue);
    }

    private String[] parseValue(String columnValue) {
        if (columnValue == null || columnValue.isEmpty()) {
            return null;
        }
        return columnValue.split(",");
    }
}
