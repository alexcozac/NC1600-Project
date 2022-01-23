
public class cpuBrainz extends Controller {

	private static int vAxis;
	private static int hAxis;
	private static int vAxis_memory;
	private static int hAxis_memory;

	private static int direction;
	// up = 0; down = 1; left = 2; right = 3;

	private static int up = 0;
	private static int down = 1;
	private static int left = 2;
	private static int right = 3;

	// Path variables show possibility of direction and takes out already tried
	// paths during a state switch (zone in on ship hits)
	private static boolean pathUp = true;
	private static boolean pathDown = true;
	private static boolean pathLeft = true;
	private static boolean pathRight = true;

	private static int state = 0;

	/*
	 * s0 - random cluster (if miss, stay in state)
	 * 
	 * s1 - from hit point, random line shoot (if miss, stay in state)
	 * 
	 * s2 - keep line (if, miss change to s3)
	 * 
	 * s3 - reverse direction starting from first hit, if already changed direction,
	 * cluster area, ship destroyed -> s0
	 * 
	 * Random range formula .nextInt(5) + 5; range 5-9
	 */

	public static int initial_vCoordinates() {
		vAxis = rand.nextInt(10);
		return vAxis;
	}

	public static int initial_hCoordinates() {
		hAxis = rand.nextInt(10);
		return hAxis;
	}

	public static int vCoordinates() {
		return vAxis;
	}

	public static int hCoordinates() {
		return hAxis;
	}

	public static int getState() {
		return state;
	}

	public static void setState(int state) {
		cpuBrainz.state = state;
	}

	public static void stateSwitch(int state, int vAxis, int hAxis, char outcome) {

		// Make the cpu less dumb by filtering already hit coordinates
		// hide "you already hit coordinates" when cpu shoots...

		// Prototype run
		cpuBrainz.vAxis = vAxis;
		cpuBrainz.hAxis = hAxis;

		boolean sent = false;

		switch (state) {
		// MAINSTATE
		case 0:
			pathUp = true;
			pathDown = true;
			pathLeft = true;
			pathRight = true;

			if (outcome == 'h') {
				direction = rand.nextInt(4);
				vAxis_memory = vAxis;
				hAxis_memory = hAxis;
				cpuBrainz.state = 1;

				switch (direction) {

				case 0:
					// Checks upper limit
					if (vAxis > 0) {
						cpuBrainz.vAxis -= 1;
					} else {
						cpuBrainz.vAxis += 1;
						direction = down;
					}
					break;

				case 1:
					// Checks lower limit
					if (vAxis < 9) {
						cpuBrainz.vAxis += 1;
					} else {
						cpuBrainz.vAxis -= 1;
						direction = up;
					}
					break;

				case 2:
					// Checks left hand-side limit
					if (hAxis > 0) {
						cpuBrainz.hAxis -= 1;
					} else {
						cpuBrainz.hAxis += 1;
						direction = right;
					}
					break;

				case 3:
					// Checks right hand-side limit
					if (hAxis < 9) {
						cpuBrainz.hAxis += 1;
					} else {
						cpuBrainz.hAxis -= 1;
						direction = left;
					}
					break;
				}
			} else {
				cpuBrainz.state = 0;
			}
			break;

		// MAINSTATE
		case 1:
			switch (direction) {

			case 0:
				if (vAxis > 0) {
					if (outcome == 'h') {
						cpuBrainz.vAxis -= 1;
						cpuBrainz.state = 2;
						pathUp = true;
						pathDown = false;
						pathLeft = false;
						pathRight = false;
					} else {
						while (sent == false) {
							direction = rand.nextInt(4);
							if ((direction == up || direction == down) && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								direction = down;
								pathDown = false;
								pathUp = false;
								sent = true;
							}
							if (direction == left && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathLeft = false;
								sent = true;
							}
							if (direction == right && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathRight = false;
								sent = true;
							}
						}
					}
				}

				else {
					if (outcome == 'h') {
						cpuBrainz.vAxis += 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = true;
						pathLeft = false;
						pathRight = false;
						direction = down;
					} else {
						while (sent == false) {
							direction = rand.nextInt(2) + 2;
							if (direction == left && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathLeft = false;
								sent = true;

							}
							if (direction == right && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathRight = false;
								sent = true;
							}
						}
					}
				}
				break;

			case 1:
				if (vAxis < 9) {
					if (outcome == 'h') {
						cpuBrainz.vAxis += 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = true;
						pathLeft = false;
						pathRight = false;
					} else {
						while (sent == false) {
							direction = rand.nextInt(4);
							if ((direction == down || direction == up) && pathUp == true && vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								direction = up;
								pathUp = false;
								pathDown = false;
								sent = true;
							}
							if (direction == left && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathLeft = false;
								sent = true;
							}
							if (direction == right && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathRight = false;
								sent = true;
							}
						}
					}
				}

				else {
					if (outcome == 'h') {
						cpuBrainz.vAxis -= 1;
						cpuBrainz.state = 2;
						pathUp = true;
						pathDown = false;
						pathLeft = false;
						pathRight = false;
						direction = up;
					} else {
						while (sent == false) {
							direction = rand.nextInt(2) + 2;
							if (direction == left && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathLeft = false;
								sent = true;
							}
							if (direction == right && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathRight = false;
								sent = true;
							}
						}
					}
				}
				break;

			case 2:
				if (hAxis > 0) {
					if (outcome == 'h') {
						cpuBrainz.hAxis -= 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = false;
						pathLeft = true;
						pathRight = false;
					} else {
						while (sent == false) {
							direction = rand.nextInt(4);
							if ((direction == left || direction == right) && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								direction = right;
								pathLeft = false;
								pathRight = false;
								sent = true;
							}
							if (direction == up && pathUp == true && vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathUp = false;
								sent = true;
							}
							if (direction == down && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathDown = false;
								sent = true;
							}
						}
					}
				} else {
					if (outcome == 'h') {
						cpuBrainz.hAxis += 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = false;
						pathLeft = false;
						pathRight = true;
						direction = right;
					} else {
						while (sent == false) {
							direction = rand.nextInt(2);
							if (direction == up && pathUp == true && vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathUp = false;
								sent = true;
							}
							if (direction == down && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathDown = false;
								sent = true;
							}
						}
					}
				}
				break;

			case 3:
				if (hAxis < 9) {
					if (outcome == 'h') {
						cpuBrainz.hAxis += 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = false;
						pathLeft = false;
						pathRight = true;
					} else {
						while (sent == false) {
							direction = rand.nextInt(4);
							if ((direction == right || direction == left) && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								direction = left;
								pathRight = false;
								pathLeft = false;
								sent = true;
							}
							if (direction == up && pathUp == true && vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathUp = false;
								sent = true;
							}
							if (direction == down && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathDown = false;
								sent = true;
							}
						}
					}
				} else {
					if (outcome == 'h') {
						cpuBrainz.hAxis -= 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = false;
						pathLeft = true;
						pathRight = false;
						direction = left;
					} else {
						while (sent == false) {
							direction = rand.nextInt(2);
							if (direction == up && pathUp == true  & vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathUp = false;
								sent = true;
							}
							if (direction == down && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathDown = false;
								sent = true;
							}
						}
					}

				}
				break;
			}
			break;

		// MAINSTATE
		case 2:

			switch (direction) {

			case 0:
				if (vAxis > 0) {
					if (outcome == 'h') {
						cpuBrainz.vAxis -= 1;
						pathUp = true;
					} else {
						if (vAxis_memory < 9) {
							cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
							cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
							pathUp = false;
							pathDown = true;
							direction = down;
						}
					}
				} else {
					if (vAxis_memory < 9) {
						cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
						cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
						pathUp = false;
						pathDown = true;
						direction = down;
					}
				}
				break;

			case 1:
				if (vAxis < 9) {
					if (outcome == 'h') {
						cpuBrainz.vAxis += 1;
						pathDown = true;
					} else {
						if (vAxis_memory > 0) {
							cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
							cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
							pathUp = true;
							pathDown = false;
							direction = up;
						}
					}
				} else {
					if (vAxis_memory > 0) {
						cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
						cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
						pathUp = true;
						pathDown = false;
						direction = up;
					}
				}
				break;

			case 2:
				if (hAxis > 0) {
					if (outcome == 'h') {
						cpuBrainz.hAxis -= 1;
						pathLeft = true;
					} else {
						if (hAxis_memory < 9) {
							cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
							cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
							pathLeft = false;
							pathRight = true;
							direction = right;
						}
					}
				} else {
					if (hAxis_memory < 9) {
						cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
						cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
						pathLeft = false;
						pathRight = true;
						direction = right;
					}
				}
				break;

			case 3:
				if (hAxis < 9) {
					if (outcome == 'h') {
						cpuBrainz.hAxis += 1;
						pathRight = true;
					} else {
						if (hAxis_memory > 0) {
							cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
							cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
							pathLeft = true;
							pathRight = false;
							direction = left;
						}
					}
				} else {
					if (hAxis_memory > 0) {
						cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
						cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
						pathLeft = true;
						pathRight = false;
						direction = left;
					}
				}
				break;
			}
			break;

		//MAINSTATE
		case 3:
			
			switch (direction) {
			
			case 0:
				
				
				break;
			
			
			
			
			
			
			}

			break;

		}

	}

}
