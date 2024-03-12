package dtu.travel;

public class Station {
    private String name;
    public Station(String name){
        this.name = name;
    }

    public ResponseMessage checkin(TravelCard card) {
        ResponseMessage response = new ResponseMessage();
        if(!card.isCheckedin()){
            if(isEnoughBalance(card)){
                card.setCheckinStatus(true);
                response.setMessage("checked in");
            } else {
                response.setMessage("the balance is not enough");
            }
        } else {
            response.setMessage("already checked in");
        }
        return response;
    }

    private boolean isEnoughBalance(TravelCard card) {
        return card.getBalance()>50;
    }
}
