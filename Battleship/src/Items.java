
public class Items {

	private int quantity;

	public Items(int ammo) {
		quantity = ammo;
	}

	public int getRadarAmmo() {
		return quantity;
	}

	public void scan(char[][] location, int vAxis, int hAxis) {

		boolean presence = false;
		if (quantity > 0) {
			if (0 < vAxis && 9 > vAxis && 0 < hAxis && 9 > hAxis) {
				quantity -= 1;
				vAxis -= 1;
				hAxis -= 1;
				for (int i = 0; i < 3; i++) {
					if (i != 0) {
						vAxis++;
					}
					for (int j = 0; j < 3; j++) {
						if (j != 0) {
							hAxis++;
						}
						if (location[vAxis][hAxis] == 'a' || location[vAxis][hAxis] == 'b'
								|| location[vAxis][hAxis] == 's' || location[vAxis][hAxis] == 'd'
								|| location[vAxis][hAxis] == 'p') {
							presence = true;
						}
						if (j == 2) {
							hAxis -= 2;
						}
					}
				}
				if (presence == true) {
					System.out.println("Ship nearby!");
				} else {
					System.out.println("No ship detected!");
				}
			}
			else {
				System.out.println("Inefficient use of radar detected!\nThe captain would be displeased.\nTry again!");
			}
		} else {
			System.out.println("Out of Ammo!");
		}
	}

}
