//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.5-2 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.09.06 时间 03:15:03 PM CST 
//


package org.hbhk.aili.esb.server.common.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>CommonExceptionInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CommonExceptionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exceptioncode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exceptiontype">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="SystemException"/>
 *               &lt;enumeration value="BusinessException"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="detailedInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonExceptionInfo", namespace = "http://www.deppon.com/esb/exception", propOrder = {
    "exceptioncode",
    "exceptiontype",
    "message",
    "createdTime",
    "detailedInfo"
})
public class CommonExceptionInfo
    implements Serializable
{

    private final static long serialVersionUID = 11082011L;
    @XmlElement(required = true)
    protected String exceptioncode;
    @XmlElement(required = true)
    protected String exceptiontype;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date createdTime;
    @XmlElement(required = true)
    protected String detailedInfo;

    /**
     * 获取exceptioncode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptioncode() {
        return exceptioncode;
    }

    /**
     * 设置exceptioncode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptioncode(String value) {
        this.exceptioncode = value;
    }

    /**
     * 获取exceptiontype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptiontype() {
        return exceptiontype;
    }

    /**
     * 设置exceptiontype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptiontype(String value) {
        this.exceptiontype = value;
    }

    /**
     * 获取message属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * 获取createdTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置createdTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedTime(Date value) {
        this.createdTime = value;
    }

    /**
     * 获取detailedInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailedInfo() {
        return detailedInfo;
    }

    /**
     * 设置detailedInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailedInfo(String value) {
        this.detailedInfo = value;
    }

}
