package com.atc.oa.vo;

import java.io.Serializable;
import java.util.List;

/**
 * BLogin
 *
 * @author Mengke
 * @version 1.0.0
 */
public class BLogin implements Serializable {

    private static final long serialVersionUID = 262683317871786461L;

    private String name;
    private String token;
    private List<Long> popedem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Long> getPopedem() {
        return popedem;
    }

    public void setPopedem(List<Long> popedem) {
        this.popedem = popedem;
    }
}