package snoob.gdd.mapper;

import snoob.gdd.model.Cover;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface CoverMapper extends Mapper<Cover>, MySqlMapper<Cover> {
}