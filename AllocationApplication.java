package Smart.Allocation;

import java.util.*;

// Person Class
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
public static void main(String[] args) {
        AllocationApplication app = new AllocationApplication();
        app.menu();
    }}

// Student Class
class Student extends Person {
    private String studentID;
    private int priority;

    public Student(String name, int age, String studentID, int priority) {
        super(name, age);
        this.studentID = studentID;
        this.priority = priority;
    }

    public String getStudentID() { return studentID; }
    public int getPriority() { return priority; }
}

// Room Class
class Room {
    private String roomNumber;
    private boolean isAllocated;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
        this.isAllocated = false;
    }

    public String getRoomNumber() { return roomNumber; }
    public boolean isAllocated() { return isAllocated; }
    public void setAllocated(boolean allocated) { this.isAllocated = allocated; }
}

// Main Class
public class AllocationApplication {

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private HashMap<String, Room> allocationMap = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    // Add Student
    public void addStudent() {
        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Student ID: ");
        String id = sc.next();

        System.out.print("Enter Priority (higher = better): ");
        int priority = sc.nextInt();

        students.add(new Student(name, age, id, priority));
        System.out.println("✅ Student Added!");
    }

    // Add Room
    public void addRoom() {
        System.out.print("Enter Room Number: ");
        String roomNo = sc.next();

        rooms.add(new Room(roomNo));
        System.out.println("✅ Room Added!");
    }

    // Allocate Rooms
    public void allocateRooms() {
        students.sort((a, b) -> b.getPriority() - a.getPriority());

        for (Student s : students) {
            if (!allocationMap.containsKey(s.getStudentID())) {
                for (Room r : rooms) {
                    if (!r.isAllocated()) {
                        allocationMap.put(s.getStudentID(), r);
                        r.setAllocated(true);
                        break;
                    }
                }
            }
        }
        System.out.println("✅ Allocation Done!");
    }

    // View Allocation
    public void viewAllocations() {
        if (allocationMap.isEmpty()) {
            System.out.println("No allocations yet.");
            return;
        }

        for (Map.Entry<String, Room> entry : allocationMap.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() +
                    " → Room: " + entry.getValue().getRoomNumber());
        }
    }

    // Search Allocation
    public void searchAllocation() {
        System.out.print("Enter Student ID to search: ");
        String id = sc.next();

        Room r = allocationMap.get(id);
        if (r != null) {
            System.out.println("Student " + id + " is in Room " + r.getRoomNumber());
        } else {
            System.out.println("❌ Not found!");
        }
    }

    // Delete Allocation
    public void deleteAllocation() {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.next();

        Room r = allocationMap.remove(id);
        if (r != null) {
            r.setAllocated(false);
            System.out.println("✅ Allocation removed!");
        } else {
            System.out.println("❌ Not found!");
        }
    }

    // MENU
    public void menu() {
        int choice;

        do {
            System.out.println("\n--- HOSTEL ALLOCATION MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Room");
            System.out.println("3. Allocate Rooms");
            System.out.println("4. View Allocations");
            System.out.println("5. Search Allocation");
            System.out.println("6. Delete Allocation");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: addRoom(); break;
                case 3: allocateRooms(); break;
                case 4: viewAllocations(); break;
                case 5: searchAllocation(); break;
                case 6: deleteAllocation(); break;
                case 7: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 7);
    }
}
