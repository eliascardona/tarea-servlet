package litc.cardona.elias.servlets.service.response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.gson.Gson;

import litc.cardona.elias.servlets.entity.response.ResponseInterface;


public class ResponseService {
    private AtomicInteger key;
    private ConcurrentMap<Integer, ResponseInterface> responseArr;
    private ResponseInterface respFmt;

    public ResponseService() {}

    public ResponseService(String argv) {
        this.responseArr = new ConcurrentHashMap<>();
        this.key = new AtomicInteger();
        this.respFmt = new ResponseInterface(argv);

        Integer id = key.incrementAndGet();
        this.responseArr.put(id, respFmt);
    }

    public String formatResponse() {
        List<ResponseInterface> list = new ArrayList<>(this.responseArr.values());
        // convert the list to json and return as string
        return this.toJson(list);
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

}






