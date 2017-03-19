package szkeleton;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("1: Játék indítása");
        System.out.println("2: Vonat hozzáadása");
        System.out.println("3: Váltó állítása");
        System.out.println("4: Alagút építése/bezárása");
        System.out.println("5: Utasok leszállítása");
        System.out.println("6: Vonatok mozgatása");
        System.out.println("7: Kilépés");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        int command = Integer.parseInt(input);

        switch (command) {
            case 1:
                test1();
                break;
            case 2:
                test2();
                break;
            case 3:
                test3();
                break;
            case 4:
                test4();
                break;
            case 5:
                test5();
                break;
            case 6:
                test6();
                break;
            case 7:
                test7();
                break;
            default:
                System.out.println("Invalid input!");
        }
    }
    
    public static void test1() {
        
    }
    
    public static void test2() {
        
    }
    
    public static void test3() {
        
    }
    
    public static void test4() {
        
    }
    
    public static void test5() {
        
    }
    
    public static void test6() {
        
    }
    
    public static void test7() {
        
    }
    
// Andris munkája, egyelőre csak kikommentezem
/*
    public static void valtoTest() {
        Rail r1 = new Rail();
        Rail r2 = new Rail();
        Rail r3 = new Rail();
        Switch sw1 = new Switch();
        Rail leftr1 = new Rail();
        Rail leftr2 = new Rail();
        Rail leftr3 = new Rail();
        Rail rightr1 = new Rail();
        Rail rightr2 = new Rail();
        Rail rightr3 = new Rail();
        r1.SetDirA(null);
        r1.SetDirB(r2);
        r2.SetDirA(r1);
        r2.SetDirB(r3);
        r3.SetDirA(r2);
        r3.SetDirB(sw1);
        sw1.SetDirA(r3);
        sw1.SetDirB(leftr1);
        sw1.SetDirC(rightr1);
        leftr1.SetDirA(sw1);
        leftr1.SetDirB(leftr2);
        leftr2.SetDirA(leftr1);
        leftr2.SetDirB(leftr3);
        leftr3.SetDirA(leftr2);
        leftr3.SetDirB(null);
        rightr1.SetDirA(sw1);
        rightr1.SetDirB(rightr2);
        rightr2.SetDirA(rightr1);
        rightr2.SetDirB(rightr3);
        rightr3.SetDirA(rightr2);
        rightr3.SetDirB(null);
    }

    public static void alagutTest() {
        Rail r1 = new Rail();
        Rail r2 = new Rail();
        Rail r3 = new Rail();
        TunnelEntrance te1 = new TunnelEntrance();
        Rail r4 = new Rail();
        Rail r5 = new Rail();
        TunnelEntrance te2 = new TunnelEntrance();
        Rail r6 = new Rail();
        Rail r7 = new Rail();
        r1.SetDirA(null);
        r1.SetDirB(r2);
        r2.SetDirA(r1);
        r2.SetDirB(r3);
        r3.SetDirA(r2);
        r3.SetDirB(te1);
        te1.SetDirA(r3);
        te1.SetDirB(r4);
        r4.SetDirA(te1);
        r4.SetDirB(r5);
        r5.SetDirA(r4);
        r5.SetDirB(te2);
        te2.SetDirA(r5);
        te2.SetDirB(r6);
        r6.SetDirA(te2);
        r6.SetDirB(r7);
        r7.SetDirA(r6);
        r7.SetDirB(null);
    }

    public static void leszallasTest() {
        Rail r1 = new Rail();
        Rail r2 = new Rail();
        Rail r3 = new Rail();
        Station st1 = new Station("red");
        Rail r4 = new Rail();
        Rail r5 = new Rail();
        Rail r6 = new Rail();
        r1.SetDirA(null);
        r1.SetDirB(r2);
        r2.SetDirA(r1);
        r2.SetDirB(r3);
        r3.SetDirA(r2);
        r3.SetDirB(st1);
        st1.SetDirA(r3);
        st1.SetDirB(r4);
        r4.SetDirA(st1);
        r4.SetDirB(r5);
        r5.SetDirA(r4);
        r5.SetDirB(r6);
        r6.SetDirA(r5);
        r6.SetDirB(null);
    }

    public static void utkozesTest() {
        Rail r1 = new Rail();
        Rail r2 = new Rail();
        Rail r3 = new Rail();
        Rail r4 = new Rail();
        Rail r5 = new Rail();
        Rail r6 = new Rail();
        Rail r7 = new Rail();
        Rail r8 = new Rail();
        Rail r9 = new Rail();
        Rail r10 = new Rail();
        r1.SetDirA(null);
        r1.SetDirB(r2);
        r2.SetDirA(r1);
        r2.SetDirB(r3);
        r3.SetDirA(r2);
        r3.SetDirB(r4);
        r4.SetDirA(r3);
        r4.SetDirB(r5);
        r5.SetDirA(r4);
        r5.SetDirB(r6);
        r6.SetDirA(r5);
        r6.SetDirB(r7);
        r7.SetDirA(r6);
        r7.SetDirB(r8);
        r8.SetDirA(r7);
        r8.SetDirB(r9);
        r9.SetDirA(r8);
        r9.SetDirB(r10);
        r10.SetDirA(r9);
        r10.SetDirB(null);
    }
*/
}
