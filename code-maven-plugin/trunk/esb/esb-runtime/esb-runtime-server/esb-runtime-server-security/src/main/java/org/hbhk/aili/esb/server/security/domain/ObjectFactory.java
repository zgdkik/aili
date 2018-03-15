

package org.hbhk.aili.esb.server.security.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _UserIntfo_QNAME = new QName("http://www.deppon.com/esb/security/domain/", "userIntfo");
    private final static QName _InterfaceCount_QNAME = new QName("http://www.deppon.com/esb/security/domain/", "interfaceCount");
    private final static QName _UserInterfaceRelation_QNAME = new QName("http://www.deppon.com/esb/security/domain/", "userInterfaceRelation");
    private final static QName _UserInterfaceCount_QNAME = new QName("http://www.deppon.com/esb/security/domain/", "userInterfaceCount");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.esb.security.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserInterfaceRelation }
     * 
     */
    public UserInterfaceRelation createUserInterfaceRelation() {
        return new UserInterfaceRelation();
    }

    /**
     * Create an instance of {@link InterfaceCount }
     * 
     */
    public InterfaceCount createInterfaceCount() {
        return new InterfaceCount();
    }

    /**
     * Create an instance of {@link UserInterfaceCount }
     * 
     */
    public UserInterfaceCount createUserInterfaceCount() {
        return new UserInterfaceCount();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/security/domain/", name = "userIntfo")
    public JAXBElement<UserInfo> createUserIntfo(UserInfo value) {
        return new JAXBElement<UserInfo>(_UserIntfo_QNAME, UserInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterfaceCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/security/domain/", name = "interfaceCount")
    public JAXBElement<InterfaceCount> createInterfaceCount(InterfaceCount value) {
        return new JAXBElement<InterfaceCount>(_InterfaceCount_QNAME, InterfaceCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserInterfaceRelation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/security/domain/", name = "userInterfaceRelation")
    public JAXBElement<UserInterfaceRelation> createUserInterfaceRelation(UserInterfaceRelation value) {
        return new JAXBElement<UserInterfaceRelation>(_UserInterfaceRelation_QNAME, UserInterfaceRelation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserInterfaceCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.deppon.com/esb/security/domain/", name = "userInterfaceCount")
    public JAXBElement<UserInterfaceCount> createUserInterfaceCount(UserInterfaceCount value) {
        return new JAXBElement<UserInterfaceCount>(_UserInterfaceCount_QNAME, UserInterfaceCount.class, null, value);
    }

}
