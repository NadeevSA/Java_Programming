public class Cat extends Animal {
    Cat (String nickname, double maxDistance, double maxHeight) {
        super(nickname, maxDistance, maxHeight);
    }

    @Override
    boolean Run(double distance) {
        var result = distance <= super.maxDistRun;
        System.out.println("Cat " + nickname + " run " + distance + " - result: " + result);

        return result;
    }

    @Override
    boolean Jump(double height) {
        var result = height <= super.maxJumpHeight;
        System.out.println("Cat " + nickname + " jump " + height + " - result: " + result);

        return result;
    }

    @Override
    boolean Swim(double distance) {
        var result = false;
        System.out.println("Cat " + nickname + " cannot swim - result: " + result);

        return result;
    }
}
