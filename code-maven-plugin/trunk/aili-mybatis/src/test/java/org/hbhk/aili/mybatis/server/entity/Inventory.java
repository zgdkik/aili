package org.hbhk.aili.mybatis.server.entity;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.hbhk.aili.mybatis.server.annotation.IndexTable;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("wms_inventory")
public class Inventory {

	@Column("inventory_id")
	@Id
	private Long inventoryId;

	@Column("waybill_id")
	private Long waybillId;

	@Column("waybill_no")
	private Long waybillNo;
	
	 /**
     *库存部门
     */
    @Column("dept_code")
    @IndexTable(cls= InventoryIndex.class,col="waybill_no")
    private String deptCode;

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Long getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(Long waybillId) {
		this.waybillId = waybillId;
	}

	public Long getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(Long waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
    
    
    
    

}
