package maraphon;

/**
 * Базовый класс животного.
 */
public abstract class Animal {
    /**
     * Имя.
     */
    String name = null;

    /**
     * Макс. высота прыжка.
     */
    double maxJumpHeight = .0;

    /**
     * Макс. длина забега.
     */
    double maxRunLength = .0;

    /**
     * Макс длина заплыва.
     */
    double maxSwimLength = .0;

    /**
     * Проверяет на возможность совершить прыжок.
     * @param height - Высота препятствия.
     */
    abstract boolean jump(double height);

    /**
     * Проверяет на возможность пробежать дистанацию.
     * @param length - Дистанция.
     */
    abstract boolean run(double length);

    /**
     * Проверяет на возможность проплыть дистанацию.
     * @param length - Дистанция.
     */
    abstract boolean swim(double length);
}
