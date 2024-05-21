package litc.cardona.elias.servlets.service.artifact;

import java.util.ArrayList;
import java.util.HashMap;

import litc.cardona.elias.servlets.entity.component.Component;
import litc.cardona.elias.servlets.entity.component.ComponentRequest;


public class ArtifactService {
    private HashMap<String, Float> componentes;
    private ArrayList<HashMap<String, Float>> artefactos;

    public ArtifactService() {
        this.componentes = new HashMap<>();
        this.artefactos = new ArrayList<>();
    }

    public boolean addComponents(ComponentRequest request) {
//        HashMap<String, Float> componentesAux = new HashMap<>();
        try {
            for (Component component : request.getComponents()) {
                String compName = component.getCname();
                float partialCost = component.getQuantity() * component.getPrice();

                this.componentes.put(compName, partialCost);
            }
            this.artefactos.add(this.componentes);
            return true;
        }
        catch (Exception e) {}
        
        return false;
    }

    public Float calculateTotalCost() {
        float totalCost = 0;
        for (HashMap<String, Float> artefacto : this.artefactos) {
            for (Float cost : artefacto.values()) {
                totalCost += cost;
            }
        }
        return totalCost;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////// inner methods ///////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////


}

