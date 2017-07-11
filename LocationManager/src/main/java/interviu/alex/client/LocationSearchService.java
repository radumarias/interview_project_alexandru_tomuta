package interviu.alex.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import interviu.alex.shared.model.MyLocation;
import interviu.alex.shared.model.googleapi.Location;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("main")
public interface LocationSearchService extends RemoteService {
  MyLocation searchByCity(MyLocation location) throws IllegalArgumentException;
}
