package com.gildedrose;

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
}



    class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}