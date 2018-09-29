package com.atc.oa.vo;

import java.io.Serializable;

/**
 * BTemplate
 *
 * @author Mengke
 * @version 1.0.0
 */
public class BTemplate implements Serializable {

    private static final long serialVersionUID = -5622779625494464971L;

    private String tempalteName;
    private String popedom;

    public String getTempalteName() {
        return tempalteName;
    }

    public void setTempalteName(String tempalteName) {
        this.tempalteName = tempalteName;
    }

    public String getPopedom() {
        return popedom;
    }

    public void setPopedom(String popedom) {
        this.popedom = popedom;
    }
}