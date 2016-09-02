package Myapp.view.backing;

import Myapp.model.Account;
import Myapp.model.AppModuleImpl;

import Myapp.view.util.ADFUtil;

import Myapp.view.util.JSFUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;


import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

public class Login {
    public String UserName;
    public String Password;
    private RichForm f1;

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }
    private RichDocument d1;

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public String login_action() {
//        // Add event code here...
//        FacesMessage fm;
//        FacesContext fc=FacesContext.getCurrentInstance();
//        String amdef="Myapp.model.AppModule";
//        String config="Myapp.model.AppModule";
//        ApplicationModule ami=Configuration.createRootApplicationModule(amdef,config);
//        AppModuleImpl aml=(AppModuleImpl)ami;
//        Account acc=aml.find(UserName, Password);
//        if(acc==null) {
//            fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"User Not Found!!","");
//            }
//        else {
//            fm=new FacesMessage(FacesMessage.SEVERITY_INFO,"User Found" + acc.getUserName(),"");   
//        }
//        System.out.println("5");
//
//    fc.addMessage("",fm);
//        return "";

        DCIteratorBinding itr = ADFUtil.findIterator("AccountView1Iterator");
        OperationBinding opr =  ADFUtil.findOperation("ExecuteWithParams");
        opr.getParamsMap().put("pUsername", UserName);
        opr.getParamsMap().put("pPassword", Password);
        opr.execute();
        
        if(itr.getViewObject().getCurrentRow() != null) {
            JSFUtils.addFacesInformationMessage("User Found");
        } else {
            JSFUtils.addFacesErrorMessage("USer not found");
        }
        return "";
        
    }
}
