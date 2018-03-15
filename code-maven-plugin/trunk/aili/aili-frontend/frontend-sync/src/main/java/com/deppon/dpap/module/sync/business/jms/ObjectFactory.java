//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.28 at 08:43:10 上午 CST 
//


package com.deppon.dpap.module.sync.business.jms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.foss.module.sync.business.jms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SyncUserInfoResponse_QNAME = new QName("http://www.deppon.com/wdgh/inteface/domain/", "syncUserInfoResponse");
    private final static QName _SyncUserInfoRequest_QNAME = new QName("http://www.deppon.com/wdgh/inteface/domain/", "syncUserInfoRequest");

    private final static QName _SyncRoleRequest_QNAME = new QName("http://www.deppon.com/wdgh/inteface/domain/", "syncRoleRequest");
    private final static QName _SyncRoleResponse_QNAME = new QName("http://www.deppon.com/wdgh/inteface/domain/", "syncRoleResponse");
    
    private final static QName _SyncEmployeeRequest_QNAME = new QName("http://www.deppon.com/wdgh/inteface/domain/", "syncEmployeeRequest");
    private final static QName _SendEmployeeResponse_QNAME = new QName("http://www.deppon.com/wdgh/inteface/domain/", "sendEmployeeResponse");
    private final static QName _SyncAdminOrgRequest_QNAME = new QName("http://www.deppon.com/uums/inteface/domain/usermanagement", "SyncAdminOrgRequest");
    private final static QName _SyncAdminOrgResponse_QNAME = new QName("http://www.deppon.com/uums/inteface/domain/usermanagement", "SyncAdminOrgResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.foss.module.sync.business.jms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SyncAdminOrgResponse }
     * 
     */
    public SyncAdminOrgResponse createSyncAdminOrgResponse() {
        return new SyncAdminOrgResponse();
    }

    /**
     * Create an instance of {@link SyncAdminOrgProcessReult }
     * 
     */
    public SyncAdminOrgProcessReult createSyncAdminOrgProcessReult() {
        return new SyncAdminOrgProcessReult();
    }

    /**
     * Create an instance of {@link AdminOrgInfo }
     * 
     */
    public AdminOrgInfo createAdminOrgInfo() {
        return new AdminOrgInfo();
    }

    /**
     * Create an instance of {@link SyncAdminOrgRequest }
     * 
     */
    public SyncAdminOrgRequest createSyncAdminOrgRequest() {
        return new SyncAdminOrgRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncAdminOrgRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/uums/inteface/domain/usermanagement", name = "SyncAdminOrgRequest")
    public JAXBElement<SyncAdminOrgRequest> createSyncAdminOrgRequest(SyncAdminOrgRequest value) {
        return new JAXBElement<SyncAdminOrgRequest>(_SyncAdminOrgRequest_QNAME, SyncAdminOrgRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncAdminOrgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/uums/inteface/domain/usermanagement", name = "SyncAdminOrgResponse")
    public JAXBElement<SyncAdminOrgResponse> createSyncAdminOrgResponse(SyncAdminOrgResponse value) {
        return new JAXBElement<SyncAdminOrgResponse>(_SyncAdminOrgResponse_QNAME, SyncAdminOrgResponse.class, null, value);
    }

    /**
     * Create an instance of {@link SendEmployeeResponse }
     * 
     */
    public SendEmployeeResponse createSendEmployeeResponse() {
        return new SendEmployeeResponse();
    }

    /**
     * Create an instance of {@link SyncEmployeeRequest }
     * 
     */
    public SyncEmployeeRequest createSyncEmployeeRequest() {
        return new SyncEmployeeRequest();
    }

    /**
     * Create an instance of {@link SendEmployeeProcessReult }
     * 
     */
    public SendEmployeeProcessReult createSendEmployeeProcessReult() {
        return new SendEmployeeProcessReult();
    }

    /**
     * Create an instance of {@link EmployeeInfo }
     * 
     */
    public EmployeeInfo createEmployeeInfo() {
        return new EmployeeInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncEmployeeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/wdgh/inteface/domain/", name = "syncEmployeeRequest")
    public JAXBElement<SyncEmployeeRequest> createSyncEmployeeRequest(SyncEmployeeRequest value) {
        return new JAXBElement<SyncEmployeeRequest>(_SyncEmployeeRequest_QNAME, SyncEmployeeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmployeeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/wdgh/inteface/domain/", name = "sendEmployeeResponse")
    public JAXBElement<SendEmployeeResponse> createSendEmployeeResponse(SendEmployeeResponse value) {
        return new JAXBElement<SendEmployeeResponse>(_SendEmployeeResponse_QNAME, SendEmployeeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link SyncRoleDetail }
     * 
     */
    public SyncRoleDetail createSyncRoleDetail() {
        return new SyncRoleDetail();
    }

    /**
     * Create an instance of {@link SyncRoleRequest }
     * 
     */
    public SyncRoleRequest createSyncRoleRequest() {
        return new SyncRoleRequest();
    }

    /**
     * Create an instance of {@link SyncRoleResponse }
     * 
     */
    public SyncRoleResponse createSyncRoleResponse() {
        return new SyncRoleResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncRoleRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/wdgh/inteface/domain/", name = "syncRoleRequest")
    public JAXBElement<SyncRoleRequest> createSyncRoleRequest(SyncRoleRequest value) {
        return new JAXBElement<SyncRoleRequest>(_SyncRoleRequest_QNAME, SyncRoleRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/wdgh/inteface/domain/", name = "syncRoleResponse")
    public JAXBElement<SyncRoleResponse> createSyncRoleResponse(SyncRoleResponse value) {
        return new JAXBElement<SyncRoleResponse>(_SyncRoleResponse_QNAME, SyncRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link SyncUserInfoRequest }
     * 
     */
    public SyncUserInfoRequest createSyncUserInfoRequest() {
        return new SyncUserInfoRequest();
    }

    /**
     * Create an instance of {@link SyncUserInfoResponse }
     * 
     */
    public SyncUserInfoResponse createSyncUserInfoResponse() {
        return new SyncUserInfoResponse();
    }

    /**
     * Create an instance of {@link SendUserInfoDeptAllProcessReult }
     * 
     */
    public SendUserInfoDeptAllProcessReult createSendUserInfoDeptAllProcessReult() {
        return new SendUserInfoDeptAllProcessReult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncUserInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/wdgh/inteface/domain/", name = "syncUserInfoResponse")
    public JAXBElement<SyncUserInfoResponse> createSyncUserInfoResponse(SyncUserInfoResponse value) {
        return new JAXBElement<SyncUserInfoResponse>(_SyncUserInfoResponse_QNAME, SyncUserInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncUserInfoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/wdgh/inteface/domain/", name = "syncUserInfoRequest")
    public JAXBElement<SyncUserInfoRequest> createSyncUserInfoRequest(SyncUserInfoRequest value) {
        return new JAXBElement<SyncUserInfoRequest>(_SyncUserInfoRequest_QNAME, SyncUserInfoRequest.class, null, value);
    }

}
