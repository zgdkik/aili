package org.hbhk.aili.mybatis.server.entity;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.BaseEntity;

@Table(value="wms_inventory_index")
public class InventoryIndex extends BaseEntity<String> {
	private static final long serialVersionUID = 6767860047837579053L;

	@Column("inventory_id")
	@Id
	private Long inventoryId;
	 /**
     *库存部门
     */
    @Column("dept_code")
    private String deptCode;


	@Column("waybill_no")
	private Long waybillNo;


	public String getDeptCode() {
		return deptCode;
	}


	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public Long getWaybillNo() {
		return waybillNo;
	}


	public void setWaybillNo(Long waybillNo) {
		this.waybillNo = waybillNo;
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	
}
