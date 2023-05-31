import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongStudentAge extends Exception { }
class WrongStudentBirthDate extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            }
          catch(WrongStudentAge e) {
                System.out.println("Błędny wiek studenta!");
          }
          catch(WrongStudentBirthDate e) {
                System.out.println("Błędna Data Urodzenia");
          }
    
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }
   public static int ReadAge() throws WrongStudentAge {
        
        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
             if( age<=1 || age>=100)
            throw new WrongStudentAge();
     return age;
   }
  public static String ReadBirthDate() throws WrongStudentBirthDate {
        scan.nextLine();
        System.out.println("Podaj Date Urodzenia: ");
      String birthdate = scan.nextLine();
    //dd-mm-rrrr

    String day=birthdate.substring(0, 2);
    String mounth=birthdate.substring(3, 5);
    String year=birthdate.substring(6, 10);

    int dzien=Integer.parseInt(day);
      int miesiac=Integer.parseInt(mounth);
     int rok=Integer.parseInt(year);

    
if( birthdate.contains(" ") || birthdate.charAt(2) !='-' || birthdate.charAt(5) !='-' || dzien>31 || miesiac >12 || rok>5000)
            throw new WrongStudentBirthDate();
     
    return birthdate;
   }

    public static void exercise1() throws IOException, WrongStudentName, WrongStudentAge, WrongStudentBirthDate{
        var name = ReadName();
        var age= ReadAge();
        var birthdate= ReadBirthDate();
        
        (new Service()).addStudent(new Student(name, age, birthdate));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}
