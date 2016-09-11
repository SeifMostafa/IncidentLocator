package view.backing;


import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.faces.bi.component.geoMap.UIGeoMap;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import view.util.ADFUtil;

public class Report {
    private RichForm f1;
    private RichDocument d1;
    private UIGeoMap map;
    private RichMessages m1;
    private RichButton b1;

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

    public void setMap(UIGeoMap map) {
        this.map = map;
    }

    public UIGeoMap getMap() {
        return map;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }
    public void update() {
        
    }
    public String edit() {

    //DCBindingContainer binding =(DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding itr = ADFUtil.findIterator("ReportViewID1Iterator");
                         
                         OperationBinding opr = ADFUtil.findOperation("ExecuteWithParams");
                                     opr.execute();

                                     //Make sure that attribute name matches view case sensitive 
                                     Row currentRow = itr.getCurrentRow();
                                     currentRow.setAttribute("Solved" ,1);
                            System.out.println(currentRow.getAttribute("Solved"));

                         
   OperationBinding opr1 = ADFUtil.findOperation("Commit");
   opr1.execute();
                                     if(opr1.getErrors().size() == 0) {
                                     System.out.println("Sucess");
                                     } else {
                                     System.out.println("Error while commit");
                                     }
                                      
    return "";
        }


    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }
}
