package com.cmcc.hui.goods.dao;

import com.cmcc.hui.goods.bean.TestGoodsBo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xuhaitao
 * @Date 2019/2/16 18:25
 **/
@Mapper
public interface ITestDao {

    @Insert("INSERT INTO mall_goods_test(name, price, stock) VALUES(#{name}, #{price}, #{stock})")
    public void insert(TestGoodsBo bo);

    @Select("SELECT id, name, price, stock FROM mall_goods_test WHERE id = #{id}")
    public TestGoodsBo get(@Param("id")long id);
}
