package com.mennomuller;

public class Soup {
    Type type;
    MainIngredient ingredient;
    Seasoning seasoning;

    public Soup(Type type, MainIngredient ingredient, Seasoning seasoning) {
        this.type = type;
        this.ingredient = ingredient;
        this.seasoning = seasoning;
    }

    @Override
    public String toString() {
        return capitalize(seasoning.toString()) + " "
                + capitalize(ingredient.toString()) + " "
                + capitalize(type.toString());
    }
    public static String capitalize(String string){
        char c = string.charAt(0);
        string = string.substring(1).toLowerCase();
        return c + string;
    }
}

enum Type {
    SOUP,
    STEW,
    GUMBO
}

enum MainIngredient {
    MUSHROOM,
    CHICKEN,
    CARROT,
    POTATO
}

enum Seasoning {
    SPICY,
    SALTY,
    SWEET
}