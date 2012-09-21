/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.test.service;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import com.atom.core.model.NodeModel;
import com.atom.core.service.NodeModelService;
import com.atom.core.service.support.DefaultNodeModelService;

/**
 * 节点服务测试
 * 
 * @author shizihu
 * @version $Id: NodeModelServiceTest.java, v 0.1 2012-9-13 下午01:41:43 shizihu Exp $
 */
public class NodeModelServiceTest {

    @Test
    public void test_unmarshal() throws Exception {
        String path = FilenameUtils.normalize(new File(".").getAbsolutePath()
                                              + "/src/test/resources/menu-config.xml");

        NodeModelService service = new DefaultNodeModelService();
        NodeModel root = service.parse(path);
        System.out.println(root);
    }

}
