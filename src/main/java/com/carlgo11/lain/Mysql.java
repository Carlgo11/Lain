package com.carlgo11.lain;

import static com.carlgo11.lain.DotCommands.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mysql {

    public static String url = "jdbc:mysql://localhost:3306/";
    public static String username = "Lain";
    public static String password = "JAMUPsBMB7mrZNzx";

    public static String getMOTD(String Whitelist)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url + "portalcraft", Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from motd");
            while (true) {
                if (rs.next()) {
                    if (rs.getString(2).equalsIgnoreCase(Whitelist)) {
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

    public static String getRank(String Player)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url + "ranks", Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from ranks");
            while (true) {
                if (rs.next()) {
                    if (rs.getString(2).equalsIgnoreCase(Player)) {
                        return rs.getString(3).toString();
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

    public static Boolean isOp(String Player)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url + "ranks", Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from ranks");
            while (true) {
                if (rs.next()) {
                    if (rs.getString(2).equalsIgnoreCase(Player)) {
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
    
    public static void setMOTD(String motd)
    {
        Connection con = null;
        Statement st = null;
        
        try {
            con = DriverManager.getConnection(Mysql.url + database, Mysql.username, Mysql.password);
            st = con.createStatement();
            st.execute("UPDATE `motd` SET `MOTD` = '" + motd + "' WHERE `only on whitelist` = 'false';");

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
