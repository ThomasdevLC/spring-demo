package fr.diginamic.hello.utils;

public class DepartementCodeUtil {
	
	  public static int parseCodeDepartement(String codeDepartementString) {
	        String codeDepartementNmbr = codeDepartementString.replaceAll("[^\\d]", "");
	        return Integer.parseInt(codeDepartementNmbr);
	    }

}
