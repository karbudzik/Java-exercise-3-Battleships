import java.util.regex.Pattern;

public class GameController {

    private View view;
    
    public GameController(){ 
        view = new View();
    }

    public int[] getCoordinates(String coordinatesAsString){
        String[] coordinateAsArray = new String[] {};
        boolean isIncorrectInput = true;
        
        while(isIncorrectInput){
            coordinatesAsString = view.inputFromUser("Please insert coordinates to attack").toUpperCase();
            if(checkCoordinates(coordinatesAsString)){
                isIncorrectInput = false;
                coordinateAsArray = transformToCorrectCoordinates(coordinatesAsString);
            }
        }
        int[] coordinatesAsInt = translateFromStringToCoordinates(coordinateAsArray);
        
        return coordinatesAsInt;
    }

    private boolean checkCoordinates(String coordinatesAsString){
        Pattern pattern = Pattern.compile("([1-9][0]?[A-J])|([A-J][0-9][0]?)");
        
        if (coordinatesAsString == null) {
            return false; 
        }
        return pattern.matcher(coordinatesAsString).matches();
    }

    private String[] transformToCorrectCoordinates(String coordinatesAsString){
        Pattern pattern = Pattern.compile("([1-9][0]?[A-J])");
        String[] arrayCoordinates = new String[] {"", ""};

        if(pattern.matcher(coordinatesAsString).matches()){
            arrayCoordinates[0] = (coordinatesAsString.length() > 2) ? coordinatesAsString.substring(0,2) : coordinatesAsString.substring(0,1);
            arrayCoordinates[1] = (coordinatesAsString.length() > 2) ? coordinatesAsString.substring(2) : coordinatesAsString.substring(1);
        } else{
            arrayCoordinates[0] = coordinatesAsString.substring(0,1);
            arrayCoordinates[1] = coordinatesAsString.length() > 2 ? coordinatesAsString.substring(1,3) : coordinatesAsString.substring(1,2);
        }        
        return arrayCoordinates;
    }
    
    private int[] translateFromStringToCoordinates(String[] coordinatesAsString){
        String[] alfabet = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        int lenghtArrayOfAlfabet = alfabet.length;
        int X_INDEX = 1;
        int Y_INDEX = 0;
        int[] coordinatesAsInt = new int[] {-1, -1};
        String xAsString = coordinatesAsString[X_INDEX];
        String yAsString = coordinatesAsString[Y_INDEX];
        
        if(!isNumeric(xAsString)){
            String temp = xAsString;
            xAsString = yAsString;
            yAsString = temp;
        }
        coordinatesAsInt[X_INDEX] = Integer.parseInt(xAsString) - 1;
        for(int index = 0; index < lenghtArrayOfAlfabet; index++){
            if(yAsString.equals(alfabet[index])){
                coordinatesAsInt[Y_INDEX] = index;
            }
        }
        return coordinatesAsInt;
    }

    private boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("\\d+");

        if (strNum == null) {
            return false; 
        }
        return pattern.matcher(strNum).matches();
    }
}