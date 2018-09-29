package com.atc.oa.vo;

import java.io.Serializable;
import java.util.List;

/**
 * PopedomPage 返回tree对象
 *
 * @author Mengle
 * @version 1.0.0
 */
public class PopedomPage implements Serializable {

    private static final long serialVersionUID = 3058534417097326794L;

    private String id;
    private String pId;
    private String title;
    private List<PopedomPage> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PopedomPage> getChildren() {
        return children;
    }

    public void setChildren(List<PopedomPage> children) {
        this.children = children;
    }
}