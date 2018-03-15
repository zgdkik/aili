package org.hbhk.aili.client.core.widget.print.entity;

/**
 * Description: 报表模板或清单模板分类类别
 *
 */
public class BillTemplateSort {
	// ID
	private Long id;
	// 名称
	private String name;
	// 模板
	private BillTemplateSort parent;
	
	/**
	 * 
	 * <p>Title: BillTemplateSort</p>
	 * <p>Description: 默认构造函数</p>
	 */
	public BillTemplateSort(){
	}

	/**
	 * 
	 * <p>Title: BillTemplateSort</p>
	 * <p>Description: 构造函数</p>
	 * @param id ID
	 * @param name 名称
	 * @param parent 所属模板
	 */
	public BillTemplateSort(Long id, String name, BillTemplateSort parent) {
		this.id = id;
		this.name = name;
		this.parent = parent;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parent
	 */
	public BillTemplateSort getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(BillTemplateSort parent) {
		this.parent = parent;
	}
}