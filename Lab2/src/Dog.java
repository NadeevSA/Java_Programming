public class Dog extends Animal{
    double _maxDistSwim;
    Dog (String nickname, double maxDistanceRun, double maxHeight, double maxDistSwim) {
        super(nickname, maxDistanceRun, maxHeight);
        _maxDistSwim = maxDistSwim;
    }
    @Override
    boolean Run(double distance) {
        var result = distance <= super.maxDistRun;
        System.out.println("Dog " + nickname + " run " + distance + " - result: " + result);

        return result;
    }

    @Override
    boolean Jump(double height) {
        var result = height <= super.maxJumpHeight;
        System.out.println("Dog " + nickname + " jump " + height + " - result: " + result);

        return result;
    }

    @Override
    boolean Swim(double distance) {
        var result = distance <= _maxDistSwim;
        System.out.println("Dog " + nickname + " swim " + distance + " - result: " + result);

        return result;
    }
}
