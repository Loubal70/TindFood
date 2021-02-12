package fr.louisboulanger.tindfood.tindfood2;

import org.jetbrains.annotations.NotNull;

public class Data {

    private int id;
    private String namerecette;
    private String ingredient;

    public Data(int id, String namerecette, String ingredient) {
        this.setId(id);
        this.setNamerecette(namerecette);
        this.setIngredient(ingredient);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamerecette() {
        return namerecette;
    }

    public void setNamerecette(String namerecette) {
        this.namerecette = namerecette;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @NotNull
    @Override
    public String toString(){
        return ingredient; // id + ": " + namerecette + " -> " +
    }
}
