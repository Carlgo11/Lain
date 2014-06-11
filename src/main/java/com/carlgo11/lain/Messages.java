package com.carlgo11.lain;

import org.bukkit.ChatColor;

public class Messages {
    public static String prefix = ChatColor.LIGHT_PURPLE+"[Lain] "+ChatColor.RESET; 
    public static String error = ChatColor.RED + "[Error] "+ChatColor.RESET;
    public static String badPerms = Messages.error + ChatColor.RED+" You don't have to permissions to do that"+ChatColor.RESET;
    public static String nomsgfound = ChatColor.RED + "Could not find any messages called that. Did you misspell it?";
    public static String nowop = " now has op!";
    public static String nolongerop = " no longer has op!";
    public static String nowowner = " is now owner!";
    public static String nolongerowner = " is no longer owner!";
    public static String nowadmin = " is now admin!";
    public static String nolongeradmin = " is no longer admin!";
    public static String nowmod = " is now moderator!";
    public static String nolongermod = " is no longer moderator!";
    public static String basicusage = "Usage: ";
    public static String usagelainsay = Messages.basicusage + "/lain say <message>";
    public static String nolibfound = "Could not find the lib: ";
    public static String taguknownas = "You're now known as ";
    public static String deoped = ChatColor.YELLOW + "Deoped ";
    public static String demoteusage = "Usage: .demote (player)";
    public static String demotemessage = "We've gone over the members of staff and have made the hard decision to demote you. Please stay calm and continue to follow the rules. Do not bring this up with any other players on the server. If you want to dispute this action email help@portalcraft.se and an owner will contact you within  24 hours. //The Owners of The Portalcraft.Se Servers";
    public static String immuneplayer = "That player is immune to that action. Please try with another user instead.";
    
}
