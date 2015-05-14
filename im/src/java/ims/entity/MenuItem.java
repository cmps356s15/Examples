
package ims.entity;

/**
 *
 * @author Mooza Al-nisf
 */
public class MenuItem {
    
    private String name;
    private String controllerName;

    public MenuItem(String name, String controllerName) {
        this.name = name;
        this.controllerName = controllerName;
    }

    
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }
    
    
    
    
    
    
}
