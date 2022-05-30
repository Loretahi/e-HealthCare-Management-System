public class CarDemo {
    public static void main(String[] args) {
       Person pesho = new Person();
//       pesho.name = "Pesho";
        System.out.println(pesho.name);

       Person ivan = new Person("Ivan", 32);
       System.out.println(ivan.name);
       System.out.println(ivan.age);

       System.out.println(ivan);

       ivan.drink("coffe");

    }
}
