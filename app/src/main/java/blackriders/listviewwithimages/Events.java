package blackriders.listviewwithimages;

/**
 * Created by Sanwal Singh on 13/5/16.
 */
public class Events {

    String platform, logo, name, oppid, hadtip;

    public Events(String platform, String logo, String name, String oppid, String hadtip) {
        this.platform = platform;
        this.logo = logo;
        this.name = name;
        this.oppid = oppid;
        this.hadtip = hadtip;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOppid() {
        return oppid;
    }

    public void setOppid(String oppid) {
        this.oppid = oppid;
    }

    public String getHadtip() {
        return hadtip;
    }

    public void setHadtip(String hadtip) {
        this.hadtip = hadtip;
    }
}
