package com.yongtao.ebuy.manager.pojo;

import java.util.Date;

public class ItemDesc {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_desc.item_id
     *
     * @mbggenerated
     */
    private Long itemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_desc.created
     *
     * @mbggenerated
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_desc.updated
     *
     * @mbggenerated
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_desc.item_desc
     *
     * @mbggenerated
     */
    private String itemDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_desc.item_id
     *
     * @return the value of item_desc.item_id
     *
     * @mbggenerated
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_desc.item_id
     *
     * @param itemId the value for item_desc.item_id
     *
     * @mbggenerated
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_desc.created
     *
     * @return the value of item_desc.created
     *
     * @mbggenerated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_desc.created
     *
     * @param created the value for item_desc.created
     *
     * @mbggenerated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_desc.updated
     *
     * @return the value of item_desc.updated
     *
     * @mbggenerated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_desc.updated
     *
     * @param updated the value for item_desc.updated
     *
     * @mbggenerated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_desc.item_desc
     *
     * @return the value of item_desc.item_desc
     *
     * @mbggenerated
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_desc.item_desc
     *
     * @param itemDesc the value for item_desc.item_desc
     *
     * @mbggenerated
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}