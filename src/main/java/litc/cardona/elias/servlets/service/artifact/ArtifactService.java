package litc.cardona.elias.servlets.service.artifact;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.gson.Gson;

import litc.cardona.elias.servlets.entity.component.Component;


public class ArtifactService {
    private AtomicInteger key;
    private ConcurrentMap<Integer, Component> arts;

    public ArtifactService() {
        this.arts = new ConcurrentHashMap<>();
        key = new AtomicInteger();
    }

    /*public String findAll() {
        List<Component> list = new ArrayList<>(this.arts.values());
        return this.toJson(list);
    }*/


    /*  Create student from the json payload  */
    public boolean createComponent(String jsonPayload) {
        if (jsonPayload == null) {
          return false;
		}

        Gson gson = new Gson();
        try {
            Component comp = (Component) gson.fromJson(jsonPayload, Component.class);
            if (comp != null) {
                return this.addComponent(comp);
            }
        }
        catch (Exception e) {}
        return false;
    }

    public Float calculateTotalCost() {
        float totalCost = 0;
        for (Component comp : this.arts.values()) {
            totalCost += comp.getPrice() * comp.getQuantity();
        }
        return totalCost;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////// inner methods ///////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private String toJson(Object list) {
        if (list == null) {
          return null;
		}
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(list);
        }
        catch (Exception e) {}
        return json;
    }

    private boolean addComponent(Component compv) {
        Integer id = key.incrementAndGet();
        this.arts.put(id, compv);
        return true;
    }
}





