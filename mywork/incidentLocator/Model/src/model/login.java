package model;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class login {
    private String userLogin;
    private String passLogin;
    private boolean remember;
    private String loginMsg;

    public login() {
    }

    public String login_action() {
        // Add event code here...
        DB db = new DB();
        String sql = "select * from user where userName ="+getUserLogin()+ "and password = "+getPassLogin();
      ResultSet res = db.runQuery(sql);
      try{
           if(res == null)
               setLoginMsg("Database Error");
           else if(res.next())
               setLoginMsg("Welcome");
           else
               setLoginMsg("user name and password incorrect");
      }catch(SQLException e){
          setLoginMsg(e.getMessage());
      }
       return null;    
               
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setPassLogin(String passLogin) {
        this.passLogin = passLogin;
    }

    public String getPassLogin() {
        return passLogin;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public boolean getRemember() {
        return remember;
    }

    public void setLoginMsg(String loginMsg) {
        this.loginMsg = loginMsg;
    }

    public String getLoginMsg() {
        return loginMsg;
    }
}
