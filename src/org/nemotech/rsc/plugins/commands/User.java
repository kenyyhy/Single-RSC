package org.nemotech.rsc.plugins.commands;

import org.nemotech.rsc.client.mudclient;
import org.nemotech.rsc.model.player.Player;
import org.nemotech.rsc.plugins.Plugin;
import org.nemotech.rsc.plugins.listeners.action.CommandListener;

public class User extends Plugin implements CommandListener {

    @Override
    public void onCommand(String command, String[] args, Player player) {
        if(command.equals("help")) {
            mudclient.getInstance().showAlert("@yel@Single Player RSC Help % %" +
                "@whi@Type ::stuck if your character gets stuck. % " +
                "Type ::pos to list your current location in the world. % " +
                "Type ::mapedit to bring up the real time map editor % " +
                "Type ::speed <n> to set walk speed (1-30) % " +
                "Type ::speed reset to return to normal speed", false);
            return;
        }
        if(command.equals("stuck")) {
            player.teleport(122, 647, true);
            return;
        }
        if(command.equals("reload")) {
            //mudclient.getInstance().loadSection(mudclient.getInstance().sectionX, mudclient.getInstance().sectionY );
            return;
        }
        if(command.equals("pos") || command.equals("coords") || command.equals("sector")) {
            int x = player.getX();
            int y = player.getY();
            int sectorH = 0;
            int sectorX = 0;
            int sectorY = 0;
            if (x != -1 && y != -1) {
                if (y >= 0 && y <= 1007)
                    sectorH = 0;
                else if (y >= 1007 && y <= 1007 + 943) {
                    sectorH = 1;
                    y -= 943;
                } else if (y >= 1008 + 943 && y <= 1007 + (943 * 2)) {
                    sectorH = 2;
                    y -= 943 * 2;
                } else {
                    sectorH = 3;
                    y -= 943 * 3;
                }
                sectorX = (x / 48) + 48;
                sectorY = (y / 48) + 37;
            }
            player.getSender().sendMessage(String.format("@whi@X: %d Y: %d (Sector h%dx%dy%d)@que@", player.getX(), player.getY(), sectorH, sectorX, sectorY));
            return;
        }

        if (command.equals("speed") || command.equals("walkspeed")) {
            if (args.length == 0) {
                int current = mudclient.getInstance().getWalkSpeedMultiplier();
                player.getSender().sendMessage("@whi@Current walk speed: x" + current + "@que@");
                player.getSender().sendMessage("@whi@Usage: ::speed <1-30> or ::speed reset@que@");
                return;
            }
            // reset aliases
            if ("reset".equalsIgnoreCase(args[0]) || "default".equalsIgnoreCase(args[0]) || "normal".equalsIgnoreCase(args[0])) {
                mudclient.getInstance().setWalkSpeedMultiplier(1);
                player.getSender().sendMessage("@whi@Walk speed reset to x1@que@");
                return;
            }
            try {
                int mult = Integer.parseInt(args[0]);
                if (mult < 1) mult = 1;
                if (mult > 30) mult = 30;
                mudclient.getInstance().setWalkSpeedMultiplier(mult);
                player.getSender().sendMessage("@whi@Walk speed set to x" + mult + "@que@");
            } catch (NumberFormatException e) {
                player.getSender().sendMessage("@red@Invalid number. Usage: ::speed <1-30> or ::speed reset@que@");
            }
            return;
        }
    }

}
