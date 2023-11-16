import java.io.Console;

class Cat extends Animal {

     public double max_run = 200;

     public double max_jump = 2;

     public Cat(String name){
          super(name);

          super.max_run = max_run;

          super.max_jump = max_jump;
     }

     @Override
     public void Swim(double distance) {
          System.out.println(String.format("%s не умеет плавать !", this.name));
     }
}
