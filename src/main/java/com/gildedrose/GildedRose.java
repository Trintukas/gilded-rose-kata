package com.gildedrose;

import com.gildedrose.items.Item;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    protected void updateItemQuality(Item item) {

        boolean neverDecreaseInValue = "Sulfuras, Hand of Ragnaros".equals(item.name);

        if ("Aged Brie".equals(item.name)) {
            item.quality++;
            item.sellIn--;

        } else if ("Sulfuras, Hand of Ragnaros".equals(item.name)) {
            // never to be sold of decrease in value, so no expire date

        } else if ("Backstage passes to a TAFKAL80ETC concert".equals(item.name)) {
            item.sellIn--;

            if (item.sellIn > 10) {
                item.quality++;
            } else if (item.sellIn <= 10 && item.sellIn > 5) {
                item.quality += 2;
            } else if (item.sellIn <= 5 && item.sellIn >= 0) {
                item.quality += 3;
            } else {
                item.quality = 0;
            }

        } else {
            // regular item
            item.sellIn--;
            if (item.sellIn < 0) {
                item.quality -= 2;
            } else {
                item.quality--;
            }

        }

        // quality is never negative
        if (item.quality < 0) {
            item.quality = 0;
        }

        if (item.quality > 50 && !neverDecreaseInValue) {
            item.quality = 50;
        }
    }

    @Deprecated
    protected void updateItemQualityLegacy(Item item) {
        if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals("Aged Brie")) {
                if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality > 0) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }
}
