package fr.louisboulanger.tindfood.tindfood2;

public class Item {

    private String name;
    private float rating;
    private String price;
    private int image;
    private String description;

    public Item(String name, float rating, String price, int image, String description) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void add(Item item) {
    }
}
