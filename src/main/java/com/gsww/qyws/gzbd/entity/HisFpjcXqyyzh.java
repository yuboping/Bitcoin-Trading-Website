package com.gsww.qyws.gzbd.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName HisFpjcXqyyzh
 * @Description
 * @Author Mar
 * @Date 2019/4/28 11:42
 * @Version 1.0
 **/
@Entity
@Table(name = "his_fpjc_xqyyzh", schema = "CDMS", catalog = "")
public class HisFpjcXqyyzh {
    private String id;
    private String jgid;
    private String jgmc;
    private String sjjgid;
    private String zzjgdm;
    private String wsjglbdm;
    private String wsjglbmc;
    private String xzqhdm;
    private String xzqhdgJd;
    private String yydjJ;
    private String yydjD;
    private String fddbr;
    private String xxdz;
    private String yzbm;
    private String account;
    private String password;
    private String bz;
    private String zt;
    private String zhfl;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JGID")
    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    @Basic
    @Column(name = "JGMC")
    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    @Basic
    @Column(name = "SJJGID")
    public String getSjjgid() {
        return sjjgid;
    }

    public void setSjjgid(String sjjgid) {
        this.sjjgid = sjjgid;
    }

    @Basic
    @Column(name = "ZZJGDM")
    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    @Basic
    @Column(name = "WSJGLBDM")
    public String getWsjglbdm() {
        return wsjglbdm;
    }

    public void setWsjglbdm(String wsjglbdm) {
        this.wsjglbdm = wsjglbdm;
    }

    @Basic
    @Column(name = "WSJGLBMC")
    public String getWsjglbmc() {
        return wsjglbmc;
    }

    public void setWsjglbmc(String wsjglbmc) {
        this.wsjglbmc = wsjglbmc;
    }

    @Basic
    @Column(name = "XZQHDM")
    public String getXzqhdm() {
        return xzqhdm;
    }

    public void setXzqhdm(String xzqhdm) {
        this.xzqhdm = xzqhdm;
    }

    @Basic
    @Column(name = "XZQHDG_JD")
    public String getXzqhdgJd() {
        return xzqhdgJd;
    }

    public void setXzqhdgJd(String xzqhdgJd) {
        this.xzqhdgJd = xzqhdgJd;
    }

    @Basic
    @Column(name = "YYDJ_J")
    public String getYydjJ() {
        return yydjJ;
    }

    public void setYydjJ(String yydjJ) {
        this.yydjJ = yydjJ;
    }

    @Basic
    @Column(name = "YYDJ_D")
    public String getYydjD() {
        return yydjD;
    }

    public void setYydjD(String yydjD) {
        this.yydjD = yydjD;
    }

    @Basic
    @Column(name = "FDDBR")
    public String getFddbr() {
        return fddbr;
    }

    public void setFddbr(String fddbr) {
        this.fddbr = fddbr;
    }

    @Basic
    @Column(name = "XXDZ")
    public String getXxdz() {
        return xxdz;
    }

    public void setXxdz(String xxdz) {
        this.xxdz = xxdz;
    }

    @Basic
    @Column(name = "YZBM")
    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    @Basic
    @Column(name = "ACCOUNT")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "BZ")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Basic
    @Column(name = "ZT")
    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Basic
    @Column(name = "ZHFL")
    public String getZhfl() {
        return zhfl;
    }

    public void setZhfl(String zhfl) {
        this.zhfl = zhfl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HisFpjcXqyyzh that = (HisFpjcXqyyzh) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(jgid, that.jgid) &&
                Objects.equals(jgmc, that.jgmc) &&
                Objects.equals(sjjgid, that.sjjgid) &&
                Objects.equals(zzjgdm, that.zzjgdm) &&
                Objects.equals(wsjglbdm, that.wsjglbdm) &&
                Objects.equals(wsjglbmc, that.wsjglbmc) &&
                Objects.equals(xzqhdm, that.xzqhdm) &&
                Objects.equals(xzqhdgJd, that.xzqhdgJd) &&
                Objects.equals(yydjJ, that.yydjJ) &&
                Objects.equals(yydjD, that.yydjD) &&
                Objects.equals(fddbr, that.fddbr) &&
                Objects.equals(xxdz, that.xxdz) &&
                Objects.equals(yzbm, that.yzbm) &&
                Objects.equals(account, that.account) &&
                Objects.equals(password, that.password) &&
                Objects.equals(bz, that.bz) &&
                Objects.equals(zt, that.zt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jgid, jgmc, sjjgid, zzjgdm, wsjglbdm, wsjglbmc, xzqhdm, xzqhdgJd, yydjJ, yydjD, fddbr, xxdz, yzbm, account, password, bz, zt);
    }
}
