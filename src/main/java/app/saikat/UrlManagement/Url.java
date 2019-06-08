package app.saikat.UrlManagement;

import app.saikat.UrlManagement.RequestObjects.AddDevice;
import app.saikat.UrlManagement.ResponseObjects.CreatedDevice;
import java.lang.Class;
import java.lang.String;

/**
 * Generated class. Not to be modified directly
 */
public enum Url {
  /**
   * Generated from:- 
   * 	Class: "app.saikat.WaspberryServer.WebsocketServer.websocket.SocketController",
   * 	Method: "addDevice"
   */
  ADD_DEVICE("/addDevice", AddDevice.class, CreatedDevice.class),

  /**
   * Generated from:- 
   * 	Class: "app.saikat.WaspberryServer.WebsocketServer.websocket.NewCont",
   * 	Method: "test"
   */
  TEST("/test", AddDevice.class, CreatedDevice.class);

  private final String path;

  private final Class<?> requestClass;

  private final Class<?> responseClass;

  Url(String path, Class<?> requestClass, Class<?> responseClass) {
    this.path = path;
    this.requestClass = requestClass;
    this.responseClass = responseClass;
  }

  public String getPath() {
    return this.path;
  }

  public Class<?> getResponseClass() {
    return this.responseClass;
  }

  public Class<?> getRequestClass() {
    return this.requestClass;
  }
}
