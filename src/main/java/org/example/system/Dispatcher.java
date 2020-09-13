package org.example.system;

import org.example.modelui.Request;
import org.example.modelui.Response;

public interface Dispatcher {

    Response processRequest(Request request);

}
