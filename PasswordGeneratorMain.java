import java.util.*; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PasswordGeneratorMain {

	
	static String generatePassword(int passLenght, boolean includeLowercase,boolean includeUppercase,boolean includeDigits,boolean includeSpecialchars,String customSpecialChars) {
		final String  lowercase= "abcdefghijklmnopqrstuvwxyz";
		final String uppercase ="ABCDEFGHIJKLMNOPQRSTUV";
		final String digits ="0123456789";
		final String specialChars ="!#$%&,.-_";
		String validChars="";
		String password="";
		Random random = new Random();
		
		if (includeLowercase) {
			validChars=validChars+lowercase;
		}
		if(includeUppercase) {
			validChars=validChars+uppercase;
		}
		if(includeDigits) {
			validChars=validChars+digits;
		}
		if(customSpecialChars.length()>0) {
			validChars=validChars+customSpecialChars;
		} else if(includeSpecialchars) {
			validChars=validChars+specialChars;
		}
		for(int i=0;i<passLenght;i++) {
			password = password+validChars.charAt(random.nextInt(validChars.length()));
		}
		return password;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*4. Generador de Contraseñas
		Descripción: Crea un programa que genere contraseñas aleatorias basadas en criterios como longitud, uso de mayúsculas, minúsculas, números y caracteres especiales.
		Permite al usuario guardar sus contraseñas generadas en un archivo.
		*/
		
		
		//VARIABLE DEFINITION
		int passLen = 0;
		String tag, inputTextToCompare;
		Boolean includeLowercase = false, includeUppercase = false, includeDigits = false, includeSpecialChar = false;
		String customSpecialChars="";
		String password="";
		
		Scanner scanner = new Scanner(System.in);
		
		//OPTIONS INPUTS
		System.out.println("Please type the lenght of the password");
		passLen = scanner.nextInt(); //READ
		//LOWERCASE
		System.out.println("Include Lowercase? Y/N");
		boolean repeat = true;
		do {
			inputTextToCompare = scanner.next(); //READ
			if (inputTextToCompare.equalsIgnoreCase("y")){
				includeLowercase = true;
				repeat = false;
			}
			if (inputTextToCompare.equalsIgnoreCase("n")) {
				includeLowercase = false;
				repeat = false;
			}
		}
		while (repeat == true);
		//UPPERCASE
		System.out.println("Include Uppercase? Y/N");
		 repeat = true;
		do {
			
			inputTextToCompare = scanner.next(); //READ
			if (inputTextToCompare.equalsIgnoreCase("y")){
				includeUppercase = true;
				repeat = false;
			}
			if (inputTextToCompare.equalsIgnoreCase("n")) {
				includeUppercase = false;
				repeat = false;
			}
		}
		while (repeat == true);
		//DIGITS
		System.out.println("Include digits? Y/N");
		repeat = true;
		do {
			inputTextToCompare = scanner.next(); //READ
			if (inputTextToCompare.equalsIgnoreCase("y")){	
				includeDigits=true;
				repeat = false;
			}
			if (inputTextToCompare.equalsIgnoreCase("n")) {
				includeDigits = false;
				repeat = false;
			}
		}
		while (repeat == true);
		//SPECIAL CHARACTERS
		System.out.println("Include Special Characters? (!#$%&,.-_) Y/N");
		repeat = true;
		includeSpecialChar= false;
		do{
			
			inputTextToCompare = scanner.next();
			if (inputTextToCompare.equalsIgnoreCase("y")){
				includeSpecialChar = true;
				repeat = false;
			}
			if (inputTextToCompare.equalsIgnoreCase("n")) {
				includeSpecialChar = false;
				repeat = false;
			}
		}
		while (repeat == true);
		//CUSTOM SPECIAL CHARS
		if (includeSpecialChar) {
			System.out.println("Select Custom Special Characters? \n Example:!#$%&,- \n Type 0 to skip");

			inputTextToCompare = scanner.next();
			if (inputTextToCompare.equals("0")) {
				customSpecialChars="";
			}
			else{
				customSpecialChars = inputTextToCompare;
				}
		}
		//CALL METHOD
		password=generatePassword(passLen,includeLowercase, includeUppercase, includeDigits, includeSpecialChar,customSpecialChars);
		System.out.println("Password: "+password);
		scanner.close();
		
		//GET USERS PATH
		String path="";
        String command="powershell -command \"[Environment]::GetFolderPath('\"Desktop\"')\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            String line;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                path= line;

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
		
        //WRITE ON FILE
        
		try {
			 File myObj = new File(path+"\\Password.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		} catch (IOException e) {
		 System.out.println("An error occurred.");
	      e.printStackTrace();
		}
		System.out.println(path+"\\Password.txt");
	      try {
	    	  FileWriter fWrite = new FileWriter(path+"\\Password.txt");
	    	  fWrite.write(password);
	    	  fWrite.close();
	      } catch (IOException ee) {
	    	  System.out.println("An error ocurred.");
	    	  ee.printStackTrace();
	      }
		
	}
	
}
