/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.service.support;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.atom.core.exception.BizException;
import com.atom.core.model.NodeConst;
import com.atom.core.model.NodeModel;
import com.atom.core.service.NodeModelService;

/**
 * 节点模型服务
 * 
 * @author shizihu
 * @version $Id: DefaultNodeModelService.java, v 0.1 2012-9-13 下午12:54:18 shizihu Exp $
 */
public class DefaultNodeModelService implements NodeModelService {

    /** 
     * @see com.atom.core.service.NodeModelService#parse(java.lang.String)
     */
    public NodeModel parse(String config) throws Exception {
        // 根节点
        NodeModel rootNode = null;

        try {
            // DOM树
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document document = dbf.newDocumentBuilder().parse(new FileInputStream(config));

            // 根节点
            Element rootElement = (Element) XPathAPI.selectSingleNode(document, "/node");
            if (rootElement == null) {
                throw new BizException("");
            }

            rootNode = this.parseNodeElement(rootElement);
            if (rootNode == null) {
                throw new BizException("");
            }

            // 子节点
            this.parseChildren(rootNode, rootElement);
        } catch (Exception e) {
            throw new BizException("");
        }

        // 成功返回
        return rootNode;
    }

    /**
     * 解析一个节点
     */
    private NodeModel parseNodeElement(Element element) {
        NamedNodeMap props = element.getAttributes();
        if (props == null || props.getLength() <= 0) {
            return null;
        }

        NodeModel node = new NodeModel();
        for (int i = 0; i < props.getLength(); i++) {
            Node prop = props.item(i);
            node.getExtMap().put(prop.getNodeName(), prop.getTextContent());
        }

        // Text
        node.getExtMap().remove(NodeConst.TEXT);
        node.setText(StringUtils.trimToEmpty(element.getAttribute(NodeConst.TEXT)));

        return node;
    }

    /**
     * 解析子节点
     */
    private void parseChildren(NodeModel parentNode, Element parentElement) throws Exception {
        // 子节点
        NodeList elements = XPathAPI.selectNodeList(parentElement, "node");
        if (elements == null || elements.getLength() < 1) {
            return;
        }

        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            NodeModel node = this.parseNodeElement(element);
            if (node == null) {
                throw new BizException("");
            }

            parentNode.getChildren().add(node);

            // 循环其子节点
            this.parseChildren(node, element);
        }
    }

}
