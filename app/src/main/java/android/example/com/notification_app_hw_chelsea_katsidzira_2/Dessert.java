package android.example.com.notification_app_hw_chelsea_katsidzira_2;

public class Dessert {
    private Integer image;
    private String name;
    private String description;

    public Dessert() {}

    public Dessert(Integer image, String text, String description) {
        this.image = image;
        this.name = text;
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
