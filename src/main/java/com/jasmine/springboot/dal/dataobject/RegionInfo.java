package com.jasmine.springboot.dal.dataobject;

import java.util.Date;

public class RegionInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region_info.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region_info.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region_info.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region_info.region_type
     *
     * @mbg.generated
     */
    private String regionType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region_info.region_id
     *
     * @mbg.generated
     */
    private String regionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region_info.region_name
     *
     * @mbg.generated
     */
    private String regionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region_info.total_num
     *
     * @mbg.generated
     */
    private Long totalNum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region_info.id
     *
     * @return the value of region_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region_info.id
     *
     * @param id the value for region_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region_info.gmt_create
     *
     * @return the value of region_info.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region_info.gmt_create
     *
     * @param gmtCreate the value for region_info.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region_info.gmt_modified
     *
     * @return the value of region_info.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region_info.gmt_modified
     *
     * @param gmtModified the value for region_info.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region_info.region_type
     *
     * @return the value of region_info.region_type
     *
     * @mbg.generated
     */
    public String getRegionType() {
        return regionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region_info.region_type
     *
     * @param regionType the value for region_info.region_type
     *
     * @mbg.generated
     */
    public void setRegionType(String regionType) {
        this.regionType = regionType == null ? null : regionType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region_info.region_id
     *
     * @return the value of region_info.region_id
     *
     * @mbg.generated
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region_info.region_id
     *
     * @param regionId the value for region_info.region_id
     *
     * @mbg.generated
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region_info.region_name
     *
     * @return the value of region_info.region_name
     *
     * @mbg.generated
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region_info.region_name
     *
     * @param regionName the value for region_info.region_name
     *
     * @mbg.generated
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region_info.total_num
     *
     * @return the value of region_info.total_num
     *
     * @mbg.generated
     */
    public Long getTotalNum() {
        return totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region_info.total_num
     *
     * @param totalNum the value for region_info.total_num
     *
     * @mbg.generated
     */
    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }
}