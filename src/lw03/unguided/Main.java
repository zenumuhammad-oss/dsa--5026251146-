package lw03.unguided;

import java.util.*;

class Main {
    public static void main(String[] args) {
        System.out.println("===== Event Check-In Results =====");
        
        Scanner zn1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Set<String> registeredStudents = new LinkedHashSet<>();
        
        while (zn1.hasNext()) {
            String studentId = zn1.next();
            registeredStudents.add(studentId); 
        }
        zn1.close();

        Scanner zn2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        Set<String> checkedInStudents = new LinkedHashSet<>();
        int rejectedAttempts = 0;

        while (zn2.hasNext()) {
            String studentId = zn2.next();
            
            if (!registeredStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (not registered)");
                rejectedAttempts++;
            } else if (checkedInStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (already checked in)");
                rejectedAttempts++;
            } else {
                checkedInStudents.add(studentId);
                System.out.println(studentId + ": Checked in");
            }
        }
        zn2.close();

        int absentStudents = 0;
        for (String studentId : registeredStudents) {
            if (!checkedInStudents.contains(studentId)) {
                absentStudents++;
            }
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStatusSize(registeredStudents)); // atau langsung .size()
        System.out.println("Successful check-ins: " + checkedInStudents.size());
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
    
    private static int registeredStatusSize(Set<String> set) {
        return set.size();
    }
}