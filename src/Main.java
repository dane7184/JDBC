import model.Topic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. Insert Topic ");
            System.out.println("2. Show Information Topic" );
            System.out.println("3. Update Topic By ID ");
            System.out.println("4. Delete Topic By ID ");
            System.out.println("5. Select By ID Operation ");
            System.out.println("6. Select By Name Operation ");
            System.out.print("0.Choose option :");
            option= scanner.nextInt();
            switch (option){
                case 1->
                    TopicAppication.insertTopic();
                case 2->
                    TopicAppication.selectTopic();
                case 3->
                    TopicAppication.updateTopic();
                case 4->{
                    System.out.println("Delete ID :");
                    Integer id = scanner.nextInt();
                    TopicAppication.deleteTopic(id);
                }
                case 5->{
                    System.out.print("Select By ID : ");
                    Integer id1 = scanner.nextInt();
                    System.out.println(id1);
                    TopicAppication.selectID(id1);
                }
                case 6->{
                    System.out.println("Select By Name : ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    TopicAppication.selectName(name);

                }
                default -> System.out.println("Program Exit ");
            }
        }while (option!=0);
    }
}