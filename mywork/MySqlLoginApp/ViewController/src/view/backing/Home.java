package view.backing;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.faces.bi.component.geoMap.UIGeoMap;
import oracle.adf.view.faces.bi.event.MapSelectionEvent;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import view.util.ADFUtil;

public class Home {
    private RichPanelGroupLayout pgl1;
    private RichGridCell gc2;
    private RichGridRow gr2;
    private RichGridCell gc1;
    private RichGridRow gr1;
    private RichPanelGridLayout pgl2;
    private RichPanelSplitter ps1;
    private RichForm f1;
    private RichDocument d1;
    private RichPanelFormLayout pfl1;
    private RichButton b1;
    private RichButton b2;
    private RichButton b3;
    private RichButton b4;
    private RichMessages m1;
    private UIGeoMap map;
    private RichOutputText ot1;
    private RichInputText it1;

    
    
    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setGc2(RichGridCell gc2) {
        this.gc2 = gc2;
    }

    public RichGridCell getGc2() {
        return gc2;
    }

    public void setGr2(RichGridRow gr2) {
        this.gr2 = gr2;
    }

    public RichGridRow getGr2() {
        return gr2;
    }

    public void setGc1(RichGridCell gc1) {
        this.gc1 = gc1;
    }

    public RichGridCell getGc1() {
        return gc1;
    }

    public void setGr1(RichGridRow gr1) {
        this.gr1 = gr1;
    }

    public RichGridRow getGr1() {
        return gr1;
    }

    public void setPgl2(RichPanelGridLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGridLayout getPgl2() {
        return pgl2;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

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

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }

    public void setB2(RichButton b2) {
        this.b2 = b2;
    }

    public RichButton getB2() {
        return b2;
    }

    public void setB3(RichButton b3) {
        this.b3 = b3;
    }

    public RichButton getB3() {
        return b3;
    }

    public void setB4(RichButton b4) {
        this.b4 = b4;
    }

    public RichButton getB4() {
        return b4;
    }


    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setMap(UIGeoMap map) {
        this.map = map;
    }

    public UIGeoMap getMap() {
        return map;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }
    public String FellMap() {
        return null;
    }

    public String SelectionLis(MapSelectionEvent mapSelectionEvent) {

        DCIteratorBinding itr = ADFUtil.findIterator("ReportViewID1Iterator");
                     
//                     OperationBinding opr = ADFUtil.findOperation("ExecuteWithParams");
//                     opr.getParamsMap().put("pReportid", it1.getValue());/// 3wz ashel it1 w a7t 7aga ml map xD
//                                 opr.execute();
                                 
        Row currentRow = itr.getCurrentRow();
        if(currentRow != null) {
            oracle.jbo.domain.Number id = (oracle.jbo.domain.Number)currentRow.getAttribute("ReportId");
        }

        
        
        ADFUtil.setEL("#{sessionScope.reportid}", it1.getValue());
        ADFUtil.setEL("#{sessionScope.lat}", currentRow.getAttribute("Latitude"));
        ADFUtil.setEL("#{sessionScope.long}", currentRow.getAttribute("Longitude"));
                
        return "GetReport";
    }

    public String b5_action() {
        DCIteratorBinding itr = ADFUtil.findIterator("ReportViewID1Iterator");
                     
                     OperationBinding opr = ADFUtil.findOperation("ExecuteWithParams");
                     opr.getParamsMap().put("pReportid", it1.getValue());
                                 opr.execute();
                                 
        Row currentRow = itr.getCurrentRow();

        
        
        ADFUtil.setEL("#{sessionScope.reportid}", it1.getValue());
        ADFUtil.setEL("#{sessionScope.lat}", currentRow.getAttribute("Latitude"));
        ADFUtil.setEL("#{sessionScope.long}", currentRow.getAttribute("Longitude"));
                
        return "GetReport";
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void mapSL(MapSelectionEvent mapSelectionEvent) {
        // Add event code here...
        DCIteratorBinding itr = ADFUtil.findIterator("ReportViewID1Iterator");
                     
        //                     OperationBinding opr = ADFUtil.findOperation("ExecuteWithParams");
        //                     opr.getParamsMap().put("pReportid", it1.getValue());/// 3wz ashel it1 w a7t 7aga ml map xD
        //                                 opr.execute();
                                 
        Row currentRow = itr.getCurrentRow();
        if(currentRow != null) {
            oracle.jbo.domain.Number id = (oracle.jbo.domain.Number)currentRow.getAttribute("ReportId");
        }

        
        
        ADFUtil.setEL("#{sessionScope.reportid}", it1.getValue());
        ADFUtil.setEL("#{sessionScope.lat}", currentRow.getAttribute("Latitude"));
        ADFUtil.setEL("#{sessionScope.long}", currentRow.getAttribute("Longitude"));
    }

    public String checkId() {
        // Add event code here...
        DCIteratorBinding itr = ADFUtil.findIterator("ReportViewID1Iterator");
                     
        //                     OperationBinding opr = ADFUtil.findOperation("ExecuteWithParams");
        //                     opr.getParamsMap().put("pReportid", it1.getValue());/// 3wz ashel it1 w a7t 7aga ml map xD
        //                                 opr.execute();
                                 
        Row currentRow = itr.getCurrentRow();
        Integer id;
        if(currentRow != null) {
             id = (Integer)currentRow.getAttribute("ReportId");
             System.out.println("Id = " + id);
        }
        return null;
    }
}
