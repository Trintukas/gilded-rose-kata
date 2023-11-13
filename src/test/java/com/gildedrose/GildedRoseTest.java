package com.gildedrose;

import com.gildedrose.items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void oneItemTest() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void regularItemPassedDueDate() {
        Item[] items = new Item[] { new Item("foo", 2, 10) };
        GildedRose app = new GildedRose(items);
        assertEquals("foo", app.items[0].name);

        nextDayAndAssert(app, 0, 1, 9);
        nextDayAndAssert(app, 0, 0, 8);
        nextDayAndAssert(app, 0, -1, 6);
        nextDayAndAssert(app, 0, -2, 4);
        nextDayAndAssert(app, 0, -3, 2);
        nextDayAndAssert(app, 0, -4, 0);
        nextDayAndAssert(app, 0, -5, 0);
        nextDayAndAssert(app, 0, -6, 0);
    }

    @Test
    void brieItemPassedDueDate() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 10) };
        GildedRose app = new GildedRose(items);
        assertEquals("Aged Brie", app.items[0].name);

        nextDayAndAssert(app, 0, 1, 11);
        for (int i=0; i < 45; i++) {
            app.updateQuality();
        }

        assertEquals(-44, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    private void nextDayAndAssert(GildedRose app, int itemNo, int expectedSellIn, int expectedQuality) {
        app.updateQuality();
        assertEquals(expectedSellIn, app.items[itemNo].sellIn);
        assertEquals(expectedQuality, app.items[itemNo].quality);
    }

    @Test
    void multiItemTest() {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)
        };

        GildedRose app = new GildedRose(items);

        int days = 1;

        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        assertEquals(app.items[0].quality, 19);
        assertEquals(app.items[0].sellIn, 9);

        assertEquals(app.items[1].sellIn, 1);
        // assertEquals(app.items[1].quality, 2); TODO fix brie
    }

}
