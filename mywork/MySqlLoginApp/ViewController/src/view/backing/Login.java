
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;

import oracle.adf.view.rich.component.rich.RichForm;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;


import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import view.util.ADFUtil;
import view.util.JSFUtils;


public class Login {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText it1;
    private RichInputText it2;
    private RichMessages m1;
    private RichButton b1;
    private RichPanelGridLayout pgl1;
    private RichGridRow gr3;
    private RichGridCell gc11;
    private RichGridCell gc12;
    private RichGridCell gc13;
    private RichGridCell gc14;
    private RichGridCell gc15;
    private RichGridRow gr4;
    private RichGridCell gc16;
    private RichGridCell gc17;
    private RichGridCell gc18;
    private RichGridCell gc19;
    private RichGridCell gc20;
    private RichGridRow gr5;
    private RichGridCell gc21;
    private RichGridCell gc22;
    private RichGridCell gc23;
    private RichGridCell gc24;
    private RichGridCell gc25;
    private RichPanelGridLayout pgl2;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;
    private RichGridCell gc3;
    private RichGridCell gc4;
    private RichGridCell gc5;
    private RichGridCell gc6;
    private RichGridCell gc7;
    private RichGridRow gr2;
    private RichGridCell gc8;
    private RichGridCell gc9;
    private RichGridCell gc10;
    private RichGridCell gc113;
    private RichGridRow gr17;
    private RichGridCell gc26;
    private RichGridCell gc27;
    private RichGridCell gc28;
    private RichGridCell gc29;
    private RichGridCell gc30;
    private RichGridCell gc31;
    private RichGridCell gc32;
    private RichGridCell gc33;
    private RichGridCell gc34;
    private RichGridCell gc35;
    private RichGridRow gr6;
    private RichGridCell gc36;
    private RichGridCell gc37;
    private RichGridCell gc38;
    private RichGridCell gc39;
    private RichGridCell gc40;
    private RichGridCell gc41;
    private RichGridCell gc42;
    private RichGridRow gr7;
    private RichGridCell gc43;
    private RichGridCell gc44;
    private RichGridCell gc45;
    private RichGridCell gc46;
    private RichGridCell gc47;
    private RichGridCell gc48;
    private RichGridCell gc49;
    private RichGridRow gr8;
    private RichGridCell gc50;
    private RichGridCell gc51;
    private RichGridCell gc52;
    private RichGridCell gc53;
    private RichGridCell gc54;
    private RichGridCell gc55;
    private RichGridCell gc56;
    private RichGridRow gr9;
    private RichGridCell gc57;
    private RichGridCell gc58;
    private RichGridCell gc59;
    private RichGridCell gc60;
    private RichGridCell gc61;
    private RichGridCell gc62;
    private RichGridCell gc63;
    private RichGridRow gr10;
    private RichGridCell gc64;
    private RichGridCell gc65;
    private RichGridCell gc66;
    private RichGridCell gc67;
    private RichGridCell gc68;
    private RichGridCell gc69;
    private RichGridCell gc70;
    private RichGridRow gr11;
    private RichGridCell gc71;
    private RichGridCell gc72;
    private RichGridCell gc73;
    private RichGridCell gc74;
    private RichGridCell gc75;
    private RichGridCell gc76;
    private RichGridCell gc77;
    private RichGridRow gr12;
    private RichGridCell gc78;
    private RichGridCell gc79;
    private RichGridCell gc80;
    private RichGridCell gc81;
    private RichGridCell gc82;
    private RichGridCell gc83;
    private RichGridCell gc84;
    private RichGridRow gr13;
    private RichGridCell gc85;
    private RichGridCell gc86;
    private RichGridCell gc87;
    private RichGridCell gc88;
    private RichGridCell gc89;
    private RichGridCell gc90;
    private RichGridCell gc91;
    private RichGridRow gr14;
    private RichGridCell gc92;
    private RichGridCell gc93;
    private RichGridCell gc94;
    private RichGridCell gc95;
    private RichGridCell gc96;
    private RichGridCell gc97;
    private RichGridCell gc98;
    private RichGridRow gr15;
    private RichGridCell gc99;
    private RichGridCell gc100;
    private RichGridCell gc101;
    private RichGridCell gc102;
    private RichGridCell gc103;
    private RichGridCell gc104;
    private RichGridCell gc105;
    private RichGridRow gr16;
    private RichGridCell gc106;
    private RichGridCell gc107;
    private RichGridCell gc108;
    private RichGridCell gc109;
    private RichGridCell gc110;
    private RichGridCell gc111;
    private RichGridCell gc112;

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

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
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
    
    public String login() {
        DCIteratorBinding itr = ADFUtil.findIterator("AccountView1Iterator"); // byshel l result 
        
        OperationBinding opr = ADFUtil.findOperation("ExecuteWithParams");
        opr.getParamsMap().put("pUsername", it1.getValue());
        opr.getParamsMap().put("pPassword", it2.getValue());
        opr.execute();
        
        if(itr.getViewObject().getEstimatedRowCount() > 0) {
            //JSFUtils.addFacesInformationMessage("Found");
            Row currentRow = itr.getCurrentRow();
            //JSFUtils.addFacesInformationMessage("Found");
            if((Integer)currentRow.getAttribute("Verified")==1)
            {
                ADFUtil.setEL("#{sessionScope.name}", currentRow.getAttribute("Name"));
             
             if(currentRow.getAttribute("Type").equals("FireFighter"))
                 ADFUtil.setEL("#{sessionScope.type}", "fire");
        else if(currentRow.getAttribute("Type").equals("Engineer"))
                 ADFUtil.setEL("#{sessionScope.type}", "road");
        else if(currentRow.getAttribute("Type").equals("PoliceMan"))
                 ADFUtil.setEL("#{sessionScope.type}", "police");
             else 
                ADFUtil.setEL("#{sessionScope.type}", "ambulance");
             return "goHome";
            }
            else {
                JSFUtils.addFacesErrorMessage("User Not Verified!");

            }
        } else {
            JSFUtils.addFacesErrorMessage("Not Found");
            
        }
        
       return "goLogin";
    }


    public String map() {
        return "goMap";
    }

    public void setPgl1(RichPanelGridLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGridLayout getPgl1() {
        return pgl1;
    }


    public void setGr3(RichGridRow gr3) {
        this.gr3 = gr3;
    }

    public RichGridRow getGr3() {
        return gr3;
    }

    public void setGc11(RichGridCell gc11) {
        this.gc11 = gc11;
    }

    public RichGridCell getGc11() {
        return gc11;
    }

    public void setGc12(RichGridCell gc12) {
        this.gc12 = gc12;
    }

    public RichGridCell getGc12() {
        return gc12;
    }

    public void setGc13(RichGridCell gc13) {
        this.gc13 = gc13;
    }

    public RichGridCell getGc13() {
        return gc13;
    }

    public void setGc14(RichGridCell gc14) {
        this.gc14 = gc14;
    }

    public RichGridCell getGc14() {
        return gc14;
    }

    public void setGc15(RichGridCell gc15) {
        this.gc15 = gc15;
    }

    public RichGridCell getGc15() {
        return gc15;
    }

    public void setGr4(RichGridRow gr4) {
        this.gr4 = gr4;
    }

    public RichGridRow getGr4() {
        return gr4;
    }

    public void setGc16(RichGridCell gc16) {
        this.gc16 = gc16;
    }

    public RichGridCell getGc16() {
        return gc16;
    }

    public void setGc17(RichGridCell gc17) {
        this.gc17 = gc17;
    }

    public RichGridCell getGc17() {
        return gc17;
    }

    public void setGc18(RichGridCell gc18) {
        this.gc18 = gc18;
    }

    public RichGridCell getGc18() {
        return gc18;
    }

    public void setGc19(RichGridCell gc19) {
        this.gc19 = gc19;
    }

    public RichGridCell getGc19() {
        return gc19;
    }

    public void setGc20(RichGridCell gc20) {
        this.gc20 = gc20;
    }

    public RichGridCell getGc20() {
        return gc20;
    }

    public void setGr5(RichGridRow gr5) {
        this.gr5 = gr5;
    }

    public RichGridRow getGr5() {
        return gr5;
    }

    public void setGc21(RichGridCell gc21) {
        this.gc21 = gc21;
    }

    public RichGridCell getGc21() {
        return gc21;
    }

    public void setGc22(RichGridCell gc22) {
        this.gc22 = gc22;
    }

    public RichGridCell getGc22() {
        return gc22;
    }

    public void setGc23(RichGridCell gc23) {
        this.gc23 = gc23;
    }

    public RichGridCell getGc23() {
        return gc23;
    }

    public void setGc24(RichGridCell gc24) {
        this.gc24 = gc24;
    }

    public RichGridCell getGc24() {
        return gc24;
    }

    public void setGc25(RichGridCell gc25) {
        this.gc25 = gc25;
    }

    public RichGridCell getGc25() {
        return gc25;
    }

    public void setPgl2(RichPanelGridLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGridLayout getPgl2() {
        return pgl2;
    }

    public void setGr1(RichGridRow gr1) {
        this.gr1 = gr1;
    }

    public RichGridRow getGr1() {
        return gr1;
    }

    public void setGc1(RichGridCell gc1) {
        this.gc1 = gc1;
    }

    public RichGridCell getGc1() {
        return gc1;
    }

    public void setGc2(RichGridCell gc2) {
        this.gc2 = gc2;
    }

    public RichGridCell getGc2() {
        return gc2;
    }

    public void setGc3(RichGridCell gc3) {
        this.gc3 = gc3;
    }

    public RichGridCell getGc3() {
        return gc3;
    }

    public void setGc4(RichGridCell gc4) {
        this.gc4 = gc4;
    }

    public RichGridCell getGc4() {
        return gc4;
    }

    public void setGc5(RichGridCell gc5) {
        this.gc5 = gc5;
    }

    public RichGridCell getGc5() {
        return gc5;
    }

    public void setGc6(RichGridCell gc6) {
        this.gc6 = gc6;
    }

    public RichGridCell getGc6() {
        return gc6;
    }

    public void setGc7(RichGridCell gc7) {
        this.gc7 = gc7;
    }

    public RichGridCell getGc7() {
        return gc7;
    }

    public void setGr2(RichGridRow gr2) {
        this.gr2 = gr2;
    }

    public RichGridRow getGr2() {
        return gr2;
    }

    public void setGc8(RichGridCell gc8) {
        this.gc8 = gc8;
    }

    public RichGridCell getGc8() {
        return gc8;
    }

    public void setGc9(RichGridCell gc9) {
        this.gc9 = gc9;
    }

    public RichGridCell getGc9() {
        return gc9;
    }

    public void setGc10(RichGridCell gc10) {
        this.gc10 = gc10;
    }

    public RichGridCell getGc10() {
        return gc10;
    }

    public void setGc113(RichGridCell gc113) {
        this.gc113 = gc113;
    }

    public RichGridCell getGc113() {
        return gc113;
    }


    public void setGr17(RichGridRow gr17) {
        this.gr17 = gr17;
    }

    public RichGridRow getGr17() {
        return gr17;
    }

    public void setGc26(RichGridCell gc26) {
        this.gc26 = gc26;
    }

    public RichGridCell getGc26() {
        return gc26;
    }

    public void setGc27(RichGridCell gc27) {
        this.gc27 = gc27;
    }

    public RichGridCell getGc27() {
        return gc27;
    }

    public void setGc28(RichGridCell gc28) {
        this.gc28 = gc28;
    }

    public RichGridCell getGc28() {
        return gc28;
    }

    public void setGc29(RichGridCell gc29) {
        this.gc29 = gc29;
    }

    public RichGridCell getGc29() {
        return gc29;
    }

    public void setGc30(RichGridCell gc30) {
        this.gc30 = gc30;
    }

    public RichGridCell getGc30() {
        return gc30;
    }

    public void setGc31(RichGridCell gc31) {
        this.gc31 = gc31;
    }

    public RichGridCell getGc31() {
        return gc31;
    }

    public void setGc32(RichGridCell gc32) {
        this.gc32 = gc32;
    }

    public RichGridCell getGc32() {
        return gc32;
    }

    public void setGc33(RichGridCell gc33) {
        this.gc33 = gc33;
    }

    public RichGridCell getGc33() {
        return gc33;
    }

    public void setGc34(RichGridCell gc34) {
        this.gc34 = gc34;
    }

    public RichGridCell getGc34() {
        return gc34;
    }

    public void setGc35(RichGridCell gc35) {
        this.gc35 = gc35;
    }

    public RichGridCell getGc35() {
        return gc35;
    }

    public void setGr6(RichGridRow gr6) {
        this.gr6 = gr6;
    }

    public RichGridRow getGr6() {
        return gr6;
    }

    public void setGc36(RichGridCell gc36) {
        this.gc36 = gc36;
    }

    public RichGridCell getGc36() {
        return gc36;
    }

    public void setGc37(RichGridCell gc37) {
        this.gc37 = gc37;
    }

    public RichGridCell getGc37() {
        return gc37;
    }

    public void setGc38(RichGridCell gc38) {
        this.gc38 = gc38;
    }

    public RichGridCell getGc38() {
        return gc38;
    }

    public void setGc39(RichGridCell gc39) {
        this.gc39 = gc39;
    }

    public RichGridCell getGc39() {
        return gc39;
    }

    public void setGc40(RichGridCell gc40) {
        this.gc40 = gc40;
    }

    public RichGridCell getGc40() {
        return gc40;
    }

    public void setGc41(RichGridCell gc41) {
        this.gc41 = gc41;
    }

    public RichGridCell getGc41() {
        return gc41;
    }

    public void setGc42(RichGridCell gc42) {
        this.gc42 = gc42;
    }

    public RichGridCell getGc42() {
        return gc42;
    }

    public void setGr7(RichGridRow gr7) {
        this.gr7 = gr7;
    }

    public RichGridRow getGr7() {
        return gr7;
    }

    public void setGc43(RichGridCell gc43) {
        this.gc43 = gc43;
    }

    public RichGridCell getGc43() {
        return gc43;
    }

    public void setGc44(RichGridCell gc44) {
        this.gc44 = gc44;
    }

    public RichGridCell getGc44() {
        return gc44;
    }

    public void setGc45(RichGridCell gc45) {
        this.gc45 = gc45;
    }

    public RichGridCell getGc45() {
        return gc45;
    }

    public void setGc46(RichGridCell gc46) {
        this.gc46 = gc46;
    }

    public RichGridCell getGc46() {
        return gc46;
    }

    public void setGc47(RichGridCell gc47) {
        this.gc47 = gc47;
    }

    public RichGridCell getGc47() {
        return gc47;
    }

    public void setGc48(RichGridCell gc48) {
        this.gc48 = gc48;
    }

    public RichGridCell getGc48() {
        return gc48;
    }

    public void setGc49(RichGridCell gc49) {
        this.gc49 = gc49;
    }

    public RichGridCell getGc49() {
        return gc49;
    }

    public void setGr8(RichGridRow gr8) {
        this.gr8 = gr8;
    }

    public RichGridRow getGr8() {
        return gr8;
    }

    public void setGc50(RichGridCell gc50) {
        this.gc50 = gc50;
    }

    public RichGridCell getGc50() {
        return gc50;
    }

    public void setGc51(RichGridCell gc51) {
        this.gc51 = gc51;
    }

    public RichGridCell getGc51() {
        return gc51;
    }

    public void setGc52(RichGridCell gc52) {
        this.gc52 = gc52;
    }

    public RichGridCell getGc52() {
        return gc52;
    }

    public void setGc53(RichGridCell gc53) {
        this.gc53 = gc53;
    }

    public RichGridCell getGc53() {
        return gc53;
    }

    public void setGc54(RichGridCell gc54) {
        this.gc54 = gc54;
    }

    public RichGridCell getGc54() {
        return gc54;
    }

    public void setGc55(RichGridCell gc55) {
        this.gc55 = gc55;
    }

    public RichGridCell getGc55() {
        return gc55;
    }

    public void setGc56(RichGridCell gc56) {
        this.gc56 = gc56;
    }

    public RichGridCell getGc56() {
        return gc56;
    }

    public void setGr9(RichGridRow gr9) {
        this.gr9 = gr9;
    }

    public RichGridRow getGr9() {
        return gr9;
    }

    public void setGc57(RichGridCell gc57) {
        this.gc57 = gc57;
    }

    public RichGridCell getGc57() {
        return gc57;
    }

    public void setGc58(RichGridCell gc58) {
        this.gc58 = gc58;
    }

    public RichGridCell getGc58() {
        return gc58;
    }

    public void setGc59(RichGridCell gc59) {
        this.gc59 = gc59;
    }

    public RichGridCell getGc59() {
        return gc59;
    }

    public void setGc60(RichGridCell gc60) {
        this.gc60 = gc60;
    }

    public RichGridCell getGc60() {
        return gc60;
    }

    public void setGc61(RichGridCell gc61) {
        this.gc61 = gc61;
    }

    public RichGridCell getGc61() {
        return gc61;
    }

    public void setGc62(RichGridCell gc62) {
        this.gc62 = gc62;
    }

    public RichGridCell getGc62() {
        return gc62;
    }

    public void setGc63(RichGridCell gc63) {
        this.gc63 = gc63;
    }

    public RichGridCell getGc63() {
        return gc63;
    }

    public void setGr10(RichGridRow gr10) {
        this.gr10 = gr10;
    }

    public RichGridRow getGr10() {
        return gr10;
    }

    public void setGc64(RichGridCell gc64) {
        this.gc64 = gc64;
    }

    public RichGridCell getGc64() {
        return gc64;
    }

    public void setGc65(RichGridCell gc65) {
        this.gc65 = gc65;
    }

    public RichGridCell getGc65() {
        return gc65;
    }

    public void setGc66(RichGridCell gc66) {
        this.gc66 = gc66;
    }

    public RichGridCell getGc66() {
        return gc66;
    }

    public void setGc67(RichGridCell gc67) {
        this.gc67 = gc67;
    }

    public RichGridCell getGc67() {
        return gc67;
    }

    public void setGc68(RichGridCell gc68) {
        this.gc68 = gc68;
    }

    public RichGridCell getGc68() {
        return gc68;
    }

    public void setGc69(RichGridCell gc69) {
        this.gc69 = gc69;
    }

    public RichGridCell getGc69() {
        return gc69;
    }

    public void setGc70(RichGridCell gc70) {
        this.gc70 = gc70;
    }

    public RichGridCell getGc70() {
        return gc70;
    }

    public void setGr11(RichGridRow gr11) {
        this.gr11 = gr11;
    }

    public RichGridRow getGr11() {
        return gr11;
    }

    public void setGc71(RichGridCell gc71) {
        this.gc71 = gc71;
    }

    public RichGridCell getGc71() {
        return gc71;
    }

    public void setGc72(RichGridCell gc72) {
        this.gc72 = gc72;
    }

    public RichGridCell getGc72() {
        return gc72;
    }

    public void setGc73(RichGridCell gc73) {
        this.gc73 = gc73;
    }

    public RichGridCell getGc73() {
        return gc73;
    }

    public void setGc74(RichGridCell gc74) {
        this.gc74 = gc74;
    }

    public RichGridCell getGc74() {
        return gc74;
    }

    public void setGc75(RichGridCell gc75) {
        this.gc75 = gc75;
    }

    public RichGridCell getGc75() {
        return gc75;
    }

    public void setGc76(RichGridCell gc76) {
        this.gc76 = gc76;
    }

    public RichGridCell getGc76() {
        return gc76;
    }

    public void setGc77(RichGridCell gc77) {
        this.gc77 = gc77;
    }

    public RichGridCell getGc77() {
        return gc77;
    }

    public void setGr12(RichGridRow gr12) {
        this.gr12 = gr12;
    }

    public RichGridRow getGr12() {
        return gr12;
    }

    public void setGc78(RichGridCell gc78) {
        this.gc78 = gc78;
    }

    public RichGridCell getGc78() {
        return gc78;
    }

    public void setGc79(RichGridCell gc79) {
        this.gc79 = gc79;
    }

    public RichGridCell getGc79() {
        return gc79;
    }

    public void setGc80(RichGridCell gc80) {
        this.gc80 = gc80;
    }

    public RichGridCell getGc80() {
        return gc80;
    }

    public void setGc81(RichGridCell gc81) {
        this.gc81 = gc81;
    }

    public RichGridCell getGc81() {
        return gc81;
    }

    public void setGc82(RichGridCell gc82) {
        this.gc82 = gc82;
    }

    public RichGridCell getGc82() {
        return gc82;
    }

    public void setGc83(RichGridCell gc83) {
        this.gc83 = gc83;
    }

    public RichGridCell getGc83() {
        return gc83;
    }

    public void setGc84(RichGridCell gc84) {
        this.gc84 = gc84;
    }

    public RichGridCell getGc84() {
        return gc84;
    }

    public void setGr13(RichGridRow gr13) {
        this.gr13 = gr13;
    }

    public RichGridRow getGr13() {
        return gr13;
    }

    public void setGc85(RichGridCell gc85) {
        this.gc85 = gc85;
    }

    public RichGridCell getGc85() {
        return gc85;
    }

    public void setGc86(RichGridCell gc86) {
        this.gc86 = gc86;
    }

    public RichGridCell getGc86() {
        return gc86;
    }

    public void setGc87(RichGridCell gc87) {
        this.gc87 = gc87;
    }

    public RichGridCell getGc87() {
        return gc87;
    }

    public void setGc88(RichGridCell gc88) {
        this.gc88 = gc88;
    }

    public RichGridCell getGc88() {
        return gc88;
    }

    public void setGc89(RichGridCell gc89) {
        this.gc89 = gc89;
    }

    public RichGridCell getGc89() {
        return gc89;
    }

    public void setGc90(RichGridCell gc90) {
        this.gc90 = gc90;
    }

    public RichGridCell getGc90() {
        return gc90;
    }

    public void setGc91(RichGridCell gc91) {
        this.gc91 = gc91;
    }

    public RichGridCell getGc91() {
        return gc91;
    }

    public void setGr14(RichGridRow gr14) {
        this.gr14 = gr14;
    }

    public RichGridRow getGr14() {
        return gr14;
    }

    public void setGc92(RichGridCell gc92) {
        this.gc92 = gc92;
    }

    public RichGridCell getGc92() {
        return gc92;
    }

    public void setGc93(RichGridCell gc93) {
        this.gc93 = gc93;
    }

    public RichGridCell getGc93() {
        return gc93;
    }

    public void setGc94(RichGridCell gc94) {
        this.gc94 = gc94;
    }

    public RichGridCell getGc94() {
        return gc94;
    }

    public void setGc95(RichGridCell gc95) {
        this.gc95 = gc95;
    }

    public RichGridCell getGc95() {
        return gc95;
    }

    public void setGc96(RichGridCell gc96) {
        this.gc96 = gc96;
    }

    public RichGridCell getGc96() {
        return gc96;
    }

    public void setGc97(RichGridCell gc97) {
        this.gc97 = gc97;
    }

    public RichGridCell getGc97() {
        return gc97;
    }

    public void setGc98(RichGridCell gc98) {
        this.gc98 = gc98;
    }

    public RichGridCell getGc98() {
        return gc98;
    }

    public void setGr15(RichGridRow gr15) {
        this.gr15 = gr15;
    }

    public RichGridRow getGr15() {
        return gr15;
    }

    public void setGc99(RichGridCell gc99) {
        this.gc99 = gc99;
    }

    public RichGridCell getGc99() {
        return gc99;
    }

    public void setGc100(RichGridCell gc100) {
        this.gc100 = gc100;
    }

    public RichGridCell getGc100() {
        return gc100;
    }

    public void setGc101(RichGridCell gc101) {
        this.gc101 = gc101;
    }

    public RichGridCell getGc101() {
        return gc101;
    }

    public void setGc102(RichGridCell gc102) {
        this.gc102 = gc102;
    }

    public RichGridCell getGc102() {
        return gc102;
    }

    public void setGc103(RichGridCell gc103) {
        this.gc103 = gc103;
    }

    public RichGridCell getGc103() {
        return gc103;
    }

    public void setGc104(RichGridCell gc104) {
        this.gc104 = gc104;
    }

    public RichGridCell getGc104() {
        return gc104;
    }

    public void setGc105(RichGridCell gc105) {
        this.gc105 = gc105;
    }

    public RichGridCell getGc105() {
        return gc105;
    }

    public void setGr16(RichGridRow gr16) {
        this.gr16 = gr16;
    }

    public RichGridRow getGr16() {
        return gr16;
    }

    public void setGc106(RichGridCell gc106) {
        this.gc106 = gc106;
    }

    public RichGridCell getGc106() {
        return gc106;
    }

    public void setGc107(RichGridCell gc107) {
        this.gc107 = gc107;
    }

    public RichGridCell getGc107() {
        return gc107;
    }

    public void setGc108(RichGridCell gc108) {
        this.gc108 = gc108;
    }

    public RichGridCell getGc108() {
        return gc108;
    }

    public void setGc109(RichGridCell gc109) {
        this.gc109 = gc109;
    }

    public RichGridCell getGc109() {
        return gc109;
    }

    public void setGc110(RichGridCell gc110) {
        this.gc110 = gc110;
    }

    public RichGridCell getGc110() {
        return gc110;
    }

    public void setGc111(RichGridCell gc111) {
        this.gc111 = gc111;
    }

    public RichGridCell getGc111() {
        return gc111;
    }

    public void setGc112(RichGridCell gc112) {
        this.gc112 = gc112;
    }

    public RichGridCell getGc112() {
        return gc112;
    }
}
