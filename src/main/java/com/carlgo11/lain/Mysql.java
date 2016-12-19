package com.carlgo11.lain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MySQL related functions.
 *
 * @since 2.0
 */
public class Mysql {

    public static String url;
    public static String username;
    public static String password;
    public static String database;
    public static String rankstable;
    public static String motdtable;

    public static void updateStrings(String url, String username, String password, String database, String rankstable, String motdtable)
    {
        Mysql.url = url;
        Mysql.username = username;
        Mysql.password = password;
        Mysql.database = database;
        Mysql.rankstable = rankstable;
        Mysql.motdtable = motdtable;
    }

    public static void createTables()
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url + Mysql.database, Mysql.username, Mysql.password);
            st = con.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS " + Mysql.database + "." + Mysql.rankstable + " (id int(11), Player text, Rank text, OP text, Hide text);");
            st.execute("CREATE TABLE IF NOT EXISTS " + Mysql.database + "." + DotCommands.table + " (command text, aliases text, message text);");
            st.execute("CREATE TABLE IF NOT EXISTS " + Mysql.database + "." + Mysql.motdtable + " (motd text, `only on whitelist` text);");
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
    }

    /**
     * Get the server MOTD message.
     *
     * @since 2.0
     * @param Whitelist Returns whitelist-specific MOTD if true.
     * @return The server's MOTD message.
     */
    public static String getMOTD(boolean Whitelist)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url + Mysql.database, Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from " + Mysql.motdtable);
            while (true) {
                if (rs.next()) {
                    if (rs.getString(2).equalsIgnoreCase(String.valueOf(Whitelist))) {
                        return rs.getString(1).toString();
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
     * Set MOTD for server. Shown for users when looking at the server list.
     *
     * @param motd New MOTD message.
     */
    public static void setMOTD(String motd)
    {
        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(url + database, Mysql.username, Mysql.password);
            st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("UPDATE " + Mysql.motdtable + " SET `MOTD` = ? WHERE `only on whitelist` = 'false'");
            ps.setString(1, motd);
            ps.execute();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Mysql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
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
    }
}
