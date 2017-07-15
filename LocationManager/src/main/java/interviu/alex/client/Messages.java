package interviu.alex.client;

import com.google.gwt.i18n.client.LocalizableResource.Generate;

@Generate(format = "com.google.gwt.i18n.server.PropertyCatalogFactory")
public interface Messages extends com.google.gwt.i18n.client.Messages {
  
  @DefaultMessage("Send")
  String sendButton();

  @DefaultMessage("Save")
  String savePlaces();
}
