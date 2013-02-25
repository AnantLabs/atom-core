/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 采用XPath方式解析XML文件
 * 
 * @author obullxl@gmail.com
 * @version $Id: XMLUtils.java, V1.0.1 2013-2-25 上午10:12:06 $
 */
public final class XMLUtils {
    /** 线程上下文文档结构 */
    private static final ThreadLocal<Document> document = new ThreadLocal<Document>();

    /**
     * 开始解析XML文件
     * 
     * @param file XML文件路径
     */
    public static final void start(String file) {
        InputStream input = null;
        try {
            input = new FileInputStream(file);

            // 解析
            start(input);
        } catch (Exception e) {
            throw new RuntimeException("开始解析XML异常！", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 开始解析XML文件
     * 
     * @param input XML文件输入流，使用完成之后，不会自动关闭，由业务来关闭！
     */
    public static final void start(InputStream input) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(input);

            // 存入线程
            document.set(doc);
        } catch (Exception e) {
            throw new RuntimeException("开始解析XML异常！", e);
        }
    }

    /**
     * 完成解系XML文件
     */
    public static final void finish() {
        document.remove();
    }

    /**
     * 解析文档内容
     * 
     * @param xpath XML节点选择路径
     */
    public static XMLNode findNode(String xpath) {
        return findNode(getDocument(), xpath);
    }

    /**
     * 解析文档内容
     * 
     * @param parent XML父节点
     * @param xpath XML节点选择路径
     */
    public static XMLNode findNode(Node element, String xpath) {
        try {
            return findNodes(element, xpath).get(0);
        } catch (Exception e) {
            throw new RuntimeException("解析XML根节点[" + xpath + "]异常！", e);
        }
    }

    /**
     * 解析文档内容
     * 
     * @param xpath XML节点选择路径
     */
    public static List<XMLNode> findNodes(String xpath) {
        return findNodes(getDocument(), xpath);
    }

    /**
     * 解析文档内容
     * 
     * @param parent XML父节点
     * @param xpath XML节点选择路径
     */
    public static List<XMLNode> findNodes(Node parent, String xpath) {
        List<XMLNode> nodes = new ArrayList<XMLNode>();
        try {
            NodeList elements = XPathAPI.selectNodeList(parent, xpath);
            for (int i = 0; i < elements.getLength(); i++) {
                nodes.add(parse(elements.item(i)));
            }

            return nodes;
        } catch (Exception e) {
            throw new RuntimeException("解析XML根节点[" + xpath + "]异常！", e);
        }
    }

    /**
     * 解析XML节点
     * <p/>
     * 节点名称和节点属性名称均转化为小写！
     */
    public final static XMLNode parse(Node element) {
        NamedNodeMap props = element.getAttributes();
        if (props == null || props.getLength() <= 0) {
            return null;
        }

        XMLNode node = new XMLNode();
        node.setElement(element);

        // 节点名称
        node.setName(StringUtils.lowerCase(element.getNodeName()));
        // 节点内容
        Node first = element.getFirstChild();
        if (first != null) {
            short ntype = first.getNodeType();
            if (ntype == Node.TEXT_NODE || ntype == Node.CDATA_SECTION_NODE) {
                node.setText(StringUtils.trimToNull(first.getTextContent()));
            }
        }

        for (int i = 0; i < props.getLength(); i++) {
            Node prop = props.item(i);
            String pname = StringUtils.lowerCase(prop.getNodeName());

            // 属性
            node.getExtMap().put(pname, prop.getTextContent());
        }

        return node;
    }

    /**
     * 获取文档结构
     */
    private static Document getDocument() {
        Document doc = document.get();
        if (doc == null) {
            throw new RuntimeException("XML文档结构为空，请先通过XMLUtils#start()方法设置！");
        }

        return doc;
    }

}
