package com.carlgo11.lain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DotCommands {

    private Lain Lain;

    public static String url = "jdbc:mysql://localhost:3306/";
    public static String username = "Lain";
    public static String password = "JAMUPsBMB7mrZNzx";
    public static String table = "commands";
    public static String database = "portalcraft";

    public void main(Lain plugin)
    {
        this.Lain = plugin;
    }

    public String getMessage(String command)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url + "portalcraft", Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from " + table);
            while (true) {
                if (rs.next()) {
                    String a = rs.getString(2);
                    String[] b = a.split(" ");

                    if (rs.getString(1).equalsIgnoreCase(command)) {
                        return rs.getString(3).toString();
                    } else if (namegoeshere(command, b)) {
                        
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
    boolean namegoeshere(String alias, String[] b){
    for(int i = 0; i < b.length; i++){
        if(b[i].equalsIgnoreCase(alias)){
            return true;
        }
    }
    return false;
}

    public void setCommand(String command, String message)
    {
        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(Mysql.url + database, Mysql.username, Mysql.password);
            st = con.createStatement();
            if (!containsCommand(command)) {
                st.execute("INSERT INTO `commands` (`command`, `aliases`, `message`) VALUES ('" + command + "', '', '" + message + "');");
            } else {
                st.execute("UPDATE `commands` SET `command` = '" + command + "', `aliases` = '', `message` = '" + message + "' WHERE `command` = '" + command + "';");
            }

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

    public void setAlias(String alias, String command)
    {
        Connection con = null;
        Statement st = null;

        String a = this.getAliases(command);
        String[] b = a.split(" ");
        StringBuilder d = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            d.append(b[i]);
            d.append(" ");
        }
        d.append(alias);
        try {
            con = DriverManager.getConnection(Mysql.url + database, Mysql.username, Mysql.password);
            st = con.createStatement();
            st.execute("UPDATE `commands` SET `aliases` = '" + d.toString() + "', `message` = '" + command + "' WHERE `command` = '" + command + "';");

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

    public void removeCommand(String command)
    {
        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(Mysql.url + database, Mysql.username, Mysql.password);
            st = con.createStatement();
            if (!containsCommand(command)) {
                st.execute("DELETE FROM `commands` WHERE `command`.`commands` = '" + command + "';");

            }

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

    public void removeAlias(String alias, String command)
    {

        StringBuilder d = new StringBuilder();
        String a = getAliases(alias);
        String[] b = a.split(" ");
        for (int i = 0; i < b.length; i++) {
            if (!b[i].equalsIgnoreCase(alias)) {
                d.append(b[i]);
                d.append(" ");
            }
        }

        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(Mysql.url, Mysql.username, Mysql.password);
            st = con.createStatement();
            st.execute("UPDATE `commands` SET `aliases` '" + d.toString() + "' WHERE `command` = '" + command + "';");

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

    public String getAliases(String command)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        StringBuilder d = new StringBuilder();

        try {
            con = DriverManager.getConnection(Mysql.url + database, Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from " + table);
            while (true) {
                if (rs.next()) {
                    if (rs.getString(1).contains(command)) {
                        return rs.getString(2);
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
        return "";
    }

    public boolean containsCommand(String command)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url + database, Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT command from commands");
            while (true) {
                if (rs.next()) {
                    if (rs.getString(1).equals(command)) {
                        return true;
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

    public boolean containsAlias(String alias)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url + database, Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT aliases from commands");
            while (true) {
                if (rs.next()) {
                    if (rs.getString(1).contains(alias)) {
                        return true;
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
