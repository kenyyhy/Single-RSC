<img width="1200" height="800" alt="rsmap" src="https://github.com/user-attachments/assets/6a91d2ac-2c7c-4ce1-a59c-4e7167c85e19" />

RSC Single Player is a standalone single player RSC reproduction and sandbox. The application handles itself entirely and does not rely on a local server nor database.

# Requirements
* Java 8 or newer

# Instructions
* To launch you can either double click the 'rsc.jar' file, or use the run.bat (Windows) or run.sh (Unix)
* To create a new account, click the "New User" button and fill out the information below
* Login and play
    
# Game Information
* UI Fixed
* Open Bank anywhere by ::bank 
* Teleports to different areas ::tele area
* Batched Skills similar to Openrsc's Cabbage
* Music player replaced Social tab
* New dynamic login screen
* Harcore enabled after you die it takes away your save file and closes the client if you open back up and login again to start over. 
* Experience modifier is set at 1x-50x
* All content, including all 50 quests
* Updated where every os that has java 8 will be able to save the game properly
* The game can now be resized to your preferred dimensions
* Swap bank items by right clicking on the item
* You can use multiple accounts, but do not use more than one account during the same session (you can have multiple windows open)
* Administrator privileges can be acquired by creating a new account with username "root", and also supports right-clicking the mini-map to teleport

# Skill Batching
- Core: Repeating skill actions use `BatchEvent` to schedule ticks and respect player busy timers.
- Count: Prefer `Formulae.getRepeatTimes(player, <skill>)` for level-based batches, or use tool/selection-specific counts where that mirrors game design (e.g., pickaxe/axe tiers, make-x).
- Pattern: `player.setBatchEvent(new BatchEvent(player, <delayMs>, <repeatCount>) { public void action() { /* attempt once; interrupt on success/depletion */ } });`
- Notes: Avoid recursive retries; let `BatchEvent` pace attempts. Keep fatigue/inventory/availability checks inside `action()` and call `interrupt()` when the action should stop early.
# Media

![picture alt](https://nemotech.org/rsc/rsc-1.png "RSCSP1")
![picture alt](https://nemotech.org/rsc/rsc-2.png "RSCSP2")
![picture alt](https://nemotech.org/rsc/rsc-3.png "RSCSP3")


