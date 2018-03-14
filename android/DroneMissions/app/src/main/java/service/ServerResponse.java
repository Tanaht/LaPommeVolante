package service;

/**
 * Created by gbrossault on 07/02/18.
 */

public class ServerResponse {

    private String type;
    private String data;

    public ServerResponse(String type, String data){
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
