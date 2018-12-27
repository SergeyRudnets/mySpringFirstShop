package com.example.mySpring.domain;

public enum Category {
    volleyball, football, basketball, tennis, hockey, billiards;
    public static boolean isPartOf(String category){
           for (Category cat:Category.values())
            if (cat.toString().equalsIgnoreCase(category))
                return true;
                return false;

        }
    }



