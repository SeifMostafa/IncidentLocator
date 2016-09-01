package view.util ; 

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.model.binding.DCParameter;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.ControlBinding;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.DMLConstraintException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ValidationException;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlListBinding;
import oracle.jbo.uicli.binding.JUCtrlValueBinding;
import oracle.jbo.uicli.binding.JUCtrlValueBindingRef;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

/**
 * Provides various utility methods that are handy to
 * have around when working with ADF.
 */
public class ADFUtil {
    /**
     * When a bounded task flow manages a transaction (marked as
     * requires-transaction, requires-new-transaction, or
     * requires- existing-transaction), then the task flow must
     * issue any commits or rollbacks that are needed.
     * This is essentially to keep the state of the transaction
     * that the task flow understands in synch with the state
     * of the transaction in the ADFbc layer.
     * Use this method to issue a commit in the middle of a task
     * flow while staying in the task flow.
     */
    public static void saveAndContinue() {
        Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        BindingContext context = (BindingContext)sessionMap.get(BindingContext.CONTEXT_ID);
        String currentFrameName = context.getCurrentDataControlFrame();
        DataControlFrame dcFrame = context.findDataControlFrame(currentFrameName);
        dcFrame.commit();
        dcFrame.beginTransaction(null);
    }

    /**
     * Programmatic Refresh for current Page .
     */
    public static void refreshPage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String refreshpage = fc.getViewRoot().getViewId();
        ViewHandler ViewH = fc.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fc, refreshpage);
        UIV.setViewId(refreshpage);
        fc.setViewRoot(UIV);
    }

    /**
     * Programmatic evaluation of EL.
     *
     * @param el EL to evaluate
     * @return Result of the evaluation
     */
    public static Object evaluateEL(String el) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);
        return exp.getValue(elContext);
    }

    public static void addMessage(UIComponent comp, ValidationException ve) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setDetail(ve.getMessage());
        message.setSummary(ve.getMessage());
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        if (comp != null)
            context.addMessage(comp.getClientId(context), message);
        else
            context.addMessage(null, message);
    }

    public static void addMessage(UIComponent comp, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setDetail(msg);
        //   message.setSummary(ve.getMessage());
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        if (comp != null)
            context.addMessage(comp.getClientId(context), message);
        else
            context.addMessage(null, message);
    }

    /**
     * Programmatic invocation of a method that an EL evaluates
     * to. The method must not take any parameters.
     *
     * @param el EL of the method to invoke
     * @return Object that the method returns
     */
    public static Object invokeEL(String el) {
        return invokeEL(el, new Class[0], new Object[0]);
    }

    public static Object invokeELException(String el) throws DMLConstraintException {
        return invokeEL(el, new Class[0], new Object[0]);
    }

    /**
     * Programmatic invocation of a method that an EL evaluates to.
     *
     * @param el EL of the method to invoke
     * @param paramTypes Array of Class defining the types of the
     * parameters
     * @param params Array of Object defining the values of the
     * parametrs
     * @return Object that the method returns
     */
    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(elContext, el, Object.class, paramTypes);
        return exp.invoke(elContext, params);
    }

    /**
     * Sets a value into an EL object. Provides similar
     * functionality to
     * the &lt;af:setActionListener&gt; tag, except the
     * <code>from</code> is
     * not an EL. You can get similar behavior by using the
     * following...<br>
     * <code>setEL(<b>to</b>, evaluateEL(<b>from</b>))</code>
     *
     * @param el EL object to assign a value
     * @param val Value to assign
     */
    public static void setEL(String el, Object val) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);
        exp.setValue(elContext, val);
    }

    /**
     * Methode to get value or any attribut of lookUp table which
     * <br>generate selectOneChoice (LOV)
     * @param index of selected item on selectOneChoice
     * @param bindingName of selectOneChoice
     * @param attributeName on lookup object
     * @return value of attribute
     */
    public static Object getSelectOneChoiceValue(String index, String bindingName, String attributeName) {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding listBinding = (JUCtrlListBinding)bindings.get(bindingName);
        Object value = null;
        if (index != null && !index.equals("")) {
            if (listBinding != null) {
                listBinding.setSelectedIndex(Integer.parseInt(index));
                Row selectedValue = listBinding.getSelectedValue() instanceof Row ? (Row)listBinding.getSelectedValue() : null;
                if (selectedValue != null && selectedValue.getAttributeNames() != null && selectedValue.getAttributeNames().length != 0) {
                    value = selectedValue.getAttribute(attributeName).toString();
                }
                return value;
            }
        } else {
            return value;
        }
        return value;
    }
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ADFUtil.class);

    /**
     * Get application module for an application module data control by name.
     * @param name application module data control name
     * @return ApplicationModule
     */
    public static ApplicationModule getApplicationModuleForDataControl(String name) {
        return (ApplicationModule)JSFUtils.resolveExpression("#{data." + name + ".dataProvider}");
    }

    /**
     * A convenience method for getting the value of a bound attribute in the
     * current page context programatically.
     * @param attributeName of the bound value in the pageDef
     * @return value of the attribute
     */
    public static Object getBoundAttributeValue(String attributeName) {
        return findControlBinding(attributeName).getInputValue();
    }

    /**
     * A convenience method for setting the value of a bound attribute in the
     * context of the current page.
     * @param attributeName of the bound value in the pageDef
     * @param value to set
     */
    public static void setBoundAttributeValue(String attributeName, Object value) {
        findControlBinding(attributeName).setInputValue(value);
    }

    /**
     * Returns the evaluated value of a pageDef parameter.
     * @param pageDefName reference to the page definition file of the page with the parameter
     * @param parameterName name of the pagedef parameter
     * @return evaluated value of the parameter as a String
     */
    public static Object getPageDefParameterValue(String pageDefName, String parameterName) {
        BindingContainer bindings = findBindingContainer(pageDefName);
        DCParameter param = ((DCBindingContainer)bindings).findParameter(parameterName);
        return param.getValue();
    }

    /**
     * Convenience method to find a DCControlBinding as an AttributeBinding
     * to get able to then call getInputValue() or setInputValue() on it.
     * @param bindingContainer binding container
     * @param attributeName name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public static AttributeBinding findControlBinding(BindingContainer bindingContainer, String attributeName) {
        if (attributeName != null) {
            if (bindingContainer != null) {
                ControlBinding ctrlBinding = bindingContainer.getControlBinding(attributeName);
                if (ctrlBinding instanceof AttributeBinding) {
                    return (AttributeBinding)ctrlBinding;
                }
            }
        }
        return null;
    }

    /**
     * Convenience method to find a DCControlBinding as a JUCtrlValueBinding
     * to get able to then call getInputValue() or setInputValue() on it.
     * @param attributeName name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public static AttributeBinding findControlBinding(String attributeName) {
        return findControlBinding(getBindingContainer(), attributeName);
    }

    /**
     * Return the current page's binding container.
     * @return the current page's binding container
     */
    public static BindingContainer getBindingContainer() {
        return (BindingContainer)JSFUtils.resolveExpression("#{bindings}");
    }

    /**
     * Return the Binding Container as a DCBindingContainer.
     * @return current binding container as a DCBindingContainer
     */
    public static DCBindingContainer getDCBindingContainer() {
        return (DCBindingContainer)getBindingContainer();
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName name of the value attribute to use
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsForIterator(String iteratorName, String valueAttrName, String displayAttrName) {
        return selectItemsForIterator(findIterator(iteratorName), valueAttrName, displayAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with description.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName name of the value attribute to use
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute to use for description
     * @return ADF Faces SelectItem for an iterator binding with description
     */
    public static List<SelectItem> selectItemsForIterator(String iteratorName, String valueAttrName, String displayAttrName, String descriptionAttrName) {
        return selectItemsForIterator(findIterator(iteratorName), valueAttrName, displayAttrName, descriptionAttrName);
    }

    /**
     * Get List of attribute values for an iterator.
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName value attribute to use
     * @return List of attribute values for an iterator
     */
    public static List attributeListForIterator(String iteratorName, String valueAttrName) {
        return attributeListForIterator(findIterator(iteratorName), valueAttrName);
    }

    /**
     * Get List of Key objects for rows in an iterator.
     * @param iteratorName iterabot binding name
     * @return List of Key objects for rows
     */
    public static List<Key> keyListForIterator(String iteratorName) {
        return keyListForIterator(findIterator(iteratorName));
    }

    /**
     * Get List of Key objects for rows in an iterator.
     * @param iter iterator binding
     * @return List of Key objects for rows
     */
    public static List<Key> keyListForIterator(DCIteratorBinding iter) {
        List<Key> attributeList = new ArrayList<Key>();
        iter.setRangeSize(-1);
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(r.getKey());
        }
        return attributeList;
    }

    /**
     * Get List of Key objects for rows in an iterator using key attribute.
     * @param iteratorName iterator binding name
     * @param keyAttrName name of key attribute to use
     * @return List of Key objects for rows
     */
    public static List<Key> keyAttrListForIterator(String iteratorName, String keyAttrName) {
        return keyAttrListForIterator(findIterator(iteratorName), keyAttrName);
    }

    /**
     * Get List of Key objects for rows in an iterator using key attribute.
     *
     * @param iter iterator binding
     * @param keyAttrName name of key attribute to use
     * @return List of Key objects for rows
     */
    public static List<Key> keyAttrListForIterator(DCIteratorBinding iter, String keyAttrName) {
        List<Key> attributeList = new ArrayList<Key>();
        iter.setRangeSize(-1);
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(new Key(new Object[] { r.getAttribute(keyAttrName) }));
        }
        return attributeList;
    }

    /**
     * Get a List of attribute values for an iterator.
     *
     * @param iter iterator binding
     * @param valueAttrName name of value attribute to use
     * @return List of attribute values
     */
    public static List attributeListForIterator(DCIteratorBinding iter, String valueAttrName) {
        List attributeList = new ArrayList();
        iter.setRangeSize(-1);
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(r.getAttribute(valueAttrName));
        }
        return attributeList;
    }

    /**
     * Find an iterator binding in the current binding container by name.
     * @param name iterator binding name
     * @return iterator binding
     */
    public static DCIteratorBinding findIterator(String name) {
        DCIteratorBinding iter = getDCBindingContainer().findIteratorBinding(name);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + name + "' not found");
        }
        return iter;
    }

    public static DCIteratorBinding findIteratorByName(String iterName) {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
        DCIteratorBinding iter = bindingsImpl.findIteratorBinding(iterName);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + iterName + "' not found");
        }
        return iter;
    }

    public static DCIteratorBinding findIterator(String bindingContainer, String iterator) {
        DCBindingContainer bindings = (DCBindingContainer)JSFUtils.resolveExpression("#{" + bindingContainer + "}");
        if (bindings == null) {
            throw new RuntimeException("Binding container '" + bindingContainer + "' not found");
        }
        DCIteratorBinding iter = bindings.findIteratorBinding(iterator);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + iterator + "' not found");
        }
        return iter;
    }

    public static JUCtrlValueBinding findCtrlBinding(String name) {
        JUCtrlValueBinding rowBinding = (JUCtrlValueBinding)getDCBindingContainer().findCtrlBinding(name);
        if (rowBinding == null) {
            throw new RuntimeException("CtrlBinding " + name + "' not found");
        }
        return rowBinding;
    }

    /**
     * Find an operation binding in the current binding container by name.
     *
     * @param name operation binding name
     * @return operation binding
     */
    public static OperationBinding findOperation(String name) {
        OperationBinding op = getDCBindingContainer().getOperationBinding(name);
        if (op == null) {
            throw new RuntimeException("Operation '" + name + "' not found");
        }
        return op;
    }

    /**
     * Find an operation binding in the current binding container by name.
     *
     * @param bindingContianer binding container name
     * @param opName operation binding name
     * @return operation binding
     */
    public static OperationBinding findOperation(String bindingContianer, String opName) {
        DCBindingContainer bindings = (DCBindingContainer)JSFUtils.resolveExpression("#{" + bindingContianer + "}");
        if (bindings == null) {
            throw new RuntimeException("Binding container '" + bindingContianer + "' not found");
        }
        OperationBinding op = bindings.getOperationBinding(opName);
        if (op == null) {
            throw new RuntimeException("Operation '" + opName + "' not found");
        }
        return op;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with description.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param valueAttrName name of value attribute to use for key
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with description
     */
    public static List<SelectItem> selectItemsForIterator(DCIteratorBinding iter, String valueAttrName, String displayAttrName, String descriptionAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        iter.setRangeSize(-1);
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName), (String)r.getAttribute(displayAttrName), (String)r.getAttribute(descriptionAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param valueAttrName name of value attribute to use for key
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsForIterator(DCIteratorBinding iter, String valueAttrName, String displayAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        iter.setRangeSize(-1);
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName).toString(), (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsByKeyForIterator(String iteratorName, String displayAttrName) {
        return selectItemsByKeyForIterator(findIterator(iteratorName), displayAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with discription.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with discription
     */
    public static List<SelectItem> selectItemsByKeyForIterator(String iteratorName, String displayAttrName, String descriptionAttrName) {
        return selectItemsByKeyForIterator(findIterator(iteratorName), displayAttrName, descriptionAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with discription.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with discription
     */
    public static List<SelectItem> selectItemsByKeyForIterator(DCIteratorBinding iter, String displayAttrName, String descriptionAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        iter.setRangeSize(-1);
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getKey(), (String)r.getAttribute(displayAttrName), (String)r.getAttribute(descriptionAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return List of ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsByKeyForIterator(DCIteratorBinding iter, String displayAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        iter.setRangeSize(-1);
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getKey(), (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }

    /**
     * Find the BindingContainer for a page definition by name.
     *
     * Typically used to refer eagerly to page definition parameters. It is
     * not best practice to reference or set bindings in binding containers
     * that are not the one for the current page.
     *
     * @param pageDefName name of the page defintion XML file to use
     * @return BindingContainer ref for the named definition
     */
    private static BindingContainer findBindingContainer(String pageDefName) {
        BindingContext bctx = getDCBindingContainer().getBindingContext();
        BindingContainer foundContainer = bctx.findBindingContainer(pageDefName);
        return foundContainer;
    }

    public static void printOperationBindingExceptions(List opList) {
        if (opList != null && !opList.isEmpty()) {
            for (Object error : opList) {
                LOGGER.severe(error.toString());
            }
        }
    }

    /**
     * Clear error msg
     * @param componentId
     */
    public static void clearErrorMsgs(String componentId) {
        if (componentId != null) {
            ExtendedRenderKitService service = Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
            String code = "AdfPage.PAGE.clearSubtreeMessages('" + componentId + "'); ";
            service.addScript(FacesContext.getCurrentInstance(), code);
        }
    }

    public static void showPopup(String popupId) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExtendedRenderKitService extRenderKitSrvc = Service.getRenderKitService(context, ExtendedRenderKitService.class);
        extRenderKitSrvc.addScript(context, "AdfPage.PAGE.findComponent('" + popupId + "').show();");
    }

    /**
     * Method to retrive row of select one choice
     * <br>if select one choice not LOV
     * @param index
     * @param iterName
     * @return Row of selected item of select on choice
     */
    public static Row getRowFromIterator(int index, String iterName) {
        DCIteratorBinding iter = findIteratorByName(iterName);
        Row currentRow = iter.getRowAtRangeIndex(index);
        return currentRow;
    }

    /**
     * Method do get current row in an iterator
     * @param iterName iterator name
     * @return current row in the iterator
     */
    public static Row getCurrentRowFromIterator(String iterName) {
        DCIteratorBinding iter = findIteratorByName(iterName);
        Row currentRow = iter.getCurrentRow();
        return currentRow;
    }

    /**
     * Method to delete current row from an iterator
     * @param iterName iterator name
     */
    public static void deleteCurrentRowFromIterator(String iterName) {
        getCurrentRowFromIterator(iterName).remove();
    }
    //    public static void createRowInIterator(String iterName) {
    //        // CreteInsert Id in Format = IteratorId + CreateInsert ; ie: ArticlConfsView2IteratorCreateInsert
    //        BindingContainer bindings =
    //            BindingContext.getCurrent().getCurrentBindingsEntry();
    //        OperationBinding operationBinding =
    //            bindings.getOperationBinding(iterName + "CreateInsert");
    //        Object result = operationBinding.execute();
    //        if (!operationBinding.getErrors().isEmpty()) {
    //        }
    //    }

    /**
     * Method to remove a specified row by row index from an iterator
     * @param iterName iterator name
     * @param rowIndex index of the row to be deleted
     */
    public static void deleteRowWithIndex(String iterName, int rowIndex) {
        DCIteratorBinding iter = setCurrentRowWithIndex(iterName, rowIndex);
        iter.getCurrentRow().remove();
    }

    /**
     * Method to set attribute at  a specified row by row index from an iterator with  given value
     * @param iterName iterator name
     * @param rowIndex index of the row to be deleted
     */
    public static void setAttributeRowWithIndex(String iterName, int rowIndex, String attributeName, Object val) {
        DCIteratorBinding iter = setCurrentRowWithIndex(iterName, rowIndex);
        iter.getCurrentRow().setAttribute(attributeName, val);
    }

    /**
     * Method to set the iterator current row with given index
     * @param iterName iterator name
     * @param rowIndex index of the row to be set
     * @return the iterator binding
     */
    public static DCIteratorBinding setCurrentRowWithIndex(String iterName, int rowIndex) {
        DCIteratorBinding iter = findIteratorByName(iterName);
        iter.setCurrentRowIndexInRange(rowIndex);
        return iter;
    }

    /**
     * Method to get Iterator Estimated Row Count
     * @param iterName iterator name
     * @return Long Row Count
     */
    public static long getEstimatedRowCountForIterator(String iterName) {
        DCIteratorBinding iter = findIteratorByName(iterName);
        return iter.getEstimatedRowCount();
    }

    /**
     * @param iterName
     */
    public static void executrQueryIterator(String iterName) {
        DCIteratorBinding iter = findIteratorByName(iterName);
        iter.executeQuery();
    }

    /**
     * Method to retrive Attribute Value of select one choice
     * @param index
     * @param iterName
     * @param attributeName
     * @return String Attribut Value
     */
    public static String getSelectedValue(int index, String iterName, String attributeName) {
        DCIteratorBinding iter = findIteratorByName(iterName);
        Row currentRow = iter.getRowAtRangeIndex(index);
        String selectedValue = null;
        if (currentRow != null)
            selectedValue = currentRow.getAttribute(attributeName) + "";
        return selectedValue;
    }

    /**
     * Method to do action as same as button to go to task flow next phase
     * @param actionString on TaskFlow
     */
    public void doActionManualy(String actionString) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, actionString);
    }

    /**
     * Method to get ArrayList of SelectItem from tree binding
     * @param treeBindingName is the tree binding name
     * @param displayAttribute is the name of the attribute to be displayed
     * @param valueAttribute is the name of attribute for the value
     * @return ArrayList of SelectItem of the tree binding
     */
    public static ArrayList<SelectItem> getSelectItemsFromTreeBinding(String treeBindingName, String displayAttribute, String valueAttribute) {
        BindingContainer bindings = getBindingContainer();
        JUCtrlHierBinding hierBinding = (JUCtrlHierBinding)bindings.get(treeBindingName);
        hierBinding.executeQuery();
        //The rangeSet, the list of queries entries, is of type JUCtrlValueBndingRef.
        List<JUCtrlValueBindingRef> displayDataList = hierBinding.getRangeSet();
        ArrayList<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (JUCtrlValueBindingRef displayData : displayDataList) {
            Row rw = displayData.getRow();
            selectItems.add(new SelectItem(rw.getAttribute(valueAttribute), (String)rw.getAttribute(displayAttribute)));
        }
        return selectItems;
    }

    /**
     * Method to get ArrayList of SelectItem from tree binding
     * used if there is no need or use for the value of the selected item
     * as in search forms, here the value will be the same as the display name
     * @param treeBindingName is the tree binding name
     * @param displayAttribute is the name of the attribute to be displayed
     * @return ArrayList of SelectItem of the tree binding
     */
    public static ArrayList<SelectItem> getSelectItemsFromTreeBinding(String treeBindingName, String displayAttribute) {
        BindingContainer bindings = getBindingContainer();
        JUCtrlHierBinding hierBinding = (JUCtrlHierBinding)bindings.get(treeBindingName);
        hierBinding.executeQuery();
        //The rangeSet, the list of queries entries, is of type JUCtrlValueBndingRef.
        List<JUCtrlValueBindingRef> displayDataList = hierBinding.getRangeSet();
        ArrayList<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (JUCtrlValueBindingRef displayData : displayDataList) {
            Row rw = displayData.getRow();
            selectItems.add(new SelectItem(rw.getAttribute(displayAttribute), (String)rw.getAttribute(displayAttribute)));
        }
        return selectItems;
    }

    /**
     * Shows the specified popup component and its contents
     * @param popupId is the clientId of the popup to be shown
     * clientId is derived from backing bean for the af:popup using getClientId method
     */
    public static void invokePopup(String popupId) {
        invokePopup(popupId, null, null);
    }

    public static void invokePopup(String popupId, String align, String alignId) {
        if (popupId != null) {
            ExtendedRenderKitService service = Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
            StringBuffer showPopup = new StringBuffer();
            showPopup.append("var hints = new Object();");
            //Add hints only if specified - see javadoc for AdfRichPopup js for details on valid values and behavior
            if (align != null && alignId != null) {
                showPopup.append("hints[AdfRichPopup.HINT_ALIGN] = " + align + ";");
                showPopup.append("hints[AdfRichPopup.HINT_ALIGN_ID] ='" + alignId + "';");
            }
            showPopup.append("var popupObj=AdfPage.PAGE.findComponent('" + popupId + "'); popupObj.show(hints);");
            service.addScript(FacesContext.getCurrentInstance(), showPopup.toString());
        }
    }

    public static String showMessage(String message, FacesMessage.Severity facesMessage) {
        FacesMessage fm = new FacesMessage(message);
        /**
             * set the type of the message.
             * Valid types: error, fatal,info,warning
             */
        fm.setSeverity(facesMessage);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }

    public static String PassParameterToBundle(String key, String[] param) {
        // Add event code here...
        String message = oracle.jbo.common.StringManager.getString("com.egabi.adsic.noc.services.view.bundle.NocViewControllerBundle", key, "No matching string found", param);
        System.out.println(message);
        return message;
    }

    public static void executeClientJavascript(String script) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExtendedRenderKitService service = Service.getRenderKitService(facesContext, ExtendedRenderKitService.class);
        service.addScript(facesContext, script);
    }

    public static String getAttributeValueFromIterator(int index, String iterName, String attributeName) {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
        DCIteratorBinding iter = bindingsImpl.findIteratorBinding(iterName);
        Row currentRow = iter.getRowAtRangeIndex(index);
        String selectedValue = null;
        if (currentRow != null)
            selectedValue = currentRow.getAttribute(attributeName) + "";
        return selectedValue;
    }

    public static void addConfirmationMessage(String confMessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setDetail(confMessage);
        message.setSummary("");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, message);
    }



    public static void addErrorMessage(String errorMessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setDetail(errorMessage);
        message.setSummary("ErrorX");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
    }
    /**
     * Locate an UIComponent in view root with its component id. Use a recursive way to achieve this.
     * Taken from http://www.jroller.com/page/mert?entry=how_to_find_a_uicomponent
     * @param id UIComponent id
     * @return UIComponent object
     */
    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            UIComponent root = facesContext.getViewRoot();
            component = findComponent(root, id);
        }
        return component;
    }

    /**
     * Locate an UIComponent from its root component.
     * Taken from http://www.jroller.com/page/mert?entry=how_to_find_a_uicomponent
     * @param base root Component (parent)
     * @param id UIComponent id
     * @return UIComponent object
     */
    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId()))
            return base;
        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent)childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }
}
