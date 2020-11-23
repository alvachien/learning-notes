package com.alvachien.learning.java_tutorial.basic_knowledge;

import java.beans.*;
import java.util.ArrayList;
import java.util.StringJoiner;

public class PersonBeanInfo {
    public String getProperties() throws Exception {
        StringJoiner sj = new StringJoiner(",");
        BeanInfo info = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            sj.add(pd.getName());
        }

        return sj.toString();
    }

    public String[] getReadMethods() throws Exception {
        ArrayList<String> listrst = new ArrayList<String>();
        BeanInfo info = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            var method = pd.getReadMethod();
            if (!method.getName().isEmpty()) {
                listrst.add(method.getName());                
            }
        }

        return (String[]) listrst.toArray();
    }
}
