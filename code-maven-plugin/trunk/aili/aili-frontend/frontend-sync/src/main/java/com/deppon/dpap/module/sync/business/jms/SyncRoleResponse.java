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
 * <p>Java class for SyncRoleResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SyncRoleResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sucessCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="details" type="{http://www.deppon.com/wdgh/inteface/domain/}SyncRoleDetail"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncRoleResponse", propOrder = {
    "sucessCount",
    "failCount",
    "details"
})
public class SyncRoleResponse {

    protected int sucessCount;
    protected int failCount;
    @XmlElement(required = true)
    protected SyncRoleDetail details;

    /**
     * Gets the value of the sucessCount property.
     * 
     */
    public int getSucessCount() {
        return sucessCount;
    }

    /**
     * Sets the value of the sucessCount property.
     * 
     */
    public void setSucessCount(int value) {
        this.sucessCount = value;
    }

    /**
     * Gets the value of the failCount property.
     * 
     */
    public int getFailCount() {
        return failCount;
    }

    /**
     * Sets the value of the failCount property.
     * 
     */
    public void setFailCount(int value) {
        this.failCount = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link SyncRoleDetail }
     *     
     */
    public SyncRoleDetail getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link SyncRoleDetail }
     *     
     */
    public void setDetails(SyncRoleDetail value) {
        this.details = value;
    }

}
