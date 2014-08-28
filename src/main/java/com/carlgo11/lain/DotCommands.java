package com.carlgo11.lain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DotCommands {

    private Lain Lain;

    public static String url;
    public static String username;
    public static String password;
    public static String table;
    public static String database;

    public void main(Lain plugin, String curl, String cusername, String cpassword, String ctable, String cdatabase)
    {
        this.Lain = plugin;
        DotCommands.url = curl;
        DotCommands.username = cusername;
        DotCommands.password = cpassword;
        DotCommands.table = ctable;
        DotCommands.database = cdatabase;
    }

    public String getMessage(String command)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(DotCommands.url + DotCommands.database, DotCommands.username, DotCommands.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from " + DotCommands.table);
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
            Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
                Logger lgr = Logger.getLogger(DotCommands.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return null;
    }

    boolean namegoeshere(String alias, String[] b)
    {
        for (int i = 0; i < b.length; i++) {
            if (b[i].equalsIgnoreCase(alias)) {
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
            con = DriverManager.getConnection(DotCommands.url + DotCommands.database, DotCommands.username, DotCommands.password);
            st = con.createStatement();
            if (!containsCommand(command)) {
                
                PreparedStatement ps = con.prepareStatement("INSERT INTO `" + DotCommands.table + "` (`command`, `aliases`, `message`) VALUES (?, '', ?);");
                ps.setString(1, command);
                ps.setString(2, message);
                ps.execute();
            } else {
                PreparedStatement ps = con.prepareStatement("UPDATE `" + DotCommands.table + "` SET `command` = ?, `aliases` = '', `message` = ? WHERE `command` = ?;");
                ps.setString(1, command);
                ps.setString(2, message);
                ps.setString(3, command);
                ps.execute();
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
            con = DriverManager.getConnection(DotCommands.url + DotCommands.database, DotCommands.username, DotCommands.password);
            st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("UPDATE "+DotCommands.table+" SET `aliases` = ?, `message` = ? WHERE `command` = ?;");
            ps.setString(1, d.toString());
            ps.setString(2, command);
            ps.setString(3, command);
            ps.execute();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
                Logger lgr = Logger.getLogger(DotCommands.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }

    public void removeCommand(String command)
    {
        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(DotCommands.url + DotCommands.database, DotCommands.username, DotCommands.password);
            st = con.createStatement();
                PreparedStatement ps = con.prepareStatement("DELETE FROM "+DotCommands.table+" WHERE `command` = ?");
                ps.setString(1, command);
                ps.execute();


        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
                Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
            con = DriverManager.getConnection(DotCommands.url + DotCommands.database, DotCommands.username, DotCommands.password);
            st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("UPDATE "+DotCommands.table+" SET `aliases` = ? WHERE `command` = ?");
            ps.setString(1, d.toString());
            ps.setString(2, command);
            ps.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
                Logger lgr = Logger.getLogger(DotCommands.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }

    public String getAliases(String command)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(DotCommands.url + DotCommands.database, DotCommands.username, DotCommands.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from " + DotCommands.table);
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
            Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
                Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
            con = DriverManager.getConnection(DotCommands.url + DotCommands.database, DotCommands.username, DotCommands.password);
           PreparedStatement ps = con.prepareStatement("SELECT command FROM " + DotCommands.table+ "WHERE `command` = ?");
           ps.setString(1, command);
            rs = ps.executeQuery();
                if (rs.next()) {
                        return true;
                } else {
                    return false;
                }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
                Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
            con = DriverManager.getConnection(DotCommands.url + DotCommands.database, DotCommands.username, DotCommands.password);
            PreparedStatement ps = con.prepareStatement("SELECT aliases FROM " + DotCommands.table);
            rs = ps.executeQuery();
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
            Logger lgr = Logger.getLogger(DotCommands.class.getName());
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
                Logger lgr = Logger.getLogger(DotCommands.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }
}
