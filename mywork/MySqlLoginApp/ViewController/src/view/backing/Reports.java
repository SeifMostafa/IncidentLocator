package view.backing;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.faces.bi.event.graph.SelectionEvent;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import oracle.jbo.Row;

import view.util.ADFUtil;

public class Reports {
    private RichForm f1;
    private RichDocument d1;
    private RichTable t1;
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

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }
public String dDo() {
   // DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();

    // Get a attribute value of the current row of iterator
    //DCIteratorBinding iterBind= (DCIteratorBinding)dcBindings.get("Reports1Iterator");
    Row selectedRow =   (Row)ADFUtil.evaluateEL("#{bindings.Reports1Iterator.currentRow}");
    //ADFUtil.setEL("#{sessionScope.reportid}", iterBind.getCurrentRow().getAttribute("ReportId"));
    //System.out.println( iterBind.getCurrentRow().getAttribute("ReportId"));
    System.out.println( selectedRow.getAttribute("ReportId"));

    //ADFUtil.setEL("#{sessionScope.lat}", iterBind.getCurrentRow().getAttribute("Latitude"));
    //ADFUtil.setEL("#{sessionScope.long}", iterBind.getCurrentRow().getAttribute("Longitude"));


return "getReport";
   
}
    
}
