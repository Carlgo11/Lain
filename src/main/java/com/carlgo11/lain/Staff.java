package com.carlgo11.lain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;

/**
 * Staff-related functions
 *
 * @since 2.0
 */
public class Staff {

    /**
     * Get whether or not the player is a bot administrator.
     *
     * @param player Player's Unique ID.
     * @param lain Main class.
     * @return Returns true if the player is an administrator. Otherwise it
     * returns false.
     * @since 2.0
     */
    public static boolean isBotAdmin(UUID player, Lain lain)
    {
        boolean outp = lain.getConfig().getList("admins").contains(player.toString());
        return outp;
    }

    /**
     * Get whether or not the player is a bot administrator.
     *
     * @param player Player.
     * @param lain Main class.
     * @return Returns true if the player is an administrator. Otherwise it
     * returns false.
     * @since 2.0
     * @deprecated Use
     * {@link #isBotAdmin(java.util.UUID, com.carlgo11.lain.Lain)} instead.
     */
    public static boolean isBotAdmin(Player player, Lain lain)
    {
        boolean outp = lain.getConfig().getList("admins").contains(player.getUniqueId().toString());
        return outp;
    }

    /**
     * Get whether or not the player is a member of staff or not.
     *
     * @param player Player to get the rank for.
     * @param lain Main class.
     * @return Returns true if the player is a member of staff. Returns false if
     * the player isn't a member.
     * @since 2.0
     */
    public static boolean isStaff(String player, Lain lain)
    {
        String rank = getRank(player);
        return rank != null;
    }

    /**
     * Get player's staff rank.
     *
     * @since 2.0
     * @param Player Player who's rank you want.
     * @return The player's rank.
     */
    public static String getRank(String Player)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Mysql.url + Mysql.database, Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from " + Mysql.rankstable);
            while (true) {
                if (rs.next()) {
                    if (rs.getString(2).equalsIgnoreCase(Player)) {
                        return rs.getString(3);
                    }
                } else {
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Mysql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Mysql.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return null;
    }

    /**
     * Get whether or not the the player should have OP.
     *
     * @param Player Player's UUID
     * @return Returns true if the user should have OP. Otherwise it returns
     * false.
     * @since 2.0
     */
    public static boolean isOp(UUID Player)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Mysql.url + Mysql.database, Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from " + Mysql.rankstable);
            while (true) {
                if (rs.next()) {
                    if (rs.getString(2).equalsIgnoreCase(Player.toString())) {
                        if (rs.getString(4).equals("true")) {
                            return true;
                        }
                    }
                } else {
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Mysql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Mysql.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }
}
