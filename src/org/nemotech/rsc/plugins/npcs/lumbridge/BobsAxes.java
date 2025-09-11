package org.nemotech.rsc.plugins.npcs.lumbridge;

import static org.nemotech.rsc.plugins.Plugin.npcTalk;
import static org.nemotech.rsc.plugins.Plugin.showMenu;
import org.nemotech.rsc.model.Shop;
import org.nemotech.rsc.model.player.InvItem;
import org.nemotech.rsc.model.NPC;
import org.nemotech.rsc.model.player.Player;

import org.nemotech.rsc.plugins.ShopInterface;
import org.nemotech.rsc.plugins.listeners.action.TalkToNpcListener;
import org.nemotech.rsc.plugins.listeners.executive.TalkToNpcExecutiveListener;

public final class BobsAxes implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

    /*
     * Updated to stock Bronze -> Rune pickaxes in addition to the existing axes/battle axes.
     * Existing axe IDs (87, 12, 88, 89, 90, 91) preserved.
     * Pickaxe IDs sourced from NurmofPickaxe.java (156, 1258-1262).
     */
    private final Shop shop = new Shop(
            "Bob's Axes",
            false,
            15000,
            100,
            60,
            2,
            // Axes / battle axes already in shop (stock counts slightly adjusted; tweak as desired)
            new InvItem(87, 10),  // Bronze axe (or bronze battle axe depending on cache)
            new InvItem(12, 8),   // Iron axe
            new InvItem(88, 6),   // Steel axe
            new InvItem(89, 5),   // Mithril axe
            new InvItem(90, 3),   // Adamant axe
            new InvItem(91, 2),   // Rune axe
            // Pickaxes Bronze -> Rune
            new InvItem(156, 5),  // Bronze pickaxe
            new InvItem(1258, 4), // Iron pickaxe
            new InvItem(1259, 3), // Steel pickaxe
            new InvItem(1260, 2), // Mithril pickaxe
            new InvItem(1261, 2), // Adamant pickaxe
            new InvItem(1262, 1)  // Rune pickaxe
    );

    @Override
    public boolean blockTalkToNpc(final Player p, final NPC n) {
        return n.getID() == 1;
    }

    @Override
    public Shop[] getShops() {
        return new Shop[] { shop };
    }

    @Override
    public boolean isMembers() {
        return false;
    }

    @Override
    public void onTalkToNpc(final Player p, final NPC n) {
        npcTalk(p, n, "Hello. How can I help you?");
        int option = showMenu(p, n, "Give me a quest!",
                "Have you anything to sell?");
        switch (option) {
        case 0:
            npcTalk(p, n, "Get yer own!");
            break;
        case 1:
            npcTalk(p, n, "Yes, I buy and sell axes, take your pick! (or axe)");
            org.nemotech.rsc.client.action.ActionManager.get(org.nemotech.rsc.client.action.impl.ShopHandler.class).handleShopOpen(shop);
            break;
        }
    }
}