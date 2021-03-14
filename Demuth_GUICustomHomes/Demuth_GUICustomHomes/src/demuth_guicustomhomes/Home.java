
package demuth_guicustomhomes;

/**
 *
 * @author mattd
 */
public class Home {

    public String getHomeID() {
        return HomeID;
    }

    public void setHomeID(String HomeID) {
        this.HomeID = HomeID;
    }

    public String getHomeFirstName() {
        return HomeFirstName;
    }

    public void setHomeFirstName(String HomeFirstName) {
        this.HomeFirstName = HomeFirstName;
    }

    public String getHomeLastName() {
        return HomeLastName;
    }

    public void setHomeLastName(String HomeLastName) {
        this.HomeLastName = HomeLastName;
    }

    public String getHomeType() {
        return HomeType;
    }

    public void setHomeType(String HomeType) {
        this.HomeType = HomeType;
    }

    public Double getHomePrice() {
        return HomePrice;
    }

    public void setHomePrice(Double HomePrice) {
        this.HomePrice = HomePrice;
    }
    
    String HomeID;
    String HomeFirstName;
    String HomeLastName;
    String HomeType;
    Double HomePrice;

}
