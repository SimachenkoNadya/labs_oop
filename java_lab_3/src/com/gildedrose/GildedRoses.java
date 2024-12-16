package com.gildedrose;

import java.util.List;

public class GildedRoses {

    public static void main(String[] args) {
        // Створення кількох товарів
        InventoryItem item1 = new InventoryItem("Regular Item", 10, 20);
        InventoryItem item2 = new InventoryItem("Aged Brie", 5, 30);
        InventoryItem item3 = new InventoryItem("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        InventoryItem item4 = new InventoryItem("Sulfuras, Hand of Ragnaros", 0, 80);

        // Додавання товарів до списку
        List<InventoryItem> items = List.of(item1, item2, item3, item4);
        GildedRose gildedRose = new GildedRose(items);

        // Виведення початкового стану товарів
        System.out.println("Initial state:");
        items.forEach(item -> System.out.println(item));

        // Оновлення стану товарів на 1 день
        gildedRose.updateQuality();

        // Виведення стану товарів після 1-го оновлення
        System.out.println("\nState after 1 day:");
        items.forEach(item -> System.out.println(item));

        // Оновлення стану товарів на ще 1 день
        gildedRose.updateQuality();

        // Виведення стану товарів після 2-го оновлення
        System.out.println("\nState after 2 days:");
        items.forEach(item -> System.out.println(item));

        // Оновлення стану товарів на ще 1 день
        gildedRose.updateQuality();

        // Виведення стану товарів після 3-го оновлення
        System.out.println("\nState after 3 days:");
        items.forEach(item -> System.out.println(item));
    }
}

class GildedRose {
    private final List<InventoryItem> items;

    public GildedRose(List<InventoryItem> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (InventoryItem item : items) {
            item.update();
        }
    }
}

class Quality {
    private int value;
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    public Quality(int value) { //Перевірка та обмеження значення value
        this.value = Math.max(MIN_QUALITY, Math.min(value, MAX_QUALITY));
    }

    public void increase(int amount) { //Збільшення значення value
        value = Math.min(value + amount, MAX_QUALITY);
    }

    public void decrease(int amount) { //Зменшення значення value
        value = Math.max(value - amount, MIN_QUALITY);
    }

    public int getValue() {
        return value;
    }
}

class SellIn { //клас використовується для роботи зі значенням кількості днів до продажу товару
    private int value;

    public SellIn(int value) {
        this.value = value;
    }

    public void decrease() {
        value--;
    }

    public int getValue() {
        return value;
    }
}

class InventoryItem {
    private final String name;
    private final Quality quality;
    private final SellIn sellIn;

    public InventoryItem(String name, int sellIn, int quality) {
        this.name = name;
        this.quality = new Quality(quality);
        this.sellIn = new SellIn(sellIn);
    }

    public void update() { //метод виконує оновлення стану конкретного елемента в інвентарі
        if (isLegendary()) return;

        updateQuality();
        sellIn.decrease();

        if (sellIn.getValue() < 0) {
            handleExpired();
        }
    }

    private boolean isLegendary() { //метод перевіряє, чи є об'єкт легендарним предметом
        return name.equals("Sulfuras, Hand of Ragnaros");
    }

    private void updateQuality() { //Метод визначає, як змінювати якість предмета залежно від його типу
        if (isAgedBrie()) {
            quality.increase(1);
        } else if (isBackstagePass()) {
            updateBackstagePassQuality();
        } else {
            quality.decrease(1);
        }
    }

    private void handleExpired() { //метод виконує спеціальну обробку для товарів, термін придатності яких закінчився
        if (isAgedBrie()) {
            quality.increase(1);
        } else if (isBackstagePass()) {
            quality.decrease(quality.getValue()); // Set to 0
        } else {
            quality.decrease(1);
        }
    }

    private void updateBackstagePassQuality() { //Метод відповідає за оновлення якості квитків на концерт "Backstage passes"
        quality.increase(1);
        if (sellIn.getValue() < 10) {
            quality.increase(1);
        }
        if (sellIn.getValue() < 5) {
            quality.increase(1);
        }
    }

    private boolean isAgedBrie() { //Метод визначає, чи є назва предмета "Aged Brie"
        return name.equals("Aged Brie");
    }

    private boolean isBackstagePass() { //Метод перевіряє назву квитка
        return name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn.getValue() +
                ", quality=" + quality.getValue() +
                '}';
    }
}