package ci.siracide.opus.backing;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseBacking implements Serializable {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    protected static final String SUCCESS_MESSAGE = "Opération bien effectuée";
    protected static final String ERROR_MESSAGE = "Une erreur s'est produite";
    public void fictiveAction(){}

    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
    public ExternalContext getExternalContext(){
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public void addGlobalWarningMessage(String message){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, message ,"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void addGlobalMessage(FacesMessage msg){
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void addMessage(String clientId,FacesMessage msg){
        FacesContext.getCurrentInstance().addMessage(clientId, msg);
    }
    public void getMessage(String severite, String titre, String detail){
        FacesContext context = this.getFacesContext();
        FacesMessage message = null;
        switch(severite){
            case "info":
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, titre, detail);
                break;
            case "warn":
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, titre, detail);
                break;
            case "error":
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titre, detail);
                break;
            case "success":
                message = new FacesMessage(titre, detail);
                break;
        }
        context.addMessage(null, message);
    }
    public void putToFlash(String key,Object value){
        Flash flash = getExternalContext().getFlash();
        if(flash != null){
            getExternalContext().getFlash().put(key, value);
        }

    }
    public Object getFromFlash(String key){
        Flash flash = getExternalContext().getFlash();
        return (flash != null) ? getExternalContext().getFlash().get(key): null;
    }
    protected String getViewId(){
        return  getFacesContext().getViewRoot().getViewId();
    }

    protected Map<String,Object> getCutomDialogOptions(String width, String height){
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("width", "75vw");
        options.put("height", "65vh");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "95%");

        return options;
    }
    protected Map<String,Object> getLevelOneDialogOptions(){
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("width", "75vw");
        options.put("height", "65vh");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "95%");

        return options;
    }
    protected Map<String,Object> getLevelTwoDialogOptions(){
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("width", "65vw");
        options.put("height", "55vh");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "95%");
        return options;
    }
    protected Map<String,Object> getLevelThreeDialogOptions(){
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("width", "55vw");
        options.put("height", "45vh");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "95%");

        return options;
    }
    protected Map<String,Object> getLevelFourDialogOptions(){
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("width", "45vw");
        options.put("height", "45vh");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "95%");

        return options;
    }

    public void addGlobalErrorMessage(String message){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message ,"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    protected Map getRequestMap() {
        return getContext().getExternalContext().getRequestMap();
    }

    protected Map<String, String> getRequestParameterMap() {
        return getContext().getExternalContext().getRequestParameterMap();
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getContext().getExternalContext().getRequest();
    }

    protected HttpSession getSession() {
        return (HttpSession) getContext().getExternalContext().getSession(false);
    }

    protected String getAuthenticatedUser(){
        if(getContext().getExternalContext().getUserPrincipal().getName() != null){
            return getContext().getExternalContext().getUserPrincipal().getName();
        } else{
            return "ANONYMOUS_USER";
        }
    }

    /*
    public String getDeleteMessage(String code) {
        return Messages.getString("messages", "confirm_delete_message", new Object[]{code});
    }
     */

    public void closeSuccess(String message){
        PrimeFaces.current().dialog().closeDynamic(message);
    }
    public void closeSuccess(){
        PrimeFaces.current().dialog().closeDynamic(SUCCESS_MESSAGE);
    }
    public void closeError(String errorMessage){
        PrimeFaces.current().dialog().closeDynamic(errorMessage);
    }
    public void closeCancel(){
        PrimeFaces.current().dialog().closeDynamic("");
    }
    public void getFailedMessage(String errorMessage){
        LOG.log(Level.SEVERE, "--->> Error : {0}", errorMessage);
    }
    public void showSuccess(String message){
        getMessage("info", message, "");
    }public void showSuccess(){
        getMessage("info", SUCCESS_MESSAGE, "");
    }
    public void showError(String errorMessage){
        getMessage("error", ERROR_MESSAGE, errorMessage);
    }
    public void showError(){
        getMessage("error", ERROR_MESSAGE, "");
    }
    public void throwExistError(String errorMessage){
        LOG.log(Level.INFO, "--- errorMessage : {0}", errorMessage);
        getMessage("error", errorMessage, "");
    }


    // DATE UTIL

    public String displayFormatedDate(Date date){
        LocalDate ld = convertIntoLocaleDate(date);
        String pattern = "dd-MM-yyyy HH:mm:ss";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern, Locale.FRENCH);
        return dtf.format(ld);
    }

    public String displayFormatedDate(LocalDate date){
        String pattern = "dd-MM-yyyy";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern, Locale.FRENCH);
        return (date != null) ? dtf.format(date) : "";
    }

    public String displayFormatedDateTime(LocalDateTime date){
        String pattern = "dd-MM-yyyy HH:mm:ss";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern, Locale.FRENCH);
        return (date != null) ? dtf.format(date) : "";
    }

    public LocalDate convertIntoLocaleDate(Date d){
        Instant instant = d.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        return zonedDateTime.toLocalDate();
    }

    public Date convertStringIntoDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date d = format.parse(date.replace('/', '-'));
        return d;
    }

    public LocalDate convertStringIntoLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }



    // MONNEY UTIL
    public static String displayThousandSeparator(BigDecimal value){
        String pattern = "###,###.###";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }

    public static String displayThousandSeparator(Long value){
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.FRENCH);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(value);
    }
}
