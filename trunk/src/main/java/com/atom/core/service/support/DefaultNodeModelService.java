/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.service.support;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.atom.core.exception.BizException;
import com.atom.core.model.NodeConst;
import com.atom.core.model.NodeModel;
import com.atom.core.service.NodeModelService;
import com.atom.core.utils.DateUtils;

/**
 * 节点模型服务
 * 
 * @author shizihu
 * @version $Id: DefaultNodeModelService.java, v 0.1 2012-9-13 下午12:54:18 shizihu Exp $
 */
public class DefaultNodeModelService implements NodeModelService {

    /** 
     * @see com.atom.core.service.NodeModelService#unmarshal(java.lang.String)
     */
    public NodeModel unmarshal(String config) throws Exception {
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
        NodeModel node = new NodeModel();

        // ID
        node.setId(StringUtils.trimToEmpty(element.getAttribute(NodeConst.ID)));

        // ParentID
        node.setParentId(StringUtils.trimToEmpty(element.getAttribute(NodeConst.PARENT_ID)));

        // Text
        node.setText(StringUtils.trimToEmpty(element.getAttribute(NodeConst.TEXT)));

        // Sort
        node.setSort(StringUtils.trimToEmpty(element.getAttribute(NodeConst.SORT)));

        // Page
        node.setPage(StringUtils.trimToEmpty(element.getAttribute(NodeConst.PAGE)));

        // Leaf
        node.setLeaf(BooleanUtils.toBoolean(element.getAttribute(NodeConst.IS_LEAF)));

        // Expand
        node.setExpanded(BooleanUtils.toBoolean(element.getAttribute(NodeConst.IS_EXPAND)));

        // Check
        node.setChecked(BooleanUtils.toBooleanObject(element.getAttribute(NodeConst.IS_CHECK)));

        // Icon
        node.setIcon(StringUtils.trimToEmpty(element.getAttribute(NodeConst.ICON)));

        // IconExt
        node.setIconExt(StringUtils.trimToEmpty(element.getAttribute(NodeConst.ICON_EXT)));

        // QTitle
        node.setQtitle(StringUtils.trimToEmpty(element.getAttribute(NodeConst.QTITLE)));

        // QTip
        node.setQtip(StringUtils.trimToEmpty(element.getAttribute(NodeConst.QTIP)));

        // GmtCreate
        node.setGmtCreate(DateUtils.tryParseDate(StringUtils.trimToEmpty(element
            .getAttribute(NodeConst.GMT_CREATE))));

        // GmtModify
        node.setGmtCreate(DateUtils.tryParseDate(StringUtils.trimToEmpty(element
            .getAttribute(NodeConst.GMT_MODIFY))));

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
