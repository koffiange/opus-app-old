package ci.siracide.opus.backing;

import ci.siracide.opus.dto.Utilisateur;
import ci.siracide.opus.service.UtilisateurService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Named
@ViewScoped
public class EnrolementBacking extends BaseBacking{
    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Inject
    UtilisateurService utilisateurService;

    @Getter @Setter
    private List<Utilisateur> utilisateurList;

    @PostConstruct
    private void init(){
        utilisateurList = utilisateurService.listAll();
    }

    public void handleReturn(SelectEvent event){
        utilisateurList = utilisateurService.listAll();
        if (event != null)
            showSuccess();
    }

    /*
    public void onRowSelect(SelectEvent<Acte> event){
        LOG.info("Row selected! Uuid : "+event.getObject().getUuid());
    }
     */

    public void openCreateDialog(){
        Map<String,Object> options = getLevelOneDialogOptions();
        options.put("closable", true);
        options.replace("height", "95vh");
        options.replace("width", "95vw");
        PrimeFaces.current().dialog().openDynamic("utilisateur-create-dlg", options, null);
    }

    public void openUpdateDialog(String uuid){
        Map<String,Object> options = getLevelTwoDialogOptions();
        options.replace("height", "95vh");
        options.replace("width", "95vh");
        Map<String, List<String>> params = new HashMap<>();
        List<String> uuidList = new ArrayList<>();
        uuidList.add(uuid);
        params.put("uuid", uuidList);
        PrimeFaces.current().dialog().openDynamic("utilisateur-update-dlg", options, params);
    }

    public void delete(String uuid){
        try{
            utilisateurService.delete(uuid);
            this.init();
            showSuccess();
        } catch (Exception e){
            showError(e.getMessage());
        }
    }
}
