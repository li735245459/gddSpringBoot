package snoob.gdd.util;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * 自动生成主键
 *
 * @Id
 * @KeySql(genId = UUIdGenIdUtil.class)
 * private String id;
 */
public class UUIdGenIdUtil implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString();
    }
}
