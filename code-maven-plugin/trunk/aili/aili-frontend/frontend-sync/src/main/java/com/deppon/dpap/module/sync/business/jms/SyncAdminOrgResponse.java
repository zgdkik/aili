//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.03 at 08:31:08 上午 CST 
//


package com.deppon.dpap.module.sync.business.jms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SyncAdminOrgResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SyncAdminOrgResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="successCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failedCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="processResult" type="{http://www.deppon.com/uums/inteface/domain/usermanagement}SyncAdminOrgProcessReult" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncAdminOrgResponse", propOrder = {
    "successCount",
    "failedCount",
    "processResult"
})
public class SyncAdminOrgResponse {

    protected int successCount;
    protected int failedCount;
    @XmlElement(required = true)
    protected List<SyncAdminOrgProcessReult> processResult;

    /**
     * Gets the value of the successCount property.
     * 
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * Sets the value of the successCount property.
     * 
     */
    public void setSuccessCount(int value) {
        this.successCount = value;
    }

    /**
     * Gets the value of the failedCount property.
     * 
     */
    public int getFailedCount() {
        return failedCount;
    }

    /**
     * Sets the value of the failedCount property.
     * 
     */
    public void setFailedCount(int value) {
        this.failedCount = value;
    }

    /**
     * Gets the value of the processResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SyncAdminOrgProcessReult }
     * 
     * 
     */
    public List<SyncAdminOrgProcessReult> getProcessResult() {
        if (processResult == null) {
            processResult = new ArrayList<SyncAdminOrgProcessReult>();
        }
        return this.processResult;
    }

}
