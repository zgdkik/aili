//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.28 at 08:40:42 上午 CST 
//


package com.deppon.dpap.module.sync.business.jms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SyncRoleRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SyncRoleRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roleid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roleStandardNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roleNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roleType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operateTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncRoleRequest", propOrder = {
    "roleid",
    "roleName",
    "roleStandardNumber",
    "roleNumber",
    "roleType",
    "operateType",
    "operateTime"
})
public class SyncRoleRequest {

    @XmlElement(required = true)
    protected String roleid;
    @XmlElement(required = true)
    protected String roleName;
    @XmlElement(required = true)
    protected String roleStandardNumber;
    @XmlElement(required = true)
    protected String roleNumber;
    @XmlElement(required = true)
    protected String roleType;
    @XmlElement(required = true)
    protected String operateType;
    @XmlElement(required = true)
    protected String operateTime;

    /**
     * Gets the value of the roleid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * Sets the value of the roleid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleid(String value) {
        this.roleid = value;
    }

    /**
     * Gets the value of the roleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the value of the roleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    /**
     * Gets the value of the roleStandardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleStandardNumber() {
        return roleStandardNumber;
    }

    /**
     * Sets the value of the roleStandardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleStandardNumber(String value) {
        this.roleStandardNumber = value;
    }

    /**
     * Gets the value of the roleNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleNumber() {
        return roleNumber;
    }

    /**
     * Sets the value of the roleNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleNumber(String value) {
        this.roleNumber = value;
    }

    /**
     * Gets the value of the roleType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * Sets the value of the roleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleType(String value) {
        this.roleType = value;
    }

    /**
     * Gets the value of the operateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * Sets the value of the operateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateType(String value) {
        this.operateType = value;
    }

    /**
     * Gets the value of the operateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateTime() {
        return operateTime;
    }

    /**
     * Sets the value of the operateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateTime(String value) {
        this.operateTime = value;
    }

}