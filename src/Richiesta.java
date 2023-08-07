public class Richiesta {

    private String IAP;
    private String request;

    public Richiesta(String IAP, String request){
        this.IAP = IAP;
        this.request = request;
    }

    public String getIAP() {
        return IAP;
    }

    public String getRequest() {
        return request;
    }

    public void setIAP(String IAP) {
        this.IAP = IAP;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String toString(){
        return IAP+","+request;
    }
}
