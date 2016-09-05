package view.backing.PagesCreated;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import view.util.ADFUtil;

public class Reports {
    private RichDocument d1;
    private RichMessages m1;
    private RichTable t1;
    private RichForm f1;
    private RichForm f2;
    private RichTable t2;
    private RichButton b1;

    public String doAction() {
        return "getReport";
    }
    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }


    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setF2(RichForm f2) {
        this.f2 = f2;
    }

    public RichForm getF2() {
        return f2;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void tableSL(SelectionEvent selectionEvent) {
        // Add event code here...
        ADFUtil.invokeEL("#{bindings.ViewCertainReports11.collectionModel.makeCurrent}", new Class[] {SelectionEvent.class},
                                 new Object[] { selectionEvent });
        
        Row selectedRow =
                    (Row)ADFUtil.evaluateEL("#{bindings.ViewCertainReports1Iterator.currentRow}");
        ADFUtil.setEL("#{sessionScope.reportid}", selectedRow.getAttribute("ReportId"));
        ActionEvent actionEvent = new ActionEvent(b1);
        actionEvent.queue();
                

        
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }

   
}
