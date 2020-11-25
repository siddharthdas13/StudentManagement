package service;

public class Validations {
	public boolean checkName(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (!isAlphabetic(name.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean isAlphabetic(char charAt) {
		int charToInt = 0;
		charToInt = (int) charAt;
		if (charToInt >= 65 && charToInt <= 90) {
			return true;
		}
		if (charToInt >= 97 && charToInt <= 122) {
			return true;
		}
		if (charToInt == 32) {
			return true;
		}
		return false;
	}

	public boolean checkGender(String gender) {
		if (gender.equalsIgnoreCase("Girl")) {
			return true;
		}
		if (gender.equalsIgnoreCase("Boy")) {
			return true;
		}
		return false;
	}
}
