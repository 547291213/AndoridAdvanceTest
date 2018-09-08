package com.example.xkfeng.andoridadvancetest.Model;

/**
 * Created by initializing on 2018/9/4.
 */

public class Gradle {


    private String version ;
    private String buildTime ;
    private Boolean current ;
    private Boolean snapshot ;
    private Boolean nightly ;
    private Boolean activeRc ;
    private String rcFor ;
    private String milestoneFor ;
    private Boolean broken ;
    private String downloadUrl ;
    private String checksumUrl ;

    public void setVersion(String version) {
        this.version = version;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public void setSnapshot(Boolean snapshot) {
        this.snapshot = snapshot;
    }

    public void setNightly(Boolean nightly) {
        this.nightly = nightly;
    }

    public void setActiveRc(Boolean activeRc) {
        this.activeRc = activeRc;
    }

    public void setRcFor(String rcFor) {
        this.rcFor = rcFor;
    }

    public void setMilestoneFor(String milestoneFor) {
        this.milestoneFor = milestoneFor;
    }

    public void setBroken(Boolean broken) {
        this.broken = broken;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void setChecksumUrl(String checksumUrl) {
        this.checksumUrl = checksumUrl;
    }

    public String getVersion() {
        return version;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public Boolean getCurrent() {
        return current;
    }

    public Boolean getSnapshot() {
        return snapshot;
    }

    public Boolean getNightly() {
        return nightly;
    }

    public Boolean getActiveRc() {
        return activeRc;
    }

    public String getRcFor() {
        return rcFor;
    }

    public String getMilestoneFor() {
        return milestoneFor;
    }

    public Boolean getBroken() {
        return broken;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getChecksumUrl() {
        return checksumUrl;
    }
}
