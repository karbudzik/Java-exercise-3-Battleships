public class PlayerComp extends Player {

    public PlayerComp(String name){
        super();
        setName(name);
        setOcean(new Ocean());
        placeShips();
    }

    private void placeShips() {
        getOcean().addShip(0, 3, true, 2); 
        getOcean().addShip(4, 0, true, 3);  
        // placeholder to add algorithm to put all computer's ships on the board
    }

}
