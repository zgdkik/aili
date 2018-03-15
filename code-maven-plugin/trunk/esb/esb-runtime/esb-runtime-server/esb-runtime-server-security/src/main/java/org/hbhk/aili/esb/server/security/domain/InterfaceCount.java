//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.5-2 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.09.06 时间 02:54:20 PM CST 
//


package org.hbhk.aili.esb.server.security.domain;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>InterfaceCount complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="InterfaceCount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="esbServiceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maxCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="concurrent" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterfaceCount", propOrder = {
    "fid",
    "createTime",
    "esbServiceCode",
    "maxCount",
    "concurrent",
    "active"
})
public class InterfaceCount
    implements Serializable
{

    private final static long serialVersionUID = 11082011L;
    @XmlElement(required = true)
    protected String fid;
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "dateTime")
    protected Date createTime;
    @XmlElement(required = true)
    protected String esbServiceCode;
    protected long maxCount;
    protected long concurrent;
    @XmlElement(required = true)
    protected String active;

    /**
     * 获取fid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFid() {
        return fid;
    }

    /**
     * 设置fid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFid(String value) {
        this.fid = value;
    }

    /**
     * 获取createTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    /**
     * 获取esbServiceCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsbServiceCode() {
        return esbServiceCode;
    }

    /**
     * 设置esbServiceCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsbServiceCode(String value) {
        this.esbServiceCode = value;
    }

    /**
     * 获取maxCount属性的值。
     * 
     */
    public long getMaxCount() {
        return maxCount;
    }

    /**
     * 设置maxCount属性的值。
     * 
     */
    public void setMaxCount(long value) {
        this.maxCount = value;
    }

    /**
     * 获取concurrent属性的值。
     * 
     */
    public long getConcurrent() {
        return concurrent;
    }

    /**
     * 设置concurrent属性的值。
     * 
     */
    public void setConcurrent(long value) {
        this.concurrent = value;
    }

    /**
     * 获取active属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActive() {
        return active;
    }

    /**
     * 设置active属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActive(String value) {
        this.active = value;
    }

}
