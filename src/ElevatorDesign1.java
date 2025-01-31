/*import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class ElevatorDesign {

	public static void main(String[] args) {
		System.out.println(" Welcome to Omega Elevator, Have a nice day!!! ");
		System.out.println(" Please enter the floor you want to go and your weight ");
		// Threads for request and processing
		Thread request = new Thread(new RequestThread());
		Thread process = new Thread(new ProcessThread());
		request.start();
		process.start();
	}
}

class RequestThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			String floorNum = null;
			// Read input from user
			Scanner sc = new Scanner(System.in);
			floorNum = sc.next();

			System.out.println("User Pressed : " + floorNum);
			Elevator elevator = Elevator.getInstance();
			elevator.addFloor(Integer.parseInt(floorNum));
		}
	}
}

class ProcessThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			Elevator elevator = Elevator.getInstance();
			int floor = elevator.getFloor();
			int currFloor = elevator.getCurrentFloor();
			if (currFloor > floor) {
				while (currFloor > floor) {
					elevator.setCurrentFloor(--currFloor);
					int f = elevator.getFloor();
					if (f == floor)
						continue;
					else {
						floor = f;
					}
				}

				if (currFloor == floor) {
					System.out.println("Processing Request : " + floor);
					elevator.remove(floor);
				}

			} else {
				while (currFloor < floor) {
					elevator.setCurrentFloor(++currFloor);
					int f = elevator.getFloor();
					if (f == floor)
						continue;
					else {
						floor = f;
					}
				}

				if (currFloor == floor) {
					System.out.println("Processing Request : " + floor);
					elevator.remove(floor);
				}
			}
		}
	}
}

class Elevator {

	public static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	private DIRECTION direction = DIRECTION.UP;
	private int currentFloor = 0;
	private static Elevator elevator = null;

	public enum DIRECTION {
		UP, DOWN;
	}

	private Elevator() {

	}

	public void remove(int floor) {
		queue.remove(floor);
	}

	public DIRECTION getDirection() {
		return direction;
	}

	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}

	// Implementing singleton design pattern
	static Elevator getInstance() {
		if (elevator == null) {
			elevator = new Elevator();
		}
		return elevator;
	}

	public synchronized void addFloor(int floor) {
		// add the request from user to reach to specific floor
		queue.add(floor);
		// sending notification to the thread to process it.
		notify();
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		if (this.currentFloor > currentFloor) {
			setDirection(DIRECTION.DOWN);
		} else {
			setDirection(DIRECTION.UP);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(" Exception occurred: " + e.getMessage());
		}
		this.currentFloor = currentFloor;
		System.out.println(" Floor is : " + currentFloor);
	}

	private Integer findCeiling(int currentFloor) {
		PriorityQueue<Integer> temp = new PriorityQueue<Integer>();
		Integer ceil = null;
		while (!queue.isEmpty()) {
			Integer floor = queue.poll();
			temp.offer(floor);
			if (floor >= currentFloor) {
				ceil = floor;
				break;
			}
		}
		while (!queue.isEmpty()) {
			temp.offer(queue.poll());
		}
		queue = temp;
		return ceil;
	}

	private Integer findFloor(int currentFloor) {
		Stack<Integer> stack = new Stack<Integer>();
		while (!queue.isEmpty()) {
			stack.push(queue.poll());
		}

		Integer f = null;
		while (!stack.isEmpty()) {
			Integer floor = stack.pop();
			queue.offer(floor);
			if (floor <= currentFloor) {
				f = floor;
				break;
			}
		}

		while (!stack.isEmpty()) {
			queue.offer(stack.pop());
		}
		return f;

	}

	public synchronized int getFloor() {
		Integer floor = null;

		if (direction == DIRECTION.UP) {
			Integer value = findCeiling(currentFloor);
			if (value != null) {
				floor = value;
			} else {
				floor = findFloor(currentFloor);
			}
		} else {
			Integer value = findFloor(currentFloor);
			if (value != null) {
				floor = value;
			} else {
				floor = findCeiling(currentFloor);
			}
		}
		if (floor == null) {
			try {
				System.out.println("No Request to process. Waiting");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return (floor == null) ? -1 : floor;
	}

}
*/