/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.util.List;

import org.junit.Test;

import com.atom.core.lang.xml.XMLNode;
import com.atom.core.lang.xml.XMLUtils;

/**
 * XMLUtils工具类测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: XMLUtilsTest.java, V1.0.1 2013-2-25 上午11:57:08 $
 */
public class XMLUtilsTest {

    /**
     * XMLUtilsTest.xml
     */
    @Test
    public void test_XMLUtils() {
        try {
            XMLUtils.start(XMLUtils.class.getResourceAsStream("/XMLUtilsTest.xml"));

            XMLNode root = XMLUtils.findNode("/root");
            System.out.println("XML根节点：" + root);

            System.out.println("===================================");

            List<XMLNode> values = XMLUtils.findNodes(root.getElement(), "values");
            System.out.println("Values节点：" + values);

            System.out.println("===================================");

            for (XMLNode value : values) {
                System.out.println("Value节点：" + XMLUtils.findNodes(value.getElement(), "value"));
            }
        } finally {
            XMLUtils.finish();
        }
    }
}
