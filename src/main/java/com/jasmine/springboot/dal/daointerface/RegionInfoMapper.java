package com.jasmine.springboot.dal.daointerface;

import com.jasmine.springboot.dal.dataobject.RegionInfo;

public interface RegionInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_info
     *
     * @mbg.generated
     */
    int insert(RegionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_info
     *
     * @mbg.generated
     */
    int insertSelective(RegionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_info
     *
     * @mbg.generated
     */
    RegionInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RegionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RegionInfo record);
}