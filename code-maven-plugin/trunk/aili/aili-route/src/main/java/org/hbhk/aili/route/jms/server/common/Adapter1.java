//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.5-2 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.08.11 时间 05:56:41 PM CST 
//


package org.hbhk.aili.route.jms.server.common;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (org.hbhk.aili.route.jms.common.utils.DataTypeAdapter.parseDateTime(value));
    }

    public String marshal(Date value) {
        return (org.hbhk.aili.route.jms.common.utils.DataTypeAdapter.printDateTime(value));
    }

}
